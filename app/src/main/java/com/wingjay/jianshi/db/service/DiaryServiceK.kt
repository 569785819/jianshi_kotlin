/*
 * Created by wingjay on 11/16/16 3:31 PM
 * Copyright (c) 2016.  All rights reserved.
 *
 * Last modified 11/10/16 11:05 AM
 *
 * Reach me: https://github.com/wingjay
 * Email: yinjiesh@126.com
 */

package com.wingjay.jianshi.db.service;


import android.content.Context
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.raizlabs.android.dbflow.sql.language.SQLite
import com.wingjay.jianshi.db.model.Diary
import com.wingjay.jianshi.db.model.Diary_Table
import com.wingjay.jianshi.di.ForApplication
import com.wingjay.jianshi.sync.Change
import com.wingjay.jianshi.sync.Operation
import com.wingjay.jianshi.sync.SyncService
import com.wingjay.jianshi.util.DateUtil
import rx.Observable
import rx.functions.Func0
import javax.inject.Inject

class DiaryService {
    var context: Context? = null

    @Inject
    var gson: Gson? = Gson()

    @Inject
    constructor(@ForApplication context: Context) {
        this.context = context
    }

    fun saveDiary(diary: Diary): Observable<Void> {
        return Observable.defer(
                Func0<Observable<Void>> {
                    var jsonObject = JsonObject()
                    diary.setTime(DateUtil.getCurrentTimeStamp());
                    if (diary.getTime_removed() > 0) {
                        jsonObject.add(Operation.DELETE.getAction(), gson?.toJsonTree(diary))
                    } else if (diary.getTime_modified() >= diary.getTime_created()) {
                        jsonObject.add(Operation.UPDATE.getAction(), gson?.toJsonTree(diary))
                    } else {
                        jsonObject.add(Operation.CREATE.getAction(), gson?.toJsonTree(diary))
                    }
                    Change.handleChangeByDBKey(Change.DBKey.DIARY, jsonObject)
                    diary.save()
                    SyncService.syncImmediately(context)
                    Observable.just(null)
                }
        )
    }

    fun getDiaryList(): Observable<List<Diary>> {
        return Observable.defer {
            Observable.just(
                    SQLite.select().from(Diary::class.java).where(Diary_Table.time_removed.eq(0)).queryList()
            )
        }
    }

    fun getDiaryByUuid(uuid: String): Observable<Diary> {
        return Observable.defer(
                object : Func0<Observable<Diary>> {
                    override fun call(): Observable<Diary> {
                        return Observable.just(
                                SQLite.select().from(Diary::class.java).where(Diary_Table.uuid.`is`(uuid)).querySingle()
                        )
                    }
                }
        )
    }
}

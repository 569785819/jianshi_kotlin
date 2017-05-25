package com.wingjay.jianshi.bean

import com.google.gson.annotations.SerializedName
import com.wingjay.jianshi.db.model.Diary

/**
 * Created by zhuzhejun on 2017/5/24.
 */
data class SyncModel(
        @SerializedName("sync_token") var syncToken: String,
        @SerializedName("synced_count") var syncedCount: Int = 0,
        var upsert: List<Diary>,
        var delete: List<Diary>)
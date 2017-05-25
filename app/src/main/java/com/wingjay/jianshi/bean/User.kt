package com.wingjay.jianshi.bean

import com.google.gson.annotations.SerializedName

/**
 * Created by zhuzhejun on 2017/5/24.
 */
data class User(
        @SerializedName("id") val id: Long = 0,
        @SerializedName("name") val name: String? = null,
        @SerializedName("encrypted_token") val encryptedToken: String? = null
)
package com.wingjay.jianshi.bean

import com.google.gson.annotations.SerializedName

/**
 * Created by zhuzhejun on 2017/5/24.
 */

data class ImagePoem(
        @SerializedName("image") var imageUrl: String,
        @SerializedName("poem") var poem: String,
        @SerializedName("next_fetch_time") var nextFetchTime: Long)
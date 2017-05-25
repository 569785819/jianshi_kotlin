package com.wingjay.jianshi.bean

import com.google.gson.annotations.SerializedName

/**
 * Created by zhuzhejun on 2017/5/24.
 */
data class ShareContent(
        @SerializedName("link") var link: String? = null,
        @SerializedName("share_text") var shareText: String? = "回归文字的本质，回归美好"
)
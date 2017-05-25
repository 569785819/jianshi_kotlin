package com.wingjay.jianshi.bean

import com.google.gson.annotations.SerializedName

/**
 * Created by zhuzhejun on 2017/5/24.
 */
data class VersionUpgrade(
        @SerializedName("version_name") var versionName: String? = null,
        @SerializedName("desc") var description: String? = null,
        @SerializedName("link") var downloadLink: String? = null
)
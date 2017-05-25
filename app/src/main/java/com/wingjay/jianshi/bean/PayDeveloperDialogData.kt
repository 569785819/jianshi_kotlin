package com.wingjay.jianshi.bean

import com.google.gson.annotations.SerializedName

/**
 * Created by zhuzhejun on 2017/5/24.
 */
data class PayDeveloperDialogData(
        @SerializedName("title") val title: String? = null,
        @SerializedName("message") val message: String? = null,
        @SerializedName("time_gap_seconds") val timeGapSeconds: Long = 0,
        @SerializedName("ali_pay_account") val aliPayAccount: String? = null,
        @SerializedName("wechat_pay_account") val wechatPayAccount: String? = null) {
}
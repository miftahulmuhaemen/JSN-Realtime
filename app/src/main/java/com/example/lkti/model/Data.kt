package com.example.lkti.model

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("No") var No: Int?,
    @SerializedName("Waktu") var Waktu: String?,
    @SerializedName("T_bawah_1") var T_bawah_1: Double?,
    @SerializedName("T_bawah_2") var T_bawah_2: Double?,
    @SerializedName("T_atas_1") var T_atas_1: Double?,
    @SerializedName("T_atas_2") var T_atas_2: Double?,
    @SerializedName("RH_bawah_1") var RH_bawah_1: Double?,
    @SerializedName("RH_bawah_2") var RH_bawah_2: Double?,
    @SerializedName("Asap_1") var Asap_1: String?,
    @SerializedName("Asap_2") var Asap_2: String?,
    @SerializedName("Api_1") var Api_1: String?,
    @SerializedName("Api_2") var Api_2: String?
)
package org.openg2p.voucherservice.models

import com.google.gson.annotations.SerializedName


data class VoucherDTO (

    @SerializedName("user_name" ) var userName : String? = null,
    @SerializedName("email"     ) var email    : String? = null,
    @SerializedName("name"      ) var name     : String? = null

)
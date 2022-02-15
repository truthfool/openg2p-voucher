package org.openg2p.voucherservice.models

import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName
import javax.persistence.*

@Entity
data class VoucherDiscount (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var voucherDiscountId         : Int,
    @SerializedName("voucherDiscountType")
    var voucherDiscountType       : String,
    @SerializedName("voucherDiscountPercentOff")
    var voucherDiscountPercentOff : Long,
    @ManyToOne
    var voucher: Voucher
)

package org.openg2p.voucherservice.models

import com.google.gson.annotations.SerializedName
import javax.persistence.*


@Entity
data class Voucher(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id                 : Int,
    var voucherCode        : String?,
    @SerializedName("redemption_quantity")
    var redemptionQuantity : Int,
    @SerializedName("voucher_type")
    var voucherType : String,
    @ManyToOne
    val program: VoucherProgram?,
)


package org.openg2p.voucherservice.models

import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName
import javax.persistence.*


@Entity
data class Voucher(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var voucherId          : Int,
    var voucherCode        : String?,
    @SerializedName("redemptionQuantity")
    var redemptionQuantity : Int,
    @SerializedName("voucherType")
    var voucherType : String,
    @ManyToOne
    val program: VoucherProgram?,
    @SerializedName("discount")
    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "voucher")
    val discount: List<VoucherDiscount>?
)


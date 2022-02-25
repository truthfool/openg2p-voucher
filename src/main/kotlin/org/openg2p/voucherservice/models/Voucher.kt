package org.openg2p.voucherservice.models

import com.google.gson.annotations.SerializedName
import java.math.BigInteger
import javax.persistence.*


@Entity
data class Voucher(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id                 : Int,
    var voucherCode        : String?,
    var redemptionQuantity : Int,
    var voucherType        : String,
    var percentOff         : Long?,
    var amount             : BigInteger?,
    @ManyToOne(fetch = FetchType.LAZY)
    val program            : VoucherProgram?,
)


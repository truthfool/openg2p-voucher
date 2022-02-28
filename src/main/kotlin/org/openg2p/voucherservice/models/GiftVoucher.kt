package org.openg2p.voucherservice.models

import java.math.BigInteger
import java.util.*
import javax.persistence.*

@Entity
data class GiftVoucher(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id                 : Int,
    var amount             : BigInteger,
    var redemptionQuantity : Int,
    var voucherCode        : String?,
    @ManyToOne
    val program            : VoucherProgram?,
)

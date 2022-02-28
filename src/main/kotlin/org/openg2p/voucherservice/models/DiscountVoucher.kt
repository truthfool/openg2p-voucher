package org.openg2p.voucherservice.models

import javax.persistence.*

@Entity
data class DiscountVoucher (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id                 : Int,
    var percentOff         : Long,
    var redemptionQuantity : Int,
    var voucherCode        : String?,
    @ManyToOne
    val program            : VoucherProgram?,
)

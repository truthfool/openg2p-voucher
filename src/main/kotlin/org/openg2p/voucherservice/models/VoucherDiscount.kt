package org.openg2p.voucherservice.models

import javax.persistence.*

@Entity
data class VoucherDiscount (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var voucherDiscountId         : Long,
    var voucherDiscountPercentOff : Long,
    var voucherDiscountType       : String,
    @ManyToOne
    var voucher: Voucher
)

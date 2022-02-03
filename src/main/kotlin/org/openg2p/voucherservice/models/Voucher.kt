package org.openg2p.voucherservice.models

import javax.persistence.*


@Entity
data class Voucher(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var voucherId          : Long,
    var voucherCode        : String?,
    var redemptionQuantity : Int,
    var voucherType : String,
    @ManyToOne
    val program: VoucherProgram?,
    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "voucher")
    val discount: List<VoucherDiscount>?
)


package org.openg2p.voucherservice.models

import javax.persistence.*


@Entity
data class Voucher(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var voucherId          : Long,
    var voucherCode        : String,
    var programName        : String,
    var redemptionQuantity : Int,
    @ManyToOne
    val program: VoucherProgram,
    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "voucher")
    val discount: List<VoucherDiscount>?
)


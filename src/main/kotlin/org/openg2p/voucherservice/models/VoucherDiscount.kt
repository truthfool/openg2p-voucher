package org.openg2p.voucherservice.models

import com.google.gson.annotations.SerializedName
import javax.persistence.*

@Entity
data class VoucherDiscount (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id         : Int,
    var voucherDiscountType       : String,
    var voucherDiscountPercentOff : Long,
    @ManyToOne
    var voucher: Voucher
)

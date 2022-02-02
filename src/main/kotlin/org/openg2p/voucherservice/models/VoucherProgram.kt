package org.openg2p.voucherservice.models

import java.time.LocalDate
import javax.persistence.*

@Entity
data class VoucherProgram(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var programId                    : Long,
    var voucherProgramName           : String,
    var voucherProgramStartDate      : LocalDate,
    var voucherProgramExpirationDate : LocalDate,
    var voucherCount                : Int,
    var voucherRedemptionQuantity   : Int,
    var voucherProgramActive         : Boolean,

    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "program")
    var voucher: List<Voucher>?

)


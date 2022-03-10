package org.openg2p.voucherservice.models

import javax.persistence.*

@Entity
data class VoucherProgram(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id                           : Int,
    var voucherProgramName           : String,
    var voucherProgramStartDate      : String,
    var voucherProgramExpirationDate : String,
    var voucherProgramActive         : Boolean
)


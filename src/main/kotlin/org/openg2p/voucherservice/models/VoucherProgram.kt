package org.openg2p.voucherservice.models

import com.google.gson.annotations.SerializedName
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


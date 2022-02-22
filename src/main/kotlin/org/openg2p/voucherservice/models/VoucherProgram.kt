package org.openg2p.voucherservice.models

import com.google.gson.annotations.SerializedName
import javax.persistence.*

@Entity
data class VoucherProgram(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id                    : Int,
    @SerializedName("program_name")
    var voucherProgramName           : String,
    @SerializedName("program_start_date")
    var voucherProgramStartDate      : String,
    @SerializedName("program_expiration_date")
    var voucherProgramExpirationDate : String,
    @SerializedName("program_active")
    var voucherProgramActive         : Boolean,
)


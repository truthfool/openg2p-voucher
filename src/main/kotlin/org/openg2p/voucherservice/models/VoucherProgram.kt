package org.openg2p.voucherservice.models

import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.time.LocalDate
import javax.persistence.*

@Entity
data class VoucherProgram(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var programId                    : Int,
    @SerializedName("voucherProgramName")
    var voucherProgramName           : String,
    @SerializedName("voucherProgramStartDate")
    var voucherProgramStartDate      : SimpleDateFormat,
    @SerializedName("voucherProgramExpirationDate")
    var voucherProgramExpirationDate : SimpleDateFormat,
    @SerializedName("voucherCount")
    var voucherCount                 : Int,
    @SerializedName("voucherProgramActive")
    var voucherProgramActive         : Boolean,

    @SerializedName("voucher")
    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "program")
    var voucher: List<Voucher>?

)


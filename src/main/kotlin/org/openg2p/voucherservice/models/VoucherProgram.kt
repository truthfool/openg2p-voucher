package org.openg2p.voucherservice.models

import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import javax.persistence.*

@Entity
data class VoucherProgram(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var programId                    : Int,
    @SerializedName("voucherProgramName")
    var voucherProgramName           : String,
    @SerializedName("voucherProgramStartDate")
    var voucherProgramStartDate      : String,
    @SerializedName("voucherProgramExpirationDate")
    var voucherProgramExpirationDate : String,
    @SerializedName("voucherCount")
    var voucherCount                 : Int,
    @SerializedName("voucherProgramActive")
    var voucherProgramActive         : Boolean,

    @SerializedName("voucher")
    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "program")
    var voucher: List<Voucher>?

)


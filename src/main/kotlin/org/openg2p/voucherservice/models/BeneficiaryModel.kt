package org.openg2p.voucherservice.models

import javax.persistence.*

@Entity
data class BeneficiaryModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Int,
    val beneficiaryName:String,
)

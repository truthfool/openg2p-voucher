package org.openg2p.voucherservice.models

import javax.persistence.*

@Entity
data class BeneficiaryModel(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val beneficiaryId:Long,
    val beneficiaryName:String,
)

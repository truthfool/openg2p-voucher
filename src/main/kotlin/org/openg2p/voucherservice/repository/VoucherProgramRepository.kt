package org.openg2p.voucherservice.repository

import org.openg2p.voucherservice.models.VoucherProgram
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

@Repository
interface VoucherProgramRepository : JpaRepository<VoucherProgram,Int>
{
    abstract fun findByProgramName(programName: String?): Optional<VoucherProgram>
}
package org.openg2p.voucherservice.service

import org.openg2p.voucherservice.models.Voucher
import org.openg2p.voucherservice.models.VoucherProgram
import org.openg2p.voucherservice.repository.VoucherProgramRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat

@Service
class VoucherService(@Autowired var voucherProgramRepository: VoucherProgramRepository) {

    fun createProgram(voucherProgram: Map<String, Any>) {

        val startDate=new SimpleDateFormat("MM/dd/yyyy");
        v

        val programId= voucherProgram["programId"] as Int;
        val voucherProgramName= voucherProgram["voucherProgramName"] as String;
        val voucherProgramStartDate= voucherProgram["voucherProgramStartDate"] as SimpleDateFormat;
        val voucherProgramExpirationDate= voucherProgram["voucherProgramExpirationDate"] as SimpleDateFormat;
        val voucherCount= voucherProgram["voucherCount"] as Int;
        val voucherProgramActive= voucherProgram["voucherProgramActive"] as Boolean;
        val voucher= voucherProgram["voucher"] as List<Voucher>?;

        val program=VoucherProgram(
            programId= voucherProgram["programId"] as Int,
            voucherProgramName= voucherProgram["voucherProgramName"] as String,
            voucherProgramStartDate= voucherProgram["voucherProgramStartDate"] as SimpleDateFormat,
            voucherProgramExpirationDate= voucherProgram["voucherProgramExpirationDate"] as SimpleDateFormat,
            voucherCount= voucherProgram["voucherCount"] as Int,
            voucherProgramActive= voucherProgram["voucherProgramActive"] as Boolean,
            voucher= voucherProgram["voucher"] as List<Voucher>?
        )
        voucherProgramRepository.save(program);
    }

    fun getAllPrograms(): List<VoucherProgram> {
        return voucherProgramRepository.findAll();
    }


}
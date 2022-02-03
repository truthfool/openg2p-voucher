package org.openg2p.voucherservice.service

import org.openg2p.voucherservice.models.VoucherProgram
import org.openg2p.voucherservice.repository.VoucherProgramRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class VoucherService(@Autowired var voucherProgramRepository: VoucherProgramRepository) {


    fun createProgram(voucherProgram: VoucherProgram) {
        voucherProgramRepository.save(voucherProgram);
    }

    fun getAllPrograms(): List<VoucherProgram> {
        return voucherProgramRepository.findAll();
    }


}
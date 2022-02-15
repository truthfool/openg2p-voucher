package org.openg2p.voucherservice.service

import org.openg2p.voucherservice.models.Voucher
import org.openg2p.voucherservice.models.VoucherProgram
import org.openg2p.voucherservice.repository.VoucherDiscountRepository
import org.openg2p.voucherservice.repository.VoucherProgramRepository
import org.openg2p.voucherservice.repository.VoucherRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.*

@Service
class VoucherService(@Autowired var voucherProgramRepository: VoucherProgramRepository,
                     var voucherDiscountRepository: VoucherDiscountRepository,
                     var voucherRepository: VoucherRepository) {

    fun createProgram(payload: Map<String, Any>) {
        println("Creating Program");
        println(payload)
        val programId= payload["programId"] as Int;
        val voucherProgramName= payload["voucherProgramName"] as String;
        val voucherCount= payload["voucherCount"] as Int;
        val voucherProgramActive= payload["voucherProgramActive"] as Boolean;
        val voucherProgramStartDate= payload["voucherProgramStartDate"] as String;
        val voucherProgramExpirationDate= payload["voucherProgramExpirationDate"] as String;
        val voucherPayload=payload["voucher"];
        val items = payload.getJSONArray("voucher")
        items.forEachString { item ->
            // use item
        }
        println(voucherPayload)
        for (v in voucherPayload)
        {
            println(v);
        }
//        val program=VoucherProgram();
//        voucherProgramRepository.save(program);
//        println("Program created");
//        val voucherPayload= payload["voucher"] as List<Voucher>?
//        if (voucherPayload != null) {
//            for (v in voucherPayload)
//            {
//                println(v);
//            }
//        }
    }

    fun getAllPrograms(): List<VoucherProgram> {
        return voucherProgramRepository.findAll();
    }
    fun getProgramById(id: Int?): Optional<VoucherProgram> {
        return voucherProgramRepository.findById(id);
    }

    fun deleteById(id: Int?): String {
        return if (id != null) {
            voucherProgramRepository.deleteById(id)
            "Program Deleted";
        } else {
            "No Program with such id exits";
        }
    }


}
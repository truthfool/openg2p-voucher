package org.openg2p.voucherservice.service

import org.json.JSONArray
import org.openg2p.voucherservice.models.Voucher
import org.openg2p.voucherservice.models.VoucherProgram
import org.openg2p.voucherservice.repository.VoucherDiscountRepository
import org.openg2p.voucherservice.repository.VoucherProgramRepository
import org.openg2p.voucherservice.repository.VoucherRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.openg2p.voucherservice.models.VoucherDiscount
import org.springframework.http.ResponseEntity
import reactor.core.publisher.Mono
import java.util.*

@Service
class VoucherService(@Autowired var voucherProgramRepository: VoucherProgramRepository,
                     var voucherDiscountRepository: VoucherDiscountRepository,
                     var voucherRepository: VoucherRepository) {

    // Generating voucher code
    fun getVoucherCode(programName:String):String
    {
        var alphaNumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"+ "0123456789"+ "abcdefghijklmnopqrstuvxyz";
        var s=programName.slice(0..4);
        for (i in 1..12) {
            var index=((alphaNumeric.length* Math.random()).toInt());
            s+=(alphaNumeric.get(index));
        }
        return s;
    }
    //Creating Programs for vouchers
    fun createProgram(voucherProgram: VoucherProgram) {
        voucherProgramRepository.save(voucherProgram)
    }
    //Getting all program details
    fun getAllPrograms(): List<VoucherProgram> {
        return voucherProgramRepository.findAll();
    }
    //Deleting Program by id
    fun deleteProgramById(id: Int?): String {
        return if (id != null) {
            voucherProgramRepository.deleteById(id)
            "Program Deleted";
        } else {
            "No Program with such id exits";
        }
    }
    //Get Program By Id
//    fun getProgramById(id: Int?): Optional<VoucherProgram> {
//        return voucherProgramRepository.findById(id);
//    }

    //Create Voucher
    fun createVoucher(voucher: Voucher) {
        voucherRepository.save(voucher)
    }

    fun getAllVouchers(): List<Voucher> {
        return voucherRepository.findAll()
    }
    //Updating Program Details
//    fun updateProgram(id: Int?,voucherProgram: VoucherProgram): VoucherProgram {
//        VoucherProgram program = voucherProgramRepository.findById(id);
//
//        if(program!=null)
//        {
//            program.set
//        }
//    }

    //Getting program details by program name
//    fun getProgramByName(programName: String?): Optional<VoucherProgram> {
//        return voucherProgramRepository.findByProgramName(programName);
//    }



}
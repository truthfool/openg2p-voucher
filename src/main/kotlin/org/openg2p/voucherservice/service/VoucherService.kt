package org.openg2p.voucherservice.service

import org.openg2p.voucherservice.models.DiscountVoucher
import org.openg2p.voucherservice.models.GiftVoucher
import org.openg2p.voucherservice.models.VoucherProgram
import org.openg2p.voucherservice.repository.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kotlin.reflect.typeOf

@Service
class VoucherService(@Autowired var voucherProgramRepository: VoucherProgramRepository,
                                var discountVoucherRepository: DiscountVoucherRepository,
                                var giftVoucherRepository: GiftVoucherRepository) {

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
    // Get all Discount Vouchers
    fun getAllDiscountVouchers(): List<DiscountVoucher> {
        return discountVoucherRepository.findAll()
    }
    //Create Discount Voucher
    fun createDiscountVoucher(voucher: DiscountVoucher) {
        var code=getVoucherCode(voucher.program!!.voucherProgramName)
        var voucherObj=DiscountVoucher(
            id=voucher.id,
            percentOff=voucher.percentOff,
            redemptionQuantity=voucher.redemptionQuantity,
            voucherCode=code,
            program=voucher.program
        )
        discountVoucherRepository.save(voucherObj)
    }
    //Create Gift Voucher
    fun createGiftVoucher(voucher: GiftVoucher) {
        var code=getVoucherCode(voucher.program!!.voucherProgramName)
        var voucherObj=GiftVoucher(
            id=voucher.id,
            amount=voucher.amount,
            redemptionQuantity=voucher.redemptionQuantity,
            voucherCode=code,
            program=voucher.program
        )
        giftVoucherRepository.save(voucherObj)
    }
    //Get all Gift Vouchers
    fun getAllGiftVouchers(): List<GiftVoucher> {
        return giftVoucherRepository.findAll()
    }


//    Get Program By Id
//    fun getProgramById(id: Int?): Optional<VoucherProgram> {
//        return voucherProgramRepository.findById(id);
//    }

//    Updating Program Details
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
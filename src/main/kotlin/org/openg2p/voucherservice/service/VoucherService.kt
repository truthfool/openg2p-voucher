package org.openg2p.voucherservice.service

import org.openg2p.voucherservice.models.DiscountVoucher
import org.openg2p.voucherservice.models.GiftVoucher
import org.openg2p.voucherservice.models.VoucherProgram
import org.openg2p.voucherservice.repository.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigInteger

@Service
class VoucherService(@Autowired var voucherProgramRepository: VoucherProgramRepository,
                                var discountVoucherRepository: DiscountVoucherRepository,
                                var giftVoucherRepository: GiftVoucherRepository) {

    // Generating voucher code
    fun getVoucherCode(programName:String):String
    {
        var programName = programName.filter { !it.isWhitespace() }
        var alphaNumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"+ "0123456789"+ "abcdefghijklmnopqrstuvxyz";
        var s=programName.slice(0..4);
        for (i in 1..11) {
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

    //Get discount voucher details by Code
    fun getDiscountVoucherByCode(voucherCode: String?): Any? {
        try {

            val discountVoucher = discountVoucherRepository.findByVoucherCode(voucherCode);
            return discountVoucher
        }
        catch (exception:Exception)
        {
            println(exception)
            return "No discount voucher with such code exists!"
        }
    }
    //Get gift voucher details by Code
    fun getGiftVoucherByCode(voucherCode: String): Any? {
        try {

            val giftVoucher= giftVoucherRepository.findByVoucherCode(voucherCode);
            return giftVoucher
        }
        catch (exception:Exception)
        {
            println(exception)
            return "No gift voucher with such code exists!"
        }
    }

    //Redeem gift voucher by code
    fun redeemGiftVoucherByCode(redeem: Map<String, Any>): Any? {
        var voucherCode=redeem.get("voucherCode").toString()
        var redemptionQuantity:Int= redeem.get("redemptionQuantity") as Int

        var giftVoucher = giftVoucherRepository.findByVoucherCode(voucherCode);
        giftVoucher.redemptionQuantity = giftVoucher.redemptionQuantity - redemptionQuantity;

        var programName:String= giftVoucher.program!!.voucherProgramName
        var amount: BigInteger = giftVoucher.amount
        giftVoucherRepository.save(giftVoucher)

        return mapOf("programName" to programName, "amount" to amount ,
                        "redemptionQuantity" to redemptionQuantity)
    }

    //Redeem discount voucher by code
    fun redeemDiscountVoucherByCode(redeem: Map<String, Any>): Any? {
        var voucherCode=redeem.get("voucherCode").toString()
        var redemptionQuantity:Int= redeem.get("redemptionQuantity") as Int

        var discountVoucher = discountVoucherRepository.findByVoucherCode(voucherCode);
        discountVoucher.redemptionQuantity = discountVoucher.redemptionQuantity - redemptionQuantity;

        var programName:String= discountVoucher.program!!.voucherProgramName
        var percentOff:Long=discountVoucher.percentOff
        discountVoucherRepository.save(discountVoucher)

        return mapOf("programName" to programName, "percentOff" to percentOff ,
            "redemptionQuantity" to redemptionQuantity)
    }


    // Get program by id
//    fun getProgramById(id: Int?): Optional<VoucherProgram> {
//        return voucherProgramRepository.findById(id)
//    }

//    Updating Program Details
//    fun updateProgram(id: Int?) {
//    return if (voucherProgramRepository.findById(id)) {
//        voucherProgramRepository.save(
//            VoucherProgram(
//                id = voucherProgram.id,
//                voucherProgramName = voucherProgram.voucherProgramName,
//                voucherProgramStartDate = voucherProgram.voucherProgramStartDate,
//                voucherProgramExpirationDate = voucherProgram.voucherProgramExpirationDate,
//                voucherProgramActive = voucherProgram.voucherProgramActive
//            )
//        )
//    }
//    }

    //Getting program details by program name
//    fun getProgramByName(programName: String?): Optional<VoucherProgram> {
//        return voucherProgramRepository.findByProgramName(programName);
//    }



}
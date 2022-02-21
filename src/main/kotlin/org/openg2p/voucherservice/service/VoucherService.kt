package org.openg2p.voucherservice.service

import org.json.JSONArray
import org.openg2p.voucherservice.models.VoucherProgram
import org.openg2p.voucherservice.repository.VoucherDiscountRepository
import org.openg2p.voucherservice.repository.VoucherProgramRepository
import org.openg2p.voucherservice.repository.VoucherRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.json.JSONObject
import org.openg2p.voucherservice.models.Voucher
import org.openg2p.voucherservice.models.VoucherDiscount

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
    //
    fun getDiscountDetails(discountArray: JSONArray):List<VoucherDiscount>
    {
        val listOfDiscounts:List<VoucherDiscount> = emptyList()
        for (i in 0 until discountArray.length())
        {
            val item = discountArray.getJSONObject(i)
            val voucherDiscountType=item.optString("voucherDiscountType")
            val voucherDiscountPercentOff=item.optString("voucherDiscountPercentOff").toLong()

            val discountObj=VoucherDiscount(
                voucherDiscountType=voucherDiscountType,
                voucherDiscountPercentOff=voucherDiscountPercentOff
            )
            listOfDiscounts.add(discountObj)
        }
        return listOfDiscounts
    }
    fun createProgram(payload: Map<String, Any>) {
        println(payload)
        // Deserializing Program parameters
        val voucherProgramName= payload["voucherProgramName"] as String;
        val voucherCount= payload["voucherCount"] as Int;
        val voucherProgramActive= payload["voucherProgramActive"] as Boolean;
        val voucherProgramStartDate= payload["voucherProgramStartDate"] as String;
        val voucherProgramExpirationDate= payload["voucherProgramExpirationDate"] as String;

        val jsonObject = JSONObject(payload)
        val vouchersAll = jsonObject.optJSONArray("voucher")
        println(vouchersAll)

        // Creating objects of voucher
        val listOfVouchers:List<Voucher> = emptyList()
        for (i in 0 until vouchersAll.length()) {
            val item = vouchersAll.getJSONObject(i)
            val voucherType=item.optString("voucherType")
            val redemptionQuantity=item.optInt("redemptionQuantity")

            val voucherCode=getVoucherCode(voucherProgramName)
            val discountArray = jsonObject.optJSONArray("discount")
            val discountAll=getDiscountDetails(discountArray)

            val voucherObj=Voucher(
                redemptionQuantity=redemptionQuantity,
                voucherType=voucherType,
                voucherCode=voucherCode,
                discount=discountAll
            )

            listOfVouchers.add(voucherObj);
        }

        //Creating voucher program
        val program=VoucherProgram(
            voucherProgramName=voucherProgramName,
            voucherCount=voucherCount,
            voucherProgramActive=voucherProgramActive,
            voucherProgramStartDate=voucherProgramStartDate,
            voucherProgramExpirationDate=voucherProgramExpirationDate,
            voucher=listOfVouchers
        );
        voucherProgramRepository.save(program);
        println("Program created");
    }

    fun getAllPrograms(): List<VoucherProgram> {
        return voucherProgramRepository.findAll();
    }
//    fun getProgramById(id: Int?): Optional<VoucherProgram> {
//        return voucherProgramRepository.findById(id);
//    }

    fun deleteById(id: Int?): String {
        return if (id != null) {
            voucherProgramRepository.deleteById(id)
            "Program Deleted";
        } else {
            "No Program with such id exits";
        }
    }


}
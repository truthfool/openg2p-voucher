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
        var s=programName.slice(0..5);
        for (i in 1..12) {
            var index=((alphaNumeric.length* Math.random()).toInt());
            s+=(alphaNumeric.get(index));
        }
        return s;
    }
    //Creating objects for discount
    fun getDiscountDetails(discountArray: JSONArray):List<VoucherDiscount>
    {
        val listOfDiscounts:List<VoucherDiscount> = emptyList()
        for (i in 0 until discountArray.length())
        {
            val item = discountArray.getJSONObject(i)
            val id=item.optInt("id");
            val voucherDiscountType=item.optString("voucherDiscountType")
            val voucherDiscountPercentOff=item.optString("voucherDiscountPercentOff").toLong()

            val discountObj=VoucherDiscount(
                id=id,
                voucherDiscountType=voucherDiscountType,
                voucherDiscountPercentOff=voucherDiscountPercentOff
            )
            listOfDiscounts.add(discountObj)
        }
        return listOfDiscounts
    }
    //Creating Programs for vouchers
    fun createProgram(payload: Map<String, Any>) {
        println(payload)
        // Deserializing Program parameters
        val id=payload["id"] as Int;
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
            val id=item.optInt("id")

            val voucherCode=getVoucherCode(voucherProgramName)
            val discountArray = jsonObject.optJSONArray("discount")
            val discountAll=getDiscountDetails(discountArray)

            val voucherObj=Voucher(
                id=id,
                redemptionQuantity=redemptionQuantity,
                voucherType=voucherType,
                voucherCode=voucherCode,
                discount=discountAll
            )

            listOfVouchers.add(voucherObj);
        }

        //Creating voucher program
        val program=VoucherProgram(
            id=id,
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
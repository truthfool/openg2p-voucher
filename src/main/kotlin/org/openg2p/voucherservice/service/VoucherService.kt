package org.openg2p.voucherservice.service

import org.openg2p.voucherservice.models.VoucherProgram
import org.openg2p.voucherservice.repository.VoucherDiscountRepository
import org.openg2p.voucherservice.repository.VoucherProgramRepository
import org.openg2p.voucherservice.repository.VoucherRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.json.JSONObject
import org.openg2p.voucherservice.models.Voucher
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
    fun getDiscountDetails():List<>
    {

    }
    fun createProgram(payload: Map<String, Any>) {
        println(payload)
        val programId= payload["programId"] as Int;
        val voucherProgramName= payload["voucherProgramName"] as String;
        val voucherCount= payload["voucherCount"] as Int;
        val voucherProgramActive= payload["voucherProgramActive"] as Boolean;
        val voucherProgramStartDate= payload["voucherProgramStartDate"] as String;
        val voucherProgramExpirationDate= payload["voucherProgramExpirationDate"] as String;

        val jsonObject = JSONObject(payload)
        val vouchersAll = jsonObject.optJSONArray("voucher")
        println(vouchersAll)

        val listOfVouchers:List<Voucher> = emptyList()
        for (i in 0 until vouchersAll.length()) {
            val item = vouchersAll.getJSONObject(i)
            val voucherType=item.optString("voucherType")
            val redemptionQuantity=item.optInt("redemptionQuantity")

//            listOfVouchers.add()
        }

//        val program=VoucherProgram();
//        voucherProgramRepository.save(program);
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
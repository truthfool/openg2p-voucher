package org.openg2p.voucherservice.controller

import org.openg2p.voucherservice.models.Voucher
import org.openg2p.voucherservice.models.VoucherProgram
import org.openg2p.voucherservice.service.VoucherService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import java.util.*


@RestController
@RequestMapping("/api/v1")
class VoucherController constructor(private val voucherService: VoucherService) {

    //Adding Program Details
    @PostMapping("/program")
    fun createProgram(@RequestBody voucherProgram: VoucherProgram): Mono<ResponseEntity<String>> {
        voucherService.createProgram(voucherProgram)
        return Mono.just(ResponseEntity("Program Created Successfully!", HttpStatus.CREATED))
    }
    //Getting all program Details
    @GetMapping("/program")
    fun getAllPrograms(): List<VoucherProgram>
    {
        return voucherService.getAllPrograms()
    }
    //Deleting Program
    @DeleteMapping("/program/{id}")
    fun deleteVoucher(@PathVariable id: Int?): String {
        return voucherService.deleteProgramById(id);
    }
    //Getting Program By Id
//    @GetMapping("/program/{id}")
//    fun getProgramById(@PathVariable id:Int?): Optional<VoucherProgram>
//    {
//        return voucherService.getProgramById(id);
//    }
    // Updating Program Details
//    @PutMapping("/program/{id}")
//    fun updateProgram(@PathVariable id:Int?,@RequestBody voucherProgram: VoucherProgram):VoucherProgram
//    {
//        return voucherService.updateProgram(id);
//    }
//    @GetMapping("/program/{id}")
//    fun getProgram(@PathVariable id: Int?): Optional<VoucherProgram> {
//        return voucherService.getProgramById(id);
//    }

//    @GetMapping("/program/{program_name}")
//    fun getProgramDetails(@PathVariable program_name: String?): Optional<VoucherProgram> {
//        return voucherService.getProgramByName(program_name);
//    }
//    @PutMapping("/{id}")
//    fun updateVoucher(@RequestBody voucher: Voucher, @PathVariable id: Int?): Mono<Voucher?>? {
//        return voucherService.findById(id)
//            .map { c ->
//                c.setName(voucher.getName())
//                c
//            }.flatMap { c -> repository.save(c) }
//    }
    //Add Vouchers
    @PostMapping("/voucher")
    fun createVoucher(@RequestBody voucher: Voucher):Mono<ResponseEntity<String>>{
        voucherService.createVoucher(voucher)
        return Mono.just(ResponseEntity("Program Created Successfully!", HttpStatus.CREATED))
    }
    //Get all vouchers
    @GetMapping("/voucher")
    fun getAllVouchers(): List<Voucher>
    {
        return voucherService.getAllVouchers()
    }
}

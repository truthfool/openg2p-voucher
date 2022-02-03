package org.openg2p.voucherservice.controller

import org.openg2p.voucherservice.models.VoucherProgram
import org.openg2p.voucherservice.service.VoucherService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono


@RestController
@RequestMapping("/api/v1")
class VoucherController constructor(private val voucherService: VoucherService) {

    @PostMapping("/program")
    fun createProgram(@RequestBody voucherProgram: VoucherProgram):Mono<ResponseEntity<String>> {
        voucherService.createProgram(voucherProgram);
        return Mono.just(ResponseEntity("Program Created Successfully!", HttpStatus.CREATED))
    }

    @GetMapping("/program")
    fun getAllPrograms(): List<VoucherProgram>
    {
        return voucherService.getAllPrograms();
    }

//    @PostMapping
//    fun createVoucher(@RequestBody voucher: Voucher?): Mono<Voucher?>? {
//        return voucherService.save(voucher)
//    }
//    @GetMapping
//    fun getVouchers(): Flux<Voucher?>? {
//        return voucherService?.findAllVouchers()
//    }
//
//    @GetMapping("/{id}")
//    fun getVoucher(@PathVariable id: Int?): Mono<Voucher?>? {
//        return voucherService?.findByVoucherId(id)
//    }
//
//
//    @PutMapping("/{id}")
//    fun updateVoucher(@RequestBody voucher: Voucher, @PathVariable id: Int?): Mono<Voucher?>? {
//        return voucherService.findById(id)
//            .map { c ->
//                c.setName(voucher.getName())
//                c
//            }.flatMap { c -> repository.save(c) }
//    }
//
//    @DeleteMapping("/{id}")
//    fun deleteVoucher(@PathVariable id: Int?): Mono<Void?>? {
//        return voucherService.deleteById(id)
//    }
}

package org.openg2p.voucherservice.controller

import org.openg2p.voucherservice.models.VoucherProgram
import org.openg2p.voucherservice.service.VoucherService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import java.util.*
import javax.validation.Valid


@RestController
@RequestMapping("/api/v1", produces = [MediaType.APPLICATION_JSON_VALUE])
class VoucherController constructor(private val voucherService: VoucherService) {

    @PostMapping("/program")
    fun createProgram(@RequestBody payload:Map<String, Any>): Mono<ResponseEntity<String>> {
        voucherService.createProgram(payload)
        return Mono.just(ResponseEntity("Program Created Successfully!", HttpStatus.CREATED))
    }

    @GetMapping("/program")
    fun getAllPrograms(): List<VoucherProgram>
    {
        return voucherService.getAllPrograms()
    }
    @DeleteMapping("/{id}")
    fun deleteVoucher(@PathVariable id: Int?): String {
        return voucherService.deleteById(id)
    }
//    @GetMapping("/{id}")
//    fun getProgram(@PathVariable id: Int?): Optional<VoucherProgram> {
//        return voucherService.getProgramById(id);
//    }
//    @PostMapping
//    fun createVoucher(@RequestBody voucher: Voucher?): Mono<Voucher?>? {
//        return voucherService.save(voucher)
//    }
//    @GetMapping
//    fun getVouchers(): Flux<Voucher?>? {
//        return voucherService?.findAllVouchers()
//    }
//

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

}

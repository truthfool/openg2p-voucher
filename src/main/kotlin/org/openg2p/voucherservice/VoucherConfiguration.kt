package org.openg2p.voucherservice

import org.springframework.boot.SpringApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration

@Configuration
class VoucherConfiguration {
    fun main(args: Array<String>) {
        runApplication<VoucherConfiguration>(*args)
    }
}
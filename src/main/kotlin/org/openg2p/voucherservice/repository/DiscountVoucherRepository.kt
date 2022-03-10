package org.openg2p.voucherservice.repository

import org.openg2p.voucherservice.models.DiscountVoucher
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface DiscountVoucherRepository : JpaRepository<DiscountVoucher, Int>
{
    @Query(value = "select * from discount_voucher where voucher_code=?1",nativeQuery = true)
    abstract fun findByVoucherCode(voucherCode: String): DiscountVoucher
}

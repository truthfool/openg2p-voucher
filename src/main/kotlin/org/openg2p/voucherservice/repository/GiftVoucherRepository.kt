package org.openg2p.voucherservice.repository

import org.openg2p.voucherservice.models.GiftVoucher
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface GiftVoucherRepository: JpaRepository<GiftVoucher, Int>
{
    @Query(value = "select * from gift_voucher where voucher_code=?1",nativeQuery = true)
    abstract fun findByVoucherCode(voucherCode: String?): GiftVoucher
}
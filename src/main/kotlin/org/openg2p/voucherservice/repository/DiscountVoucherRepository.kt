package org.openg2p.voucherservice.repository

import org.openg2p.voucherservice.models.DiscountVoucher
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DiscountVoucherRepository : JpaRepository<DiscountVoucher, Int>
{
}

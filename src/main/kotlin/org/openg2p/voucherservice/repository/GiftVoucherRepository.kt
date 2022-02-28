package org.openg2p.voucherservice.repository

import org.openg2p.voucherservice.models.GiftVoucher
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GiftVoucherRepository: JpaRepository<GiftVoucher, Int>
{
}
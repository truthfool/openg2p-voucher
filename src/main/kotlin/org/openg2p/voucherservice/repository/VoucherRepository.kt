package org.openg2p.voucherservice.repository

import org.openg2p.voucherservice.models.Voucher
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VoucherRepository : JpaRepository<Voucher, Int>
{
}

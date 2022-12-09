package Indiviual_Use.McDonald_Ordering1.macOrdering.repository

import Indiviual_Use.McDonald_Ordering1.macOrdering.model.OrderingDetail
import org.springframework.data.jpa.repository.JpaRepository

interface OrderingDetailRepository :JpaRepository<OrderingDetail,Long> {
    fun findAllByOrderId(orderId: Long): List<OrderingDetail>
}
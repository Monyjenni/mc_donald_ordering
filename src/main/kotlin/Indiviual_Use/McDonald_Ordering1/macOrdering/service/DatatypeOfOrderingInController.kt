package Indiviual_Use.McDonald_Ordering1.macOrdering.service

data class DatatypeOfOrderingInController (
    val new_payment_method_ordering : String = "",
    val new_payment_status_ordering : String ="",
    val new_time_ordering: String,
    val user_id: Long,
    val orderProducts: List<OrderProduct>
)

data class OrderProduct(
    val productId: Long,
    val quantity: Int
)
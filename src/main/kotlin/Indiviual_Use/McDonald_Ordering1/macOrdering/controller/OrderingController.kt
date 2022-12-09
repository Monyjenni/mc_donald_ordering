package Indiviual_Use.McDonald_Ordering1.macOrdering.controller

import Indiviual_Use.McDonald_Ordering1.macOrdering.model.Ordering
import Indiviual_Use.McDonald_Ordering1.macOrdering.model.OrderingDetail
import Indiviual_Use.McDonald_Ordering1.macOrdering.repository.McDonaldRepository
import Indiviual_Use.McDonald_Ordering1.macOrdering.repository.OrderingDetailRepository
import Indiviual_Use.McDonald_Ordering1.macOrdering.repository.OrderingRepository
import Indiviual_Use.McDonald_Ordering1.macOrdering.repository.UserRepository
import Indiviual_Use.McDonald_Ordering1.macOrdering.service.DatatypeOfOrderingInController
import Indiviual_Use.McDonald_Ordering1.macOrdering.service.DatatypeOfUpdatingOrderingInController
import Indiviual_Use.McDonald_Ordering1.macOrdering.service.OrderingService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("api/v1")

class OrderingController(
    private val orderingRepository: OrderingRepository,
    private val orderingService: OrderingService,
    private val mcDonaldRepository: McDonaldRepository,
    private val orderingDetailRepository: OrderingDetailRepository,
    private val userRepository: UserRepository

)
{
    @GetMapping("/getAllOrdering")
    fun getAllUsers(): List<Ordering>{
        return orderingService.getAllOrdering()
    }

    //create data class for (GetMapping ) to return ordering and orderingdetail

    data class OrderResponseDto(
        val ordering: Ordering,
        val orderDetails: List<OrderingDetail>
    )


    @GetMapping("/getOrderingById/{id}")
    fun getById(@PathVariable ("id")id:Long): OrderResponseDto? {
        val getOrder = orderingRepository.findById(id)
        if(getOrder.isPresent){
            val orderingDetails = orderingDetailRepository.findAllByOrderId(id)

            // just return the data class

            return OrderResponseDto(
                ordering = getOrder.get(),
                orderDetails = orderingDetails
            )
        }else{
            throw java.lang.RuntimeException("Id isn't found.")
        }
    }
    @PostMapping("/createOrder")

    fun createOrder (@RequestBody request : DatatypeOfOrderingInController): Ordering? {
        val user = userRepository.findById(request.user_id).get()

        val order = Ordering(
            payment_method = request.new_payment_method_ordering,
            payment_status = request.new_payment_status_ordering,
            time = LocalDateTime.now().toString()
        )
        order.user= user

        val savedOrder = orderingRepository.save(order)

        for (orderProduct in request.orderProducts) {
            val getProduct = mcDonaldRepository.findById(orderProduct.productId)

            if (getProduct.isPresent) {
                val product = getProduct.get()

                val orderDetail = OrderingDetail(
                    description = "Ordering",
                    unitPrice = product.total_price,
                    quantity = orderProduct.quantity,
                    totalPrice = (product.total_price * orderProduct.quantity),
                )
                orderDetail.mcDonald = product
                orderDetail.order = savedOrder

                orderingDetailRepository.save(orderDetail)
            }
        }

        return savedOrder

//        return orderingService.createOrder(
//            new_payment_method = addOrder.new_payment_method_ordering,
//            new_payment_status = addOrder.new_payment_status_ordering,
//            new_time = addOrder.new_time_ordering
//        )
    }

    @PutMapping("/updateOrderById/{id}")
    fun updateOrder(@PathVariable("id")id:Long, @RequestBody updateOrder : DatatypeOfUpdatingOrderingInController):
            Ordering {
        val updating = orderingService.updateOrderByID(id,
            newPaymentMethod = updateOrder.payment_method1, newPaymentStatus = updateOrder.payment_status1,
        newTime = updateOrder.time1)
        if(updating != null){
            return updating
        }else{
            throw java.lang.RuntimeException("Id isn't found.")
        }
    }

    @DeleteMapping("/deleteOrderingById/{id}")
    fun deleteOrderById(@PathVariable("id")id:Long):String{
        val delete= orderingService.deleteById(id)
        return " $delete is successfully deleted. "
    }

    @DeleteMapping("/deleteOrderingByIdReviewingRemaining/{id}")
    fun deleteOrderByIdRemaining (@PathVariable("id")id:Long):MutableList<Ordering>{
        return orderingService.deleteByIdReviewingAllOrders(id)

    }
//    @DeleteMapping("/deleteOrderingByIdWithoutReviewingRemaining")
//    fun deleteOrderingByIdNoRemaining(@PathVariable("id")id:Long): Any? {
//        val findId= orderingRepository.findById(id)
//        if(findId.isPresent){
//            orderingRepository.deleteById(id)
//            return null
//         }else{
//            throw java.lang.RuntimeException("Id isn't found.")
//        }
//
//    }
    @DeleteMapping("/deleteAllOrders")
    fun deleteAll(): Any{
        return orderingService.deleteAllOrders()
    }

}
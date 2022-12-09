package Indiviual_Use.McDonald_Ordering1.macOrdering.service

import Indiviual_Use.McDonald_Ordering1.macOrdering.model.Ordering
import Indiviual_Use.McDonald_Ordering1.macOrdering.repository.OrderingRepository
import Indiviual_Use.McDonald_Ordering1.macOrdering.repository.UserRepository
import jakarta.persistence.criteria.Order
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrderingService(
    private val orderingRepository: OrderingRepository,
    private val userRepository: UserRepository
) {

    //MutableList ( elements can be added for furthermore
    fun getAllOrdering (): MutableList<Ordering> {
        val findAll = orderingRepository.findAll()
        return findAll
    }

    // optional ordering vs ordering ?

    fun getbyId(id:Long): Optional<Ordering> {
        val findId= orderingRepository.findById(id)
        return findId
    }

    fun createOrder(new_payment_method:String,new_payment_status:String, new_time: String, user_id: Long):Ordering ?{
        val user = userRepository.findById(user_id).get()
        val newOrder = Ordering(
            payment_method = new_payment_method,
            payment_status = new_payment_status,
            time = new_time
        ).apply {
            this.user = user
        }
        val saved = orderingRepository.save(newOrder)
        return saved
    }


    fun deleteAllOrders(): String {
        val delete = orderingRepository.deleteAll()
        return "Orders have been successfully deleted"
    }


    fun deleteById (id:Long): String {
          orderingRepository.deleteById(id)
        return "$id has been deleted"
        }


    fun deleteByIdReviewingAllOrders (id:Long): MutableList<Ordering> {

         orderingRepository.deleteById(id)
        val remaining = orderingRepository.findAll()
        remaining.toMutableList()
        return remaining

// start from here
    }

    fun updateOrderByID (id:Long , newPaymentMethod: String , newPaymentStatus: String, newTime: String ): Ordering{
        val findId = orderingRepository.findById(id)
        if (findId.isPresent){
            var condition = findId.get()
            condition.payment_method = newPaymentMethod
            condition.payment_status = newPaymentStatus
            condition.time= newTime

            val saved = orderingRepository.save(condition)
            return saved

        }else{
            throw java.lang.RuntimeException("Ordering id isn't found")
        }

    }

}



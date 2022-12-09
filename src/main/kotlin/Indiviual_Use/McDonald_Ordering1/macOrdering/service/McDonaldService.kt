package Indiviual_Use.McDonald_Ordering1.macOrdering.service

import Indiviual_Use.McDonald_Ordering1.macOrdering.model.McDonald
import Indiviual_Use.McDonald_Ordering1.macOrdering.repository.McDonaldRepository
import org.springframework.stereotype.Service

@Service

//mcdonaldRepository:McDonaldRepository , yk vea mk brer here

class McDonaldService(
    private val mcdonaldRepository: McDonaldRepository) {

    //function to get all orders from McDonald  as a list
    fun getAll(): List<McDonald>{
        val meal = mcdonaldRepository.findAll()
        return meal
    }

    //function to get each items by id
    fun getByID (id:Long):Any{
        val findItem = mcdonaldRepository.findById(id)
        return findItem
    }

    //insert the record

    fun createItem (new_item:String,new_total_price:Double):McDonald{

        //primary construction

        val newMcDonald = McDonald(
            item = new_item,
            total_price = new_total_price

        )

        // we also gotta save these records into database
        val savedNewItems= mcdonaldRepository.save(newMcDonald)
        return savedNewItems
    }

    //updating the orders
    fun updateItemByID (id: Long, new_item: String,new_total_price: Double): McDonald?{
        val item = mcdonaldRepository.findById(id)
        if(item.isPresent){
            //
            var conditionItem = item.get()
            conditionItem.item= new_item
            conditionItem.total_price=new_total_price
            val saved = mcdonaldRepository.save(conditionItem)
            return saved
        }
        else{
            return null
        }

    }

    fun deleteItem ():String{
        val delete= mcdonaldRepository.deleteAll()
        return "All items have been successfully deleted!"
       // return "All items have been successfully deleted"

    }

    fun deleletItemByID(id: Long):List<McDonald>{
        mcdonaldRepository.deleteById(id)
        val remainingItems= mcdonaldRepository.findAll()
        return remainingItems

    }

}

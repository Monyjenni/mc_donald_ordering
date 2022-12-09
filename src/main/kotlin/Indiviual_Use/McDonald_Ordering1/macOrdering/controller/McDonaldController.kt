package Indiviual_Use.McDonald_Ordering1.macOrdering.controller

import Indiviual_Use.McDonald_Ordering1.macOrdering.model.McDonald
import Indiviual_Use.McDonald_Ordering1.macOrdering.repository.McDonaldRepository
import Indiviual_Use.McDonald_Ordering1.macOrdering.service.McDonaldService
import Indiviual_Use.McDonald_Ordering1.macOrdering.service.body_updating
import org.hibernate.sql.Update
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@ControllerAdvice
@RequestMapping("api/v1")

class McDonaldController(private val mcdonaldRepository: McDonaldRepository,
                         private val mcdonaldService: McDonaldService)
{
   @GetMapping("/getAll")
   fun getAll ():List<McDonald>{
       return mcdonaldService.getAll()
    }

   @GetMapping("/get/{id}")
   // this is working with postman so also relate with pathvariable
   fun getByID (@PathVariable("id")id:Long):Any{
       // find here to using repository ( not service coz we dont use the service to define if it's present or not)
       val findMeal = mcdonaldRepository.findById(id)
       if(findMeal.isPresent){
           return mcdonaldService.getByID(id)
       }
       else{
           throw java.lang.RuntimeException("The item isn't found in our menu , please try sth new")
       }
   }

   @PostMapping("/createItems")
   // meal here is just the variable of the requestbody
   fun createItemByID (@RequestBody meal : CreateByController):McDonald{

       return mcdonaldService.createItem(

           new_item = meal.item,
           new_total_price = meal.total_price

       )
   }

    @PutMapping("/updateItem/{id}")
    fun updateItemById(@PathVariable id:Long,@RequestBody request: body_updating ):McDonald?{
      val update = mcdonaldService.updateItemByID(id, new_item = request.item, new_total_price = request.total_price)
        if(update !=null){
            return update
        }else{
            throw java.lang.RuntimeException("item not found")
        }

    }

    @DeleteMapping("/deleteAll")
    fun deleteAll():String{

        return mcdonaldService.deleteItem()
    }


    @DeleteMapping("/deleteById/{id}")
    fun deleteById(@PathVariable("id")id:Long):List<McDonald>{
        return mcdonaldService.deleletItemByID(id)
    }
}

data class CreateByController(
    val item : String,
    val total_price: Double = 0.0

        )
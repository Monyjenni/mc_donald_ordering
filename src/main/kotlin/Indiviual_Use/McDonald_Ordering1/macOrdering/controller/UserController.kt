package Indiviual_Use.McDonald_Ordering1.macOrdering.controller

import Indiviual_Use.McDonald_Ordering1.macOrdering.model.McDonaldUser
import Indiviual_Use.McDonald_Ordering1.macOrdering.repository.UserRepository
import Indiviual_Use.McDonald_Ordering1.macOrdering.service.DatatypeOfUserInController
import Indiviual_Use.McDonald_Ordering1.macOrdering.service.DatatypeUserUpdatingInController
import Indiviual_Use.McDonald_Ordering1.macOrdering.service.UserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
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
@RequestMapping("api/v1")
@ControllerAdvice

class UserController(

    private val userRepository: UserRepository,
    private val userService: UserService
) {

    val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @GetMapping("/getAllUser")
    fun getAll():List<McDonaldUser>{
        return userService.getAllUser()
    }

//    @GetMapping("/getById")
//    fun getByID ( id:Long ): Any{
//
//        val findID= userRepository.findById(id)
//        return findID
//    }

    @GetMapping("/getById/{id}")
    fun getById (@PathVariable("id") id:Long): Any {
        val findID = userRepository.findById(id)
        if(findID.isPresent){
            return userService.getUserById(id)
        }else
            throw java.lang.RuntimeException("user isnt found")
    }


    @PostMapping("/createUser")
    //here user is just the variable of requestbody ,after variable we need datatype
    fun createUser(@RequestBody userAdd : DatatypeOfUserInController): McDonaldUser?{
        return userService.createUser(
            new_name = userAdd.name1,
            new_email = userAdd.email1,
            new_phone_number = userAdd.phone_number1

        )
    }

    @PutMapping("/updateUser/{id}")
    fun updateUser(@PathVariable("id") id: Long, @RequestBody userUpdating: DatatypeUserUpdatingInController): McDonaldUser{
        val updateUser = userService.updateUser(id, new_name = userUpdating.new_name2,
        new_email = userUpdating.new_email2, new_phone_number = userUpdating.new_phone_number2)
        if(updateUser!=null){
            return updateUser
        }else{
            throw java.lang.RuntimeException("user isn't found")
        }
    }

    @DeleteMapping("/deleteAllUser")
    fun deleteUser(): String {
        return userService.deleteAllUser()
    }

    @DeleteMapping("/deleteUserByIdNoSeeRemaining/{id}")
    fun deleteUserById(@PathVariable id: Long): String {
        log.info("[DELETE-USER-BY-ID] id: $id")


        val findId = userRepository.findById(id)
        if(findId.isPresent){
            return userService.deleteByIdNoSeeRemaining(id)
        }else{
            throw java.lang.RuntimeException("user isn't found")
        }
    }

}

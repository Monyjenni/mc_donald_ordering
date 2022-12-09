package Indiviual_Use.McDonald_Ordering1.macOrdering.service

import Indiviual_Use.McDonald_Ordering1.macOrdering.model.McDonaldUser
import Indiviual_Use.McDonald_Ordering1.macOrdering.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService (val userRepository: UserRepository){

    //get all users
    fun getAllUser():List<McDonaldUser>{
        val findAllUser= userRepository.findAll()
        return findAllUser
    }


    //get user by id
    fun getUserById(id:Long):Any{
        val findUserById = userRepository.findById(id)
        return findUserById
    }

    // insert more users

    fun createUser(new_name:String,new_email:String,new_phone_number:Long):McDonaldUser?{

        val newUser=McDonaldUser(
            name = new_name,
            email = new_email,
            phone_number = new_phone_number
        )
        val saved= userRepository.save(newUser)
        return saved

    }

    fun deleteAllUser():String{
        val deleteUser= userRepository.deleteAll()
        return "All users have been successfully removed"
    }

    fun deleteByIdSeeRemaining(id: Long):List<McDonaldUser>{
         userRepository.deleteById(id)
        val remainingUser= userRepository.findAll()
        remainingUser.toMutableList()
        return remainingUser

    }

    fun deleteByIdNoSeeRemaining(id: Long):String{
        userRepository.deleteById(id)
        return "$id has been successfully deleted."

    }

    fun updateUser(id:Long, new_name: String,new_email: String,new_phone_number: Long):McDonaldUser{
        val findById = userRepository.findById(id)

        if(findById.isPresent){

            var conditionUser= findById.get()
            conditionUser.name = new_name
            conditionUser.email= new_email
            conditionUser.phone_number= new_phone_number

            val saved = userRepository.save(conditionUser)

            return saved

        }else
            throw java.lang.RuntimeException("user is not found")

    }


}
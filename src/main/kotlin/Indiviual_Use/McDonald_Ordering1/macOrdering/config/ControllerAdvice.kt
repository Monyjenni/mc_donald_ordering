package Indiviual_Use.McDonald_Ordering1.macOrdering.config

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

class ControllerAdvice {

 // class to send the MSG for the RuntimeException

    @ResponseBody
    @ExceptionHandler(java.lang.RuntimeException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun mcDonaldNotFoundHandler(ex: java.lang.RuntimeException):String?{
        return ex.localizedMessage
    }
}
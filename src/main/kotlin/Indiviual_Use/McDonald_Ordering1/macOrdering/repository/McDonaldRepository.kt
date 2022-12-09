package Indiviual_Use.McDonald_Ordering1.macOrdering.repository

import Indiviual_Use.McDonald_Ordering1.macOrdering.model.McDonald
import org.springframework.data.jpa.repository.JpaRepository


//the connection between database and java
interface McDonaldRepository : JpaRepository<McDonald,Long> {
    fun findAllByIdIn(ids: List<Long>): List<McDonald>
}
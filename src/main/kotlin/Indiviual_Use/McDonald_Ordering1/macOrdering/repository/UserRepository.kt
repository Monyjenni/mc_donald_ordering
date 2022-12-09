package Indiviual_Use.McDonald_Ordering1.macOrdering.repository

import Indiviual_Use.McDonald_Ordering1.macOrdering.model.McDonaldUser
import org.springframework.data.jpa.repository.JpaRepository

//the connection between database and jpa relation
interface UserRepository :JpaRepository <McDonaldUser,Long>
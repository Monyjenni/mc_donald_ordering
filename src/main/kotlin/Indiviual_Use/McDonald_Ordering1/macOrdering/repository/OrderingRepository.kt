package Indiviual_Use.McDonald_Ordering1.macOrdering.repository

import Indiviual_Use.McDonald_Ordering1.macOrdering.model.Ordering
import org.springframework.data.jpa.repository.JpaRepository

interface OrderingRepository :JpaRepository<Ordering,Long>
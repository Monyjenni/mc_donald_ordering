package Indiviual_Use.McDonald_Ordering1.macOrdering.model

import jakarta.persistence.*

// table in database

@Entity
@Table(name="mc_donald")
data class McDonald(
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mc_donald_id_seq")
    @SequenceGenerator(name = "mc_donald_id_seq", sequenceName = "mc_donald_id_seq", allocationSize = 1)
    val id: Long = 0,

    @Column(name="items")
    public var item: String = "",

    @Column(name="total_price")
    public var total_price: Double = 0.0,

)
package Indiviual_Use.McDonald_Ordering1.macOrdering.model

import jakarta.persistence.*


@Entity
@Table(name="ordering")
data class Ordering (

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ordering_id_seq")
    @SequenceGenerator(name = "ordering_id_seq", sequenceName = "ordering_id_seq", allocationSize = 1)
    var id : Long = 0,

    @Column(name="payment_method")
    var payment_method: String ="",

    @Column(name="payment_status")
    var payment_status: String ="",

    @Column(name="time")
    var time: String  = ""
){
    @ManyToOne
    @JoinColumn(name = "user_id")
    lateinit var user: McDonaldUser
}

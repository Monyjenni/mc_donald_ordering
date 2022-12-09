package Indiviual_Use.McDonald_Ordering1.macOrdering.model

import jakarta.persistence.*


@Entity
@Table(name="users")
data class McDonaldUser(

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
    val id: Long = 0,

    @Column(name="name")
    var name: String = "" ,

    @Column(name="email")
    var email:String = "",

    @Column (name="phone_number")
    var phone_number: Long = 0
)
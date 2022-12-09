package Indiviual_Use.McDonald_Ordering1.macOrdering.model

import jakarta.persistence.*
import org.apache.catalina.User


@Entity
@Table(name="ordering_detail")
data class OrderingDetail (

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ordering_detail_id_seq")
    @SequenceGenerator(name = "ordering_detail_id_seq", sequenceName = "ordering_detail_id_seq", allocationSize = 1)
    var id : Long = 0,

    @Column(name="description")
    var description: String = "",

    @Column(name="unit_price")
    var unitPrice: Double = 0.0,

    @Column(name="quantity")
    var quantity: Int = 0,

    @Column(name="total_price")
    var totalPrice: Double = 0.0
) {


    // ManytoOne here is many orders can have same product(one product)

    @ManyToOne
    @JoinColumn(name = "mc_donald_id", nullable = false)
    lateinit var mcDonald: McDonald

    // One order can many detail product(diff product)

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    lateinit var order: Ordering


}
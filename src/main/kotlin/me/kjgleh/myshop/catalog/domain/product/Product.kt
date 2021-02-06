package me.kjgleh.myshop.catalog.domain.product

import me.kjgleh.myshop.common.jpa.MoneyConverter
import me.kjgleh.myshop.common.model.Money
import java.util.*
import javax.persistence.*

@Entity
class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @ElementCollection
    @CollectionTable(
        name = "product_category",
        joinColumns = [JoinColumn(name = "product_id")]
    )
    val categoryIds: Set<Long>,

    val name: String,

    @Convert(converter = MoneyConverter::class)
    var price: Money,

    val detail: String? = null
) {

    @OneToMany(
        cascade = [CascadeType.PERSIST, CascadeType.REMOVE],
        orphanRemoval = true,
        fetch = FetchType.EAGER
    )
    @JoinColumn(name = "product_id")
    @OrderColumn(name = "list_idx")
    val images: MutableList<Image> = ArrayList()

    fun changeImages(newImages: List<Image>) {
        images.clear()
        images.addAll(newImages)
    }

    fun firstImageThumbnailPath(): String? {
        return if (images.isEmpty()) {
            null
        } else {
            images[0].getThumbnailUrl()
        }
    }
}
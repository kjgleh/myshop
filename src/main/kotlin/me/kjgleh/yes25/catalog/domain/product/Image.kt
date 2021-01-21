package me.kjgleh.yes25.catalog.domain.product

import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "image_type")
@EntityListeners(AuditingEntityListener::class)
@Table(name = "image")
abstract class Image(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    val path: String
) {

    val createdAt: LocalDateTime = LocalDateTime.now()

    abstract fun getUrl(): String

    abstract fun hasThumbnail(): Boolean

    abstract fun getThumbnailUrl(): String?
}
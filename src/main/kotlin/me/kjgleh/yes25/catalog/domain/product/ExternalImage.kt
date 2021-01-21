package me.kjgleh.yes25.catalog.domain.product

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("EI")
class ExternalImage(
    override val path: String
) : Image(
    path = path
) {

    override fun getUrl() = this.path

    override fun hasThumbnail() = false

    override fun getThumbnailUrl(): Nothing? = null
}
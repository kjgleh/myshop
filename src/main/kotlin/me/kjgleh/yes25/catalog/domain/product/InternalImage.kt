package me.kjgleh.yes25.catalog.domain.product

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("II")
class InternalImage(
    override val path: String
) : Image(
    path = path
) {

    override fun getUrl() = "/images/original/${this.path}"

    override fun hasThumbnail() = true

    override fun getThumbnailUrl() = "/images/thumbnail/${this.path}"
}
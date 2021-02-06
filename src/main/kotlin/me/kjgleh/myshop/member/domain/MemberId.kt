package me.kjgleh.myshop.member.domain

import au.com.console.kassava.kotlinEquals
import au.com.console.kassava.kotlinHashCode
import java.io.Serializable
import javax.persistence.Embeddable

@Embeddable
class MemberId(
    val id: String
) : Serializable {

    companion object {
        private val equalsAndHashCodeProperties = arrayOf(MemberId::id)
    }

    override fun equals(other: Any?) = kotlinEquals(
        other = other,
        properties = equalsAndHashCodeProperties
    )

    override fun hashCode() =
        kotlinHashCode(properties = equalsAndHashCodeProperties)
}

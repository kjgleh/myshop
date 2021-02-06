package me.kjgleh.myshop.common.jpa

import me.kjgleh.myshop.common.model.Email
import me.kjgleh.myshop.common.model.EmailSet
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter
class EmailSetConverter : AttributeConverter<EmailSet, String> {

    override fun convertToDatabaseColumn(attribute: EmailSet): String {
        return attribute.emails.joinToString(",") { it.address }
    }

    override fun convertToEntityAttribute(dbData: String): EmailSet {
        val emails = dbData.split(",")
        val emailSet = emails.map { Email(it) }.toHashSet()

        return EmailSet(emailSet)
    }
}
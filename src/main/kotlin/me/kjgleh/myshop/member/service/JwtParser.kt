package me.kjgleh.myshop.member.service

import io.jsonwebtoken.Jwts
import me.kjgleh.myshop.member.exception.InvalidTokenException

object JwtParser {
    private const val SIGNING_KEY = "my-signing-key"

    fun getMemberId(authorization: String): String {
        return try {
            val claims = Jwts
                .parser()
                .setSigningKey(SIGNING_KEY.toByteArray())
                .parseClaimsJws(authorization.substring("Bearer ".length)).body

            claims["id"].toString()
        } catch (e: Exception) {
            throw InvalidTokenException()
        }
    }
}
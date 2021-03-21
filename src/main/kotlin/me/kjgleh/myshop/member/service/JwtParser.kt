package me.kjgleh.myshop.member.service

import io.jsonwebtoken.Jwts

object JwtParser {
    private const val SIGNING_KEY = "my-signing-key"

    fun getMemberId(authorization: String): String {
        val claims = Jwts
            .parser()
            .setSigningKey(SIGNING_KEY.toByteArray())
            .parseClaimsJws(authorization.substring("Bearer ".length)).body

        return claims["id"].toString()
    }
}
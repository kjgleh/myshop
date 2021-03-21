package me.kjgleh.myshop.member.service.dto

import io.swagger.annotations.ApiModelProperty

data class MemberView(
    @ApiModelProperty(example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6ImEwOTg0ZTc1LTJiZmMtNDQ5Zi1iMGY2LTlmMWQzMDgxZDRjMiJ9.F8vtwJxtEJClVIMT-CGg8RBHfm6FeoSDm6S7o9gOAYA")
    val id: String,
    val name: String
)

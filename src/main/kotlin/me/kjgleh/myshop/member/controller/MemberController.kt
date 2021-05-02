package me.kjgleh.myshop.member.controller

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.Authorization
import me.kjgleh.myshop.member.service.FindMemberService
import me.kjgleh.myshop.member.service.UpdateMemberService
import me.kjgleh.myshop.member.service.dto.MemberView
import me.kjgleh.myshop.member.service.dto.UpdateMemberRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import springfox.documentation.annotations.ApiIgnore

@RestController
@Api(tags = ["Members"], description = " ")
class MemberController(
    private val findMemberService: FindMemberService,
    private val updateMemberService: UpdateMemberService
) {

    @ApiOperation(
        value = "",
        authorizations = [Authorization(value = "Bearer JWT")]
    )
    @GetMapping("/api/me")
    fun me(
        @ApiIgnore
        @RequestHeader(name = "Authorization") authorization: String
    ): ResponseEntity<MemberView> {
        val memberView =
            findMemberService.findMember(authorization)

        return ResponseEntity.status(HttpStatus.OK).body(memberView)
    }

    @ApiOperation(
        value = "",
        authorizations = [Authorization(value = "Bearer JWT")]
    )
    @PutMapping("/api/me")
    fun updateMember(@RequestBody updateMemberRequest: UpdateMemberRequest): ResponseEntity<Unit> {
        updateMemberService.updateMember(updateMemberRequest)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}

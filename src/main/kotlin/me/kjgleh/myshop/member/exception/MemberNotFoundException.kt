package me.kjgleh.myshop.member.exception

import me.kjgleh.myshop.common.exception.ServiceNotFoundException

class MemberNotFoundException(message: String = "Member not found.") :
    ServiceNotFoundException(message)
package me.kjgleh.myshop.order.ui.exception

class MemberNotFoundException(message: String = "This member id does not exist") :
    ServiceNotFoundException(message)
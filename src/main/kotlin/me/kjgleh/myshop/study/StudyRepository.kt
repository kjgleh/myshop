package me.kjgleh.myshop.study

import org.springframework.data.jpa.repository.JpaRepository

interface StudyRepository : JpaRepository<Study, Long>

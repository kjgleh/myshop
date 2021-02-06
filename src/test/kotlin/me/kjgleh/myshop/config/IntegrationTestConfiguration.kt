package me.kjgleh.myshop.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@TestConfiguration
@EnableJpaRepositories(basePackages = ["me.kjgleh.myshop"])
@EntityScan(basePackages = ["me.kjgleh.yes25"])
@AutoConfigureDataJpa
class IntegrationTestConfiguration
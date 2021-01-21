package me.kjgleh.yes25.catalog.repository

import me.kjgleh.yes25.catalog.domain.product.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository: JpaRepository<Product, Long>
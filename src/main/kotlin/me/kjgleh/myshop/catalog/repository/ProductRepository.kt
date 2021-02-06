package me.kjgleh.myshop.catalog.repository

import me.kjgleh.myshop.catalog.domain.product.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository: JpaRepository<Product, Long>
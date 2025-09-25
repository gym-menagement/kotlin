package com.gowoobro.gymspring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("com.gowoobro.gymspring")
class GymspringApplication

fun main(args: Array<String>) {
	runApplication<GymspringApplication>(*args)
}


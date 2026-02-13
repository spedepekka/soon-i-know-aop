package fi.kranu.servicec

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ServicecApplication

fun main(args: Array<String>) {
	runApplication<ServicecApplication>(*args)
}

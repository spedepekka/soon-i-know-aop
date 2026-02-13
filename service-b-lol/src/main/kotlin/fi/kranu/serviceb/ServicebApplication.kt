package fi.kranu.serviceb

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate

@SpringBootApplication
class ServicebApplication {
	@Bean
	fun restTemplate(): RestTemplate {
		return RestTemplate()
	}
}

fun main(args: Array<String>) {
	runApplication<ServicebApplication>(*args)
}

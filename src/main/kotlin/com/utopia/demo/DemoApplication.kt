package com.utopia.demo

import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Profile
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@SpringBootApplication
@RestController
class DemoApplication {
    companion object {
        private val log = LoggerFactory.getLogger(DemoApplication::class.java)
    }

    @Bean
    fun restTemplate(builder: RestTemplateBuilder): RestTemplate = builder.build()

    @Bean
    @Profile("!test")
    fun run(restTemplate: RestTemplate): CommandLineRunner {
        return CommandLineRunner {
            // 需要先在本地把另一个项目跑起来，https://github.com/spring-guides/quoters
            val quote = restTemplate.getForObject("http://localhost:8080/api/random", Quote::class.java)
            log.info(quote.toString())
        }
    }
}

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}

package org.kilinochi.demo.zeebe

import io.zeebe.spring.client.EnableZeebeClient
import io.zeebe.spring.client.annotation.ZeebeDeployment
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
@EnableZeebeClient
@ZeebeDeployment(classPathResources = ["emergency-process.bpmn"])
class Application

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
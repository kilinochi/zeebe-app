package org.kilinochi.demo.zeebe

import io.zeebe.spring.client.EnableZeebeClient
import io.zeebe.spring.client.annotation.ZeebeDeployment
import org.kilinochi.demo.zeebe.messages.binder.RecordVariablesSink
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.stream.annotation.EnableBinding

@SpringBootApplication
@EnableZeebeClient
@EnableBinding(value = [
    RecordVariablesSink::class
])
@ZeebeDeployment(classPathResources = ["emergency-process.bpmn"])
class Application

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
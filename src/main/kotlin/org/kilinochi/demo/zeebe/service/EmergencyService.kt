package org.kilinochi.demo.zeebe.service

import io.zeebe.spring.client.ZeebeClientLifecycle
import java.util.UUID
import org.kilinochi.demo.zeebe.controller.dto.EmergencyReasonDto
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class EmergencyService(
    private val zeebeClient: ZeebeClientLifecycle) {

    fun startEvent(reason: EmergencyReasonDto) {
        val emergencyReason = reason.emergencyReason
        val event = zeebeClient.newCreateInstanceCommand().bpmnProcessId("EMERGENCY_PROCESS")
            .latestVersion().variables("{\n" +
                    "  \"emergencyReason\": {\n" +
                    "    \"status\": \"$emergencyReason\",\n" +
                    "    \"id\": ${UUID.randomUUID()}\n" +
                    "  }\n" +
                    "}").send().join()
        logger.info(
            "started instance for workflowKey='{}', bpmnProcessId='{}', version='{}' with workflowInstanceKey='{}'",
            event.workflowKey, event.bpmnProcessId, event.version, event.workflowInstanceKey
        )
    }

    private companion object {
        val logger: Logger = LoggerFactory.getLogger(EmergencyService::class.java)
    }
}
package org.kilinochi.demo.zeebe.worker

import io.zeebe.client.api.response.ActivatedJob
import io.zeebe.client.api.worker.JobClient
import io.zeebe.spring.client.annotation.ZeebeWorker
import org.springframework.stereotype.Component
import java.time.Instant
import org.slf4j.Logger
import org.slf4j.LoggerFactory


@Component
class EmergencyWorker {

    @ZeebeWorker(type = "classify", name = "Classify Emergency")
    fun classifyEmergency(client: JobClient, job: ActivatedJob) {
        logJob(job)
        when {
            job.variablesAsMap["emergencyReason"] == null -> { // default to ambulance if no reason is provided
                client.newCompleteCommand(job.key).variables("{\"emergencyType\": \"Injured\"}").send().join()
            }
            job.variablesAsMap["emergencyReason"].toString().contains("person") -> {
                client.newCompleteCommand(job.key).variables("{\"emergencyType\": \"Injured\"}").send().join()
            }
            job.variablesAsMap["emergencyReason"].toString().contains("fire") -> {
                client.newCompleteCommand(job.key).variables("{\"emergencyType\": \"Fire\"}").send().join()
            }
        }
    }


    @ZeebeWorker(type = "hospital", name = "Coordinate With Hospital")
    fun handleHospitalCoordination(client: JobClient, job: ActivatedJob) {
        logJob(job)
        client.newCompleteCommand(job.key).send().join()
    }

    @ZeebeWorker(type = "firefighters", name = "Coordinate with  FireFightters")
    fun handleFirefighterCoordination(client: JobClient, job: ActivatedJob) {
        logJob(job)
        client.newCompleteCommand(job.key).send().join()
    }

    private fun logJob(job: ActivatedJob) {
        log.info(
            "complete job\n>>> [type: {}, key: {}, element: {}, workflow instance: {}]\n{deadline; {}]\n[headers: {}]\n[variables: {}]",
            job.type,
            job.key,
            job.elementId,
            job.workflowInstanceKey,
            Instant.ofEpochMilli(job.deadline),
            job.customHeaders,
            job.variables
        )
    }

    private companion object {
        val log: Logger = LoggerFactory.getLogger(EmergencyWorker::class.java)
    }
}
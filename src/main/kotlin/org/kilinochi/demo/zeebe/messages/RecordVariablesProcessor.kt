package org.kilinochi.demo.zeebe.messages

import org.kilinochi.demo.zeebe.messages.binder.RecordVariablesSink
import org.kilinochi.demo.zeebe.service.EmergencyService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.stereotype.Service

@Service
class RecordVariablesProcessor {

    @StreamListener(RecordVariablesSink.INPUT)
    fun processConsumeVariables(record: String) {
        logger.info("Record from kafka is =  $record  ")
    }

    private companion object {
        val logger: Logger = LoggerFactory.getLogger(EmergencyService::class.java)
    }
}
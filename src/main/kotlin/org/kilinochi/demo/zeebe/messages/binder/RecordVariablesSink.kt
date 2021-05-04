package org.kilinochi.demo.zeebe.messages.binder

import org.springframework.cloud.stream.annotation.Input
import org.springframework.messaging.SubscribableChannel

interface RecordVariablesSink {

    @Input(INPUT)
    fun input(): SubscribableChannel

    companion object {
        const val INPUT = "variablesInput"
    }
}
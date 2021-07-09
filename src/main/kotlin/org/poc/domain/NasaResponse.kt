package org.poc.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer
import java.time.LocalDate

@JsonIgnoreProperties(ignoreUnknown = true)
data class NasaResponse(
    @JsonDeserialize(using = LocalDateDeserializer::class) var date: LocalDate,
    val explanation: String,
    val url: String
)
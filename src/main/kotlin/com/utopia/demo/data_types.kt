package com.utopia.demo

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Value(val id: Int, val quote: String)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Quote(val type: String, val value: Value)
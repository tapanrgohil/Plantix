package com.test.plantix.data.name.model

//NetworkModel
data class NameResponse(val id: Int, val value: String)

//Database model
data class NameEntity(val id: Int, val value: String)

//Business/Domain model
data class Name(val id: Int, val value: String)

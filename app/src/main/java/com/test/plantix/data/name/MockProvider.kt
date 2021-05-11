package com.test.plantix.data.name

import com.test.plantix.data.name.model.NameResponse

class MockProvider {

    //UnEnven case from aoi
    fun getNames() = listOf(
        NameResponse(1, "abc"),
        NameResponse(2, "Xyx"),
        NameResponse(3, "Smith"),
        NameResponse(4, "Jhone"),
        NameResponse(5, "Apple"),
        NameResponse(6, "Google"),
        NameResponse(7, "only"),
        NameResponse(8, "pepsi")
    )
}
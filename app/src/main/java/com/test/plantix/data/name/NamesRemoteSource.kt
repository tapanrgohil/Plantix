package com.test.plantix.data.name

import com.test.plantix.data.name.model.NameResponse
import javax.inject.Inject

interface NamesRemoteSource {

    fun getNames(): List<NameResponse>
}

class NamesRemoteSourceImpl @Inject constructor(private val mockProvider: MockProvider) :
    NamesRemoteSource {
    override fun getNames(): List<NameResponse> {
        return mockProvider.getNames()
    }


}
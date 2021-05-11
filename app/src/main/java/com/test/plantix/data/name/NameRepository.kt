package com.test.plantix.data.name

import com.test.plantix.data.name.core.Resource
import com.test.plantix.data.name.model.Name
import com.test.plantix.data.name.model.NameEntity
import com.test.plantix.data.name.model.NameResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface NameRepository {
    fun getNames(): Flow<Resource<List<Name>>>
}

class NameRepositoryImpl @Inject constructor(
    private val namesLocalSource: NamesLocalSource,
    private val namesRemoteSource: NamesRemoteSource
) : NameRepository {
    override fun getNames(): Flow<Resource<List<Name>>> {
        return getFLow(
            localCall = {
                println("Local called")
                namesLocalSource.getNames()
            },
            remoteCall = {
                println("Remote called")
                namesRemoteSource.getNames()
            },
            remoteToLocalInsert = {
                insertIntoLocal(it)
            },
            mapper = {
                mapToDomain(it)
            }
        )

    }

    private fun mapToDomain(localData: List<NameEntity>): List<Name> {
        return localData.map {
            with(it) {
                Name(id, value)
            }
        }
    }

    private fun insertIntoLocal(remoteData: List<NameResponse>) {
        println("fetched remote")
        println(remoteData)
        namesLocalSource.insertNames(remoteData.map {
            NameEntity(
                it.id,
                it.value.toLowerCase()
            ) //Saving to lower case even though apis has uneven data
        })
    }
}

fun <Api, Local, Result> getFLow(
    localCall: () -> Local?,
    remoteCall: () -> Api,
    remoteToLocalInsert: (Api) -> Unit,
    mapper: (Local) -> Result
): Flow<Resource<Result>> {
    return flow<Resource<Result>> {
        emit(Resource.loading()) //Show loading
        delay(2000) // Delay for demo
        var local = localCall.invoke()  // invokes local
        if (local == null) { //if no local data
            val remote = remoteCall.invoke() // check remote and insert to local if found
            remoteToLocalInsert.invoke(remote)
            local =
                localCall.invoke() // For Single source of truth data will return only from local
        } else {
            println("Only local called")
        }
        emit(Resource.success(mapper.invoke(local!!))) // mapp local data to domain model again
    }.catch {
        emit(Resource.error("Something went wrong"))
    }

}

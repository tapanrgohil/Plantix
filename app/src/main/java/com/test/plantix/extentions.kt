package com.test.plantix.ui

import androidx.lifecycle.MutableLiveData
import com.test.plantix.data.name.core.Resource
import com.test.plantix.data.name.core.Status
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*


fun <T> Flow<T>.launchInBackGround(
    coroutineScope: CoroutineScope,
    liveData: MutableLiveData<T>? = null
) {
    if (liveData == null)
        flowOn(Dispatchers.IO)
            .launchIn(coroutineScope)
    else {
        onEach {
            liveData.postValue(it)
        }.flowOn(Dispatchers.IO)
            .launchIn(coroutineScope)
    }
}

fun <T, R> Flow<Resource<T>>.mapResource(
    data: (T?) -> R
): Flow<Resource<R>> {
    return map {
        when (it.status) {
            Status.SUCCESS -> {
                Resource.success<R>(data.invoke(it.data))
            }
            Status.ERROR -> {
                Resource.error<R>(
                    it.message.orEmpty(),
                    null,
                    null

                )
            }
            else -> {
                Resource.loading<R>()
            }
        }
    }
}

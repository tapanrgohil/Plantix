package com.test.plantix.ui.namelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.plantix.data.name.NameRepository
import com.test.plantix.data.name.core.Resource
import com.test.plantix.ui.launchInBackGround
import com.test.plantix.ui.mapResource
import com.test.plantix.ui.namelist.model.NameUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class NameListViewModel @Inject constructor(
    private val nameRepository: NameRepository
) : ViewModel() {

    private val _listNameUiModelLiveData = MutableLiveData<Resource<List<NameUIModel>>>()
    val listNameUiModelLiveData = _listNameUiModelLiveData as LiveData<Resource<List<NameUIModel>>>


    fun getName() {
        nameRepository.getNames()
            .mapResource {
                it.orEmpty().map { name ->
                    with(name) {
                        NameUIModel(id, value.capitalize(Locale.ENGLISH))//FirstLetter capital
                    }
                }
            }.launchInBackGround(viewModelScope, _listNameUiModelLiveData)

    }

}
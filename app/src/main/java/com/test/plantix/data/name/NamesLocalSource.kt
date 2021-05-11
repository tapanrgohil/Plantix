package com.test.plantix.data.name

import com.test.plantix.data.name.model.NameEntity
import javax.inject.Inject

interface NamesLocalSource {

    fun insertNames(names: List<NameEntity>): Long
    fun getNames(): List<NameEntity>?
}

class NamesLocalSourceImpl @Inject constructor(
    private val nameLocalDBInstance: NameLocalDBInstance
) : NamesLocalSource {
    override fun insertNames(names: List<NameEntity>): Long {
        nameLocalDBInstance.List = names
        return names.size.toLong()//emulates number of rows inserted
    }

    override fun getNames(): List<NameEntity>? {
        return nameLocalDBInstance.List
    }

}
package com.test.plantix.data.name

import com.test.plantix.data.name.model.NameEntity

//Caching data for session  replicate in momery DB
data class NameLocalDBInstance(var List: List<NameEntity>? = null)

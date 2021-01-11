package com.example.haris.jetpacksubmission2.project.utils

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResource {
    private val RESOURCE: String? ="GLOBAL"
    private val esspressoTestidlingRersource = CountingIdlingResource(RESOURCE)

    fun increment(){
        esspressoTestidlingRersource.increment()
    }
    fun decrement(){
        esspressoTestidlingRersource.decrement()
    }
    fun getEspressoIdlingResource(): IdlingResource{
        return esspressoTestidlingRersource
    }

}
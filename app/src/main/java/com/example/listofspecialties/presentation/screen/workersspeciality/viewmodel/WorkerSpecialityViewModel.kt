package com.example.listofspecialties.presentation.screeen.workersspeciality.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.listofspecialties.presentation.model.Speciality

class WorkerSpecialityViewModel(val speciality: Speciality) {

    private val specialityData = MutableLiveData<Speciality>()

    init {
        specialityData.value = speciality
    }
}
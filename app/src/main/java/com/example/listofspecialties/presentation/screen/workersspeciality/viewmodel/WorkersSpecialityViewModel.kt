package com.example.listofspecialties.presentation.screeen.workersspeciality.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.listofspecialties.domain.usecase.GetWorkersSpecialityUseCase
import com.example.listofspecialties.presentation.model.Speciality
import com.example.listofspecialties.presentation.screeen.workersspeciality.mapper.WorkersSpecialityMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WorkersSpecialityViewModel @Inject constructor(
    private val getWorkersSpecialityUseCase: GetWorkersSpecialityUseCase,
    private val mapper: WorkersSpecialityMapper
) : ViewModel() {

    val specialityReceivedLiveData = MutableLiveData<List<Speciality>>()
    val isLoad = MutableLiveData<Boolean>()
    val isError = MutableLiveData<Boolean>()

    init {
        isLoad.value = false
        isError.value = false
    }

    fun loadWorkers() {
        getWorkersSpecialityUseCase.execute(
            onSuccess = {
                isLoad.value = true
                specialityReceivedLiveData.value =
                    mapper.convertWorkerResponseToSpeciality(it)

                getWorkersSpecialityUseCase.deleteAll()
                getWorkersSpecialityUseCase.addWorkers(mapper.convertWorkerResponseToWorker(it))
            },
            onError = {
                it.printStackTrace()
                isError.value = true
            }
        )
    }
}
package com.example.listofspecialties.presentation.screeen.worker.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.listofspecialties.domain.usecase.GetWorkersUseCase
import com.example.listofspecialties.presentation.model.Worker
import com.example.listofspecialties.presentation.screeen.worker.mapper.WorkerMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WorkersViewModel @Inject constructor(
    private val getWorkersUseCase: GetWorkersUseCase,
    private val mapper: WorkerMapper
) : ViewModel() {

    val workersReceivedLiveData = MutableLiveData<List<Worker>>()
    val isLoad = MutableLiveData<Boolean>()
    val isError = MutableLiveData<Boolean>()

    init {
        isLoad.value = false
        isError.value = false
    }

    fun convertToWorkDetailsModel(worker: Worker) =
        mapper.convertToWorkDetailsModel(worker)

    fun loadWorkers(specialityId: Int?) {
        getWorkersUseCase.saveSpecialityId(specialityId)
        getWorkersUseCase.execute(
            onSuccess = {
                isLoad.value = true
                workersReceivedLiveData.value =
                    mapper.convertWorkerDBToWorker(it)
            },
            onError = {
                it.printStackTrace()
                isError.value = true
            }
        )
    }
}
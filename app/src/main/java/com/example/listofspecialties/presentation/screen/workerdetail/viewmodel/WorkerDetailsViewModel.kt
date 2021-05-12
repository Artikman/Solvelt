package com.example.listofspecialties.presentation.screeen.workerdetail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.listofspecialties.domain.model.WorkerFavorite
import com.example.listofspecialties.domain.usecase.GetWorkerDetailsUseCase
import com.example.listofspecialties.presentation.screeen.workerdetail.mapper.WorkerDetailsMapper
import com.example.listofspecialties.presentation.screeen.workerdetail.model.WorkerDetailsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WorkerDetailsViewModel @Inject constructor(
    private val getWorkerDetailsUseCase: GetWorkerDetailsUseCase,
    private val mapper: WorkerDetailsMapper
) : ViewModel() {

    private val isFavorite = MutableLiveData<Boolean>()

    val workerFavoriteData = MutableLiveData<WorkerFavorite>()
    val isError = MutableLiveData<Boolean>()

    init {
        isError.value = false
    }

    fun setDetail(workerDetailsModel: WorkerDetailsModel) {
        workerFavoriteData.value =
            mapper.convertWorkerDetailsModelToWorkerFavorite(workerDetailsModel)
    }

    fun checkFavoriteStatus(workerDetailsModel: WorkerDetailsModel) {
        val workerFavorite = mapper.convertWorkerDetailsModelToWorkerFavorite(workerDetailsModel)
        getWorkerDetailsUseCase.saveWorkerFavorite(workerFavorite)
        getWorkerDetailsUseCase.execute(
            onSuccess = {
                isFavorite.value = it
            },
            onError = {
                it.printStackTrace()
                isError.value = true
            }
        )
    }
}
package com.example.listofspecialties.presentation.screeen.worker.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.listofspecialties.presentation.model.Worker

class WorkerViewModel(val worker: Worker) {

    private val workerData = MutableLiveData<Worker>()

    init {
        workerData.value = worker
    }
}
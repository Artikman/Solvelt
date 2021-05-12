package com.example.listofspecialties.presentation.screeen.worker.mapper

import com.example.listofspecialties.domain.model.WorkerDatabase
import com.example.listofspecialties.presentation.model.Speciality
import com.example.listofspecialties.presentation.model.Worker
import com.example.listofspecialties.presentation.screeen.workerdetail.model.WorkerDetailsModel
import com.example.listofspecialties.util.Constants.DOUBLE_HYPHEN
import com.example.listofspecialties.util.calculateAge
import com.example.listofspecialties.util.convertDate
import com.example.listofspecialties.util.toFirstUpperCase
import java.util.Locale
import javax.inject.Inject

class WorkerMapper @Inject constructor() {

    fun convertWorkerDBToWorker(worker: MutableList<WorkerDatabase>): List<Worker> {
        val list: MutableList<Worker> = mutableListOf()

        worker.forEach { workerItem ->

            val listSpecialty: MutableList<Speciality> = mutableListOf()

            listSpecialty.add(
                Speciality(
                    workerItem.specialityId,
                    workerItem.specialityName
                )
            )

            list.add(
                Worker(
                    workerItem.firstName?.toLowerCase(Locale.getDefault())?.toFirstUpperCase(),
                    workerItem.lastName?.toLowerCase(Locale.getDefault())?.toFirstUpperCase(),
                    workerItem.birthday?.convertDate() ?: DOUBLE_HYPHEN,
                    workerItem.birthday?.calculateAge() ?: DOUBLE_HYPHEN,
                    workerItem.avatarUrl,
                    listSpecialty
                )
            )
        }

        return list
    }

    fun convertToWorkDetailsModel(worker: Worker) =
        WorkerDetailsModel(
            worker.firstName,
            worker.lastName,
            worker.birthday,
            worker.avatarUrl,
            worker.speciality?.first()?.specialityId,
            worker.speciality?.first()?.specialityName
        )
}
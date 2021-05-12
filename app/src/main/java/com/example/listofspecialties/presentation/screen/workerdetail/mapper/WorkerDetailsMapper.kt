package com.example.listofspecialties.presentation.screeen.workerdetail.mapper

import com.example.listofspecialties.domain.model.WorkerFavorite
import com.example.listofspecialties.presentation.screeen.workerdetail.model.WorkerDetailsModel
import com.example.listofspecialties.util.Constants.DOUBLE_HYPHEN
import com.example.listofspecialties.util.calculateAge
import com.example.listofspecialties.util.toFirstUpperCase
import java.util.Locale
import javax.inject.Inject

class WorkerDetailsMapper @Inject constructor() {

    fun convertWorkerDetailsModelToWorkerFavorite(
        workerDetailsModel: WorkerDetailsModel
    ): WorkerFavorite {
        return WorkerFavorite(
            0,
            workerDetailsModel.firstName
                ?.toLowerCase(Locale.getDefault())
                ?.toFirstUpperCase(),
            workerDetailsModel.lastName
                ?.toLowerCase(Locale.getDefault())
                ?.toFirstUpperCase(),
            workerDetailsModel.birthday,
            workerDetailsModel.birthday
                ?.calculateAge() ?: DOUBLE_HYPHEN,
            workerDetailsModel.avatarUrl,
            workerDetailsModel.specialtyId,
            workerDetailsModel.specialtyName
        )
    }
}
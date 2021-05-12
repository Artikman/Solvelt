package com.example.listofspecialties.presentation.screeen.workerdetail.model

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator

data class WorkerDetailsModel(
    val firstName: String?,
    val lastName: String?,
    val birthday: String?,
    val avatarUrl: String?,
    val specialtyId: Int?,
    val specialtyName: String?
) : Parcelable {

    constructor(source: Parcel) : this(
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readInt(),
        source.readString()
    )

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(firstName)
        writeString(lastName)
        writeString(birthday)
        writeString(avatarUrl)
        writeValue(specialtyId)
        writeString(specialtyName)
    }

    companion object CREATOR : Creator<WorkerDetailsModel> {
        override fun createFromParcel(parcel: Parcel): WorkerDetailsModel {
            return WorkerDetailsModel(parcel)
        }

        override fun newArray(size: Int): Array<WorkerDetailsModel?> {
            return arrayOfNulls(size)
        }
    }
}
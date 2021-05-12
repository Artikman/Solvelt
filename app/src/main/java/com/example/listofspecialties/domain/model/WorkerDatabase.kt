package com.example.listofspecialties.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Worker")
data class WorkerDatabase(
    @PrimaryKey(autoGenerate = true) var id: Long,
    var firstName: String?,
    var lastName: String?,
    var birthday: String?,
    var avatarUrl: String?,
    var specialityId: Int?,
    var specialityName: String?
)
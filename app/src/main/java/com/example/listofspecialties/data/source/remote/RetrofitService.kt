package com.example.listofspecialties.data.source.remote

import com.example.listofspecialties.domain.model.WorkerResponse
import com.example.listofspecialties.util.Constants.GET_WORKERS
import io.reactivex.Single
import retrofit2.http.GET

interface RetrofitService {

    @GET(GET_WORKERS)
    fun getWorkers(): Single<WorkerResponse>
}
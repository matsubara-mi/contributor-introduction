package com.example.contributorintruductionapp

import retrofit2.Call
import retrofit2.http.GET

interface ContributorListService {
    @GET("contributors")
    fun getContributorList() : Call<List<ContributorData>>
}
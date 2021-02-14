package com.example.contributorintruductionapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contributorintruductionapp.ServiceBuilder.buildService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContributorListActivity<JSonObject> : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contributor_list)
        loadContributors()
    }

    private fun loadContributors() {
        val destinationService = buildService(ContributorListService::class.java)
        val requestCall = destinationService.getContributorList()

        requestCall.enqueue(object : Callback<List<ContributorData>> {
            override fun onFailure(call: Call<List<ContributorData>>, t: Throwable) {
                Toast.makeText(this@ContributorListActivity, "error $t", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onResponse(
                call: Call<List<ContributorData>>,
                response: Response<List<ContributorData>>
            ) {
                Log.d("Response", "onResponse: ${response.body()}")
                if (response.isSuccessful) {
                    val contributorList = response.body()!!
                    Log.d("Response", "contributorList size : ${contributorList.size}")
                    findViewById<RecyclerView>(R.id.contributor_recycler).apply {
                        setHasFixedSize(true)
                        layoutManager =
                            GridLayoutManager(this@ContributorListActivity, 2)
                        adapter = ContributorListAdapter(response.body()!!)
                    }
                } else {
                    Toast.makeText(
                        this@ContributorListActivity,
                        "${response.message()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }
}
package com.example.contributorintruductionapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import okhttp3.*
import java.io.IOException

class ContributorListActivity<JSonObject> : AppCompatActivity() {
    private val client:OkHttpClient = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contributor_list)

        val url:String = "https://api.github.com/repos/googlesamples/android-architecture-components/contributors"
        val body: FormBody = FormBody.Builder().build()
        val handler = Handler(Looper.getMainLooper())
        val textView: TextView = findViewById<TextView>(R.id.textView)

        getContributorData(url, handler, textView)
    }

    private fun getContributorData (url: String, handler: Handler, textView: TextView) {
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                println("fail : $e")
            }

            override fun onResponse(call: Call, response: Response) {
                if(response.isSuccessful) {
                    response.body()?.let {
                        val responseText: String? = response.body()?.string()
                        handler.post() {
                            textView.text = responseText
                        }
                    }
                }
            }
        })
    }
}

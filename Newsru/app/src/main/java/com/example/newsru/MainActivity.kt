package com.example.newsru
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(){
    lateinit var mService: RetrofitService
    lateinit var layoutManager: LinearLayoutManager
  private lateinit var  adapter:Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.news)
        setContentView(R.layout.activity_main)


        val request = Retrofit.buildService(RetrofitService::class.java)
        val call = request.getDataList()

        call.enqueue(object : Callback<Result> {
            override fun onResponse(call: Call<Result>, response: Response<Result>) {

                updateViews(call, response)
                swipeRefresh!!.setOnRefreshListener {
                Handler().postDelayed({
                    swipeRefresh.isRefreshing = false;
                    updateViews(call, response)
                }, 1000)
                }
            }
         override fun onFailure(call: Call<Result>, t: Throwable) {
            }
        })
    }
    fun updateViews(call: Call<Result>, response: Response<Result>) {
        if(call.isExecuted)
        {
            if (response.isSuccessful) {
                recyclerView.apply {
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    adapter = Adapter(response.body()!!.articles)
                }
            }
                else
                {
                    Handler().postDelayed({
                        updateViews(call, response)
                    },1000)
                }

            }
        }

}



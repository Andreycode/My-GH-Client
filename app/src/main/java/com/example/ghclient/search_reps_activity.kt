package com.example.ghclient

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ghclient.models.GitModel
import com.grapesnberries.curllogger.CurlLoggerInterceptor
import kotlinx.android.synthetic.main.search_reps.*
import kotlinx.android.synthetic.main.welcomescreen.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class search_reps_activity : AppCompatActivity() {

    companion object {
        const val USER_NAME = "user_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val retrofit = getRetrofit()

        setContentView(R.layout.search_reps)
        getUserName()

        findButton.setOnClickListener{
            val keyword: String = searchField.getText().toString()
            SearchRepositories(keyword)

        }
    }

    fun getUserName() {
        val userName = intent.getStringExtra(USER_NAME)
        User.text = "Ваше имя: " + userName
    }

    fun SearchRepositories(keyword: String) {

        val retrofit = getRetrofit()
        val apiService = retrofit.create(searchGH::class.java)
        apiService.searchRepos(keyword).enqueue(object : Callback<GitModel> {
            override fun onResponse(
                call: Call<GitModel>,
                response: Response<GitModel>
            ) {
                showData(response.body()!!.items)
                Toast.makeText(applicationContext, "Request is succsess", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<GitModel>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(applicationContext, "Request error", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getRetrofit() = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addCallAdapterFactory(
            RxJava2CallAdapterFactory.create()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .client(
            OkHttpClient()
                .newBuilder()
                .addInterceptor(CurlLoggerInterceptor())
                .addInterceptor(HttpLoggingInterceptor { Log.i("NET", it) }
                    .setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
        )
        .build()

    private fun showData(repos: List<RepoModel>) {
        if (repos.isNotEmpty())
            repoList.apply {
                layoutManager = LinearLayoutManager(this@search_reps_activity)
                adapter = RecyclerAdapter(repos)
            } else {
            Toast.makeText(applicationContext, "Empty data from server", Toast.LENGTH_SHORT).show()
        }

    }




}

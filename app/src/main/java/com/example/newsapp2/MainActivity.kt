package com.example.newsapp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp2.adapters.RecyclerAdapter
import com.example.newsapp2.api.NewsApiJSON
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL = "https://newsapi.org"

class MainActivity : AppCompatActivity() {


    lateinit var countDownTimer: CountDownTimer
    lateinit var rv_recyclerView : RecyclerView
    lateinit var v_blackScreen : View
    lateinit var progressBar: ProgressBar


    private var titlesList = mutableListOf<String>()
    private var descList = mutableListOf<String>()
    private var imagesList = mutableListOf<String>()
    private var linksList = mutableListOf<String>()
    private var authorList = mutableListOf<String>()
    private var contentList = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        makeAPIRequest()

    }

    private fun fadeInFromBlack(){
        v_blackScreen.animate().apply {
            alpha(0f)
            duration = 2000
        }.start()
    }



    private fun setUpRecyclerView(){
        rv_recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        rv_recyclerView.adapter = RecyclerAdapter(titlesList,descList,imagesList,linksList,authorList,contentList)
    }

    private fun addToList(
        title: String,
        description: String,
        image: String,
        link: String,
        author: String,
        content: String
    ){
        contentList.add(content)
        authorList.add(author)
        titlesList.add(title)
        descList.add(description)
        imagesList.add(image)
        linksList.add(link)

    }



    private fun makeAPIRequest() {



        val api: APIRequest = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIRequest::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response : NewsApiJSON =api.getNews()
                for (article in response.articles) {
                    Log.i("MainActivity","Result= $article")
                    addToList(article.title,article.description,article.urlToImage,article.url,article.author,article.content)
                }
                withContext(Dispatchers.Main){
                    setUpRecyclerView()
                    fadeInFromBlack()
                    progressBar.visibility = View.GONE
                }


            }

            catch (e : Exception){
                Log.e("MainActivity",e.toString() )

                withContext(Dispatchers.Main){
                    attemptRequestAgain()
                }
            }
        }
    }

    private fun attemptRequestAgain() {
       countDownTimer = object : CountDownTimer(5*1000,1000){

           override fun onFinish() {
               makeAPIRequest()
               countDownTimer.cancel()

           }
           override fun onTick(millisUntilFinished: Long) {
               Log.i("MainActivity","Could not reterive data .... Trying again in ${millisUntilFinished/1000} seconds")
           }
       }
        countDownTimer.start()
    }
}

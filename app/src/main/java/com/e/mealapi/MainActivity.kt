package com.e.mealapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MealAdapter
    private var listOfMeals= mutableListOf<MealListResponse>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView=findViewById(R.id.recyclerview)
        recyclerView.layoutManager=LinearLayoutManager(this)
        adapter= MealAdapter()
        recyclerView.adapter=adapter
        getRetrofit()
        getListOfMeals()
        getService()


    }

    private fun getService() {
        startService(Intent(this,MyService::class.java))
    }

    private fun getListOfMeals() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ApiService::class.java).getMeal()
            val response = call.body()

            runOnUiThread {
                listOfMeals.clear()
                if (call.isSuccessful) {
                    response?.apply { listOfMeals=
                        this.categories as MutableList<MealListResponse>
                    }
                    adapter.submitList(listOfMeals)


                }

            }

        }

    }

    private fun getRetrofit():Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }


    companion object{
        val BASE_URL="https://www.themealdb.com/api/"
    }
}
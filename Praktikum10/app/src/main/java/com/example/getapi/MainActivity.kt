package com.example.getapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.getapi.Api.ApiConfig
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val morty = findViewById<RecyclerView>(R.id.rv_morty)

        ApiConfig.getService().getMorty().enqueue(object : Callback<ResponseJson>{
            override fun onResponse(call: Call<ResponseJson>, response: Response<ResponseJson>) {
                if (response.isSuccessful){
                    val responseMorty = response.body()
                    val dataMorty = responseMorty?.results
                    val mortyAdapter = MortyAdapter(dataMorty)
                    morty.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        setHasFixedSize(true)
                        mortyAdapter.notifyDataSetChanged()
                        adapter = mortyAdapter
                    }
                }
            }

            override fun onFailure(call: Call<ResponseJson>, t: Throwable) {
                Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        })

    }
}

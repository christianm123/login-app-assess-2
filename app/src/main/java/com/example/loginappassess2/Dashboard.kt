package com.example.loginappassess2

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import data.Entity
import data.EntityAdapter
import network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var entityAdapter: EntityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Get the keypass from the intent
        val keypass = intent.getStringExtra("fashion")

        // Fetch entities from the dashboard API using the keypass
        fetchEntities(keypass ?: "fashion")
    }

    private fun fetchEntities(keypass: String) {
        RetrofitClient.instance.getEntities("fashion $keypass").enqueue(object : Callback<List<Entity>> {
            override fun onResponse(call: Call<List<Entity>>, response: Response<List<Entity>>) {
                if (response.isSuccessful && response.body() != null) {
                    val entities = response.body()!!
                    entityAdapter = EntityAdapter(this@DashboardActivity, entities)
                    recyclerView.adapter = entityAdapter
                } else {
                    Toast.makeText(this@DashboardActivity, "Failed to load entities", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Entity>>, t: Throwable) {
                Log.e("DashboardActivity", "Error: ${t.message}")
                Toast.makeText(this@DashboardActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}


package com.example.covidmapper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), DataListener {

    private lateinit var covidAdapter: CovidAdapter
    private lateinit var progress: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val covidList = findViewById<RecyclerView>(R.id.rv_main)
        progress = findViewById(R.id.progress)
        covidAdapter = CovidAdapter()
        covidList.adapter = covidAdapter
        covidList.layoutManager = LinearLayoutManager(this)
        progress.visibility = View.VISIBLE

        DataParser(this).getCountriesData()
    }

    override fun onDataReceived(response: Response) {
        when(response){
            is Success -> {
                progress.visibility = View.GONE
                covidAdapter.setData(response.data)
            }
            is Failure -> {
                progress.visibility = View.GONE
                Toast.makeText(applicationContext, "Failed to fetch data!", Toast.LENGTH_SHORT).show()
            }
        }.exhaustiveCall
    }
}

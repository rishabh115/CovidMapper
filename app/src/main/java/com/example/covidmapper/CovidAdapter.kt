package com.example.covidmapper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CovidAdapter: RecyclerView.Adapter<CovidAdapter.VH>() {
    private var dataSet: List<CountryData>? = null

    class VH(val view: View): RecyclerView.ViewHolder(view){
        val tvName: TextView
        val tvCases: TextView
        val tvDeaths: TextView
        val tvRecovered: TextView
        val tvActive: TextView
        val tvSerious: TextView

        init {
            tvName = view.findViewById<TextView>(R.id.tv_name)
            tvCases = view.findViewById<TextView>(R.id.tv_cases)
            tvDeaths = view.findViewById(R.id.tv_deaths)
            tvRecovered = view.findViewById(R.id.tv_recovered)
            tvActive = view.findViewById(R.id.tv_active)
            tvSerious = view.findViewById(R.id.tv_serious)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_country, parent,
            false)
        return VH(view)
    }

    override fun getItemCount(): Int {
        if (dataSet == null)
            return 0;
        return dataSet!!.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
      val item = dataSet!![position]
      holder.tvName.text = item.country
      holder.tvCases.text = "${item.totalCases} (+${item.newCases})"
      holder.tvDeaths.text = "${item.totalDeaths} (+${item.newDeaths})"
      holder.tvRecovered.text = item.totalRecovered.toString()
      holder.tvActive.text = item.activeCases.toString()
      holder.tvSerious.text = item.criticalCases.toString()
    }

    fun setData(list: List<CountryData>){
      this.dataSet = list
      notifyDataSetChanged()
    }


}
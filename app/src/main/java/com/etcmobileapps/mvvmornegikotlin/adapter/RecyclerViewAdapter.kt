package com.etcmobileapps.mvvmornegikotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.etcmobileapps.mvvmornegikotlin.R
import com.etcmobileapps.mvvmornegikotlin.model.UniversityModel
import com.etcmobileapps.mvvmornegikotlin.model.UniversityModelElement


class RecyclerViewAdapter(val dataList: ArrayList<UniversityModelElement>): RecyclerView.Adapter<RecyclerViewAdapter.DataViewHolder> (){
    class DataViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_recylerview,parent,false)
        return DataViewHolder(view)

    }
    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.view.findViewById<TextView>(R.id.universityName).text = dataList[position].name
        holder.view.findViewById<TextView>(R.id.universityWebsite).text = dataList[position].domains!!.get(0).toString()
    }

    fun updateDataList(newDataList: List<UniversityModelElement>) {
        dataList.clear()
        dataList.addAll(newDataList)
        notifyDataSetChanged()
    }


}
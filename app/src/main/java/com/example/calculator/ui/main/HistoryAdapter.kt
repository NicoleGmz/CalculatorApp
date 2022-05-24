package com.example.calculator.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.calculator.R

class HistoryAdapter(private var presenter: MainPresenter) :
    RecyclerView.Adapter<HistoryViewHolder>(){

    fun updateData(){
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_layout, parent, false)

        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val historyRow = presenter.getHistoryRow(position)
        holder.bindData(historyRow)
    }

    override fun getItemCount(): Int = presenter.getItemCount()

}
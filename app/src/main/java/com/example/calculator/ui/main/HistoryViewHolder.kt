package com.example.calculator.ui.main

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.calculator.databinding.RowLayoutBinding

class HistoryViewHolder(mainView: View): RecyclerView.ViewHolder(mainView) {

    private val binding: RowLayoutBinding = RowLayoutBinding.bind(mainView)
    private val historyRow: TextView = binding.itemRow

    fun bindData(historyItem: String){
        historyRow.text = historyItem
    }
}
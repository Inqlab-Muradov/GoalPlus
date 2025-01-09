package com.example.goalplus.presentation.ui.matches

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.goalplus.databinding.TableItemBinding
import com.example.goalplus.domain.model.MatchesTableModel

class MatchesTableAdapter:RecyclerView.Adapter<MatchesTableAdapter.MatchesTableViewHolder>() {
    private val matchesTableList = ArrayList<MatchesTableModel>()

    inner class MatchesTableViewHolder(val binding:TableItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchesTableViewHolder {
        val view = TableItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  MatchesTableViewHolder(view)
    }

    override fun getItemCount(): Int {
        return matchesTableList.size
    }

    override fun onBindViewHolder(holder: MatchesTableViewHolder, position: Int) {
       val item= matchesTableList[position]
        holder.binding.tableItem = item
    }

    fun submitList(newList:List<MatchesTableModel>){
        matchesTableList.clear()
        matchesTableList.addAll(newList)
        notifyDataSetChanged()
    }
}
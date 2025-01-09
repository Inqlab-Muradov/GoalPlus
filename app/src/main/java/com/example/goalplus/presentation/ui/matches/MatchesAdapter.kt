package com.example.goalplus.presentation.ui.matches

import android.renderscript.ScriptGroup.Binding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.goalplus.databinding.MatchesItemBinding
import com.example.goalplus.domain.model.MatchesModel

class MatchesAdapter: ListAdapter<MatchesModel,MatchesAdapter.MatchesViewHolder>(MatchesDiffUtil()){

    inner class MatchesViewHolder(val binding:MatchesItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchesViewHolder {
        val view = MatchesItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MatchesViewHolder(view)
    }


    override fun onBindViewHolder(holder: MatchesViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.matchesItem = item
    }


    class MatchesDiffUtil : DiffUtil.ItemCallback<MatchesModel>() {
        override fun areItemsTheSame(oldItem: MatchesModel, newItem: MatchesModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MatchesModel, newItem: MatchesModel): Boolean {
            return oldItem == newItem
        }
    }

}
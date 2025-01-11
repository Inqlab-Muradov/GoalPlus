package com.example.goalplus.presentation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.goalplus.databinding.CompetitonsItemBinding
import com.example.goalplus.domain.model.CompetitionModel

class CompetitionsAdapter:RecyclerView.Adapter<CompetitionsAdapter.CompetitionsViewHolder>() {

    private val competitionsList= ArrayList<CompetitionModel>()
    lateinit var onClick :(String)->Unit


    inner class CompetitionsViewHolder(val binding:CompetitonsItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompetitionsViewHolder {
        val view = CompetitonsItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CompetitionsViewHolder(view)
    }

    override fun getItemCount(): Int {
       return  competitionsList.size
    }

    override fun onBindViewHolder(holder: CompetitionsViewHolder, position: Int) {
        val item = competitionsList[position]
        holder.binding.competitionItem = item
        holder.binding.compCard.setOnClickListener {
            item.code?.let{
                onClick(it)
            }
        }
    }

    fun submitList(newList:List<CompetitionModel>){
        competitionsList.clear()
        competitionsList.addAll(newList)
        notifyDataSetChanged()
    }
}
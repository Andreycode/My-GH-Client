package com.example.ghclient

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter (private val list: List<RepoModel>): RecyclerView.Adapter<RepositoriesViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoriesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return RepositoriesViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: RepositoriesViewHolder, position: Int) {
        val repository: RepoModel = list[position]
        holder.bind(repository)
    }

    override fun getItemCount(): Int = 24

}
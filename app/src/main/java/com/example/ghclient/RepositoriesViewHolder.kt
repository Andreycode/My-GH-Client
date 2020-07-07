package com.example.ghclient

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RepositoriesViewHolder(inflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder(inflater.inflate(R.layout.repositem, parent, false)) {
    var reponame: TextView? = null
    var repodecript: TextView? = null
    init {
        reponame = itemView.findViewById(R.id.repoName)
        repodecript = itemView.findViewById(R.id.repoDecript)
    }
    fun bind(repositories: RepoModel){
        reponame?.text = repositories.full_name
        repodecript?.text = repositories.description
    }
}
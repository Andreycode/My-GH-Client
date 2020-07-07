package com.example.ghclient

import com.google.gson.annotations.SerializedName

data class RepoModel(
    val full_name: String,
    val description: String) {}
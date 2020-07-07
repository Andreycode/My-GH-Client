package com.example.ghclient

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.welcomescreen.*

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcomescreen)
        nextButton.setOnClickListener {
            val randomIntent = Intent(this,search_reps_activity::class.java)
            val ourUserName = userName.text.toString()
            if(ourUserName != null && ourUserName != "") {
                randomIntent.putExtra(search_reps_activity.USER_NAME, ourUserName)
                startActivity(randomIntent)
            } else {
                Toast.makeText(applicationContext, "Имя обязательно", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
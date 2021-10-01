package com.example.newsapp2

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import java.util.stream.Stream

class Filter : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

         val apply : Button = findViewById(R.id.apply)
        val clear :Button = findViewById(R.id.clear)
        val checkBoxAsc : CheckBox = findViewById(R.id.checkBoxAsc)
        val checkBoxDesc : CheckBox= findViewById(R.id.checkBoxDsc)


        apply.setOnClickListener {
            if(checkBoxDesc.isChecked){
                val intent = Intent(Intent.ACTION_VIEW)
                val view = layoutInflater.inflate(R.layout.activity_main,parent,false)
               val filter= arrayListOf<String>()
                filter.filterIsInstance<String>().sortedDescending()
                }
                else {
                    val intent = Intent(Intent.ACTION_VIEW)
                    val view = layoutInflater.inflate(R.layout.activity_main,parent,false)
                    val filter= arrayListOf<String>()
                    filter.filterIsInstance<String>().sorted()
                }
            clear.setOnClickListener {
                val view = layoutInflater.inflate(R.layout.activity_main,parent,false)
                view.clear()
            }

            }
        }
    }

private fun Unit.clear() {

}


private fun LayoutInflater.inflate(activityMain: Int, parent: Activity?, b: Boolean) {

}

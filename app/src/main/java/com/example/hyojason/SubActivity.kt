package com.example.hyojason

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

class SubActivity : AppCompatActivity() {

    var peopleList = arrayListOf<People>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        val peopleAdapter = MainListAdapter(this, peopleList)
        mainListView.adapter = peopleAdapter
    }

}
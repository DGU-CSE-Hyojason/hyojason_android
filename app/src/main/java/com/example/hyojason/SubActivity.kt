package com.example.hyojason

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter;
import android.widget.Button
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import android.view.View
import android.content.Intent
import android.widget.ImageButton

class SubActivity : AppCompatActivity() {

    var peopleList = arrayListOf<People>(
        People("이우제", "24세", "코딩"),
        People("김태형", "24세", "커피"),
        People("이하늘", "25세", "탈색"),
        People("정규용", "23세", "컴파일러공부")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)
        val mainListView: ListView = findViewById(R.id.mainListView)

        val peopleAdapter = MainListAdapter(this, peopleList)
        mainListView.adapter = peopleAdapter

        // 홈으로 돌아가기 버튼
        val transitionButton: ImageButton = findViewById(R.id.backButton)
        transitionButton.setOnClickListener {
            val intent = Intent(this@SubActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }

}
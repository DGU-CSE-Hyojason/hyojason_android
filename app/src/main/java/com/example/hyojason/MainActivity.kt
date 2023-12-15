package com.example.hyojason

import android.content.ActivityNotFoundException
import android.content.Intent
import android.speech.RecognizerIntent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.extensions.jsonBody

class MainActivity : AppCompatActivity() {

    private val SPEECH_REQUEST_CODE = 123
    private var recognizedText: String = "No speech input"
    private var responseText: String = "No response"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // move to subactivity
        val movePageButton: Button = findViewById(R.id.movePageButton)
        movePageButton.setOnClickListener {
            val intent = Intent(this, SubActivity::class.java)
            startActivity(intent)
        }

        // use keyboard
        val keyBoardButton: Button = findViewById(R.id.keyboardButton)
        keyBoardButton.setOnClickListener {

        }

        // use record
        val recordButton: Button = findViewById(R.id.recordButton)
        recordButton.setOnClickListener {
            startSpeechRecognition()
        }
    }

    private fun startSpeechRecognition() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak something")

        try {
            startActivityForResult(intent, SPEECH_REQUEST_CODE)
        } catch (e: ActivityNotFoundException) {
            // Handle the exception
        }
    }

    private fun callApiWithRecognizedText(requestStr:String) {
        // 여기에서 API를 호출하고 recognizedText를 사용하세요.
        // 예시: MyApiService.callApi(recognizedText)
        callApiGet(requestStr)
    }
    private fun callApiGet(requestStr: String) {

        val url = "https://09c7-210-94-220-228.ngrok-free.app/api/chatbot/reply"

        // 전송할 JSON 데이터
        val jsonData = """
        {
            "question": "$requestStr"
        }
    """.trimIndent()

        Fuel.post(url)
            .header("Content-Type" to "application/json")
            .jsonBody(jsonData)
            .response { request, response, result ->
                val (data, error) = result
                if (error == null) {
                    val outputText: TextView = findViewById(R.id.outputText)
                    outputText.text = data?.let { String(it) }
                    println("Response data: $data")
                } else {
                    println("Error: $error")
                    val outputText: TextView = findViewById(R.id.outputText)
                }
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SPEECH_REQUEST_CODE) {
            if (resultCode == RESULT_OK && data != null) {
                val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                recognizedText = result?.get(0) ?: "No speech input"

                // Handle the recognized text (e.g., update UI)
                val inputText: TextView = findViewById(R.id.inputText)
                inputText.text = recognizedText

                // Call API with the updated recognizedText
                callApiWithRecognizedText(recognizedText)
            }
        }
    }
}
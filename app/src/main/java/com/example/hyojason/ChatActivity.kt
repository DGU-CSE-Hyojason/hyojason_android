package com.example.hyojason

import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.marginBottom
import androidx.core.view.marginTop
import androidx.core.view.setMargins
import androidx.core.view.setPadding

class ChatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat) // ChatActivity의 레이아웃 파일을 설정

        // 더미 대화 데이터 추가
        addMessage("안녕하세요!", true)
        addMessage("안녕하세요! 반갑습니다.", false)
        addMessage("오늘 날씨 어때요?", true)
        addMessage("맑고 좋아요!", false)
        addMessage("뭐 해요?", true)
        addMessage("일하고 있어요.", false)
        addMessage("여행을 좋아하시나요?", true)
        addMessage("네, 여행을 좋아해요. 새로운 곳을 방문하는 건 항상 즐거워요.", false)
        addMessage("가장 기억에 남는 여행지가 어디에요?", true)
        addMessage("제일 기억에 남는 여행은 유럽 일주였어요. 다양한 문화를 경험할 수 있어서 좋았어요.", false)
        addMessage("음식도 좋아하시나요?", true)
        addMessage("네, 음식도 좋아해요. 특히 현지 음식을 먹는 걸 좋아해요.", false)
        // 추가적인 가상의 대화
    }

    private fun addMessage(message: String, isUser: Boolean) {
        val chatContainer: LinearLayout = findViewById(R.id.chatContainer)
        val chatText = TextView(this)
        chatText.text = message
        chatText.setPadding(32, 32, 32, 32)
        chatText.maxWidth = (resources.displayMetrics.widthPixels * 0.7).toInt()

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(16)

        if (isUser) {
            params.gravity = Gravity.START
            chatText.setBackgroundResource(R.drawable.bubble_right)
        } else {
            params.gravity = Gravity.END
            chatText.setBackgroundResource(R.drawable.bubble_left)
        }

        chatText.layoutParams = params
        chatContainer.addView(chatText)
    }
}
package com.example.assignment1

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private var pref: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pref = getSharedPreferences("user_info", MODE_PRIVATE)
        val id = pref?.getString("id", "")
        val password = pref?.getString("password","")
        Log.e("TAG", "쉐어드에 저장된 아이디 = " + pref?.getString("id", ""))
        Log.e("TAG", "쉐어드에 저장된 비밀번호 = " + pref?.getString("password", ""))

        var signInButton : Button = findViewById(R.id.signIn)

        signInButton.setOnClickListener{
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        var noLogInButton: Button = findViewById(R.id.noLogIn)

        noLogInButton.setOnClickListener {
            val intent = Intent(this, ProductActivity::class.java)
            startActivity(intent)
        }
    }
}
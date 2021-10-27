package com.example.assignment1

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

internal var isMember = false
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

        val logInButton : Button = findViewById(R.id.logIn)
        val idTextView : TextView = findViewById(R.id.memberID)
        val pwTextView : TextView = findViewById(R.id.memberPassword)

        logInButton.setOnClickListener{
            val idInput = idTextView.text.toString()
            val pwInput = pwTextView.text.toString()
            if(idInput == id && pwInput == password){
                isMember = true
                val intent = Intent(this, ProductActivity::class.java)
                startActivity(intent)
            } else{
                Toast.makeText(this,"아이디 또는 비밀번호가 잘못되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        val signInButton : Button = findViewById(R.id.signIn)

        signInButton.setOnClickListener{
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        val noLogInButton: Button = findViewById(R.id.noLogIn)

        noLogInButton.setOnClickListener {
            isMember = false
            val intent = Intent(this, ProductActivity::class.java)
            startActivity(intent)
        }
    }
}
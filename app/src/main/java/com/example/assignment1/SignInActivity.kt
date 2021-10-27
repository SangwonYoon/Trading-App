package com.example.assignment1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.SharedPreferences
import android.widget.*


class SignInActivity : AppCompatActivity(){
    private var pref: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        pref = getSharedPreferences("user_info", MODE_PRIVATE)

        val radioGroup : RadioGroup = findViewById(R.id.radio_group)
        val submitButton : Button = findViewById(R.id.submit)
        submitButton.setOnClickListener{
            val checkedRadio = radioGroup.checkedRadioButtonId
            val radioButton : RadioButton = findViewById(checkedRadio)
            if(radioButton.text.toString() == "동의함"){
                val editor = pref?.edit()

                val id : TextView = findViewById(R.id.ID)
                val password : TextView = findViewById(R.id.password)
                val name : TextView = findViewById(R.id.name)
                val phoneNum : TextView = findViewById(R.id.phonenum)
                val address : TextView = findViewById(R.id.address)

                val idInput = id.text.toString()
                editor?.putString("id",idInput)

                val passwordInput = password.text.toString()
                editor?.putString("password",passwordInput)

                val nameInput = name.text.toString()
                editor?.putString("name",nameInput)

                val phoneNumInput = phoneNum.text.toString()
                editor?.putString("phoneNum",phoneNumInput)

                val addressInput = address.text.toString()
                editor?.putString("address",addressInput)

                editor?.apply()
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            } else{
                Toast.makeText(this,"개인 정보 사용에 동의하셔야 회원 가입이 가능합니다.", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
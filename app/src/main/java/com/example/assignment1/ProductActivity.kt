package com.example.assignment1

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class ProductActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        val infoButton : Button = findViewById(R.id.info)
        infoButton.setOnClickListener{
            if(isMember){
                val pref = getSharedPreferences("user_info", MODE_PRIVATE)
                val id = pref.getString("id","")
                val password = pref.getString("password","")
                val name = pref.getString("name","")
                val phoneNum = pref.getString("phoneNum","")
                val address = pref.getString("address","")

                val builder = AlertDialog.Builder(this)
                builder.setTitle("회원 정보")
                builder.setMessage(" 아이디 : ${id}\n 비밀번호 : ${password}\n 이름 : ${name}\n 전화번호 : ${phoneNum}\n 주소 : ${address}")
                builder.setPositiveButton(
                    "확인",
                    {dialogInterface: DialogInterface?, i:Int ->})
                builder.setCancelable(false)
                builder.show()
            } else{
                val builder = AlertDialog.Builder(this)
                builder.setTitle("비회원 상태입니다.")
                builder.setMessage("회원가입 하시겠습니까?")
                builder.setPositiveButton(
                    "네",
                    {dialogInterface: DialogInterface?, i:Int ->
                        Toast.makeText(this, "회원가입 창으로 이동합니다.", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this,SignInActivity::class.java)
                        startActivity(intent)
                    })
                builder.setNegativeButton(
                    "아니오",
                    {dialogInterface: DialogInterface?, i:Int ->}
                )
                builder.setCancelable(false)
                builder.show()
            }
        }
    }
}
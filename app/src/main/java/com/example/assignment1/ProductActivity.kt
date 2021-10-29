package com.example.assignment1

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProductActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        val productList = arrayListOf(
            Products(R.drawable.cuckoo,"쿠쿠 압력 밥솥"),
            Products(R.drawable.ps, "플레이스테이션 4"),
            Products(R.drawable.shelf, "철제 선반"),
            Products(R.drawable.shoes, "아디다스 운동화"),
            Products(R.drawable.tv, "42인치 UHD TV")
        )

        val rv_product : RecyclerView = findViewById(R.id.rv_product)

        rv_product.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_product.setHasFixedSize(true)

        rv_product.adapter = ProductAdapter(productList)

        val addButton : Button = findViewById(R.id.add)
        addButton.setOnClickListener{
            var image : Int
            var name : String = ""



            val alert : AlertDialog.Builder = AlertDialog.Builder(this)
            alert.setTitle("등록할 상품명을 적어주세요")

            val productName : EditText = EditText(this)
            alert.setView(productName)

            alert.setPositiveButton("확인",
                {dialogInterface: DialogInterface?, i:Int ->
                    name = productName.text.toString()
                })

            alert.setCancelable(false)
            alert.show()

            val item = Products(image,name)
            productList.add(item)
        }

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
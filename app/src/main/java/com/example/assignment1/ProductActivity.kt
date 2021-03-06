package com.example.assignment1

import android.Manifest
import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.*
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray

class ProductActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        val productList = arrayListOf(
            Products(Uri.parse("android.resource://com.example.assignment1/drawable/cuckoo"),"쿠쿠 압력 밥솥"),
            Products(Uri.parse("android.resource://com.example.assignment1/drawable/ps"), "플레이스테이션 4"),
            Products(Uri.parse("android.resource://com.example.assignment1/drawable/shelf"), "철제 선반"),
            Products(Uri.parse("android.resource://com.example.assignment1/drawable/shoes"), "아디다스 운동화"),
            Products(Uri.parse("android.resource://com.example.assignment1/drawable/tv"), "42인치 UHD TV")
        )

        var image : Uri = Uri.parse("")

        /*
        var pref = getSharedPreferences("productList", MODE_PRIVATE)
        val json = pref?.getString("list",null)
        val productList = ArrayList<Products>()
        if(json != null){
            try{
                val a = JSONArray(json)
                for(i in 0 until a.length())
            }
        }
        */

        val rv_product : RecyclerView = findViewById(R.id.rv_product)

        rv_product.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_product.setHasFixedSize(true)

        val mAdapter = ProductAdapter(productList)
        rv_product.adapter = mAdapter

        val resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
                result ->
            if(result.resultCode == Activity.RESULT_OK){
                val myData : Intent? = result.data
                val uri = myData?.data
                image = uri!!

                var name : String
                val alert : AlertDialog.Builder = AlertDialog.Builder(this)
                alert.setTitle("등록할 상품명을 적어주세요")

                val productName : EditText = EditText(this)
                alert.setView(productName)

                alert.setPositiveButton("등록",
                    {dialogInterface: DialogInterface?, i:Int ->
                        name = productName.text.toString()
                        val item = Products(image,name)
                        //Log.e("Tag", image.toString())
                        //Log.e("Tag", name)
                        mAdapter.addItem(item)
                    })

                alert.setCancelable(false)
                alert.show()
            }
        }

        val addButton : Button = findViewById(R.id.add)
        addButton.setOnClickListener{
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                val intent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                resultLauncher.launch(intent)
            } else{
                ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),0)
            }
        }

        val deleteButton : ToggleButton = findViewById(R.id.delete)
        deleteButton.setOnClickListener{
            if(deleteButton.isChecked) {
                Toast.makeText(this, "삭제하고자 하는 물품을 클릭해주세요", Toast.LENGTH_SHORT).show()
                val mAdapter = ProductDeleteAdapter(productList)
                rv_product.adapter = mAdapter
            } else{
                val mAdapter = ProductAdapter(productList)
                rv_product.adapter = mAdapter
            }
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

    /*
    fun checkPermission(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            //갤러리 앱에 접근
        } else{
        ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),0)
        }
    }
     */

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode){
            0 -> {
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"접근 권한이 허용되었습니다! 상품 등록 버튼을 다시 눌러주세요",Toast.LENGTH_SHORT).show()
                } else{
                    Toast.makeText(this,"권한을 승인하지 않으면 갤러리에 접근할 수 없습니다.\n 2회 이상 거절하여 접근 권한 허용창이 뜨지 않는 경우 어플리케이션 설정에서 권한을 허용해주셔야 합니다.", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
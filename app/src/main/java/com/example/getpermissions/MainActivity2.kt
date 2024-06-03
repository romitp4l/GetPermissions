package com.example.getpermissions

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {

    private val REQUEST_CALL_PERMISSION = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_main2)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        val editText2 : EditText = findViewById(R.id.editTextNumber2)
        val button : Button = findViewById(R.id.button2)
        button.setOnClickListener{
            val phoneNumber = editText2.text.toString()
            if(phoneNumber.isNotEmpty()){
                makeCall(phoneNumber)
            }
        }

    }



    private fun makeCall(number: String) {
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE)) != PackageManager.PERMISSION_GRANTED{
            androidx.core.app.ActivityCompat.requestPermissions((this,arrayOf(Manifest.permission.CALL_PERMISSION),REQUEST_CALL_PERMISSION))
        }  else{
            callPhoneNumber(number)
        }


    }
    private fun callPhoneNumber(number: String) {
        val uri : Uri = Uri.parse("tel:$number")
        val intent = Intent(Intent.ACTION_CALL,uri)
        startActivity(intent)


    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CALL_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val editText: EditText = findViewById(R.id.editTextNumber)
                val phoneNumber = editText.text.toString()
                if (phoneNumber.isNotEmpty()) {
                    callPhoneNumber(phoneNumber)
                }
            } else {
                // Permission denied, show a message to the user
            }
        }
    }





}̵
package com.example.dbconnection

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    lateinit var database:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        val message=findViewById<TextInputEditText>(R.id.message)
        val to=findViewById<TextInputEditText>(R.id.to)
        val from=findViewById<TextInputEditText>(R.id.from)
        val but=findViewById<Button>(R.id.button)
        but.setOnClickListener {
            val m=message.text.toString()
            val t=to.text.toString()
            val f=from.text.toString()
            val user=User(m,t,f)
            database=FirebaseDatabase.getInstance().getReference("Users")
            database.child(f).setValue(user).addOnSuccessListener {
                message.text?.clear()
                to.text?.clear()
                from.text?.clear()
                Toast.makeText(this,"Message Sent",Toast.LENGTH_SHORT).show()
            }
        }
    }
}
package com.benny.firebaseapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputName: EditText = findViewById(R.id.inputName)
        val inputAge: EditText = findViewById(R.id.inputAge)
        val inputEmail: EditText = findViewById(R.id.inputEmailAddress)
        val buttonSave: Button = findViewById(R.id.buttonSave)
        val buttonFetch: Button = findViewById(R.id.buttonFetch)
        val txtRealTime: TextView = findViewById(R.id.txtViewRealTime)

        val database = Firebase.database

        //Creating a Table-like Structure
        val refUsers = database.getReference("Users")

        //Saving Data
       // refUsers.setValue("Hinn")

        buttonSave.setOnClickListener {
            val name = inputName.text.toString().trim()
            val age = inputAge.text.toString().trim().toIntOrNull()
            val email = inputEmail.text.toString()

            if (name.isNotEmpty() && age != null && email.isNotEmpty()){
                //Save
                val user = User(name, age, email)
                refUsers.push().setValue(user)//Saving
            }
        }

        buttonFetch.setOnClickListener {
            refUsers.get().addOnSuccessListener {
                val children = it.children
                for (child in children){
                    Log.d("USER_CHILD", "OnCreate: $child")
                }
            }

            val intent = Intent(this, UserActivity::class.java)
            startActivity(intent)
        }

        val realTime= database.getReference("RealTime")
        realTime.setValue("Benny")

        realTime.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                txtRealTime.text = snapshot.getValue(String::class.java)
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }
}


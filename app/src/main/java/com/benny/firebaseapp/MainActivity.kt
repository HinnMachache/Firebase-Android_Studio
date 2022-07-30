package com.benny.firebaseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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

    }
}

data class User(val name: String = "", val age: Int = 0, val email: String = "")
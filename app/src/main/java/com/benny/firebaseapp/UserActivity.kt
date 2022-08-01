package com.benny.firebaseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class UserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        val recyclerUsers: RecyclerView = findViewById(R.id.recyclerUser)

        val database = Firebase.database
        val userRef = database.getReference("Users")

        val userList = ArrayList<User>()

        val layoutManager = LinearLayoutManager(this)
        recyclerUsers.layoutManager = layoutManager

        val divider = DividerItemDecoration(this, layoutManager.orientation)
        recyclerUsers.addItemDecoration(divider)

        val adapter = CustomAdapter(userList)
        recyclerUsers.adapter = adapter

        //Listener to UserRef to fetch data in realtime
        userRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                userList.clear()
                for (child in snapshot.children) {
                    val person = child.getValue(User::class.java)
                    userList.add(person!!)
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}
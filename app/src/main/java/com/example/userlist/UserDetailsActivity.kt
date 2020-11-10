package com.example.userlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_user_details.*

class UserDetailsActivity : AppCompatActivity() {

    var user: UserDetails? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        if(intent != null && intent.hasExtra("user")){
            user = intent.getSerializableExtra("user") as UserDetails
            name_textView.text = user?.name
        }
    }
}
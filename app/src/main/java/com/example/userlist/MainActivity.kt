package com.example.userlist

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), UserListInterface {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    val userOne = UserDetails(
        "Christer Muntean",
        "https://cdn.business2community.com/wp-content/uploads/2014/04/profile-picture-300x300.jpg",
        "chri@gmail.com",
        "91919191"
    )

    val userTwo = UserDetails(
        "Kristian Maister",
        "https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
        "krist@gmail.com",
        "41914191"
    )

    val users = listOf(userOne, userTwo, userOne, userTwo)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewManager = LinearLayoutManager(this)

        viewAdapter = MyAdapter(users, this)

        recyclerView = userDetails_recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    private fun navigate(userDetails: UserDetails) {
        val intent = Intent(this, UserDetailsActivity::class.java).apply {
            putExtra("user", userDetails)
        }
        startActivity(intent)
    }

    override fun onUserClicked(userDetails: UserDetails) {
        navigate(userDetails)
    }

    override fun onCallClicked(tel: String) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", tel, null))
        startActivity(intent)
    }
}
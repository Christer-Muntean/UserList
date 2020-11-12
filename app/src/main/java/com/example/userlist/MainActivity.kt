package com.example.userlist

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
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

    val users = mutableListOf(userOne, userTwo, userOne, userTwo)

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

    override fun onStart() {
        super.onStart()

        fab.setOnClickListener {
            navigateToAddUser()
        }

        settings_button.setOnClickListener {
            navigateToSettings()
        }
    }

    override fun onResume() {
        super.onResume()
        setAppSettingColor()
        getUsersInSharedPref()
    }

    private fun getUsersInSharedPref(){
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(
            applicationContext
        )
        var sharedPrefUsers = sharedPref.getStringSet("list_users", setOf())

        val gson = Gson()

        sharedPrefUsers = sharedPrefUsers?.reversed()?.toMutableSet()

        sharedPrefUsers?.let {
            for (user in it) {
                val user = gson.fromJson(user, UserDetails::class.java)

                var doesUserExist = false
                for (userInList in users){
                    if(userInList.email == user.email){
                        doesUserExist = true
                    }
                }

                if(doesUserExist == false){
                    users.add(user)
                    viewAdapter.notifyDataSetChanged()
                }
            }
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

    private fun navigateToAddUser() {
        val intent = Intent(this, AddUserActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToSettings() {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

    private fun setAppSettingColor() {
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(
            applicationContext
        )

        var settingsColor = sharedPref.getString("color_setting", "")

        if (settingsColor == "dark") {
            mainContent.setBackgroundColor(Color.BLACK)
        }
        else if (settingsColor == "light") {
            mainContent.setBackgroundColor(Color.WHITE)
        }
    }
}
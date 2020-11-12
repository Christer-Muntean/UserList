package com.example.userlist

import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_add_user.*


class AddUserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)
    }

    override fun onStart() {
        super.onStart()

        add_user_button.setOnClickListener {
            val name = name_editText.text.toString()
            val img = image_url_editText.text.toString()
            val email = email_editText.text.toString()
            val tel = tel_editText.text.toString()

            val user = UserDetails(name, img, email, tel)
            val gson = Gson()
            val json = gson.toJson(user)

            addUser(json)
        }
    }

    /*private fun addUser(userJson: String) {
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(
            applicationContext
        )
        val editor = sharedPref.edit()

        editor.putString("pref_user", userJson)
        editor.commit()
        onBackPressed()
    }*/

    private fun addUser(userJson: String) {
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(
                applicationContext
        )
        val editor = sharedPref.edit()


        //Get users from sharedPref
        val users = sharedPref.getStringSet("list_users", setOf())?.toMutableList()

        users?.add(userJson)

        //Add new users list to shared pref
        editor.putStringSet("list_users", users?.toSet())
        editor.commit()

        onBackPressed()
    }
}
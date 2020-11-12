package com.example.userlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        set_dark_mode_button.setOnClickListener {
            setDarkMode()
        }

        set_light_mode_button.setOnClickListener {
            setLightMode()
        }
    }

    private fun setDarkMode(){
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(
            applicationContext
        )
        val editor = sharedPref.edit()

        editor.putString("color_setting", "dark")
        editor.commit()
        onBackPressed()
    }

    private fun setLightMode(){
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(
            applicationContext
        )
        val editor = sharedPref.edit()

        editor.putString("color_setting", "light")
        editor.commit()
        onBackPressed()
    }
}
package com.example.userlist

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_user_details.*


class UserDetailsActivity : AppCompatActivity() {

    var user: UserDetails? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        if(intent != null && intent.hasExtra("user")){
            user = intent.getSerializableExtra("user") as UserDetails

            Picasso.get().load(user?.imageUrl).into(profile_imageView)
            name_textView.text = user?.name
        }
    }

    override fun onStart() {
        super.onStart()

        sendEmail_button.setOnClickListener {
            animateAlpha(email_layout)
            sendEmail()
        }

        call_button.setOnClickListener {
            animateAlpha(tel_layout)

            val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", user?.tel, null))
            startActivity(intent)
        }
    }

    private fun animateAlpha(view: View) {
        val animation1 = AlphaAnimation(0.1f, 1.0f)
        animation1.duration = 500
        view.startAnimation(animation1)
    }

    private fun sendEmail() {
        val intentMail = Intent(Intent.ACTION_SEND)
        intentMail.type = "message/rfc822"
        intentMail.putExtra(Intent.EXTRA_EMAIL, arrayOf(
                user?.email)) // the To mail.

        intentMail.putExtra(Intent.EXTRA_SUBJECT, "Subject goes here")
        intentMail.putExtra(Intent.EXTRA_TEXT, "Content goes here")

        try {
            startActivity(Intent.createChooser(intentMail, "Message to User to do what next"))
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show()
        }
    }
}
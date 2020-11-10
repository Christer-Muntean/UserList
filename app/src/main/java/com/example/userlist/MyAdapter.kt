package com.example.userlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MyAdapter(private val myDataset: List<UserDetails>, val userListInterface: UserListInterface) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(val userListItem: View) : RecyclerView.ViewHolder(userListItem)


    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyAdapter.MyViewHolder {
        val userListItem = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_list_item, parent, false) as View

        return MyViewHolder(userListItem)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val user = myDataset[position]

        val userListItem = holder.userListItem
        val userTextView = userListItem.findViewById<TextView>(R.id.name_textView)
        val userImageView = userListItem.findViewById<ImageView>(R.id.profile_imageView)
        val callButton = userListItem.findViewById<ImageButton>(R.id.call_button)

        userTextView.text = user.name
        Picasso.get().load(user.imageUrl).into(userImageView)

        userListItem.setOnClickListener {
            userListInterface.onUserClicked(user)
        }

        callButton.setOnClickListener {
            userListInterface.onCallClicked(user.tel)
        }
    }

    override fun getItemCount() = myDataset.size
}
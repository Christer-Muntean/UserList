package com.example.userlist

import java.io.Serializable

class UserDetails(val name: String, val imageUrl: String, val email: String, val tel: String) : Serializable {
}
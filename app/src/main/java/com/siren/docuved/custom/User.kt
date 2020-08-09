package com.siren.docuved.custom

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class User{

    var auth: FirebaseAuth = Firebase.auth

    fun getUserData(): FirebaseUser?  = auth.currentUser

    fun removeUserData(){

        auth.signOut()
    }
}
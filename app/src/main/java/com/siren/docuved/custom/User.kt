package com.ita.e_laundry.custom

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class User{

    var auth: FirebaseAuth = Firebase.auth

    fun getUserData(): FirebaseUser?  = auth.currentUser

//    fun setUserData(email: String, password: String){
//
//        auth.createUserWithEmailAndPassword(email, password)
//            .addOnCompleteListener(activity) { task ->
//                if (task.isSuccessful) {
//                    // Sign in success, update UI with the signed-in user's information
//                    Log.d("ben", "createUserWithEmail:success")
//                    val user = auth.currentUser
//                } else
//                    Toast.makeText(activity, "Authentication failed.", Toast.LENGTH_SHORT).show()
//            }
//    }

    fun removeUserData(){

        auth.signOut()
    }
}
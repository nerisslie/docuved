package com.siren.docuved.base

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.ita.e_laundry.custom.Client
import com.ita.e_laundry.custom.User
import dmax.dialog.SpotsDialog

abstract class BaseActivity: AppCompatActivity() {

    var firebaseRef = FirebaseFirestore.getInstance()

    lateinit var client: Client
    lateinit var user: User
    lateinit var mProgressDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        client              = Client(this)
        user                = User()

        mProgressDialog     = SpotsDialog.Builder()
            .setContext(this)
            .setMessage("Loading...")
            .setCancelable(true)
            .build()
    }

    fun showMessage(message: String){

        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
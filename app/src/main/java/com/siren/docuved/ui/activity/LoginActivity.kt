package com.siren.docuved.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuthException
import com.siren.docuved.R
import com.siren.docuved.base.BaseActivity
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

@SuppressLint("Registered")
class LoginActivity : BaseActivity() {

    private lateinit var mEmail: EditText
    private lateinit var mPassword: EditText

    private lateinit var btnLogin: Button
    private lateinit var btnHelp: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mEmail              = findViewById(R.id.email)
        mPassword           = findViewById(R.id.password)
        btnLogin            = findViewById(R.id.btn_login)
        btnHelp             = findViewById(R.id. btn_help)

        btnLogin.setOnClickListener {

            validateUser()
        }
    }

    public override fun onStart() {
        super.onStart()

        if(user.getUserData() != null)
            goToHome()
    }

    private fun validateUser(){

        when{
            isEmpty(mEmail)         ->  mEmail.error        = "Email is required"
            isEmpty(mPassword)      ->  mPassword.error     = "Password is required"
            client.isOnline()       ->  processLogin()
            else                    ->  showMessage("Tidak ada koneksi internet")
        }
    }

    private fun processLogin(){

        mProgressDialog.show()

        user.auth.signInWithEmailAndPassword(mEmail.text.toString(), mPassword.text.toString())
            .addOnSuccessListener {

                goToHome()
            }
            .addOnFailureListener {
                if(it is FirebaseAuthException)
                    showMessage(it.message.toString())
                else
                    showMessage("Authentication Failed")
                mProgressDialog.dismiss()
            }
    }

    private fun goToHome(){

        showMessage("Success Login")

        Intent(applicationContext, DashboardSiswa::class.java).run {
            startActivity(this)
            finish()
        }
    }

    private fun isEmpty(inputText: EditText)    = TextUtils.isEmpty(inputText.text.toString())

    fun md5(s: String): String? {
        try {
            // Create MD5 Hash
            val digest = MessageDigest.getInstance("MD5")
            digest.update(s.toByteArray())
            val messageDigest = digest.digest()

            // Create Hex String
            val hexString = StringBuffer()
            for (i in messageDigest.indices) hexString.append(
                Integer.toHexString(
                    0xFF and messageDigest[i].toInt()
                )
            )
            return hexString.toString()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return ""
    }
}
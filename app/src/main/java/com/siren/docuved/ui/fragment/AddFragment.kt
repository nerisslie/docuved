package com.siren.docuved.ui.fragment

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore
import com.ita.e_laundry.custom.Client
import com.ita.e_laundry.custom.User
import com.siren.docuved.R
import dmax.dialog.SpotsDialog
import android.widget.ArrayAdapter as ArrayAdapter

class AddFragment : Fragment() {

//    private lateinit var user: User
//    private lateinit var client: Client
//
//    private lateinit var mProgressDialog: AlertDialog
//
//    private lateinit var spinner: Spinner
//    private lateinit var spinnerJurusan: Spinner
//
//    private lateinit var spinnerAdapter: SpinnerAdapter
//    private lateinit var spinnerJurusanAdapter: SpinnerAdapter
//
//    private lateinit var mNisn: EditText
//    private lateinit var mNama: EditText
//    private lateinit var mEmail: EditText
//    private lateinit var mPassword: EditText
//
//    private lateinit var btnSave: Button
//
//    private var firebaseRef = FirebaseFirestore.getInstance()
//
//    private val tahunArray = arrayOf(
//        "2017",
//        "2018",
//        "2019",
//        "2020"
//    )
//    private val jurusanArray = arrayOf(
//        "IPA",
//        "IPS"
//    )
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val root                = inflater.inflate(R.layout.fragment_add, container, false)
//
//        spinner                 = root.findViewById(R.id.tahun)
//        spinnerJurusan          = root.findViewById(R.id.jurusan)
//        mNisn                   = root.findViewById(R.id.nisn)
//        mNama                   = root.findViewById(R.id.nama)
//        mEmail                  = root.findViewById(R.id.email)
//        mPassword               = root.findViewById(R.id.password)
//        btnSave                 = root.findViewById(R.id.btn_save)
//
//        spinner.adapter         = spinnerAdapter
//        spinnerJurusan.adapter  = spinnerJurusanAdapter
//
//        spinner.dropDownVerticalOffset
//        spinnerJurusan.dropDownVerticalOffset
//
//        btnSave.setOnClickListener {
//
//            validateRegister()
//        }
//
//        return root
//    }
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//
//        user                    = User()
//        client                  = Client(context)
//
//        mProgressDialog         = SpotsDialog.Builder()
//            .setContext(context)
//            .setMessage("Loading...")
//            .setCancelable(true)
//            .build()
//
//        spinnerAdapter          = ArrayAdapter(
//            context,
//            R.layout.spinner,
//            tahunArray
//        )
//
//        spinnerJurusanAdapter   = ArrayAdapter(
//            context,
//            R.layout.spinner,
//            jurusanArray
//        )
//    }
//
//    private fun validateRegister(){
//
//        when{
//            isEmpty(mNama)          ->  mNama.error         = "Nama siswa dibutuhkan"
//            isEmpty(mNisn)          ->  mNisn.error         = "Nomor induk siswa dibutuhkan"
//            isEmpty(mEmail)         ->  mEmail.error        = "Nomor induk siswa dibutuhkan"
//            isEmpty(mPassword)      ->  mPassword.error     = "Nomor induk siswa dibutuhkan"
//            client.isOnline()       ->  processRegister()
//            else                    ->  showMessage("Tidak ada koneksi internet")
//        }
//    }
//
//    private fun processRegister(){
//
//        mProgressDialog.show()
//
//        user.auth.createUserWithEmailAndPassword(mEmail.text.toString(), mPassword.text.toString())
//            .addOnCompleteListener(requireActivity()) { task ->
//                if (task.isSuccessful) {
//
//                    setProfile()
//                } else {
//
//                    showMessage("Authentication Failed : ${task.exception}")
//                    mProgressDialog.dismiss()
//                }
//            }
//    }
//
//    private fun setProfile(){
//
//        if(user.auth.uid != null){
//
//            firebaseRef.collection("user").document(user.auth.uid.toString())
//                .set(hashMapOf(
//                    "nisn" to mNisn.text.toString(),
//                    "nama" to mNama.text.toString(),
//                    "tahun" to spinner.selectedItem.toString(),
//                    "jurusan" to spinnerJurusan.selectedItem.toString()
//                )).addOnCompleteListener { task ->
//                    if (task.isSuccessful) {
//
//                        showMessage("Registration Success")
//                        mProgressDialog.dismiss()
//                    }else{
//
//                        showMessage(task.exception.toString())
//                        mProgressDialog.dismiss()
//                    }
//                }
//
//            user.removeUserData()
//        }else {
//
//            showMessage("Something went wrong, please try again")
//            mProgressDialog.dismiss()
//        }
//    }
//
//    private fun isEmpty(inputText: EditText)    = TextUtils.isEmpty(inputText.text.toString())
//
//    private fun showMessage(message: String){
//
//        Toast.makeText(activity?.applicationContext, message, Toast.LENGTH_LONG).show()
//    }
}

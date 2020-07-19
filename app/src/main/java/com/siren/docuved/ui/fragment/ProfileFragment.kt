package com.siren.docuved.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.firebase.firestore.FirebaseFirestore
import com.siren.docuved.R
import com.siren.docuved.base.BaseFragment
import com.siren.docuved.ui.activity.DashboardActivity

class ProfileFragment : BaseFragment() {

    private lateinit var txtNama: TextView
    private lateinit var txtEmail: TextView
    private lateinit var txtJurusan: TextView
    private lateinit var txtTahun: TextView

    private lateinit var btnLogOut: Button

    private val firebaseRef = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root    = inflater.inflate(R.layout.fragment_profile, container, false)

        txtNama     = root.findViewById(R.id.txt_nama)
        txtEmail    = root.findViewById(R.id.txt_email)
        txtJurusan  = root.findViewById(R.id.txt_jurusan)
        txtTahun    = root.findViewById(R.id.txt_tahun)
        btnLogOut   = root.findViewById(R.id.btn_log_out)

        btnLogOut.setOnClickListener {

            (activity as DashboardActivity).logout()
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        if(client.isOnline()) {

            firebaseRef.collection("siswa").document(user.auth.uid.toString())
                .get()
                .addOnSuccessListener { document ->

                    txtNama.text    = document.get("nama").toString()
                    txtEmail.text   = user.auth.currentUser?.email
                    txtJurusan.text = document.get("jurusan").toString()
                    txtTahun.text   = document.get("tahun").toString()
                }
                .addOnFailureListener { exception ->

                    showMessage(exception.toString())
                }
        }else
            showMessage("No Internet Connection")
    }
}
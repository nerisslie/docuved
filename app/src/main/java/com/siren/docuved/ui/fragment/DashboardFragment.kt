package com.siren.docuved.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.siren.docuved.R
import com.siren.docuved.base.BaseFragment

class DashboardFragment : BaseFragment() {

    private lateinit var txtNama: TextView
    private lateinit var txtJurusan: TextView
    private lateinit var txtTahun: TextView
    private lateinit var txtTotalRaport: TextView
    private lateinit var txtTotalIjazah: TextView

    private var firebaseRef = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

        txtNama             = root.findViewById(R.id.txt_nama)
        txtJurusan          = root.findViewById(R.id.txt_jurusan)
        txtTahun            = root.findViewById(R.id.txt_tahun)
        txtTotalRaport      = root.findViewById(R.id.txt_total_raport)
        txtTotalIjazah      = root.findViewById(R.id.txt_total_ijazah)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        var ijazahCount     = 0
        var raportCount     = 0

        if(client.isOnline()) {

            firebaseRef.collection("siswa").document(user.auth.uid.toString())
                .get()
                .addOnSuccessListener { document ->

                    if(document != null){

                        txtNama.text    = document.data?.get("nama").toString()
                        txtJurusan.text = document.data?.get("jurusan").toString()
                        txtTahun.text   = document.data?.get("tahun").toString()
                    }
                }
                .addOnFailureListener { exception ->

                    showMessage(exception.toString())
                }

            firebaseRef.collection("archive")
                .whereEqualTo("user_id", user.auth.uid.toString())
                .get()
                .addOnSuccessListener { documents ->

                    for(document in documents){

                        when(document.data["type"]){
                            "ijazah"    -> ijazahCount++
                            else        -> raportCount++
                        }
                    }

                    txtTotalIjazah.text = ijazahCount.toString()
                    txtTotalRaport.text = raportCount.toString()
                }
                .addOnFailureListener { exception ->

                    showMessage(exception.toString())
                }
        }else
            showMessage("No Internet Connection")
    }
}
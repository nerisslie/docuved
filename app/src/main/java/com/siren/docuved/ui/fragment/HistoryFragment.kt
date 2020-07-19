package com.siren.docuved.ui.fragment

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.siren.docuved.R
import com.siren.docuved.adapter.HistoryAdapter
import com.siren.docuved.base.BaseFragment
import com.siren.docuved.model.ArchiveResponse
import com.siren.docuved.ui.activity.DashboardActivity
import com.siren.docuved.utils.Constant
import kotlinx.android.synthetic.main.fragment_history.*
import java.io.File


class HistoryFragment : BaseFragment() {

    private lateinit var rvHistory: RecyclerView

    private val firebaseRef = FirebaseFirestore.getInstance()
    private val storageRef  = Firebase.storage(Constant.STORAGE_LINK).reference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root    = inflater.inflate(R.layout.fragment_history, container, false)

        rvHistory   = root.findViewById(R.id.rv_history)

        checkPermission()

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        if(client.isOnline()) {

            firebaseRef.collection("archive")
                .whereEqualTo("user_id", user.auth.uid)
                .get()
                .addOnSuccessListener { documents ->

                    val archiveArrayList  = documents.toObjects(ArchiveResponse::class.java)

                    rv_history.apply {

                        layoutManager = LinearLayoutManager(activity)
                        adapter = HistoryAdapter(
                            archiveArrayList,
                            { partItem: ArchiveResponse -> processDownload(partItem) },
                            { partItemEmail: ArchiveResponse -> processEmail(partItemEmail) }
                        )
                    }
                }
                .addOnFailureListener { exception ->

                    showMessage(exception.toString())
                }
        }else
            showMessage("No Internet Connection")
    }

    private fun checkPermission(){

        if (ActivityCompat.checkSelfPermission(
                (activity as DashboardActivity).applicationContext,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
         ) {

            requestPermissions(
                (activity as DashboardActivity), arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ), 1
            )
        }
    }

    private fun processDownload(partItem: ArchiveResponse){

        val localFile = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), partItem.file.toString())

        loadingDialog.show()

        storageRef.child("pdf/${partItem.file}").getFile(localFile)
        .addOnSuccessListener {

            showMessage("Download Success")
        }.addOnFailureListener {

            showMessage(it.toString())
        }
        .addOnCompleteListener{

            loadingDialog.dismiss()
        }
    }

    private fun processEmail(partItemEmail: ArchiveResponse){

    }
}
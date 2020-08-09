package com.siren.docuved.base

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.siren.docuved.custom.Client
import com.siren.docuved.custom.User
import com.siren.docuved.ui.activity.DashboardActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class BaseFragment: Fragment(), CoroutineScope {

    lateinit var job: Job
    lateinit var user: User
    lateinit var client: Client
    lateinit var loadingDialog: AlertDialog

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onAttach(context: Context) {
        super.onAttach(context)

        job             = Job()
        user            = (activity as DashboardActivity).user
        client          = (activity as DashboardActivity).client
        loadingDialog   = (activity as DashboardActivity).mProgressDialog
    }

    fun showMessage(message: String){

        launch(Dispatchers.Main) {

            Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
        }
    }
}
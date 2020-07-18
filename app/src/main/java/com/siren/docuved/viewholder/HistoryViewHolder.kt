package com.siren.docuved.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.siren.docuved.R
import com.siren.docuved.model.ArchiveResponse

class HistoryViewHolder(inflater: LayoutInflater, parent: ViewGroup)
    : RecyclerView.ViewHolder(inflater.inflate(R.layout.detail_history, parent, false)){

    private lateinit var txtName: TextView

    private lateinit var btnImgDownload: ImageButton

    init {

        txtName         = itemView.findViewById(R.id.txt_name)
        btnImgDownload  = itemView.findViewById(R.id.btn_img_download)
    }

    @ExperimentalStdlibApi
    fun bind(archiveResponse: ArchiveResponse, downloadListener: (ArchiveResponse) -> Unit, emailListener: (ArchiveResponse) -> Unit) {

        txtName?.text       = archiveResponse.name

        btnImgDownload.setOnClickListener {

            downloadListener(archiveResponse)
        }
    }
}
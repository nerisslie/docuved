package com.siren.docuved.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.siren.docuved.model.ArchiveResponse
import com.siren.docuved.viewholder.HistoryViewHolder

class HistoryAdapter(
    private val archiveList: List<ArchiveResponse>,
    private val downloadListener: (ArchiveResponse) -> Unit,
    private val emailListener: (ArchiveResponse) -> Unit):  RecyclerView.Adapter<HistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {

        val inflater = LayoutInflater.from(parent.context)

        return HistoryViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = archiveList.size

    @ExperimentalStdlibApi
    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {

        holder.bind(archiveList[position], downloadListener, emailListener)
    }
}
package com.kardabel.notepad.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kardabel.notepad.R
import com.kardabel.notepad.model.NoteViewState

class NoteAdapter : ListAdapter<NoteViewState, NoteAdapter.NoteViewHolder>(NoteComparator()) {

    private var onItemClicked: OnItemClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current, onItemClicked)
    }

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.item_title)
        private val text: TextView = itemView.findViewById(R.id.item_excerpt)

        fun bind(note: NoteViewState, onItemClicked: OnItemClickListener?) {
            title.text = note.title
            text.text = note.text

            itemView.setOnClickListener {
                onItemClicked!!.onItemClicked(note)
            }
        }

        companion object {
            fun create(parent: ViewGroup): NoteViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_note, parent, false)
                return NoteViewHolder(view)
            }
        }
    }

    class NoteComparator : DiffUtil.ItemCallback<NoteViewState>() {
        override fun areItemsTheSame(oldItem: NoteViewState, newItem: NoteViewState): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: NoteViewState, newItem: NoteViewState): Boolean {
            return oldItem == newItem
        }
    }

    fun setOnItemClickedListener(listener: OnItemClickListener) {
        onItemClicked = listener
    }

    interface OnItemClickListener {
        fun onItemClicked(note: NoteViewState)
    }
}
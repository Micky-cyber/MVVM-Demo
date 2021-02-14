package com.demomvvmapp.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.demomvvmapp.R
import com.demomvvmapp.model.Watches

class WatchPostView(view: View, var watchClick : ((Watches) -> Unit)? = null
) : RecyclerView.ViewHolder(view) {
    val textView: TextView

    init {
        // Define click listener for the ViewHolder's View.
        textView = view.findViewById(R.id.textview)
    }

    fun bind(watches: Watches){
        textView.text = watches.model_name
        textView.setOnClickListener {
            watchClick?.invoke(watches)
        }
    }
}
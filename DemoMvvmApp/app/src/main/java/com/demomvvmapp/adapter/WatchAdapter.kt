package com.demomvvmapp.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demomvvmapp.R
import com.demomvvmapp.model.Watches

class WatchAdapter :
    RecyclerView.Adapter<WatchPostView>() {
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    var watchClick : ((Watches) -> Unit)? = null
    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): WatchPostView {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.text_raw_item, viewGroup, false)
        return WatchPostView(view,watchClick)
    }

    var dataList: List<Watches> = arrayListOf()
    // Replace the contents of a view (invoked by the layout manager)

    override fun onBindViewHolder(viewHolder: WatchPostView, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.bind(dataList[viewHolder.adapterPosition])
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataList.size

    fun updateList(dataList: List<Watches>) {
        this.dataList = dataList
        notifyDataSetChanged()
    }
}
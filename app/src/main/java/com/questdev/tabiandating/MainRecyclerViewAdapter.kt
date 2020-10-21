package com.questdev.tabiandating

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.questdev.tabiandating.models.User
import kotlinx.android.synthetic.main.layout_main_feed.view.*

class MainRecyclerViewAdapter(private var users: ArrayList<User>)
    : RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder>() {

    val TAG = MainRecyclerViewAdapter::class.java.simpleName
    var mInterface: IMainActivity? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_main_feed, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mInterface = recyclerView.context as IMainActivity
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder: called.")

        holder.bind(
            users[position].profile_image,
            users[position].name,
            users[position].interested_in,
            users[position].status
        )
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val image: AppCompatImageView = itemView.profile_image
        private val name: TextView = itemView.tvName
        private val interestedIn: TextView = itemView.tvInterestedIn
        private val status: TextView = itemView.tvStatus
        private val cardView: CardView = itemView.card_view

        fun bind(imageUriString: String?, name: String?, interestedIn: String?, status: String?) {
            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .load(imageUriString)
                .apply(requestOptions)
                .into(image)

            this.name.text = name
            this.interestedIn.text = interestedIn
            this.status.text = status

            cardView.setOnClickListener {
                Log.d(TAG, "onClick: clicked on $name")
                mInterface?.inflateViewProfileFragment(users[adapterPosition])
            }
        }
    }
}
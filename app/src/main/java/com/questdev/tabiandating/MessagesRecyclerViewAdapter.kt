package com.questdev.tabiandating

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.questdev.tabiandating.models.Message
import com.questdev.tabiandating.models.User
import com.questdev.tabiandating.util.Messages
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.layout_messages_listitem.view.*
import java.util.*
import kotlin.collections.ArrayList


class MessagesRecyclerViewAdapter(private val users: ArrayList<User>)
    : RecyclerView.Adapter<MessagesRecyclerViewAdapter.ViewHolder>(), Filterable{

    private val TAG = "ConnectionsAdapter"
    private var filteredUsers: ArrayList<User> = users
    var mInterface: IMainActivity? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_messages_listitem, parent, false)
        return ViewHolder(itemView)
    }

//    override fun getItemCount(): Int { return filteredUsers.size }

    override fun getItemCount() = filteredUsers.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder: called.")

        holder.bind(users[position].name, Messages.MESSAGES[position], users[position].profile_image)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

        mInterface = recyclerView.context as IMainActivity
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val charString = p0.toString()
                filteredUsers = if (charString.isEmpty()){
                    users
                }
                else {
                    val filteredList = ArrayList<User>()
                    for (row in users) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.name?.toLowerCase(Locale.ENGLISH)?.contains(charString.toLowerCase(Locale.ENGLISH))!!)
                            filteredList.add(row)
                    }

                    filteredList
                }

                val filterResult = FilterResults()
                filterResult.values = filteredUsers

                return filterResult
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                filteredUsers = p1?.values as ArrayList<User>

                notifyDataSetChanged()
            }

        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var image: CircleImageView = itemView.image
        private var name: TextView = itemView.name
        private var message: TextView = itemView.message
        private var reply: TextView = itemView.reply
        private var parent: ConstraintLayout = itemView.parent_view

        fun bind(name: String?, message: String?, imageUrl: String?) {
            this.name.text = name
            this.message.text = message

            val requestOptions = RequestOptions().placeholder(R.drawable.ic_launcher_background)
            Glide.with(itemView.context)
                .load(imageUrl)
                .apply(requestOptions)
                .into(image)

            parent.setOnClickListener {
                Log.d(TAG, "onClick: clicked on $name")

                mInterface?.onMessageSelected(Message(users[adapterPosition], Messages.MESSAGES[adapterPosition]))
            }
        }
    }
}
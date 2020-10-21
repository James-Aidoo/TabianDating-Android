package com.questdev.tabiandating


import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.edit
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.like.LikeButton
import com.like.OnLikeListener
import com.questdev.tabiandating.models.User
import com.questdev.tabiandating.util.PreferenceKeys
import de.hdodenhof.circleimageview.CircleImageView
import com.questdev.tabiandating.util.Resources

class ViewProfileFragment : Fragment(), OnLikeListener {

    private val TAG = this.javaClass.simpleName
    private var user: User? = null

    // views
    private var image: CircleImageView? = null
    private var name: TextView? = null
    private var gender: TextView? = null
    private var interest: TextView? = null
    private var status: TextView? = null
    private var likeBtn: LikeButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        user = arguments?.getParcelable(getString(R.string.intent_user))

        Log.d(TAG, "onCreate: got incoming bundle: ${user?.name}")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_view_profile, container, false)
        image = view.findViewById(R.id.profile_image)
        name = view.findViewById(R.id.name)
        gender = view.findViewById(R.id.gender)
        interest = view.findViewById(R.id.interested_in)
        status = view.findViewById(R.id.status)
        likeBtn = view.findViewById(R.id.heart_button)

        init()
        setBackground(view)
        checkIfConnected()

        likeBtn?.setOnLikeListener(this)

        return view
    }

    private fun init() {

        name?.text = user?.name

        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)

        Glide.with(context!!)
            .load(user?.profile_image)
            .apply(requestOptions)
            .into(image as CircleImageView)

            gender?.text = user?.gender
            interest?.text = user?.interested_in
            status?.text = user?.status
    }

    private fun setBackground(view: View) {
        val backgroundImage = view.findViewById<ImageView>(R.id.background)
        Glide.with(context!!)
            .load(Resources.BACKGROUND_HEARTS)
            .into(backgroundImage)
    }

    private fun checkIfConnected() {
        val prefs = activity?.getPreferences(Context.MODE_PRIVATE)
        val savedNames = prefs?.getStringSet(PreferenceKeys.SAVED_CONNECTIONS, HashSet<String>())!!
        likeBtn?.isLiked = savedNames.contains(user!!.name)
    }

    override fun liked(likeButton: LikeButton?) {
        Log.d(TAG, "liked: liked.")

        val prefs = activity?.getPreferences(Context.MODE_PRIVATE)
        val savedNames = prefs?.getStringSet(PreferenceKeys.SAVED_CONNECTIONS, HashSet<String>())!!
        savedNames.add(user!!.name)
        prefs.edit {
            putStringSet(PreferenceKeys.SAVED_CONNECTIONS, savedNames)
            commit()
        }
    }

    override fun unLiked(likeButton: LikeButton?) {
        Log.d(TAG, "unLiked: unLiked.")
        val prefs = activity?.getPreferences(Context.MODE_PRIVATE)
        val savedNames = prefs?.getStringSet(PreferenceKeys.SAVED_CONNECTIONS, HashSet<String>())!!
        savedNames.remove(user!!.name)
        prefs.edit {
            remove(PreferenceKeys.SAVED_CONNECTIONS)
            commit()
            putStringSet(PreferenceKeys.SAVED_CONNECTIONS, savedNames)
            commit()
        }
    }
}

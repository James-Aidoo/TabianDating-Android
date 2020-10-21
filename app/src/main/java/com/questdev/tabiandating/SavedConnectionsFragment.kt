package com.questdev.tabiandating


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.questdev.tabiandating.models.User
import com.questdev.tabiandating.util.PreferenceKeys
import com.questdev.tabiandating.util.Users

/**
 * A simple [Fragment] subclass.
 */
class SavedConnectionsFragment : Fragment() {

    private val TAG: String = this.javaClass.simpleName
    // Constants
    private val NUM_GRID_COLUMNS = 2
    // Widgets
    private val users by lazy { ArrayList<User>() }

    private var recyclerView: RecyclerView? = null

    private var recyclerViewAdapter: MainRecyclerViewAdapter? = null

    private val staggeredGridLayoutManager by lazy {
        StaggeredGridLayoutManager(NUM_GRID_COLUMNS, StaggeredGridLayoutManager.VERTICAL)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView: Started")
        val view = inflater.inflate(R.layout.fragment_saved_connections, container, false)

        recyclerView = view.findViewById(R.id.recycler_view)

        getConnections()

        return view
    }

    private fun getConnections() {
        val prefs = activity?.getPreferences(Context.MODE_PRIVATE)
        val savedNames = prefs?.getStringSet(PreferenceKeys.SAVED_CONNECTIONS, HashSet<String>())

        val allUsers = Users()
        if (!users.isNullOrEmpty()) users.clear()

        for (user in allUsers.USERS) {
            if (savedNames!!.contains(user.name))
                users.add(user)
        }
        if (recyclerViewAdapter == null)
            initRecyclerView()
    }

    private fun initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init recyclerView")
        recyclerViewAdapter = MainRecyclerViewAdapter(users)
        recyclerView?.adapter = recyclerViewAdapter
        recyclerView?.layoutManager = staggeredGridLayoutManager
    }


}

package com.questdev.tabiandating


import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.questdev.tabiandating.models.User
import com.questdev.tabiandating.util.PreferenceKeys
import com.questdev.tabiandating.util.Users

class MessagesFragment : Fragment() {

    private val TAG = this.javaClass.simpleName

    private val users by lazy { ArrayList<User>() }

    // widgets
    private lateinit var recyclerView: RecyclerView
    private var recyclerViewAdapter: MessagesRecyclerViewAdapter? = null
    private var searchView: SearchView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_messages, container, false)

        recyclerView = view.findViewById(R.id.recycler_view)
        searchView = view.findViewById(R.id.action_search)

        getConnections()
        initSearchView()

        return view
    }

    private fun initSearchView() {
        // associate searchable configuration with the SearchView
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager

        searchView?.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
        searchView?.maxWidth = Int.MAX_VALUE

        // listening to search query text change
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // filter recycler view when query is submitted
                recyclerViewAdapter?.filter?.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // filter recycler view when query text changes
                recyclerViewAdapter?.filter?.filter(newText)
                return false
            }
        })
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
        recyclerViewAdapter = MessagesRecyclerViewAdapter(users)
        recyclerView.adapter = recyclerViewAdapter
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

}

package com.questdev.tabiandating

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.questdev.tabiandating.models.User
import com.questdev.tabiandating.util.Users

class HomeFragment : Fragment() {

    private val TAG = HomeFragment::class.java.simpleName

    private val NUM_COLS = 2

    private val matches by lazy { ArrayList<User>() }
    private val staggeredGridLayoutManager by lazy {
        StaggeredGridLayoutManager(NUM_COLS, StaggeredGridLayoutManager.VERTICAL)
    }

    private var recyclerView: RecyclerView? = null

    private var recyclerViewAdapter: MainRecyclerViewAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView = view?.findViewById(R.id.recycler_view)
//        findMatches()

        return view
    }

    override fun onResume() {
        super.onResume()
        findMatches()
    }

    private fun findMatches() {
        val users = Users()
        if (matches.size > 0)
            matches.clear()

        matches.addAll(users.USERS)

        if (recyclerViewAdapter == null)
            initRecyclerView()
    }

    private fun initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init RecyclerView")
        recyclerView?.layoutManager = staggeredGridLayoutManager
        recyclerViewAdapter = MainRecyclerViewAdapter(matches)
        recyclerView?.adapter = recyclerViewAdapter
    }

    /*companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }*/
}

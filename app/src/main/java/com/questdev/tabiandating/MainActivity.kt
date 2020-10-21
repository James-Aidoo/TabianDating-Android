package com.questdev.tabiandating

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx
import com.questdev.tabiandating.models.Message
import com.questdev.tabiandating.models.User
import com.questdev.tabiandating.util.PreferenceKeys
import kotlinx.android.synthetic.main.layout_main_content.*

class MainActivity : AppCompatActivity(), IMainActivity, BottomNavigationView.OnNavigationItemSelectedListener {

    private val TAG = MainActivity::class.java.simpleName

    private lateinit var bottomNavView: BottomNavigationViewEx

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavView = findViewById(R.id.bottom_nav_view)

        isFirstLogin()
        initBottomNavigationView()
        init()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.bottom_nav_home -> {
                Log.d(TAG, "onNavigationItemSelected: HomeFragment")
                item.isChecked = true
                navigateToFragment(HomeFragment(), getString(R.string.tag_fragment_home))
            }
            R.id.bottom_nav_connections -> {
                Log.d(TAG, "onNavigationItemSelected: ConnectionsFragment")
                item.isChecked = true
                navigateToFragment(SavedConnectionsFragment(), getString(R.string.tag_fragment_saved_connections))
            }
            R.id.bottom_nav_messages -> {
                Log.d(TAG, "onNavigationItemSelected: MessagesFragment")
                item.isChecked = true
                navigateToFragment(MessagesFragment(), getString(R.string.tag_fragment_messages))
            }
        }

        return false
    }

    private fun initBottomNavigationView() {
        Log.d(TAG, "initBottomNavigationView: initialising the bottom navigation view")
        bottomNavView.enableAnimation(false)
        bottomNavView.onNavigationItemSelectedListener = this
    }

    private fun init() {
        val homeFragment = HomeFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_content_frame, homeFragment, getString(R.string.tag_fragment_home))
        transaction.addToBackStack(getString(R.string.tag_fragment_home))
        transaction.commit()
    }

    private fun navigateToFragment(fragment: Fragment, tag: String) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_content_frame, fragment, tag)
        transaction.addToBackStack(tag)
        transaction.commit()
    }

    private fun isFirstLogin() {
        Log.d(TAG, "isFirstLogin: Checking if this the first login")

        val pref = getPreferences(Context.MODE_PRIVATE)
        val isFirstLogin = pref.getBoolean(PreferenceKeys.FIRST_TIME_LOGIN, true)

        if (isFirstLogin) {
            Log.d(TAG, "isFirstLogin: Launching Alert Dialog")

            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setMessage(getString(R.string.first_time_user_message))
            alertDialogBuilder.setPositiveButton("OK"
            ) { dialogInterface, _ ->
                Log.d(TAG, "onClick: Closing dialog")

                pref.edit {
                    putBoolean(PreferenceKeys.FIRST_TIME_LOGIN, false)
                    commit()
                }
                dialogInterface.dismiss()
            }
            alertDialogBuilder.setIcon(R.drawable.tabian_dating)
            alertDialogBuilder.setTitle(" ")
            alertDialogBuilder.create().show()
        }
    }

    override fun inflateViewProfileFragment(user: User) {
        val viewProfileFragment = ViewProfileFragment()

        val args = Bundle()
        args.putParcelable(getString(R.string.intent_user), user)

        viewProfileFragment.arguments = args

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_content_frame, viewProfileFragment, getString(R.string.tag_fragment_view_profile))
        transaction.commit()
    }

    override fun onMessageSelected(message: Message?) {

    }

}

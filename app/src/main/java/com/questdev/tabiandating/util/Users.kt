package com.questdev.tabiandating.util

import android.net.Uri
import com.questdev.tabiandating.R

import com.questdev.tabiandating.models.User




class Users {
    var USERS = arrayOf(James, Elizabeth, Robert, Carol, Jennifer, Susan, Michael, William, Karen,
            Joseph, Nancy, Charles, Matthew, Sarah, Jessica, Donald, Mary, Paul, Patricia, Linda, Steve
        )


    companion object {
        /*
                Men
         */
        val James: User = User(
            Uri.parse("""android.resource://com.questdev.tabiandating/${R.drawable.james}""").toString(),
            "James", "Male", "Female", "Looking"
        )

        val Robert: User = User(
            Uri.parse("android.resource://com.questdev.tabiandating/" + R.drawable.robert).toString(),
            "Robert", "Male", "Female", "Looking"
        )

        val Michael: User = User(
            Uri.parse("android.resource://com.questdev.tabiandating/" + R.drawable.michael).toString(),
            "Michael", "Male", "Female", "Looking"
        )

        val William: User = User(
            Uri.parse("android.resource://com.questdev.tabiandating/" + R.drawable.william).toString(),
            "William", "Male", "Female", "Not Looking"
        )

        val Joseph: User = User(
            Uri.parse("android.resource://com.questdev.tabiandating/" + R.drawable.joseph).toString(),
            "Joseph", "Male", "Female", "Looking"
        )

        val Charles: User = User(
            Uri.parse("android.resource://com.questdev.tabiandating/" + R.drawable.charles).toString(),
            "Charles", "Male", "Female", "Looking"
        )

        val Matthew: User = User(
            Uri.parse("android.resource://com.questdev.tabiandating/" + R.drawable.mattew).toString(),
            "Matthew", "Male", "Female", "Looking"
        )

        val Donald: User = User(
            Uri.parse("android.resource://com.questdev.tabiandating/" + R.drawable.donald).toString(),
            "Donald", "Male", "Female", "Looking"
        )

        val Paul: User = User(
            Uri.parse("android.resource://com.questdev.tabiandating/" + R.drawable.paul).toString(),
            "Paul", "Male", "Female", "Looking"
        )

        val Steve: User = User(
            Uri.parse("android.resource://com.questdev.tabiandating/" + R.drawable.steve).toString(),
            "Steve", "Male", "Female", "Looking"
        )


        /*
                Females
         */
        val Mary: User = User(
            Uri.parse("android.resource://com.questdev.tabiandating/" + R.drawable.mary).toString(),
            "Mary", "Female", "Male", "Looking"
        )

        val Patricia: User = User(
            Uri.parse("android.resource://com.questdev.tabiandating/" + R.drawable.patricia).toString(),
            "Patricia", "Female", "Male", "Looking"
        )

        val Jennifer: User = User(
            Uri.parse("android.resource://com.questdev.tabiandating/" + R.drawable.jennifer).toString(),
            "Jennifer", "Female", "Male", "Looking"
        )

        val Elizabeth: User = User(
            Uri.parse("android.resource://com.questdev.tabiandating/" + R.drawable.elizabeth).toString(),
            "Elizabeth", "Female", "Male", "Looking"
        )

        val Linda: User = User(
            Uri.parse("android.resource://com.questdev.tabiandating/" + R.drawable.linda).toString(),
            "Linda", "Female", "Male", "Looking"
        )

        val Susan: User = User(
            Uri.parse("android.resource://com.questdev.tabiandating/" + R.drawable.susan).toString(),
            "Susan", "Female", "Male", "Looking"
        )

        val Jessica: User = User(
            Uri.parse("android.resource://com.questdev.tabiandating/" + R.drawable.jessica).toString(),
            "Jessica", "Female", "Male", "Looking"
        )

        val Sarah: User = User(
            Uri.parse("android.resource://com.questdev.tabiandating/" + R.drawable.sarah).toString(),
            "Sarah", "Female", "Male", "Looking"
        )

        val Karen: User = User(
            Uri.parse("android.resource://com.questdev.tabiandating/" + R.drawable.karen).toString(),
            "Karen", "Female", "Male", "Looking"
        )

        val Nancy: User = User(
            Uri.parse("android.resource://com.questdev.tabiandating/" + R.drawable.nancy).toString(),
            "Nancy", "Female", "Male", "Looking"
        )

        val Carol: User = User(
            Uri.parse("android.resource://com.questdev.tabiandating/" + R.drawable.carol).toString(),
            "Carol", "Do Not Identify", "Anyone", "Looking"
        )
    }
}
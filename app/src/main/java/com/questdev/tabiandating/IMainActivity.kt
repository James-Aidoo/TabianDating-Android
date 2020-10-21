package com.questdev.tabiandating

import com.questdev.tabiandating.models.Message
import com.questdev.tabiandating.models.User

interface IMainActivity {

    fun inflateViewProfileFragment(user: User)

    fun onMessageSelected(message: Message?)

}
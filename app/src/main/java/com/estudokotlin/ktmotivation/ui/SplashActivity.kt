package com.estudokotlin.ktmotivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.estudokotlin.ktmotivation.R
import com.estudokotlin.ktmotivation.infra.MotivationConstants
import com.estudokotlin.ktmotivation.infra.SecurityPrefences
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mSecurityPreferences: SecurityPrefences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        mSecurityPreferences = SecurityPrefences(this)

        buttonSave.setOnClickListener(this)
        verifyUserName()
    }

    override fun onClick(view: View?) {
        val id: Int? = view?.id
        if (id == R.id.buttonSave) {
            handleSave()
        }
    }

    private fun verifyUserName() {
        val name = mSecurityPreferences.getStoredString(MotivationConstants.KEY.PERSON_NAME)
        if (name != "") {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun handleSave() {

        val name: String = editName.text.toString()
        if (name == "") {
            Toast.makeText(this, "Informe seu nome.", Toast.LENGTH_LONG).show()
        } else {
            mSecurityPreferences.storeString(MotivationConstants.KEY.PERSON_NAME, name)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
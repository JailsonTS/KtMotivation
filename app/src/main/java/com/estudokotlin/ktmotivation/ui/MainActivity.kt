package com.estudokotlin.ktmotivation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.estudokotlin.ktmotivation.R
import com.estudokotlin.ktmotivation.infra.MotivationConstants
import com.estudokotlin.ktmotivation.infra.SecurityPrefences
import com.estudokotlin.ktmotivation.repository.Mock
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mSecurityPrefences: SecurityPrefences

    private var filter: Int = MotivationConstants.PHRASEFILTER.ALL
    private val mMock: Mock = Mock()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        mSecurityPrefences = SecurityPrefences(this)

        setListeners()

        handleFilter(R.id.imageAll)
        refreshPhrase()
        showUserName()

    }

    override fun onClick(view: View) {
        val id: Int = view.id

        val listId = listOf(
            R.id.imageAll,
            R.id.imageHappy,
            R.id.imageMorning
        )

        if (id in listId) {
            handleFilter(id)
        } else if (id == R.id.buttonRefresh) {
            refreshPhrase()
        }
    }

    private fun setListeners() {
        imageAll.setOnClickListener(this)
        imageHappy.setOnClickListener(this)
        imageMorning.setOnClickListener(this)
        buttonRefresh.setOnClickListener(this)
    }

    private fun refreshPhrase() {
        textPhrase.text = mMock.getPhrase(filter)
    }

    private fun showUserName() {
        val name = mSecurityPrefences.getStoredString(MotivationConstants.KEY.PERSON_NAME)
        textUserName.text = "Olá, $name!"
    }

    private fun handleFilter(id: Int) {

        imageAll.setColorFilter(ContextCompat.getColor(this, R.color.white))
        imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.white))
        imageMorning.setColorFilter(ContextCompat.getColor(this, R.color.white))

        when (id) {
            R.id.imageAll -> {
                filter = MotivationConstants.PHRASEFILTER.ALL
                imageAll.setColorFilter(
                    ContextCompat.getColor(this, R.color.purple_700)
                )
            }
            R.id.imageHappy -> {
                filter = MotivationConstants.PHRASEFILTER.HAPPY
                imageHappy.setColorFilter(
                    ContextCompat.getColor(this, R.color.purple_700)
                )
            }
            else -> {
                filter = MotivationConstants.PHRASEFILTER.MORNING
                imageMorning.setColorFilter(
                    ContextCompat.getColor(this, R.color.purple_700)
                )
            }
        }

    }
}
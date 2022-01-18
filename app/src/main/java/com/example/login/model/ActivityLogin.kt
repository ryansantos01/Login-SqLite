package com.example.login.model

import android.content.Intent
import android.os.Bundle
//import android.support.design.widget.Snackbar
import com.google.android.material.snackbar.Snackbar
//import android.support.design.widget.TextInputEditText
import com.google.android.material.textfield.TextInputEditText
//import android.support.design.widget.TextInputLayout
import com.google.android.material.textfield.TextInputLayout
//import android.support.v4.widget.NestedScrollView
import androidx.core.widget.NestedScrollView
//import android.support.v7.app.AppCompatActivity
import androidx.appcompat.app.AppCompatActivity
//import android.support.v7.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatButton
// import android.support.v7.widget.AppCompatTextView
import androidx.appcompat.widget.AppCompatTextView
import android.view.View
import com.example.login.DB.DBHelper
import com.example.login.InputValidation
import com.example.login.R
import com.example.login.databinding.ActivityLoginBinding

//import com.example.login.loginregisterkotlin.R
//import com.androidtutorialshub.loginregisterkotlin.helpers.InputValidation
//import com.androidtutorialshub.loginregisterkotlin.sql.DatabaseHelper


class ActivityLogin : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityLoginBinding
    private val activity = this@ActivityLogin

    private lateinit var nestedScrollView: NestedScrollView

    private lateinit var textInputLayoutEmail: TextInputLayout
    private lateinit var textInputLayoutPassword: TextInputLayout

    private lateinit var textInputEditTextEmail: TextInputEditText
    private lateinit var textInputEditTextPassword: TextInputEditText

    private lateinit var appCompatButtonLogin: AppCompatButton

    private lateinit var textViewLinkRegister: AppCompatTextView

    private lateinit var inputValidation: InputValidation
    private lateinit var databaseHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        // esconder a action bar
        supportActionBar!!.hide()

        // inicializando as views
        initViews()

        // inicializando os listeners
        initListeners()

        // inicializando os objects
        initObjects()
    }

    /**
     * This method is to initialize views
     */
    private fun initViews() {

        nestedScrollView = findViewById(R.id.nestedScrollView) as NestedScrollView

        textInputLayoutEmail = findViewById(R.id.textInputLayoutEmail) as TextInputLayout
        textInputLayoutPassword = findViewById(R.id.textInputLayoutPassword) as TextInputLayout

        textInputEditTextEmail = findViewById(R.id.textInputEditTextEmail) as TextInputEditText
        textInputEditTextPassword = findViewById(R.id.textInputEditTextPassword) as TextInputEditText

        appCompatButtonLogin = findViewById(R.id.appCompatButtonLogin) as AppCompatButton

        textViewLinkRegister = findViewById(R.id.textViewLinkRegister) as AppCompatTextView

    }

    /**
     * This method is to initialize listeners
     */
    private fun initListeners() {

        appCompatButtonLogin!!.setOnClickListener(this)
        textViewLinkRegister!!.setOnClickListener(this)
    }

    /**
     * This method is to initialize objects to be used
     */
    private fun initObjects() {

        databaseHelper = DBHelper(this, null)
        inputValidation = InputValidation(activity)
    }

    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    override fun onClick(v: View) {
        when (v.id) {
            R.id.appCompatButtonLogin -> verifyFromSQLite()
            R.id.textViewLinkRegister -> {
                // Navigate to RegisterActivity
                val intentRegister = Intent(applicationContext, ActivityRegister::class.java)
                startActivity(intentRegister)
            }
        }
    }

    /**
     * This method is to validate the input text fields and verify login credentials from SQLite
     */
    private fun verifyFromSQLite() {

        if (!inputValidation!!.isInputEditTextFilled(textInputEditTextEmail!!, textInputLayoutEmail!!, getString(
                R.string.error_message_email
            ))) {
            return
        }
        if (!inputValidation!!.isInputEditTextEmail(textInputEditTextEmail!!, textInputLayoutEmail!!, getString(
                R.string.error_message_email
            ))) {
            return
        }
        if (!inputValidation!!.isInputEditTextFilled(textInputEditTextPassword!!, textInputLayoutPassword!!, getString(
                R.string.error_message_email
            ))) {
            return
        }

        if (databaseHelper!!.checkUser(textInputEditTextEmail!!.text.toString().trim { it <= ' ' }, textInputEditTextPassword!!.text.toString().trim { it <= ' ' })) {
            val accountsIntent = Intent(activity, ActivityUsersList::class.java)
            //val accountsIntent = Intent(activity,User::class.java)
            accountsIntent.putExtra("EMAIL", textInputEditTextEmail!!.text.toString().trim { it <= ' ' })
            emptyInputEditText()
            startActivity(accountsIntent)


        } else {

            // Snack Bar to show success message that record is wrong
            Snackbar.make(nestedScrollView!!, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show()
        }
    }

    /**
     * This method is to empty all input edit text
     */
    private fun emptyInputEditText() {
        textInputEditTextEmail!!.text = null
        textInputEditTextPassword!!.text = null
    }
}
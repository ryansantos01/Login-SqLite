package com.example.login.model

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import androidx.core.widget.NestedScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import android.view.View
import com.example.login.DB.DBHelper
import com.example.login.InputValidation
import com.example.login.R
import com.example.login.User

class ActivityRegister : AppCompatActivity(), View.OnClickListener {

    private val activity = this@ActivityRegister

    private lateinit var nestedScrollView: NestedScrollView
    private lateinit var textInputLayoutName: TextInputLayout
    private lateinit var textInputLayoutCoren: TextInputLayout
    private lateinit var textInputLayoutEmail: TextInputLayout
    private lateinit var textInputLayoutPassword: TextInputLayout
    private lateinit var textInputLayoutConfirmPassword: TextInputLayout
    private lateinit var textInputEditTextName: TextInputEditText
    private lateinit var textInputEditTextCoren: TextInputEditText
    private lateinit var textInputEditTextEmail: TextInputEditText
    private lateinit var textInputEditTextPassword: TextInputEditText
    private lateinit var textInputEditTextConfirmPassword: TextInputEditText
    private lateinit var appCompatButtonRegister: AppCompatButton
    private lateinit var appCompatTextViewLoginLink: AppCompatTextView
    private lateinit var inputValidation: InputValidation
    private lateinit var databaseHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar!!.hide()
        initViews()
        initListeners()
        initObjects()
    }

    /**
     * Inicializar views
     */
    private fun initViews() {
        nestedScrollView = findViewById(R.id.nestedScrollView) as NestedScrollView

        textInputLayoutName = findViewById(R.id.textInputLayoutName) as TextInputLayout
        textInputLayoutEmail = findViewById(R.id.textInputLayoutEmail) as TextInputLayout
        textInputLayoutCoren = findViewById(R.id.textInputLayoutCoren) as TextInputLayout
        textInputLayoutPassword = findViewById(R.id.textInputLayoutPassword) as TextInputLayout
        textInputLayoutConfirmPassword = findViewById(R.id.textInputLayoutConfirmPassword) as TextInputLayout
        textInputEditTextName = findViewById(R.id.textInputEditTextName) as TextInputEditText
        textInputEditTextEmail = findViewById(R.id.EditTextEmail) as TextInputEditText
        textInputEditTextCoren = findViewById(R.id.EditTextCoren) as TextInputEditText
        textInputEditTextPassword = findViewById(R.id.EditTextPassword) as TextInputEditText
        textInputEditTextConfirmPassword = findViewById(R.id.textInputEditTextConfirmPassword) as TextInputEditText
        appCompatButtonRegister = findViewById(R.id.appCompatButtonRegister) as AppCompatButton
        appCompatTextViewLoginLink = findViewById(R.id.appCompatTextViewLoginLink) as AppCompatTextView

    }

    /**
     * Para iniciar os listeners
     */
    private fun initListeners() {
        appCompatButtonRegister!!.setOnClickListener(this)
        appCompatTextViewLoginLink!!.setOnClickListener(this)

    }

    /**
     * Para iniciar os objetos
     */
    private fun initObjects() {
        inputValidation = InputValidation(activity)
        databaseHelper = DBHelper(this, null)
    }


    /**
     * Este m??todo implementado ?? ouvir o clique na visualiza????o
     *
     * @param v
     */
    override fun onClick(v: View) {
        when (v.id) {
            R.id.appCompatButtonRegister -> postDataToSQLite()
            R.id.appCompatTextViewLoginLink -> finish()
        }
    }

    /**
     * Valida????o de campos de entrada para assim coloca-los no SQlite
     */
    private fun postDataToSQLite() {
        if (!inputValidation!!.isInputEditTextFilled(textInputEditTextName, textInputLayoutName, getString(
                R.string.error_message_name
            ))) {
            return
        }
        if (!inputValidation!!.isInputEditTextFilled(textInputEditTextCoren, textInputLayoutCoren, getString(
                R.string.error_message_coren
            ))) {
            return
        }
        if (!inputValidation!!.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(
                R.string.error_message_email
            ))) {
            return
        }
        if (!inputValidation!!.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(
                R.string.error_message_email
            ))) {
            return
        }
        if (!inputValidation!!.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(
                R.string.error_message_password
            ))) {
            return
        }
        if (!inputValidation!!.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword,
                textInputLayoutConfirmPassword, getString(R.string.error_password_match))) {
            return
        }

        if (!databaseHelper!!.checkUser(textInputEditTextEmail!!.text.toString().trim())) {

            var user = User(name = textInputEditTextName!!.text.toString().trim(),
                email = textInputEditTextEmail!!.text.toString().trim(),
                coren = textInputEditTextCoren!!.text.toString().trim(),
                password = textInputEditTextPassword!!.text.toString().trim())

            databaseHelper!!.addUser(user)

            //Messagem que indica que o cadastro foi finalizado
            Snackbar.make(nestedScrollView!!, getString(R.string.success_message), Snackbar.LENGTH_LONG).show()
            emptyInputEditText()


        } else {
            //Messagem que indica que o email j?? existe no banco de dados
            Snackbar.make(nestedScrollView!!, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show()
        }


    }

    /**
     * Usado para esvaziar/limpar todos os edittext de entrada
     */
    private fun emptyInputEditText() {
        textInputEditTextName!!.text = null
        textInputEditTextEmail!!.text = null
        textInputEditTextCoren!!.text = null
        textInputEditTextPassword!!.text = null
        textInputEditTextConfirmPassword!!.text = null
    }
}
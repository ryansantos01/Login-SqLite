package com.example.login

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.widget.NestedScrollView
import com.example.login.DB.DBHelper
import com.example.login.model.ActivityLogin
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class NovasenhaActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var textInputpass: TextInputEditText
    private lateinit var textInputConfirmPassword: TextInputEditText
    private lateinit var textInputLayoutPassword: TextInputLayout
    private lateinit var textInputConfirmPass: TextInputLayout
    private lateinit var inputValidation: InputValidation
    private lateinit var databaseHelper: DBHelper
    private lateinit var nestedScrollView: NestedScrollView
    private lateinit var appCompatButtonReset: AppCompatButton
    lateinit var email: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.login.R.layout.activity_novasenha)
        initObjects()
        initViews()

        val intent = intent
        email = intent.getStringExtra("EMAIL").toString()
        title = "Reset password"
        appCompatButtonReset.setOnClickListener { updatePassword() }
    }

    private fun initObjects() {

        inputValidation = InputValidation(this)
        databaseHelper = DBHelper(this,null)
    }

    private fun initViews() {
        textInputpass = findViewById(com.example.login.R.id.textInputpass) as TextInputEditText
        textInputConfirmPassword = findViewById(com.example.login.R.id.textInputConfirmPassword) as TextInputEditText
        textInputLayoutPassword = findViewById(com.example.login.R.id.textInputLayoutPassword) as TextInputLayout
        textInputConfirmPass = findViewById(com.example.login.R.id.textInputConfirmPass) as TextInputLayout
        nestedScrollView = findViewById(com.example.login.R.id.nestedScrollView) as NestedScrollView
        appCompatButtonReset = findViewById(com.example.login.R.id.appCompatButtonReset) as AppCompatButton

    }

    private fun updatePassword() {
        val value1 = textInputpass.text.toString().trim { it <= ' ' }
        val value2 = textInputConfirmPassword.text.toString().trim { it <= ' ' }
        if (value1.isEmpty() && value2.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_LONG).show()
            return
        }
        if (!value1.contentEquals(value2)) {
            Toast.makeText(this, "Senhas não correspondentes", Toast.LENGTH_LONG).show()
            return
        }
        if (!databaseHelper.checkUser(email)) {
            Snackbar.make(nestedScrollView, "Email inválido", Snackbar.LENGTH_LONG).show()
            return
        } else {
            databaseHelper.updatePassword(email, value1)
            Toast.makeText(this, "Senha alterada com sucesso", Toast.LENGTH_SHORT).show()
            emptyInputEditText()
            val intent = Intent(this, ActivityLogin::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun emptyInputEditText() {
        textInputpass.setText("")
        textInputConfirmPassword.setText("")
    }

    override fun onClick(p0: View) {
        when(p0.id){com.example.login.R.id.appCompatButtonReset-> updatePassword()}
    }
}
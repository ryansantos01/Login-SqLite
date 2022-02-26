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
        initViews()
        inputValidation = InputValidation(this)

        databaseHelper = DBHelper(this,null)


        val intent = intent
        email = intent.getStringExtra("EMAIL").toString()
        title = "Reset password"
        appCompatButtonReset.setOnClickListener { updatePassword() }
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
//        when(p0.id){R.id.appCompatButtonReset -> updatePassword()}
    }
}
//
//class NovasenhaActivity : AppCompatActivity(), View.OnClickListener {
//
//}

//    private lateinit var textInputpass: TextInputEditText
//    private lateinit var textInputConfirmPassword: TextInputEditText

//    private lateinit var email: String
//
//    private lateinit var textInputLayoutPassword: TextInputLayout
//    private lateinit var textInputConfirmPass: TextInputLayout
//
//    private lateinit var nestedScrollView: NestedScrollView
//    private lateinit var appCompatButtonReset: AppCompatButton
//    private lateinit var inputValidation: InputValidation
//    private lateinit var databaseHelper: DBHelper
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_novasenha)
//        initViews()
//        initObjects()
//        initListeners()
////
////        val intent = intent
////        email = intent.getStringExtra("EMAIL")!!
//    }
//
//    private fun initListeners() {
//        appCompatButtonReset!!.setOnClickListener(this)
//    }
//
//    private fun initObjects() {
//
//        databaseHelper = DBHelper(this, null)
//        inputValidation = InputValidation(this)
//    }
//
//    private fun initViews() {
//
//        nestedScrollView = findViewById(android.R.id.nestedScrollView) as NestedScrollView
//
//        textInputEditTextPassword = findViewById(android.R.id.textInputpass) as TextInputEditText
//        textInputEditTextConfirmPassword = findViewById(android.R.id.textInputConfirmPassword) as TextInputEditText
//
//        textInputLayoutPassword = findViewById(android.R.id.textInputLayoutPassword) as TextInputLayout
//        textInputLayoutConfirmPassword = findViewById(android.R.id.textInputConfirmPass) as TextInputLayout
//
//    }
//
////    private fun updatePassword() {
////        val value1: String = textInputpass.getText().toString().trim()
////        val value2: String = textInputConfirmPassword.getText().toString().trim()
////        if (value1.isEmpty() && value2.isEmpty()) {
////            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_LONG).show()
////            return
////        }
////        if (!value1.contentEquals(value2)) {
////            Toast.makeText(this, "Senhas não correspondentes", Toast.LENGTH_LONG).show()
////            return
////        }
////        if (!databaseHelper.checkUser(email)) {
////            Snackbar.make(nestedScrollView, "Email inválido", Snackbar.LENGTH_LONG).show()
////            return
////        } else {
////            databaseHelper.updatePassword(email, value1)
////            Toast.makeText(this, "Senha alterada com sucesso", Toast.LENGTH_SHORT).show()
////            emptyInputEditText()
////            val intent = Intent(this, ActivityLogin::class.java)
////            startActivity(intent)
////            finish()
////        }
////    }
////    private fun UpdatePassword(){
////
////        val value1 = textInputpass.text.toString().trim { it <= ' ' }
////        val value2 = textInputConfirmPassword.text.toString().trim { it <= ' ' }
////
////        if (value1.isEmpty() && value2.isEmpty()) {
////            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_LONG).show()
////            return
////        }
////
////        if (!value1.contentEquals(value2)) {
////            Toast.makeText(this, "Senhas não correspondentes", Toast.LENGTH_LONG).show()
////            return
////        }
////
////        if (!databaseHelper.checkUser(email)) {
////            Snackbar.make(nestedScrollView, "Email inválido", Snackbar.LENGTH_LONG).show()
////            return
////        } else {
////            databaseHelper.updatePassword(email, value1)
////            Toast.makeText(this, "Senha alterada com sucesso", Toast.LENGTH_SHORT).show()
////            emptyInputEditText()
////            val intent = Intent(this, ActivityLogin::class.java)
////            startActivity(intent)
////            finish()
////        }
////    }
////
////    private fun emptyInputEditText() {
////        textInputpass!!.text = null
////        textInputConfirmPassword!!.text = null
////    }
//
//    override fun onClick(p0: View) {
////        when(p0.id){R.id.appCompatButtonReset -> updatePassword()}
//    }
//
//}
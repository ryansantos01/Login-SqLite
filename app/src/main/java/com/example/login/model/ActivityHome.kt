package com.example.login.model

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.login.R

class ActivityHome : AppCompatActivity() {

    private lateinit var btnHoLogin: androidx.appcompat.widget.AppCompatButton
    private lateinit var btnCadastro: androidx.appcompat.widget.AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar!!.hide()

        btnHoLogin = findViewById(R.id.loginHoBtn)
        btnCadastro = findViewById(R.id.cadastroHoBtn)

        //i = abreviação de intent
        //Botao que faz ir da Home para LoginActivity
        btnHoLogin.setOnClickListener {
            val i0 = Intent(applicationContext, ActivityLogin::class.java)
            startActivity(i0)
        }
        //Botao que faz ir da Home para CadastroActivity
        btnCadastro.setOnClickListener {
            val i1 = Intent(applicationContext, ActivityRegister::class.java)
            startActivity(i1)
        }

    }
}
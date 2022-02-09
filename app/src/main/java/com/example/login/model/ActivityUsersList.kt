package com.example.login.model

import android.os.AsyncTask
import android.os.Bundle

import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import com.example.login.DB.DBHelper
import com.example.login.R
import com.example.login.User
import com.example.login.UsersRecyclerAdapter


class ActivityUsersList : AppCompatActivity() {

    private val activity = this@ActivityUsersList
    private lateinit var textViewName: AppCompatTextView
    private lateinit var recyclerViewUsers: RecyclerView
    private lateinit var listUsers: MutableList<User>
    private lateinit var usersRecyclerAdapter: UsersRecyclerAdapter
    private lateinit var databaseHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_list)
        supportActionBar!!.title = ""
        initViews()
        initObjects()

    }

    /**
     * Para iniciar as views
     */
    private fun initViews() {
        textViewName = findViewById(R.id.textViewName) as AppCompatTextView
        recyclerViewUsers = findViewById(R.id.recyclerViewUsers) as RecyclerView
    }

    /**
     * Para iniciar os objetos
     */
    private fun initObjects() {
        listUsers = ArrayList<User>()
        usersRecyclerAdapter = UsersRecyclerAdapter(listUsers)

        val mLayoutManager = LinearLayoutManager(applicationContext)
        recyclerViewUsers.layoutManager = mLayoutManager
        recyclerViewUsers.itemAnimator = DefaultItemAnimator()
        recyclerViewUsers.setHasFixedSize(true)
        recyclerViewUsers.adapter = UsersRecyclerAdapter(listUsers)
        databaseHelper = DBHelper(activity, factory = null)

        val emailFromIntent = intent.getStringExtra("EMAIL")
        textViewName.text = emailFromIntent

        var getDataFromSQLite = GetDataFromSQLite()
        getDataFromSQLite.execute()
    }

    /**
     * classe para buscar todos os registros de usuário do SQLite
     */
    public final inner class GetDataFromSQLite : AsyncTask<Void, Void, ArrayList<User>>() {

        override fun doInBackground(vararg p0: Void?): ArrayList<User> {
            return databaseHelper.getAllUser()
        }

        override fun onPostExecute(result: ArrayList<User>?) {
            super.onPostExecute(result)
            listUsers.clear()
            listUsers.addAll(result!!)
        }

    }
}
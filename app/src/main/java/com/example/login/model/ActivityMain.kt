package com.example.login.model
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.login.DB.DBHelper
import com.example.login.databinding.ActivityMainBinding

class ActivityMain : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setupViews()
        binding.addName.setOnClickListener {
            val db = DBHelper(this, null)

            // creating variables for values
            // in name and age edit texts
            val name = binding.enterName.text.toString()
            val email = binding.enterEmail.text.toString()
            val password = binding.enterPasswd.text.toString()

            // calling method to add
            // name to our database
            db.addName(name, email, password)

            // Toast to message on the screen
            Toast.makeText(this, "$name added to database", Toast.LENGTH_LONG).show()

            // at last, clearing edit texts
            binding.enterName.text.clear()
            binding.enterEmail.text.clear()
            binding.enterPasswd.text.clear()
        }
        binding.printName.setOnClickListener{

            // creating a DBHelper class
            // and passing context to it
            val db = DBHelper(this, null)

            // below is the variable for cursor
            // we have called method to get
            // all names from our database
            // and add to name text view

            val cursor = db.getName()
            // movendo o cursor para a primeira posição e
            // acrescentando valor na TextView
            cursor!!.moveToFirst()
            // movendo o cursor para a primeira posição e
            // acrescentando valor na TextView
            cursor.moveToFirst()
            binding.Name.append(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_USER_NAME)) + "\n")
            binding.Email.append(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_USER_EMAIL)) + "\n")
            binding.Pk.append(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_USER_ID)) + "\n")

            // moving our cursor to next
            // position and appending values
            while(cursor.moveToNext()){
                binding.Name.append(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_USER_NAME)) + "\n")
                binding.Email.append(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_USER_EMAIL)) + "\n")
                binding.Pk.append(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_USER_ID)) + "\n")

            }
            // at last we close our cursor
            cursor.close()
        }
    }

}

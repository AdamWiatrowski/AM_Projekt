package com.example.shaker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Button
import android.widget.EditText
import com.example.shaker.data.ShakerDatabase
import com.example.shaker.data.tables.Tag
import com.example.shaker.data.tables.User

import kotlinx.coroutines.*

import kotlinx.coroutines.flow.first


class Login : AppCompatActivity() {

    private lateinit var db: ShakerDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // initialize your database
        CoroutineScope(Dispatchers.IO).launch {
            db = ShakerDatabase.getDatabase(applicationContext)

            // fill DB with tags
            db.tagDao().addTag(Tag("Kurtki"))
            db.tagDao().addTag(Tag("Obuwie"))
            db.tagDao().addTag(Tag("Krótkie rękawy"))
            db.tagDao().addTag(Tag("Długie rękawy"))
            db.tagDao().addTag(Tag("Długie spodnie"))
            db.tagDao().addTag(Tag("Krótkie spodnie"))
            db.tagDao().addTag(Tag("Sukienki"))
            db.tagDao().addTag(Tag("Bluzy"))
            db.tagDao().addTag(Tag("Skarpety"))
            db.tagDao().addTag(Tag("Bielizna"))
            db.tagDao().addTag(Tag("Koszule"))
            db.tagDao().addTag(Tag("Rękawiczki"))
            db.tagDao().addTag(Tag("Szalik"))
        }

        // find your buttons and text fields
        val loginButton: Button = findViewById(R.id.login)
        val registerButton: Button = findViewById(R.id.register)
        val usernameField: EditText = findViewById(R.id.username)
        val passwordField: EditText = findViewById(R.id.password)

        // set an onClickListener for your register button
        registerButton.setOnClickListener {
            val username = usernameField.text.toString()
            val password = passwordField.text.toString()

            // perform some basic validation
            if (username.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Username oraz password nie moga byc puste!", Toast.LENGTH_SHORT).show()
            } else {
                CoroutineScope(Dispatchers.IO).launch {
                    val userExists = db.userDao().isUserExist(username)
                    if (userExists){
                        withContext(Dispatchers.Main) {
                            Toast.makeText(applicationContext, "Użytkownik istnieje!", Toast.LENGTH_SHORT).show()
                        }
                    }
                    else {
                        // insert the new user into the database
                        db.userDao().registerUser(User(username, password))

                        // switch to the main thread to display a Toast
                        withContext(Dispatchers.Main) {
                            Toast.makeText(applicationContext, "Zarejestrowano!", Toast.LENGTH_SHORT).show()
                        }
                    }

                }
            }
        }

        // set an onClickListener for your login button
        loginButton.setOnClickListener {
            val username = usernameField.text.toString()
            val password = passwordField.text.toString()

            // perform some basic validation
            if (username.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Username oraz password nie moga byc puste!", Toast.LENGTH_SHORT).show()
            } else {
                CoroutineScope(Dispatchers.IO).launch {
                    // get the user with the provided username and password from the database
                    val userFlow = db.userDao().getUser(username, password)
                    val user = userFlow.first()


                    // switch to the main thread to display a Toast
                    withContext(Dispatchers.Main) {
                        if (user != null) {
                            Toast.makeText(applicationContext, "Zalogowano!", Toast.LENGTH_SHORT).show()
                            // przejscie dalej;
                        } else {
                            Toast.makeText(applicationContext, "Niepoprawny username lub password!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}




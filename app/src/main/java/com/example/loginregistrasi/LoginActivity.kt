package com.example.loginregistrasi

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private var usernameEditText: EditText? = null
    private var passwordEditText: EditText? = null
    private var loginButton: Button? = null
    private var registerButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_login)

        usernameEditText = findViewById(R.id.username)
        passwordEditText = findViewById(R.id.password)
        loginButton = findViewById(R.id.loginButton)
        registerButton = findViewById(R.id.registerButton)

        loginButton?.setOnClickListener {
            val username = usernameEditText?.text.toString().trim()
            val password = passwordEditText?.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                    this@LoginActivity,
                    "Username dan Password tidak boleh kosong",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val sharedPref = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
                val registeredUsername = sharedPref.getString("username", "admin")
                val registeredPassword = sharedPref.getString("password", "1234")

                if (username == registeredUsername && password == registeredPassword) {
                    Toast.makeText(this@LoginActivity, "Login Berhasil", Toast.LENGTH_SHORT).show()
                    
                    // PINDAH KE MAIN ACTIVITY
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish() // Menutup LoginActivity agar tidak bisa kembali dengan tombol back
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        "Username atau Password salah",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        registerButton?.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
    }
}

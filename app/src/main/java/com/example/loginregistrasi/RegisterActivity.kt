package com.example.loginregistrasi

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    private var usernameEditText: EditText? = null
    private var passwordEditText: EditText? = null
    private var confirmPasswordEditText: EditText? = null
    private var registerButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Sembunyikan ActionBar agar seragam dengan Login
        supportActionBar?.hide()
        setContentView(R.layout.activity_register)

        usernameEditText = findViewById(R.id.username)
        passwordEditText = findViewById(R.id.password)
        confirmPasswordEditText = findViewById(R.id.confirmPassword)
        registerButton = findViewById(R.id.registerButton)

        registerButton?.setOnClickListener {
            val username = usernameEditText?.text.toString().trim()
            val password = passwordEditText?.text.toString().trim()
            val confirmPassword = confirmPasswordEditText?.text.toString().trim()

            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(
                    this@RegisterActivity,
                    "Semua field harus diisi",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (password.length < 4) {
                Toast.makeText(
                    this@RegisterActivity,
                    "Password minimal 4 karakter",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (password != confirmPassword) {
                Toast.makeText(
                    this@RegisterActivity,
                    "Password dan Konfirmasi Password tidak cocok",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                // Simpan data ke SharedPreferences agar bisa dibaca di LoginActivity
                val sharedPref = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
                with(sharedPref.edit()) {
                    putString("username", username)
                    putString("password", password)
                    apply()
                }

                Toast.makeText(this@RegisterActivity, "Registrasi Berhasil", Toast.LENGTH_SHORT)
                    .show()
                finish()
            }
        }
    }
}

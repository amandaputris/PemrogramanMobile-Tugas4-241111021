package com.example.loginregistrasi

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView

class MainActivity : AppCompatActivity() {
    private var welcomeText: TextView? = null
    private var usernameText: TextView? = null
    private var logoutButton: Button? = null
    private var menuFood: MaterialCardView? = null
    private var menuDrink: MaterialCardView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Cek apakah user sudah login (opsional untuk keamanan)
        val sharedPref = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val username = sharedPref.getString("username", null)
        
        // Jika karena suatu alasan username null, kita berikan default atau kembalikan ke login
        val displayUsername = username ?: "Admin"

        try {
            supportActionBar?.hide()
            setContentView(R.layout.activity_main)

            welcomeText = findViewById(R.id.welcomeText)
            usernameText = findViewById(R.id.usernameText)
            logoutButton = findViewById(R.id.logoutButton)
            menuFood = findViewById(R.id.menuFood)
            menuDrink = findViewById(R.id.menuDrink)

            welcomeText?.text = "Selamat Datang!"
            usernameText?.text = "Login sebagai: $displayUsername"

            menuFood?.setOnClickListener {
                val intent = Intent(this, EventListActivity::class.java)
                intent.putExtra("CATEGORY", "Makanan")
                startActivity(intent)
            }

            menuDrink?.setOnClickListener {
                val intent = Intent(this, EventListActivity::class.java)
                intent.putExtra("CATEGORY", "Minuman")
                startActivity(intent)
            }

            logoutButton?.setOnClickListener {
                Toast.makeText(this@MainActivity, "Logout berhasil", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                finish()
            }
        } catch (e: Exception) {
            // Jika terjadi error saat load layout, tampilkan pesan
            Toast.makeText(this, "Gagal memuat halaman utama: ${e.message}", Toast.LENGTH_LONG).show()
            finish()
        }
    }
}

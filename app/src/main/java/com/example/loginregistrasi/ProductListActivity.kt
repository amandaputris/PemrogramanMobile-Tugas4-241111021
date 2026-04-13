package com.example.loginregistrasi

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProductListActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter
    private lateinit var categoryTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_event_list) // Menggunakan layout yang sama

        categoryTitle = findViewById(R.id.categoryTitle)
        recyclerView = findViewById(R.id.recyclerViewEvent)

        val category = intent.getStringExtra("CATEGORY") ?: "Makanan"
        categoryTitle.text = "Daftar $category"

        val data = if (category == "Makanan") {
            getFoodData()
        } else {
            getDrinkData()
        }

        adapter = ProductAdapter(data)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun getFoodData(): List<Product> {
        return listOf(
            Product(1, "Nasi Goreng Spesial", "Nasi goreng dengan telur dan ayam", "Rp 25.000"),
            Product(2, "Mie Ayam Bakso", "Mie ayam dengan bakso sapi asli", "Rp 20.000"),
            Product(3, "Sate Ayam Madura", "10 tusuk sate ayam bumbu kacang", "Rp 30.000"),
            Product(4, "Ayam Bakar Taliwang", "Ayam bakar pedas khas Lombok", "Rp 45.000"),
            Product(5, "Gado-Gado Jakarta", "Sayuran segar dengan bumbu kacang", "Rp 18.000")
        )
    }

    private fun getDrinkData(): List<Product> {
        return listOf(
            Product(1, "Es Teh Manis", "Teh seduh segar dengan gula asli", "Rp 5.000"),
            Product(2, "Jus Alpukat", "Jus alpukat kental dengan coklat", "Rp 15.000"),
            Product(3, "Kopi Susu Gula Aren", "Kopi robusta dengan susu dan aren", "Rp 18.000"),
            Product(4, "Es Jeruk Peras", "Jeruk peras murni tanpa pengawet", "Rp 10.000"),
            Product(5, "Soda Gembira", "Campuran sirup, susu, dan soda", "Rp 12.000")
        )
    }
}

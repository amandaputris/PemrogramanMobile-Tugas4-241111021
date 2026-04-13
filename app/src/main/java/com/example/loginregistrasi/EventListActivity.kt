package com.example.loginregistrasi

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EventListActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: EventAdapter
    private lateinit var categoryTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_event_list)

        categoryTitle = findViewById(R.id.categoryTitle)
        recyclerView = findViewById(R.id.recyclerViewEvent)

        // Mengambil kategori dari intent (Makanan atau Minuman)
        val category = intent.getStringExtra("CATEGORY") ?: "Makanan"
        categoryTitle.text = "Daftar Penjualan $category"

        val data = if (category == "Makanan") {
            getFoodData()
        } else {
            getDrinkData()
        }

        adapter = EventAdapter(data)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun getFoodData(): List<Event> {
        return listOf(
            Event(1, "Nasi Goreng Spesial", "Tersedia", "Dapur Utama", "Rp 25.000"),
            Event(2, "Mie Ayam Bakso", "Tersedia", "Dapur Utama", "Rp 20.000"),
            Event(3, "Sate Ayam Madura", "Tersedia", "Area Bakar", "Rp 30.000"),
            Event(4, "Ayam Bakar Taliwang", "Stok Terbatas", "Dapur Utama", "Rp 45.000"),
            Event(5, "Gado-Gado Jakarta", "Tersedia", "Area Sayur", "Rp 18.000")
        )
    }

    private fun getDrinkData(): List<Event> {
        return listOf(
            Event(1, "Es Teh Manis", "Dingin", "Bar Minuman", "Rp 5.000"),
            Event(2, "Jus Alpukat Kocok", "Dingin", "Bar Buah", "Rp 15.000"),
            Event(3, "Kopi Susu Gula Aren", "Panas/Dingin", "Coffee Corner", "Rp 18.000"),
            Event(4, "Es Jeruk Peras", "Segar", "Bar Buah", "Rp 10.000"),
            Event(5, "Soda Gembira", "Dingin", "Bar Minuman", "Rp 12.000")
        )
    }
}

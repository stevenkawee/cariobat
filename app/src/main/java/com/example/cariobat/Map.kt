package com.example.cariobat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager

import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import kotlin.collections.Map

class Map : AppCompatActivity() {
    private lateinit var mapView: MapView
    private lateinit var etSearch: EditText
    private lateinit var btnSearch: Button
    private lateinit var btnback4: Button
    private val LOCATION_PERMISSION_REQUEST_CODE = 1
    private lateinit var requestQueue: RequestQueue


    // Koordinat ISTTS
    private val ISTTS_LAT = -7.2912752
    private val ISTTS_LON = 112.7585951
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        // Memuat konfigurasi OSMDroid
        Configuration.getInstance().load(this, getSharedPreferences("osmdroid", MODE_PRIVATE))

        // Inisialisasi MapView
        mapView = findViewById(R.id.mapView)
        mapView.setTileSource(TileSourceFactory.MAPNIK)
        mapView.setBuiltInZoomControls(true)
        mapView.setMultiTouchControls(true)

        // Set posisi awal pada peta (misalnya di ISTTS)
        val startPoint = GeoPoint(-7.2912752, 112.7585951) // Koordinat ISTTS
        mapView.controller.setCenter(startPoint)
        mapView.controller.setZoom(17.0)

        // Inisialisasi EditText dan Button
        etSearch = findViewById(R.id.etSearch)
        btnSearch = findViewById(R.id.btnSearch)
        btnback4 = findViewById(R.id.btnback4)

        // Inisialisasi RequestQueue untuk Volley
        requestQueue = Volley.newRequestQueue(this)

        // Set klik listener untuk tombol pencarian
        btnSearch.setOnClickListener {
            val query = etSearch.text.toString()
            if (query.isNotEmpty()) {
                searchPharmacy(query)
            } else {
                Toast.makeText(this, "Masukkan alamat atau nama apotek.", Toast.LENGTH_SHORT).show()
            }
        }

        btnback4.setOnClickListener {
            val intent = Intent(this, home_page::class.java)
            startActivity(intent)
            finish()

        }


        // Meminta izin lokasi jika diperlukan
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
        }
    }

    private fun searchPharmacy(query: String) {
        // Menggunakan Nominatim API untuk geocoding
        val url = "https://nominatim.openstreetmap.org/search?q=${query}&format=json&limit=1"

        // Membuat request ke Nominatim API
        val jsonArrayRequest = JsonArrayRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                try {
                    // Nominatim mengembalikan array JSON
                    if (response.length() > 0) {
                        val place = response.getJSONObject(0) // Mendapatkan objek pertama dari array
                        val lat = place.getDouble("lat")
                        val lon = place.getDouble("lon")

                        // Set marker pada lokasi apotek
                        val geoPoint = GeoPoint(lat, lon)
                        mapView.controller.setCenter(geoPoint)
                        mapView.controller.setZoom(18.0)

                        // Menambahkan marker pada lokasi
                        val marker = Marker(mapView)
                        marker.position = geoPoint
                        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                        marker.title = place.getString("display_name")
                        mapView.overlays.add(marker)
                        mapView.invalidate() // Refresh peta

                    } else {
                        Toast.makeText(this, "Lokasi tidak ditemukan.", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                    Toast.makeText(this, "Terjadi kesalahan saat mencari lokasi.", Toast.LENGTH_SHORT).show()
                }
            },
            Response.ErrorListener { error ->
                error.printStackTrace()
                Toast.makeText(this, "Terjadi kesalahan jaringan.", Toast.LENGTH_SHORT).show()
            })

        // Menambahkan request ke antrian Volley
        requestQueue.add(jsonArrayRequest)
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDetach() // Bersihkan resource saat mapView tidak lagi digunakan
    }
}
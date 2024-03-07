package com.example.proyectocei.mapa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyectocei.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class UbicacionActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ubicacion)

        createFragment()
    }

    private fun createFragment() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googlemap: GoogleMap) {
        map = googlemap
        createMarker()
    }

    private fun createMarker() {
        val coordinates = LatLng(43.352563, -8.420131)
        val marker = MarkerOptions().position(coordinates).title("Congreso Fedem")
        map.addMarker(marker)
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(coordinates, 15f),
            4000,
            null
        )
    }
}



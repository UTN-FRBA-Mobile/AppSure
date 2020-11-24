package com.utn.appsure.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.utn.appsure.R
import com.utn.appsure.viewmodel.MapViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MapFragment : Fragment(), OnMapReadyCallback {
    private val viewModel by viewModel<MapViewModel>()

    private lateinit var mapFragment: SupportMapFragment
    private lateinit var mMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_map, container, false)

        mapFragment = SupportMapFragment.newInstance()

        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.googleMap, mapFragment)
        transaction.commit()



        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.policies.observe(viewLifecycleOwner, Observer {
            //test_label.text = it?.joinToString()
        })
        viewModel.getPolicies()

        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        //mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}
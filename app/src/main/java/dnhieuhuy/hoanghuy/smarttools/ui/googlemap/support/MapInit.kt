package com.arasthel.spannedgridlayoutmanager.sample.googlemap.support

import android.annotation.SuppressLint
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

/**
 * Created by Administrator on 03/07/2017.
 */
class MapInit
{
    @SuppressLint("MissingPermission")
    constructor(mMap: GoogleMap, latitude: Double, longitude: Double)
    {
        // Add a market in Sydney and move the camera
        val sydney = LatLng(latitude, longitude)
        mMap.addMarker(MarkerOptions().position(sydney).title("Hey,I'm Here!"))
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE)
        mMap.setMyLocationEnabled(true)
        mMap.getUiSettings().setZoomControlsEnabled(true)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,15.5F))
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10.0F), 2000, null)

    }
}
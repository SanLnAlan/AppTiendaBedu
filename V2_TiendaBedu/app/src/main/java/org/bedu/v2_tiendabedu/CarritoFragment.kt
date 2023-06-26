package org.bedu.v2_tiendabedu

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import org.bedu.v2_tiendabedu.utilitis.NotificationApp
import org.bedu.v2_tiendabedu.utilitis.Tabla
import org.bedu.v2_tiendabedu.utilitis.executeOrRequestPermission
import org.bedu.v2_tiendabedu.utilitis.carritoPendienteNotification

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CarritoFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_carrito, container, false)
    }

    private lateinit var buttonPagar : Button
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onStop() {
        super.onStop()
        Log.i("not","on stop")

        //Lanzamiento de notification
        try {
            runCarritoPendienteNotification()
        } catch (exception: Exception){
            Log.i("not","$exception")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonPagar = view.findViewById(R.id.button_pagar)
        val txtTotalPagar = view.findViewById<TextView>(R.id.textTotalOrden)
        val notificationApp = NotificationApp()

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        buttonPagar.setOnClickListener {
            getLocation()
            showLoadingFragment()
        }

        val tabla = Tabla(context as Activity?, view.findViewById(R.id.tabla) )
        tabla.agregarCabecera(R.array.cabecera_tabla)
        if( orden.listaProducto.isNotEmpty()){
            var totalP = orden.calcularTotal().toString()
            totalP = "$" + totalP
            txtTotalPagar.text = totalP

            for (p in orden.listaProducto){
                val elementos = ArrayList<String>()
                elementos.add(p["_id"].toString())
                elementos.add(p["Descripción"].toString())
                elementos.add(p["Cantidad"].toString())
                elementos.add(p["precio"].toString())
                elementos.add(p["statusProducto"].toString())
                tabla.agregarFilaTabla(elementos)
            }
        }
    }
    companion object {

        const val PERMISSION_ID = 33
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CarritoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun checkGranted(permission: String) =
        ActivityCompat.checkSelfPermission(requireContext(), permission) == PackageManager.PERMISSION_GRANTED

    private fun checkPermissions() =
        checkGranted(android.Manifest.permission.ACCESS_COARSE_LOCATION) &&
                checkGranted(android.Manifest.permission.ACCESS_FINE_LOCATION)

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_ID
        )
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager : LocationManager = requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    @SuppressLint("MissingPermission")
    private fun getLocation(){
        if (checkPermissions()){
            if (isLocationEnabled()){
                fusedLocationClient.lastLocation.addOnSuccessListener{
                    if (it != null){
                        Toast.makeText(requireContext(),"latitude: ${it.latitude.toString()} longitude: ${it.longitude.toString()}",Toast.LENGTH_SHORT).show()
                        Log.d("gps",it.latitude.toString())
                    } else {
                        Toast.makeText(requireContext(),"ubicación nula",Toast.LENGTH_SHORT).show()
                        Log.d("gps","nula gps")
                    }
                }
            }
        } else {
          requestPermissions()
        }
    }

    private fun runCarritoPendienteNotification(){
        Log.i("not",orden.listaProducto[0]["precio"].toString())
        executeOrRequestPermission(requireActivity()){
            carritoPendienteNotification(requireActivity(),requireContext())
        }
    }

    private fun showLoadingFragment() {
        val fragmentManager = childFragmentManager
        PagandoFragment.newInstance("pagando")
            .show(fragmentManager,"fragment_pagando")

    }
}
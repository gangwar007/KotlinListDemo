package com.india.kotlinlistdemo.views

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.*
import com.india.kotlinlistdemo.R
import com.india.kotlinlistdemo.adapter.StudioAdapter
import com.india.kotlinlistdemo.databinding.ActivityMainBinding
import com.india.kotlinlistdemo.view_model.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var mFusedLocationClient: FusedLocationProviderClient
    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel =
            ViewModelProvider.AndroidViewModelFactory(application).create(MainViewModel::class.java)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
            .apply {
                this.lifecycleOwner = this@MainActivity
                this.viewModel = mainViewModel
            }
        initObserver()
        initLocationManager()
    }

    private fun initLocationManager(){
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        if (!checkPermissions()){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                requestPermissions()
            }
        }else{
            getLastLocation()
        }
    }


   fun initObserver(){
     mainViewModel.studiosResponse.observe(this, androidx.lifecycle.Observer {

         print("Size1 : "+it)
         if(it.code==200){
            binding.rvStudioList.apply {
                adapter = StudioAdapter(it.data!!)
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            }
         }
     })
   }

    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                mFusedLocationClient.lastLocation.addOnCompleteListener(this) { task ->
                    var location: Location? = task.result
                    if (location == null) {
                        requestNewLocationData()
                    } else {
                        mainViewModel.getStudiosApi("1",location.latitude.toString(),location.longitude.toString())
                        Toast.makeText(this, location.latitude.toString()+" : "+location.longitude.toString(), Toast.LENGTH_SHORT).show();

                    }
                }
            } else {
                Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            requestPermissions()
        }
    }


    @SuppressLint("MissingPermission")
    private fun requestNewLocationData() {
        var mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 0
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mFusedLocationClient!!.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            Looper.myLooper()
        )
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            var mLastLocation: Location = locationResult.lastLocation
            Toast.makeText(applicationContext, mLastLocation.latitude.toString()+" : "+mLastLocation.longitude.toString(), Toast.LENGTH_SHORT).show();

        }
    }

    private fun isLocationEnabled(): Boolean {
        var locationManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION),
            44
        )
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == 44) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                getLastLocation()
            }
        }
    }

}

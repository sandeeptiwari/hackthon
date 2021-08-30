package com.solvathon

import android.Manifest
import android.os.Bundle
import android.view.Menu
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.solvathon.core.PermissionUtil
import com.solvathon.core.PermissionUtil.netPermissions
import com.solvathon.databinding.ActivityMainBinding
import com.solvathon.databinding.ActivitySplashBinding
import com.solvathon.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    val PERMISSIONS_REQUEST_CODE = 124
    val CALENDER_PERMISSIONS_REQUEST_CODE = 125
    val ALL_PERMISSIONS_REQUEST_CODE = 126
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_locator, R.id.nav_support, R.id.nav_elocker, R.id.nav_more
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        checkIfAlPermissionGiven()
    }

    override fun findContentView(): Int {
       return R.layout.activity_main
    }

    override fun bindViewWithViewBinding(view: View) {
        binding = ActivityMainBinding.bind(view)
    }

    private fun checkIfAlPermissionGiven() {
        val perms = arrayOf<String>(
            Manifest.permission.READ_CALENDAR,
            Manifest.permission.WRITE_CALENDAR,
            Manifest.permission.INTERNET,
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        if (PermissionUtil.ifPermissionGiven(this)) {
//do nothing
        } else if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_CALENDAR) || shouldShowRequestPermissionRationale(
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
            || shouldShowRequestPermissionRationale(Manifest.permission.RECORD_AUDIO) ||  shouldShowRequestPermissionRationale(
                Manifest.permission.CAMERA) ) {
            requestPermissions(
                netPermissions(perms,this),
                ALL_PERMISSIONS_REQUEST_CODE
            )
        } else {
            requestPermissions(
                netPermissions(perms,this),
                ALL_PERMISSIONS_REQUEST_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == CALENDER_PERMISSIONS_REQUEST_CODE) {

        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }

    }
}
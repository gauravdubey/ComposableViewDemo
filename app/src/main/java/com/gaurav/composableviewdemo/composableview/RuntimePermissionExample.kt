package com.gaurav.composableviewdemo.composableview

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

@Composable
fun MultiplePermissionsExample() {
    val context = LocalContext.current
    val permissions = listOf(
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.ACCESS_FINE_LOCATION
    )

    // State to track permissions
    var permissionsGranted by remember { mutableStateOf(false) }
    var permissionsToRequest by remember { mutableStateOf(permissions) }
    var showPermissionRationale by remember { mutableStateOf(false) }


    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
        data = Uri.fromParts("package", context.packageName, null)
    }
    context.startActivity(intent)

    // Permission request launcher
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissionsResult ->
            val deniedPermissions = permissionsResult.filterValues { !it }.keys
            permissionsGranted = deniedPermissions.isEmpty()
            showPermissionRationale = deniedPermissions.any { permission ->
                ActivityCompat.shouldShowRequestPermissionRationale(context as Activity, permission)
            }
            permissionsToRequest = deniedPermissions.toList()
        }
    )

    // Initial permission check
    LaunchedEffect(Unit) {
        val deniedPermissions = permissions.filter { permission ->
            ContextCompat.checkSelfPermission(
                context,
                permission
            ) != PackageManager.PERMISSION_GRANTED
        }
        permissionsGranted = deniedPermissions.isEmpty()
        permissionsToRequest = deniedPermissions
    }

    // UI for permissions
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when {
            permissionsGranted -> {
                Text("All permissions granted! You can now use the camera and location features.")
            }

            showPermissionRationale -> {
                Text("This app needs Camera and Location permissions to work properly.")
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { permissionLauncher.launch(permissionsToRequest.toTypedArray()) }) {
                    Text("Grant Permissions")
                }
            }

            else -> {
                Text("Permissions not granted. Please grant them to proceed.")
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { permissionLauncher.launch(permissionsToRequest.toTypedArray()) }) {
                    Text("Request Permissions")
                }
            }
        }
    }
}



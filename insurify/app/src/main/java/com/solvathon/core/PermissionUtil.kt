package com.solvathon.core

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import androidx.core.content.ContextCompat
import com.solvathon.R
import java.util.ArrayList


object PermissionUtil {

    /*check if permissions are there*/
    fun verifyPermissions(grantResults: IntArray): Boolean {
        // At least one result must be checked.
        if (grantResults.isEmpty()) {
            return false
        }

        // Verify that each required permission has been granted, otherwise return false.
        for (result in grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    fun hasPermission(permissionString: String,context: Context): Boolean {
        return ContextCompat.checkSelfPermission(context, permissionString) == PackageManager.PERMISSION_GRANTED
    }
    fun netPermissions(wantedPermissions: Array<String>,context: Context): Array<String> {
        val result = ArrayList<String>()
        for (permission in wantedPermissions) {
            if (!hasPermission(permission, context)) {
                result.add(permission)
            }
        }
        return result.toTypedArray()
    }


    fun canOpenCalender(context: Context): Boolean {
        return (hasPermission(Manifest.permission.WRITE_CALENDAR,context)
                && hasPermission(Manifest.permission.READ_CALENDAR,context))
    }


    fun ifPermissionGiven(context: Context): Boolean {
        return (hasPermission(Manifest.permission.WRITE_CALENDAR,context)
                && hasPermission(Manifest.permission.READ_CALENDAR,context)
                && hasPermission(Manifest.permission.INTERNET,context)
                && hasPermission(Manifest.permission.CAMERA,context)
                && hasPermission(Manifest.permission.RECORD_AUDIO,context)
                && hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,context)
                && hasPermission(Manifest.permission.READ_EXTERNAL_STORAGE,context)
                && hasPermission(Manifest.permission.ACCESS_FINE_LOCATION,context))
    }


    /*if user clicks for deny and don't ask again permission, this dialog will be shown*/
    fun displayDialogDenyAndDontAsk(context: Context, permissionType: String) {
        val builder = AlertDialog.Builder(context, R.style.alertDialogCustom)

        when (permissionType) {
            "CaptureImage" -> builder.setMessage(context.getString(R.string.storage_permission_never_ask))
            "PickImage" -> builder.setMessage(context.getString(R.string.camera_permission_never_ask))
            "CaptureOrPickVideo" -> builder.setMessage(context.getString(R.string.video_permission_never_ask))
            "CalenderPermission" -> builder.setMessage(context.getString(R.string.calender_permission_never_ask))
            else ->  builder.setMessage(context.getString(R.string.permission_never_ask))
        }


        builder.setCancelable(false)
        builder.setPositiveButton(
            context.getString(R.string.permission_manually)
        ) { dialog, which ->
            dialog.dismiss()
            val intent = Intent()
            intent.action = android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            val uri = Uri.fromParts("package", context.packageName, null)
            intent.data = uri
            ContextCompat.startActivity(context, intent, null)
        }
        builder.setNegativeButton(context.getString(R.string.cancel), null)
        val dialog: AlertDialog = builder.create()

        dialog.show()

        val negativeButton = dialog.getButton(DialogInterface.BUTTON_NEGATIVE)
        negativeButton.setTextColor(context.resources.getColor(R.color.white))
        negativeButton.setBackgroundColor(Color.WHITE)

        val positiveButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE)
        positiveButton.setTextColor(ContextCompat.getColor(context, R.color.white))
        positiveButton.setBackgroundColor(Color.WHITE)
    }


}
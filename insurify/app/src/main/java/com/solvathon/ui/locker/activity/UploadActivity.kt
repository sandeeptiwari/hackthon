package com.solvathon.ui.locker.activity

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.core.content.ContextCompat
import com.solvathon.R
import com.solvathon.core.Common
import com.solvathon.databinding.ActivityUploadBinding
import com.solvathon.di.InsurifyApp
import com.solvathon.ui.base.BaseActivity
import java.io.File
import java.util.*
import android.webkit.MimeTypeMap

import android.content.ContentResolver
import android.content.Context
import android.provider.OpenableColumns
import com.solvathon.Util.DocPickerHelper
import com.solvathon.domain.pojo.Doc
import com.solvathon.ui.policy.PolicyActivity


class UploadActivity : BaseActivity() {
    private lateinit var doc: Doc

    companion object {
        const val TAG = "ImagePickerHelper"
        const val RESULT_LOAD_IMAGE = 2
        const val REQUEST_TAKE_PHOTO = 1
        const val CAMERA_VIDEO = 3
        const val GALLERY_VIDEO = 3
        private val PICK_OR_CAPTURE_IMAGE_PERMISSION = arrayOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
        )
        private val PICK_OR_CAPTURE_VIDEO_PERMISSION = arrayOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO
        )
        private const val PICK_IMAGE_PERMISSION_REQUEST_CODE = 102
        private const val CAPTURE_IMAGE_PERMISSION_REQUEST_CODE = 103
        private const val PICK_VIDEO_PERMISSION_REQUEST_CODE = 104
        private const val CAPTURE_VIDEO_PERMISSION_REQUEST_CODE = 105
    }

    lateinit var binding: ActivityUploadBinding
    private var captureImageRationalShown = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //actionbar
        val actionbar = supportActionBar

        //set back button
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true)
            actionbar.setDisplayHomeAsUpEnabled(true)
        }
        val docType = intent.getStringExtra("DOC_TYPE")

        //set actionbar title
        actionbar!!.title = "$docType Documents"
        handleUploadDocument()
        binding.btnAdd.setOnClickListener {
            val intent = Intent()
            intent.putExtra("DOC", doc)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    private fun handleUploadDocument() {
        binding.btnGallery.setOnClickListener {
            tryPickDoc();
        }
    }

    private fun tryPickDoc() {
        if (canPickImage()) {
            pickImage()
        } else if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            captureImageRationalShown = true
            requestPermissions(
                netPermissions(PICK_OR_CAPTURE_IMAGE_PERMISSION),
                PICK_IMAGE_PERMISSION_REQUEST_CODE
            )
        } else {
            requestPermissions(
                netPermissions(PICK_OR_CAPTURE_IMAGE_PERMISSION),
                PICK_IMAGE_PERMISSION_REQUEST_CODE
            )
        }
    }

    override fun findContentView(): Int {
        return R.layout.activity_upload
    }

    override fun bindViewWithViewBinding(view: View) {
        binding = ActivityUploadBinding.bind(view)
    }

    override fun toggleLoader(b: Boolean) {
        TODO("Not yet implemented")
    }

    private fun netPermissions(wantedPermissions: Array<String>): Array<String> {
        val result = ArrayList<String>()
        for (permission in wantedPermissions) {
            if (!hasPermission(permission)) {
                result.add(permission)
            }
        }
        return result.toTypedArray()
    }

    private fun hasPermission(permissionString: String): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            permissionString
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun pickImage() {
        val i = Intent(Intent.ACTION_PICK)
        i.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "*/*")
        startActivityForResult(i, RESULT_LOAD_IMAGE)
    }


    private fun canPickImage(): Boolean {
        return (hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                && hasPermission(Manifest.permission.READ_EXTERNAL_STORAGE))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                data.data?.let {
                    doc = getSaveImageUriDestination(it)
                    binding.selectedDoc.visibility = View.VISIBLE
                    binding.selectedDoc.text = "${doc.name}"
                }
            }
        }
    }
    private fun getSaveImageUriDestination(uri: Uri): Doc {
        val destin: Uri
        val root
        = File(InsurifyApp.mContext?.filesDir.toString() + "/${Common.APP_DIRECTORY}/Doc")
        if (!root.exists()) {
            root.mkdirs()
        }

        val mime = MimeTypeMap.getSingleton()
        val type:String? = mime.getExtensionFromMimeType(contentResolver.getType(uri))
        val docName = System.currentTimeMillis().toString() + ".$type"
        val sdImageMainDirectory = File(root, docName)
        destin = Uri.fromFile(sdImageMainDirectory)
        val pathFromUri = DocPickerHelper().getPathFromUri(this, destin);
        return Doc(1001, type, pathFromUri, getFileName(uri))
    }


    fun Context.getFileName(uri: Uri): String? = when(uri.scheme) {
        ContentResolver.SCHEME_CONTENT -> getContentFileName(uri)
        else -> uri.path?.let(::File)?.name
    }

    private fun Context.getContentFileName(uri: Uri): String? = runCatching {
        contentResolver.query(uri, null, null, null, null)?.use { cursor ->
            cursor.moveToFirst()
            return@use cursor.getColumnIndexOrThrow(OpenableColumns.DISPLAY_NAME).let(cursor::getString)
        }
    }.getOrNull()

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
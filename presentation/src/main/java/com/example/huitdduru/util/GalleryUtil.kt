package com.example.huitdduru.util

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream
import java.lang.Exception

fun openGallery(activity: Activity, startForResult: ActivityResultLauncher<Intent>) {
    val writePermission =
        ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    val readPermission =
        ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE)

    if (writePermission == PackageManager.PERMISSION_DENIED || readPermission == PackageManager.PERMISSION_DENIED) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ),
            0
        )
    } else {
        val intent = Intent(Intent.ACTION_PICK)
        intent.apply {
            data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            type = "image/*"
            putExtra("crop", "true")
            putExtra("aspectX", 1)
            putExtra("aspectY", 1)
        }
        startForResult.launch(intent)
    }
}

fun uriToMultipart(context: Context, filePath: String, uri: Uri): MultipartBody.Part {
    val file = File(filePath)
    var inputStream: InputStream? = null
    try {
        inputStream = context.contentResolver.openInputStream(uri)
    } catch (e: Exception) {
        e.printStackTrace()
    }

    val bitmap = BitmapFactory.decodeStream(inputStream)
    val byteArrayOutputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream)
    val requestFile =
        byteArrayOutputStream.toByteArray().toRequestBody("image/jpg".toMediaTypeOrNull())
    val body = MultipartBody.Part.createFormData("file", file.name, requestFile)
    return body
}

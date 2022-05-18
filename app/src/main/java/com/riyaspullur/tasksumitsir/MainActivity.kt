package com.riyaspullur.tasksumitsir

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        videoBtnID.isEnabled=false
        captureImageBtnID.isEnabled=false

        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.CAMERA)!==PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 111)
        }
        else{
            videoBtnID.isEnabled=true
            captureImageBtnID.isEnabled=true
        }

        videoBtnID.setOnClickListener {
           var i = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
            startActivityForResult(i,1111)
        }
        captureImageBtnID.setOnClickListener {
            var i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(i,100)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100 && resultCode == RESULT_OK) {
            val bitmap = data?.extras!!["data"] as Bitmap?
            imagViewID.setImageBitmap(bitmap)
        }
        if (requestCode==1111){
            videoViewID.setVideoURI(data?.data)
            videoViewID.start();
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode==100 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            videoBtnID.isEnabled=true
            captureImageBtnID.isEnabled=true
        }

    }
}
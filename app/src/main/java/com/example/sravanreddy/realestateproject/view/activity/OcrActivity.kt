package com.example.sravanreddy.realestateproject.view.activity

import android.Manifest
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.util.SparseArray
import android.view.SurfaceHolder
import com.example.sravanreddy.realestateproject.R
import com.google.android.gms.vision.text.TextRecognizer
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.text.TextBlock
import kotlinx.android.synthetic.main.activity_ocr.*
import java.io.IOException


class OcrActivity : AppCompatActivity() {
    private lateinit var mCameraSource: CameraSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ocr)

        startCameraSource()

    }

    private fun startCameraSource() {
        //Create the TextRecognizer
        val textRecognizer = TextRecognizer.Builder(applicationContext).build()

        if (!textRecognizer.isOperational) {
            Log.w("Err", "Detector dependencies not loaded yet")
        } else {
            //Initialize camerasource to use high resolution and set Autofocus on.
            mCameraSource = CameraSource.Builder(applicationContext, textRecognizer)
                    .setFacing(CameraSource.CAMERA_FACING_BACK)
                    .setRequestedPreviewSize(1280, 1024)
                    .setRequestedFps(2.0f)
                    .setAutoFocusEnabled(true)
                    .build()

            /**
             * Add call back to SurfaceView and check if camera permission is granted.
             * If permission is granted we can start our cameraSource and pass it to surfaceView
             */
            surfaceView.holder.addCallback(object: SurfaceHolder.Callback {

                override fun surfaceCreated(holder: SurfaceHolder?) {
                    try {
                        if (ActivityCompat.checkSelfPermission(applicationContext,
                                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(this@OcrActivity, arrayOf(Manifest.permission.CAMERA),
                                    1000)
                            return
                        }
                        mCameraSource.start(surfaceView.holder)
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }

                override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
                }

                override fun surfaceDestroyed(holder: SurfaceHolder?) {
                    mCameraSource.stop()
                }
            })

            textRecognizer.setProcessor(object : Detector.Processor<TextBlock> {
                override fun release() {

                }

                override fun receiveDetections(p0: Detector.Detections<TextBlock>?) {
                    val items: SparseArray<TextBlock> = p0!!.detectedItems
                    if (items.size() != 0) {
                        tv_identify.post({
                            val stringBuilder = StringBuilder()
                            for (i in 0 until items.size()) {
                                val item = items.valueAt(i)
                                stringBuilder.append(item.value)
                                stringBuilder.append("\n")
                            }
                            tv_identify.text = stringBuilder.toString()
                        })
                    }
                }
            })

        }
    }
}

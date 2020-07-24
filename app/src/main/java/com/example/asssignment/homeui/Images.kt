package com.example.asssignment.homeui

import android.Manifest
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.asssignment.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_images.*
import kotlinx.android.synthetic.main.fragment_images.view.*

class Images : Fragment() {

    lateinit var mCtx: Context
    private val GET_FROM_GALLERY = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_images, container, false)

        view.button.setOnClickListener {
            getpic()
        }
        return  view
    }
    private fun getpic() {
        if (ContextCompat.checkSelfPermission(
                mCtx,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            == PackageManager.PERMISSION_DENIED
        ) {

            // Requesting the permission
            ActivityCompat.requestPermissions(
                mCtx as Activity,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                100
            )
        } else {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_PICK
            startActivityForResult(
                Intent.createChooser(intent, "Select an image"), GET_FROM_GALLERY
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getpic()
        } else {
            Toast.makeText(mCtx,"Storage Permission Denied",Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GET_FROM_GALLERY && resultCode == RESULT_OK && data != null && data.data != null){
            Picasso.get().load(data.data).into(imageView)
            imageView.visibility = View.VISIBLE
            button.visibility = View.INVISIBLE
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mCtx = context
    }

}
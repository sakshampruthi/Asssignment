package com.example.asssignment.homeui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.asssignment.R
import kotlinx.android.synthetic.main.fragment_contactus.*
import kotlinx.android.synthetic.main.fragment_contactus.view.*


class Contactus : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_contactus, container, false)
        val phone = view.phoneNo.text
        view.phoneNo.setOnClickListener {
            val uri = "tel:" + phone.trim()
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse(uri)
            startActivity(intent)
        }
        val emailid = view.email.text
        view.email.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:abc@xyz.com")
            }
            startActivity(Intent.createChooser(emailIntent, "Send Email"))
        }
        return  view
    }

}
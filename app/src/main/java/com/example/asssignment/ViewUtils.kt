package com.example.asssignment

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun Context.toast(message: String){
    Toast.makeText(this,message,Toast.LENGTH_LONG).show()
}
fun Context.snackbar(message: String, view: View){
    Snackbar.make(view,message,Snackbar.LENGTH_LONG).show()
}
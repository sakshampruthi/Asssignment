package com.example.asssignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        act = this
        SignIn.setOnClickListener {
            startActivity(Intent(this,SignInActivity::class.java))
        }
        SignUp.setOnClickListener {
            startActivity(Intent(this,SignUpActivity::class.java))
        }
    }
    companion object{
        public var act: AppCompatActivity? = null
    }
}
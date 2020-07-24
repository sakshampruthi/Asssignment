package com.example.asssignment

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.asssignment.database.Details
import com.example.asssignment.database.DetailsDatabase
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class SignInActivity : AppCompatActivity(),CoroutineScope {
    private lateinit var job: Job
    var emaildb: String? = null
    var passworddb: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        job = Job()
        launch {
            applicationContext.let {
                val details = DetailsDatabase(it).getDetailsDao().getAllDetails()
                emaildb = details[0].email
                passworddb = details[0].password
            }
        }

        button_sign_in.setOnClickListener {
            val email = edit_text_email.editText?.text
            val password = edit_text_password.editText?.text
            if (email.toString() == emaildb && password.toString() == passworddb)
                startActivity(Intent(this@SignInActivity,HomeActivity::class.java))
            else
                snackbar("Credentials do not match!",findViewById(R.id.root_layout))
        }
    }
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

}
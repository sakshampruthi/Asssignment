package com.example.asssignment

import android.os.AsyncTask
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.asssignment.database.Details
import com.example.asssignment.database.DetailsDatabase
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import kotlin.coroutines.CoroutineContext

class SignUpActivity : AppCompatActivity(),CoroutineScope {
    private lateinit var job: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        job = Job()
        button_sign_up.setOnClickListener {
            val email = edit_text_email.editText?.text
            val name = edit_text_name.editText?.text
            val password = edit_text_password.editText?.text
            val confirmPassword = edit_text_confirm_password.editText?.text

            if (email.isNullOrEmpty()){
                edit_text_email.error = "Field cannot be empty"
                edit_text_email.requestFocus()
                return@setOnClickListener
            }
            else if(!isEmailValid(email.toString())){
                edit_text_email.error = "Incorrect Email"
                edit_text_email.requestFocus()
                return@setOnClickListener
            }
            if (name.isNullOrEmpty()){
                edit_text_name.error = "Field cannot be empty"
                edit_text_name.requestFocus()
                return@setOnClickListener
            }
            if (password.isNullOrEmpty()){
                edit_text_password.error = "Field cannot be empty"
                edit_text_password.requestFocus()
                return@setOnClickListener
            }
            if (confirmPassword.isNullOrEmpty()) {
                edit_text_confirm_password.error = "Field cannot be empty"
                edit_text_confirm_password.requestFocus()
                return@setOnClickListener
            }
            else if(password.toString()!=confirmPassword.toString()){
                edit_text_confirm_password.error = "Passwords do not match"
                edit_text_confirm_password.requestFocus()
                return@setOnClickListener
            }


            launch {
                val details = Details(email.toString(),name.toString(),password.toString())
                applicationContext?.let {
                    DetailsDatabase(it).getDetailsDao().addDetails(details)
                    toast("Account Created")
                    finish()
                }
            }
        }
    }
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }


    fun isEmailValid(email: String?): Boolean {
        val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern =
            Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }
}
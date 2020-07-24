package com.example.asssignment.homeui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.asssignment.R
import com.example.asssignment.adapters.ImageAdapter
import com.example.asssignment.adapters.JsonPlaceHolderApi
import com.example.asssignment.adapters.Phtotos
import com.example.asssignment.model
import kotlinx.android.synthetic.main.fragment_viewimages.*
import kotlinx.android.synthetic.main.fragment_viewimages.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ViewImages : Fragment() {
    lateinit var mCtx:Context


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_viewimages, container, false)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val jsonPlaceHolderApi:JsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)

        val call: Call<List<Phtotos>> = jsonPlaceHolderApi.getphtotos()

        call.enqueue(object : Callback<List<Phtotos>> {
            override fun onFailure(call: Call<List<Phtotos>>?, t: Throwable?) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<List<Phtotos>>?,
                response: Response<List<Phtotos>>?
            ) {
                if(!response?.isSuccessful!!){
                    Log.d("TAG", "onResponse: "+ response.code())
                    return
                }
                val photos = response.body()
                var arrayList = arrayListOf<model>()
                for(photo in photos){
                    val model = model(photo.id.toString(),photo.title,photo.url)
                    arrayList.add(model)
                }
                Log.d("ViewImages", "onCreateView: "+arrayList.size)
                val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
//                recyclerview.setHasFixedSize(true)
                val imageAdapter = ImageAdapter(mCtx,arrayList)
                recyclerview.layoutManager = LinearLayoutManager(mCtx)
                recyclerview.adapter = imageAdapter
            }

        })


        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mCtx = context
    }

}
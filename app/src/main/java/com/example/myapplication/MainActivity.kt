package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.example.myapplication.API.Controller
import com.example.myapplication.API.Redmoapps
import com.example.myapplication.databinding.ActivityMainBinding
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =  supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        var navController = navHostFragment.navController
        Controller.getApiArguments().getURLorNull().enqueue(object : Callback<String?>{
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                println("im here1" + response.body().toString())
                if (response.body().toString().isNotEmpty()) {
                    println("im here")
                    navController.navigate(R.id.blankFragment)
                }else{
                    var bundle = Bundle()
                    bundle.putString("URL", "https://www.google.com/")
                    navController.navigate(R.id.fragmentWeb, bundle)
                }

            }

            override fun onFailure(call: Call<String?>, t: Throwable) {
                println(t.message)
                var bundle = Bundle()
                bundle.putString("URL", "https://www.google.com/")
                navController.navigate(R.id.fragmentWeb, bundle)
//                navController.navigate(R.id.blankFragment)
            }
        })
    }
}
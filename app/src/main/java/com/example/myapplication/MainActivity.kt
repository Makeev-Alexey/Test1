package com.example.myapplication

import android.os.Bundle
import android.view.KeyEvent
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.myapplication.API.Controller
import com.example.myapplication.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding!!
    private var flagWebFragment = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =  supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        Controller.getApiArguments().getURLorNull().enqueue(object : Callback<String?>{
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                if (response.body().toString().isEmpty()) {
                    navController.navigate(R.id.blankFragment)
                }else{
                    flagWebFragment = 1
                    val bundle = Bundle()
                    bundle.putString("URL", response.body().toString())
                    navController.navigate(R.id.fragmentWeb, bundle)
                }

            }

            override fun onFailure(call: Call<String?>, t: Throwable) {
                println(t.message)
                navController.navigate(R.id.blankFragment)
            }
        })
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (flagWebFragment == 1){
        var webTemp = findViewById<WebView>(R.id.web)
        if (keyCode == KeyEvent.KEYCODE_BACK && webTemp.canGoBack()) {
            webTemp.goBack()
            return true
        }
        }
        return super.onKeyDown(keyCode, event)
    }
}
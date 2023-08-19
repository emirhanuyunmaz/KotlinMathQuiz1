package com.emirhanuyunmaz.kotlinmathquiz

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.emirhanuyunmaz.kotlinmathquiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences=this.getSharedPreferences("com.emirhanuyunmaz.kotlinmathquiz",
            MODE_PRIVATE)

        binding.textViewTopScore.text="Top Score:"+sharedPreferences.getInt("topScore",0)
    }

    fun start_OnClick(view:View){
        var intent= Intent(this@MainActivity,QuizActivity::class.java)
        startActivity(intent)
    }

}
package com.emirhanuyunmaz.kotlinmathquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.emirhanuyunmaz.kotlinmathquiz.databinding.ActivityFinishBinding

class FinishActivity : AppCompatActivity() {

    private lateinit var binding:ActivityFinishBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var getIntent=intent
        var getScore=getIntent.getIntExtra("score",0)
        binding.textViewFinalScore.text="Score:"+getScore

    }


    fun finish_OnClick(view : View){
        val intent= Intent(this@FinishActivity,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
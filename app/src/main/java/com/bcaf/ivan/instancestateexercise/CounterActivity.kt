package com.bcaf.ivan.instancestateexercise

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_counter.*

class CounterActivity : AppCompatActivity() {
    private var num: Int = 0
    private val sharedPrefFile = "savedData"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_counter)
        actionBar!!.hide()
    }

    fun btnToastClick(v: View) {
        var txt=inp_num.text.toString()
        Toast.makeText(applicationContext, "Counter $txt : $num", Toast.LENGTH_LONG).show()
    }

    fun btnUpClick(v: View) {
        num++;
        txt_number.setText("$num")
    }

    fun btnDownClick(v: View) {
        if (num > 0)
            num--;
        else
            Toast.makeText(applicationContext, "Counter already reach $num", Toast.LENGTH_SHORT)
                .show()
        txt_number.setText("$num")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState != null)
            num = savedInstanceState.getInt("num")
        txt_number.setText("$num")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState?.run {
            putInt("num", num)
        }
    }

    override fun onResume() {
        val sharedPreferences: SharedPreferences =
            getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        num = sharedPreferences.getString("num", "0")!!.toInt()
        txt_number.setText("$num")
        super.onResume()
    }

    override fun onPause() {
        val sharedPreferences: SharedPreferences =
            getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("num", "$num")
        editor.apply()
        super.onPause()
    }
}

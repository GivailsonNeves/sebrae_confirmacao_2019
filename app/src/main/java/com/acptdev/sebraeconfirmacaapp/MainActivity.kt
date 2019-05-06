package com.acptdev.sebraeconfirmacaapp

import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.form.*
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {

    private var progress: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prepareActions()
    }

    private fun prepareActions() {
        btnRegister.setOnClickListener { showLoading() }
        prepareSpinner()
    }

    private fun prepareSpinner() {
        val ufAdapter =
            ArrayAdapter.createFromResource(this, R.array.uf_array, android.R.layout.simple_spinner_dropdown_item)
        ufAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spUf.adapter = ufAdapter
    }

    private fun showLoading() {
        if (progress == null) {
            progress = ProgressDialog(this@MainActivity)
            progress!!.setMessage(getString(R.string.registrando))
            progress!!.show()
        }

        Timer().schedule(2000){
            progress!!.dismiss()
        }
    }
}

package com.example.percobaan3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_indentitas.*
import org.json.JSONArray
import org.json.JSONObject

class IndentitasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_indentitas)

        btnBackIdentitas.setOnClickListener{
            val inten = Intent(this, MainActivity::class.java)
            startActivity(inten)
        }

        getAllIdentitas()

        btnUpdateIdentitas.setOnClickListener {
            var data1 = identitas1.text.toString()
            var data2 = identitas2.text.toString()
            postUpdateIdentitas(data1,data2)
            val inten = Intent(this, IndentitasActivity::class.java)
            startActivity(inten)
        }
    }


    fun postUpdateIdentitas(data1: String, data2: String) {
        AndroidNetworking.post("http://$ip/jadwal_solat/update_identitas.php")
            .addBodyParameter("nama_masjid", data1)
            .addBodyParameter("alamat_masjid", data2)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener {
                override fun onResponse(response: JSONArray?) {}
                override fun onError(anError: ANError?) {}

            })
    }

    fun getAllIdentitas() {
        AndroidNetworking.get("http://$ip/jadwal_solat/identitas_masjid.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.e("_kotlinResp", response.toString())

                    val jsonArray = response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()){
                        val jsonObject = jsonArray.getJSONObject(i)

                        var nama = jsonObject.optString("nama_masjid")
                        var alamat = jsonObject.optString("alamat_masjid")

                        Log.e("_kotlinTitle", nama)
                        Log.e("_kotlinTitle", alamat)


                        nama_masjid.setText(nama)
                        alamat_masjid.setText(alamat)

                    }
                }

                override fun onError(anError: ANError?) {
                    Log.i("_err", anError.toString())
                }
            })
    }
}

package com.example.percobaan3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_jadwal.*
import org.json.JSONObject

class JadwalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jadwal)

        btnBackJadwal.setOnClickListener {
            val inten = Intent(this, MainActivity::class.java)
            startActivity(inten)
        }

        getAllJadwal()

        btnToUpdate.setOnClickListener {
            var data1 = subuh.text.toString()
            var data2 = dhuha.text.toString()
            var data3 = dhuhur.text.toString()
            var data4 = ashar.text.toString()
            var data5 = maghrib.text.toString()
            var data6 = isha.text.toString()
            val inten = Intent(this, UpdateActivity::class.java)
            inten.putExtra("data1", data1)
            inten.putExtra("data2", data2)
            inten.putExtra("data3", data3)
            inten.putExtra("data4", data4)
            inten.putExtra("data5", data5)
            inten.putExtra("data6", data6)
            startActivity(inten)
        }
    }

    fun getAllJadwal() {
        var isi1:String
        var isi2:String
        var isi3:String
        var isi4:String
        var isi5:String
        var isi6:String

        AndroidNetworking.get("http://$ip/jadwal_solat/contoh_jadwal_json.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.e("_kotlinResp", response.toString())

                    val jsonArray = response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()){
                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("_kotlinTitle", jsonObject.optString("shubuh"))
                        Log.e("_kotlinTitle", jsonObject.optString("dhuha"))
                        Log.e("_kotlinTitle", jsonObject.optString("dhuhur"))
                        Log.e("_kotlinTitle", jsonObject.optString("ashar"))
                        Log.e("_kotlinTitle", jsonObject.optString("maghrib"))
                        Log.e("_kotlinTitle", jsonObject.optString("isha"))

                        isi1 =  jsonObject.optString("shubuh")
                        isi2 =  jsonObject.optString("dhuha")
                        isi3 =  jsonObject.optString("dhuhur")
                        isi4 =  jsonObject.optString("ashar")
                        isi5 = jsonObject.optString("maghrib")
                        isi6 = jsonObject.optString("isha")

                        subuh.setText(isi1)
                        dhuha.setText(isi2)
                        dhuhur.setText(isi3)
                        ashar.setText(isi4)
                        maghrib.setText(isi5)
                        isha.setText(isi6)
                    }
                }

                override fun onError(anError: ANError?) {
                    Log.i("_err", anError.toString())
                }
            })
    }
}

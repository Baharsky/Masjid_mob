package com.example.percobaan3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import kotlinx.android.synthetic.main.activity_update.*
import org.json.JSONArray

class UpdateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)


        var terimaData1 = intent.getStringExtra("data1")
        var terimaData2 = intent.getStringExtra("data2")
        var terimaData3 = intent.getStringExtra("data3")
        var terimaData4 = intent.getStringExtra("data4")
        var terimaData5 = intent.getStringExtra("data5")
        var terimaData6 = intent.getStringExtra("data6")

        isiData1.setText("$terimaData1")
        isiData2.setText("$terimaData2")
        isiData3.setText("$terimaData3")
        isiData4.setText("$terimaData4")
        isiData5.setText("$terimaData5")
        isiData6.setText("$terimaData6")

        btnSubmitUpload.setOnClickListener {
            var data1 =isiData1.text.toString()
            var data2 =isiData2.text.toString()
            var data3 =isiData3.text.toString()
            var data4 =isiData4.text.toString()
            var data5 =isiData5.text.toString()
            var data6 =isiData6.text.toString()

            postSholat(data1,data2,data3,data4,data5,data6)

            val inten = Intent(this, JadwalActivity::class.java)
            startActivity(inten)
        }
    }

    fun postSholat(data1_1: String, data2_2: String, data3_3: String, data4_4: String, data5_5: String, data6_6: String) {
        AndroidNetworking.post("http://$ip/jadwal_solat/update_sholat_json.php")
            .addBodyParameter("shubuh", data1_1)
            .addBodyParameter("dhuha", data2_2)
            .addBodyParameter("dhuhur", data3_3)
            .addBodyParameter("ashar", data4_4)
            .addBodyParameter("maghrib", data5_5)
            .addBodyParameter("isha", data6_6)

            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener {
                override fun onResponse(response: JSONArray?) {}
                override fun onError(anError: ANError?) {}

            })
    }
}

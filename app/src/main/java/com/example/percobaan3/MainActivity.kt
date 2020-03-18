package com.example.percobaan3

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import kotlin.system.exitProcess
import kotlinx.android.synthetic.main.activity_main.*

const val ip= "192.168.43.163"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gotoJadwal.setOnClickListener{
            val inten = Intent(this, JadwalActivity::class.java)
            startActivity(inten)
        }

        gotoIdentitas.setOnClickListener{
            val inten = Intent(this, IndentitasActivity::class.java)
            startActivity(inten)
        }

        gotoMarquee.setOnClickListener{
            val inten = Intent(this, MarqueeActivity::class.java)
            startActivity(inten)
        }

        gotoPengumuman.setOnClickListener{
            val inten = Intent(this, PengumumanActivity::class.java)
            startActivity(inten)
        }

        gotoSlideshow.setOnClickListener{
            val inten = Intent(this, SlideShowActivity::class.java)
            startActivity(inten)
        }

        gotoTagline.setOnClickListener{
            val inten = Intent(this, TaglineActivity::class.java)
            startActivity(inten)
        }

    }

}

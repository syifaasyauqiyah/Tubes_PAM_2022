package tubes.pam.syifa.myghibli

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

@Suppress("DEPRECATION")
//Splashscreen app
class SplashActivity : AppCompatActivity () {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            val pindah = Intent(this, MainActivity::class.java)
            startActivity(pindah)
            finish()
        }, 2500)
    }
}
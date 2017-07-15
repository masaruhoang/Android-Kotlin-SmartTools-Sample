package dnhieuhuy.hoanghuy.compass

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.Toast

/**
 * Created by Administrator on 09/07/2017.
 */
class CompassMain : AppCompatActivity() {
    private var compass: Compass? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.compass_main)

        try {
            compass = Compass(this)
        } catch (e:IllegalStateException) {
            e.printStackTrace()
            Toast.makeText(this, "Either accelerometer or magnetic sensor not found" , Toast.LENGTH_LONG).show()
        }
        compass?.arrowView = findViewById<ImageView>(R.id.main_image_hands)
    }

    override fun onResume() {
        super.onResume()
        compass?.start()
    }

    override fun onPause() {
        super.onPause()
        compass?.stop()
    }

    companion object {
        private val TAG = "CompassActivity"
    }

}
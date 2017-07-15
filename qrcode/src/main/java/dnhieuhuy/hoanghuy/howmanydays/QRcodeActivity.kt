package dnhieuhuy.hoanghuy.howmanydays

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_qr_code.*
import android.widget.Toast
import android.content.Intent
import android.net.Uri
import org.json.JSONException

class QRcodeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_code)
        addEvents()

}

    private fun addEvents() {
        var intentIntergrator: IntentIntegrator = IntentIntegrator(this)

        btnScan.setOnClickListener {
            intentIntergrator.initiateScan()
        }

    }

    /**
     * Supported Function
     * */
     override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
            } else {
                try {
                        val intent :Intent  = Intent()
                        intent.action = Intent.ACTION_VIEW
                        intent.setData(Uri.parse(result.contents))
                        startActivity(intent)

                }catch (ex: JSONException)
                {
                    ex.printStackTrace()
                }
            }
        } else {
                super.onActivityResult(requestCode, resultCode, data)
        }
    }

}

package dnhieuhuy.hoanghuy.translationgoogleapi

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import dnhieuhuy.hoanghuy.translationgoogleapi.transhelper.GoogleTranslateHelper
import kotlinx.android.synthetic.main.activity_translation.*

class TranslationActivity : AppCompatActivity() {

     var toTranslate: EditText? = null
     var buttonTranslate: Button? = null
     var translator: GoogleTranslateHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translation)

        toTranslate = findViewById(R.id.ed_toTranslate)
        buttonTranslate = findViewById(R.id.button_translate)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        buttonTranslate?.setOnClickListener {
            EnglishToTagalog().execute()
        }
    }

    /**
     *
     *
     * **/

    @SuppressLint("StaticFieldLeak")
    inner class EnglishToTagalog : AsyncTask<Void, Void, Void>() {
        private var progress: ProgressDialog? = null

        protected fun onError(ex: Exception) {

        }

        override fun doInBackground(vararg params: Void): Void? {

            try {
                translator = GoogleTranslateHelper(API_KEY)

                Thread.sleep(1000)

            } catch (e: Exception) {
                // TODO Auto-generated catch block
                e.printStackTrace()
            }

            return null

        }

        override fun onCancelled() {
            super.onCancelled()
        }

        override fun onPreExecute() {
            //start the progress dialog
            progress = ProgressDialog.show(this@TranslationActivity, null, "Translating...")
            progress!!.setProgressStyle(ProgressDialog.STYLE_SPINNER)
            progress!!.isIndeterminate = true
            super.onPreExecute()
        }

        override fun onPostExecute(result: Void) {
            progress!!.dismiss()
            translated()
            super.onPostExecute(result)

        }


          override fun onProgressUpdate(vararg values: Void) {
            super.onProgressUpdate(*values)
        }
    }

    fun translated() {
        val translatetotagalog = toTranslate?.text.toString()//get the value of text
        val text = translator?.translte(translatetotagalog, "en", "ru")
        //        translatabletext = (TextView) findViewById(R.id.translatabletext);
        //        translatabletext.setText(text);
        Toast.makeText(applicationContext, text, Toast.LENGTH_LONG).show()
    }

    companion object {
        var API_KEY = "AIzaSyBATg-UzyG-jcQDDz--S3KoxXhkCw1byFc"
    }
}
package dnhieuhuy.hoanghuy.relaxsound.untils

import android.media.MediaPlayer
import android.os.AsyncTask
import android.widget.SeekBar
import android.widget.TextView

/**
 * Created by Administrator on 09/07/2017.
 */
class SeekBarHandler(val seekBar: SeekBar?, var mediaPlayer: MediaPlayer?,
                     var isViewOn: Boolean, val timer: TextView): AsyncTask<Void, Void, Boolean>()
{

    override fun onPreExecute() {
        super.onPreExecute()
        seekBar?.max = mediaPlayer?.duration!!
    }

    override fun doInBackground(vararg p0: Void?): Boolean {
        while (mediaPlayer?.isPlaying() == true && isViewOn == true)
        {
           try {
               Thread.sleep(200)
           }catch (e: InterruptedException)
           {
               e.printStackTrace()
           }

            publishProgress()
        }
        return true
    }

    override fun onCancelled(result: Boolean?) {
        super.onCancelled()
        setViewOnOff(false)
    }
    fun setViewOnOff(isOn:Boolean) {
        isViewOn = isOn
    }

    fun refreshMediaPlayer(mediaPlayer: MediaPlayer?)
    {
        this.mediaPlayer = mediaPlayer
    }

    override fun onPostExecute(result: Boolean?) {
        super.onPostExecute(result)
    }

    override fun onProgressUpdate(vararg values: Void?) {
        super.onProgressUpdate(*values)
        val time = mediaPlayer?.currentPosition!!
        seekBar?.setProgress(time)
        var seconds = (time/1000)
        val minutes = time / (1000*60) % 60
        seconds = seconds - minutes * 60
        timer.setText(minutes.toString() + ":" + seconds.toString())
    }

}
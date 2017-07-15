package dnhieuhuy.hoanghuy.relaxsound

import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PowerManager
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import dnhieuhuy.hoanghuy.relaxsound.untils.LogHelper
import dnhieuhuy.hoanghuy.relaxsound.untils.SeekBarHandler
import android.support.v7.widget.RecyclerView
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import dnhieuhuy.hoanghuy.relaxsound.adapter.CustomRecyclerAdapter
import kotlin.collections.ArrayList


class SoundActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener, MediaPlayer.OnCompletionListener,
                      MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnSeekCompleteListener,
                      View.OnClickListener {

    private val TAG = LogHelper.makeLogTag("MainActivity")
    private var mMediaPlayer: MediaPlayer? = null
    private var mPlayPauseButton: ImageButton? = null
    private var mSeekbar: SeekBar? = null
    private var mTimer: TextView? = null
    private var seekBarHandler: SeekBarHandler? = null
    private var adapter : CustomRecyclerAdapter?  = null
    private var animationDics: Animation? = null
    private var animationDicsB: Animation? = null
    private var imgRotateDics: ImageView? = null
    private var imgRotateDicsB: ImageView? = null
    var idSoundCheck: Int?  = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sound)

        addControls()

    }

    private fun addControls() {
        mSeekbar = findViewById<SeekBar>(R.id.progressbar)
        mSeekbar?.setOnSeekBarChangeListener(this)

        mPlayPauseButton = findViewById<ImageButton>(R.id.play_pause_btn)
        mPlayPauseButton?.setOnClickListener(this)

        mTimer = findViewById<TextView>(R.id.tv_progress)


        //Setting up RecycleView And adapter
        val sound_list: RecyclerView = findViewById<RecyclerView>(R.id.recycler)
        sound_list.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        val users = ArrayList<User>()
        users.add(User("Dawn Chorus",R.raw.dawnchorus))
       /* users.add(User("Forest Walk",R.raw.forestwalk))
        users.add(User("Ocean Euphoric", R.raw.oceaneuphoric))
        users.add(User("Ocean Waves",R.raw.oceanwaves))
        users.add(User("Summer Rain",R.raw.summerrain))
        users.add(User("Tropical Beach",R.raw.tropicalbeach))
        users.add(User("Tropical Beach",R.raw.govindaradhe))
        users.add(User("Tropical Beach",R.raw.mantramahakala))*/

        adapter  = CustomRecyclerAdapter(users, this)
        sound_list.adapter = adapter

        //Init with Animation of the Disc Image
        imgRotateDics = findViewById<ImageView>(R.id.imgDiskAnimation)
        imgRotateDicsB = findViewById<ImageView>(R.id.imgDiskAnimationB)
        animationDics = AnimationUtils.loadAnimation(this, R.anim.disc_rotate)
        animationDicsB = AnimationUtils.loadAnimation(this, R.anim.dics_rotate_opposite)
    }


    override fun onClick(v: View?) {
        if (v?.id == R.id.play_pause_btn) {
            togglePlayback()
            imgRotateDics?.startAnimation(animationDics)
            imgRotateDicsB?.startAnimation(animationDicsB)
        }
    }

    fun changeSoundByTapList()
    {
        if(idSoundCheck == adapter?.getPositionInf())
        {
            imgRotateDics?.startAnimation(animationDics)
            imgRotateDicsB?.startAnimation(animationDicsB)
            relaxResources(true)
            idSoundCheck = adapter?.getPositionInf()
            createMediaPlayerIfNeeded(idSoundCheck!!)
            playAudio()
        }
    }
    fun togglePlayback() {
        if (mMediaPlayer?.isPlaying == true) {
            pauseAudio()
        } else {
                createMediaPlayerIfNeeded(R.raw.dawnchorus)
                playAudio()
        }
    }

    /**
     * Makes sure the media player exists and has been reset. This will create
     * the media player if needed, or reset the existing media player if one
     * already exists.
     */
    private fun createMediaPlayerIfNeeded(idSound: Int) {
        Log.d(TAG, "createMediaPlayerIfNeeded")
        if (mMediaPlayer == null) {
            mMediaPlayer = MediaPlayer.create(this, idSound)

            // Make sure the media player will acquire a wake-lock while
            // playing. If we don't do that, the CPU might go to sleep while the
            // song is playing, causing playback to stop.
            mMediaPlayer?.setWakeMode(this.getApplicationContext(),
                    PowerManager.PARTIAL_WAKE_LOCK)

            // we want the media player to notify us when it's ready preparing,
            // and when it's done playing:
            mMediaPlayer?.setOnPreparedListener(this)
            mMediaPlayer?.setOnCompletionListener(this)
            mMediaPlayer?.setOnErrorListener(this)
            mMediaPlayer?.setOnSeekCompleteListener(this)
        }
    }

    fun playAudio() {
        mMediaPlayer?.start()
        seekBarHandler = SeekBarHandler(mSeekbar, mMediaPlayer, isViewOn = true, timer = mTimer!!)
        seekBarHandler?.execute()
        val pauseDrawabale = ContextCompat.getDrawable(this, android.R.drawable.ic_media_pause)
        mPlayPauseButton?.setImageDrawable(pauseDrawabale)
    }

    fun pauseAudio() {
        seekBarHandler?.cancel(true)
        mMediaPlayer?.pause()
        val playDrawabale = ContextCompat.getDrawable(this, android.R.drawable.ic_media_play)
        mPlayPauseButton?.setImageDrawable(playDrawabale)


    }

    /**
     * Releases resources used by the service for playback. This includes the
     * "foreground service" status, the wake locks and possibly the MediaPlayer.
     * @param releaseMediaPlayer Indicates whether the Media Player should also
     * *            be released or not
     */
    private fun relaxResources(releaseMediaPlayer: Boolean) {
        LogHelper.d(TAG, "relaxResources. releaseMediaPlayer=", releaseMediaPlayer)

        seekBarHandler?.cancel(true)
        seekBarHandler = null
        // stop and release the Media Player, if it's available
        if (releaseMediaPlayer && mMediaPlayer != null) {
            mMediaPlayer?.reset()
            mMediaPlayer?.release()
            mMediaPlayer = null
        }
    }

    override fun onPause() {
        super.onPause()
        if (mMediaPlayer?.isPlaying == true) {
            pauseAudio()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        relaxResources(true)
    }

    override fun onCompletion(mp: MediaPlayer?) {
        relaxResources(true)
        val playDrawabale = ContextCompat.getDrawable(this, android.R.drawable.ic_media_play)
        mPlayPauseButton?.setImageDrawable(playDrawabale)
        mSeekbar?.progress = 0
    }

    override fun onError(mp: MediaPlayer?, what: Int, extra: Int): Boolean {
        return false
    }

    override fun onPrepared(mp: MediaPlayer?) {
    }

    override fun onSeekComplete(mp: MediaPlayer?) {
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        if (fromUser) {
            val progress = seekBar?.progress
            mMediaPlayer?.seekTo(progress!!)
        }
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
    }
}
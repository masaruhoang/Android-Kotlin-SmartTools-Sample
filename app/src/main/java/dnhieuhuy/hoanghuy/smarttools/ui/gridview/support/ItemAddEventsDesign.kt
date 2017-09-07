package dnhieuhuy.hoanghuy.smarttools.ui.gridview.support

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import dnhieuhuy.hoanghuy.bookfoods.FoodBooksActivity
import dnhieuhuy.hoanghuy.calculator.CalculatorActivity
import dnhieuhuy.hoanghuy.compass.CompassMain
import dnhieuhuy.hoanghuy.howmanydays.QRcodeActivity
import dnhieuhuy.hoanghuy.quicknews.QuickNewsActivity
import dnhieuhuy.hoanghuy.relaxsound.SoundActivity
import dnhieuhuy.hoanghuy.smarttools.MainActivity
import dnhieuhuy.hoanghuy.smarttools.R
import dnhieuhuy.hoanghuy.smarttools.flashlight.SetOnOffFlashLight
import dnhieuhuy.hoanghuy.smarttools.ui.googlemap.MapsActivity
import dnhieuhuy.hoanghuy.translationgoogleapi.TranslationActivity

/**
 * Created by Administrator on 05/07/2017.
 */
class ItemAddEventsDesign
{


    companion object
    {
        private const val  COLORS: Int = 2130706432
        fun itemDesign( itemView: View, position: Int,width: Int, height: Int)
        {
             when (position)
             {
                 0 ->(itemView as? GridItemView)?.setTitle("Light", R.drawable.flashlamp)
                 1 ->(itemView as? GridItemView)?.setTitle("Maps", R.drawable.mapscreen)
                 2 ->(itemView as? GridItemView)?.setTitle("Compass", R.drawable.compassscreen)
                 3 ->(itemView as? GridItemView)?.setTitle("Relax Sound",  R.drawable.musicscreen)
                 4 ->(itemView as? GridItemView)?.setTitle("Calculator",  R.drawable.calculatorscreen)
                 5 ->(itemView as? GridItemView)?.setTitle("QR Scanner",  R.drawable.qrscannerscreen)
                 6 ->(itemView as? GridItemView)?.setTitle("Quick News",  R.drawable.newsscreen)
                 7 ->(itemView as? GridItemView)?.setTitle("Translate",  R.drawable.translatescreen)
                 8 ->(itemView as? GridItemView)?.setTitle("FoodBooks",  R.drawable.foodbookscreem)

                 else ->(itemView as? GridItemView)?.setTitle("$position", R.drawable.mapicon)
             }
             itemView.layoutParams = RecyclerView.LayoutParams(width, height)
             itemView.setBackgroundColor(COLORS)  //MAU TRONG SUOT
        }

        fun itemEventClick(position: Int, setOnOffLight: SetOnOffFlashLight, mainActivity: MainActivity)
        {
            when (position)
            {
                0 ->
                {
                     setOnOffLight.getCamera()
                     if (setOnOffLight.isFlashOn) { setOnOffLight.turnOffFlash() }
                     else { setOnOffLight.turnOnFlash() }
                }
                1 ->
                {
                    val mapIntent = Intent(mainActivity, MapsActivity::class.java)
                    mainActivity.startActivity(mapIntent)
                }
                2 ->
                {
                    val comPassIntent = Intent(mainActivity, CompassMain::class.java)
                    mainActivity.startActivity(comPassIntent)
                }
                3 ->
                {
                    val mediaPlayerIntent = Intent(mainActivity, SoundActivity::class.java)
                    mainActivity.startActivity(mediaPlayerIntent)
                }
                4 ->
                {
                    val calculatorIntent = Intent(mainActivity, CalculatorActivity::class.java)
                    mainActivity.startActivity(calculatorIntent)
                }
                5 ->
                {
                    val qrCodeIntent = Intent(mainActivity, QRcodeActivity::class.java)
                    mainActivity.startActivity(qrCodeIntent)
                }

                6 ->
                {
                    val quickNewsIntent = Intent(mainActivity, QuickNewsActivity::class.java)
                    mainActivity.startActivity(quickNewsIntent)
                }
                7 ->
                {
                    val transIntent = Intent(mainActivity, TranslationActivity::class.java)
                    mainActivity.startActivity(transIntent)
                }
                8 ->
                {
                    val foodbookIntent = Intent(mainActivity, FoodBooksActivity::class.java)
                    mainActivity.startActivity(foodbookIntent)
                }
            }

        }
    }
}
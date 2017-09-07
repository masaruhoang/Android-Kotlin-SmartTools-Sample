package dnhieuhuy.hoanghuy.foodbooks.adapter

import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.Image
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CursorAdapter
import android.widget.ImageView
import android.widget.TextView
import dnhieuhuy.hoanghuy.bookfoods.R
import dnhieuhuy.hoanghuy.foodbooks.dbhelper.DBHelper
import org.w3c.dom.Text
import java.io.BufferedReader
import java.io.ByteArrayInputStream
import java.sql.Blob
import java.text.DateFormat
import java.text.FieldPosition
import java.text.SimpleDateFormat
import java.util.*
import java.util.zip.Inflater

/**
 * Created by Administrator on 20/07/2017.
 */
class FoodCursorAdapter(context: Context, cursor: Cursor?): CursorAdapter(context, cursor, true)
{
    private var contextMain: Context?
    private var mCursor: Cursor?

    object DateHelper
    {
        const val DF_SIMPLE_STRING = "dd-MM-yy"
        @JvmField val DF_SIMPLE_FORMAT = object : ThreadLocal<DateFormat>() {
            override fun initialValue(): DateFormat {
                return SimpleDateFormat(DF_SIMPLE_STRING, Locale.US)
            }
        }
    }


    init {
        contextMain = context
        mCursor = cursor
    }


    override fun newView(context: Context?, cursor: Cursor?, parent: ViewGroup?): View {
        return LayoutInflater.from(context).inflate(R.layout.food_item, parent, false)

    }

    override fun bindView(view: View?, context: Context?, cursor: Cursor?) {

        try {
            val imgFood = view?.findViewById<ImageView>(R.id.imgFood)
            val data: ByteArray = cursor!!.getBlob(cursor.getColumnIndexOrThrow(DBHelper.FImage))
            val imageStream = ByteArrayInputStream(data)
            val myImage : Bitmap =  BitmapFactory.decodeStream(imageStream)
            imgFood?.setImageBitmap(myImage)

            val txtFoodName = view?.findViewById<TextView>(R.id.txtFoodName)
            txtFoodName?.text = getString(cursor, DBHelper.FoodName)
        }catch (ex: Exception)
        {
            Log.d("ERROR IN CURSORADAPTER", ex.toString())
        }


    }

    //Getting data from Column Index of the DB
    private fun getString(cursor: Cursor?, key: String?): String
    {
        return cursor!!.getString(cursor.getColumnIndexOrThrow(key))
    }

}
package dnhieuhuy.hoanghuy.foodbooks.dbhelper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import dnhieuhuy.hoanghuy.bookfoods.model.FoodSource
import dnhieuhuy.hoanghuy.foodbooks.model.Food
import org.jetbrains.anko.db.update
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream

/**
 * Created by Administrator on 20/07/2017.
 */
class DBHelper(context: Context): SQLiteOpenHelper(context, "dbNewFood.db", null, 4)
{
    private val TABLE = "tbAllFood"
    private var contextMain: Context = context
    private var foodList: ArrayList<Food>? = null
    private var db: SQLiteDatabase? = null

    companion object {
        private val DATABASE_NAME = "dbNewFood.db"
        private val DB_PATH_SUFFIX = "/databases/"

        //DB Flieds
         var ID: String = "_id"
         var FoodID: String = "FoodID"
         var FoodName: String =  "FoodName"
         var FoodLink: String = "FoodLink"
         var EngFood: String = "EngFood"
         var FoodInf: String = "FoodInf"
         var Price: String = "Price"
         var DayPub: String = "DayPub"
         var FImage: String = "FImage"
         var IsDeleted: String = "IsDeleted"
         var TypeID: String = "TypeID"
    }


    override fun onCreate(p0: SQLiteDatabase?) {

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    /**
     * INSERT
     * */

    /**
     * SELECT
     * */

    /**
    ===============================FOOD TYPE PROVIDER====================================
    ====================================================================================*/
    fun getFoods(mPage: String): Cursor
    {
        val type = "'"+mPage+"'"
        val cursor = readableDatabase
                .query(TABLE, arrayOf("rowid _id",FoodID, FoodName, FoodLink, EngFood, FoodInf, Price, DayPub, FImage, IsDeleted, TypeID),
                        "TypeID = " + type, null, null, null, null)
        return cursor
    }


    fun getFoodsSubForm (mPage: String, num: Int): FoodSource
    {

        when(num)
        {
            0-> {
                val cursor = readableDatabase
                        .query(TABLE, arrayOf("rowid _id",FoodID, FoodName, FoodLink, EngFood, FoodInf, Price, DayPub, FImage, IsDeleted, TypeID),
                                null, null, null, null, null)
                val foodList: MutableList<Food> = mutableListOf()
                while(cursor.moveToNext())
                {
                    val foodID: String? = cursor.getString(cursor.getColumnIndexOrThrow(FoodID))
                    val foodName: String? = cursor.getString(cursor.getColumnIndexOrThrow(FoodName))
                    val foodLink: String? = cursor.getString(cursor.getColumnIndexOrThrow(FoodLink))
                    val engFood: String? = cursor.getString(cursor.getColumnIndexOrThrow(EngFood))
                    val foodInf: String? = cursor.getString(cursor.getColumnIndexOrThrow(FoodInf))
                    val price: String? = cursor.getString(cursor.getColumnIndexOrThrow(Price))
                    val dayPub: Int? = cursor.getInt(cursor.getColumnIndexOrThrow(DayPub))
                    val fImage: ByteArray? = cursor.getBlob(cursor.getColumnIndexOrThrow(FImage))
                    val isDeleted: Int? = cursor.getInt(cursor.getColumnIndexOrThrow(IsDeleted))
                    val typeID: String? = cursor.getString(cursor.getColumnIndexOrThrow(TypeID))

                    val food = Food(FoodID = foodID, FoodName = foodName, FoodLink = foodLink, EngFood = engFood,
                            FoodInf = foodInf, Price = price, DayPub = dayPub, FImage = fImage,
                            IsDeleted = isDeleted, TypeID = typeID)

                    foodList.add(food)
                }
                cursor.close()
                val foodResource = FoodSource(foodsListRS = foodList)
                return foodResource
            }
            1->{
                val type = "'"+mPage+"'"
                val cursor = readableDatabase
                        .query(TABLE, arrayOf("rowid _id",FoodID, FoodName, FoodLink, EngFood, FoodInf, Price, DayPub, FImage, IsDeleted, TypeID),
                                "TypeID = " + type, null, null, null, null)
                val foodList: MutableList<Food> = mutableListOf()
                while(cursor.moveToNext())
                {
                    val foodID: String? = cursor.getString(cursor.getColumnIndexOrThrow(FoodID))
                    val foodName: String? = cursor.getString(cursor.getColumnIndexOrThrow(FoodName))
                    val foodLink: String? = cursor.getString(cursor.getColumnIndexOrThrow(FoodLink))
                    val engFood: String? = cursor.getString(cursor.getColumnIndexOrThrow(EngFood))
                    val foodInf: String? = cursor.getString(cursor.getColumnIndexOrThrow(FoodInf))
                    val price: String? = cursor.getString(cursor.getColumnIndexOrThrow(Price))
                    val dayPub: Int? = cursor.getInt(cursor.getColumnIndexOrThrow(DayPub))
                    val fImage: ByteArray? = cursor.getBlob(cursor.getColumnIndexOrThrow(FImage))
                    val isDeleted: Int? = cursor.getInt(cursor.getColumnIndexOrThrow(IsDeleted))
                    val typeID: String? = cursor.getString(cursor.getColumnIndexOrThrow(TypeID))

                    val food = Food(FoodID = foodID, FoodName = foodName, FoodLink = foodLink, EngFood = engFood,
                            FoodInf = foodInf, Price = price, DayPub = dayPub, FImage = fImage,
                            IsDeleted = isDeleted, TypeID = typeID)

                    foodList.add(food)
                }
                cursor.close()
                val foodResource = FoodSource(foodsListRS = foodList)
                return foodResource
            }
            else->{
                Log.d("DB","ERROR IN DBHELPER: GET FOODSUBFORM")
                val foodList: MutableList<Food> = mutableListOf()
                val foodResource = FoodSource(foodsListRS = foodList)
                return   foodResource
            }
        }
    }


    /**
     * Like Food Button 's Events
     * */
    fun likeAFood(foodID: String, likeIndex: Boolean)
    {
        var contentValues = ContentValues()

        if (likeIndex == true)
        {
            contentValues.put(IsDeleted, 1)
            var cursor = writableDatabase
                .update(TABLE,contentValues, FoodID +"=?", arrayOf(foodID) )
        }else
        {
            contentValues.put(IsDeleted, 0)
            var cursor = writableDatabase
                    .update(TABLE,contentValues, FoodID +"=?", arrayOf(foodID) )
        }

    }

    /**
    ====================================================================================
    ====================================================================================*/


    /**
     * Copy Database from Assets Folder to Mobile's database
     * */
    fun copyDBToMobile()
    {
        var dbfile: File = contextMain.getDatabasePath(DATABASE_NAME)

        if (!dbfile.exists())
        {
            try
            {
                CopyDatabase()
                Toast.makeText(contextMain, "Database is saved!", Toast.LENGTH_SHORT).show()
            }catch (ex:Exception)
            {
                ex.printStackTrace()
            }
        }
    }

    private fun CopyDatabase() {
        try {
            val myInput : InputStream = contextMain.assets.open(DATABASE_NAME)
            val outFileName = getPathOfPacket()
            val f: File = File(contextMain.applicationInfo.dataDir + DB_PATH_SUFFIX)
            //Path to just create empty db
            if(!f.exists())
            {
                f.mkdir()
            }

            //If the path doesn't exist for the first, creates it.
            val myOutPut : OutputStream = FileOutputStream(outFileName)
            val buffer = ByteArray(1024)
            var length: Int = 0

            while( {length =  myInput.read(buffer); length}() > 0)
            {
                myOutPut.write(buffer, 0 , length)
            }

            //Close the stream
            myOutPut.flush()
            myOutPut.close()
            myInput.close()

        }catch (ex: Exception)
        {
            Log.e("DB", "Saving DB is error!!!")
        }
    }

    //Get Path where storage Your App Packet
    private fun getPathOfPacket(): String
    {
        return contextMain.applicationInfo.dataDir + DB_PATH_SUFFIX + DATABASE_NAME
    }
}
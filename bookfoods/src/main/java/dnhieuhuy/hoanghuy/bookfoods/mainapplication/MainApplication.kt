package dnhieuhuy.hoanghuy.foodbooks.mainapplication

import android.annotation.SuppressLint
import android.app.Application
import dnhieuhuy.hoanghuy.foodbooks.dbhelper.DBHelper
import kotlin.properties.Delegates

/**
 * Created by Administrator on 22/07/2017.
 */
class MainApplication: Application()
{
    //Database Helper
    var dbHelper: DBHelper by Delegates.notNull()

    @SuppressLint("MissingSuperCall")
    override fun onCreate() {
        //intantiate the db-helper when the app is created
        dbHelper = DBHelper(this)
    }
}
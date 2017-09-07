package dnhieuhuy.hoanghuy.foodbooks.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Administrator on 20/07/2017.
 */
data class Food (
                 val FoodID: String?,
                 val FoodName: String?,
                 val FoodLink: String?,
                 val EngFood: String?,
                 val FoodInf: String?,
                 val Price: String?,
                 val DayPub: Int?,
                 val FImage: ByteArray?,
                 val IsDeleted: Int?,
                 val TypeID: String?
){
}

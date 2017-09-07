package dnhieuhuy.hoanghuy.bookfoods.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import dnhieuhuy.hoanghuy.bookfoods.R
import dnhieuhuy.hoanghuy.bookfoods.model.FoodSource
import dnhieuhuy.hoanghuy.foodbooks.dbhelper.DBHelper
import dnhieuhuy.hoanghuy.foodbooks.model.Food
import kotlinx.android.synthetic.main.liked_food_item.view.*
import java.io.ByteArrayInputStream
import android.arch.lifecycle.ViewModel
import android.R.attr.data





/**
 * Created by Administrator on 05/09/2017.
 */
class FoodAdapterRecycleView(val foodlist: MutableList<Food>): RecyclerView.Adapter<FoodAdapterRecycleView.ViewHolder>()
{

    /**
     * List of Foods
     * **/

    override fun getItemCount(): Int {
        return foodlist.size
    }

    /**
     * Bind the view with the data
     */
    override fun onBindViewHolder(holder: FoodAdapterRecycleView.ViewHolder, position: Int) {
        holder.bind(foodlist[position])
    }


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) : FoodAdapterRecycleView.ViewHolder {
    val v = LayoutInflater.from(parent?.context).inflate(R.layout.liked_food_item, parent, false)
    return ViewHolder(v)
}
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        /**
         * Binds the UI with the data and handles clicks
         */
        fun bind(food: Food) = with(itemView)
        {
            if (food.IsDeleted == 1)
            {
                //Image
                val imgLikeNameFood = findViewById<ImageView>(R.id.imgLikedFood)
                val data: ByteArray? = food.FImage
                val imageStream = ByteArrayInputStream(data)
                val myImage : Bitmap =  BitmapFactory.decodeStream(imageStream)
                imgLikeNameFood?.setImageBitmap(myImage)

                //FoodName
                val txtLikedFoodName = findViewById<TextView>(R.id.txtNameLikedFood)
                txtLikedFoodName?.text = food.FoodName

                //Food's Information
                val txtInfLikedFood = findViewById<TextView>(R.id.txtInfLikedFood)
                txtInfLikedFood?.text = food.FoodInf
            }

                val dbHelper = DBHelper(context)
                 btnDeleteLikedFood.setOnClickListener {
                dbHelper.likeAFood(foodID = food.FoodID!!, likeIndex = false)
                }

        }
    }





}
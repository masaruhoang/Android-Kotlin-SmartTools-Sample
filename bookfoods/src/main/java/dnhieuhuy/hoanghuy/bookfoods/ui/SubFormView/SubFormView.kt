package dnhieuhuy.hoanghuy.bookfoods.ui.SubFormView

import android.app.AlertDialog
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.akshay.newsapp.ui.NewsArticleListViewModel
import dnhieuhuy.hoanghuy.bookfoods.R
import dnhieuhuy.hoanghuy.bookfoods.adapter.FoodAdapterRecycleView
import dnhieuhuy.hoanghuy.bookfoods.model.FoodSource
import dnhieuhuy.hoanghuy.foodbooks.dbhelper.DBHelper
import dnhieuhuy.hoanghuy.foodbooks.model.Food
import dnhieuhuy.hoanghuy.quicknews.adapter.NewsArticlesAdapter
import java.io.ByteArrayInputStream

/**
 * Created by Administrator on 31/08/2017.
 */
class SubFormView
{
    private var listFood : MutableList<Food>? = null
    private var index: ArrayList<Int>? = null

    fun initSubForm(context: Context, food: Food)
    {
        //Call DialogAlert displaying detailed Food
        val foodSubFormdialog: AlertDialog.Builder = AlertDialog.Builder(context)
        val dialogInstance: AlertDialog = foodSubFormdialog.create()
        val factory: LayoutInflater = LayoutInflater.from(context)
        val view: View = factory.inflate(R.layout.activity_sub_form, null)

        /**
         * Setting View
         **/
        //Image
        val imgFoodDA = view.findViewById<ImageView>(R.id.imgFoodDA)
        val data: ByteArray? = food.FImage
        val imageStream = ByteArrayInputStream(data)
        val myImage : Bitmap =  BitmapFactory.decodeStream(imageStream)
        imgFoodDA?.setImageBitmap(myImage)

        //FoodName
        val txtFoodName = view.findViewById<TextView>(R.id.txtFoodNameDA)
        txtFoodName?.text = food.FoodName

        //Food's Information
        val txtInfFood = view.findViewById<TextView>(R.id.txtFoodInfDA)
        txtInfFood?.text = food.FoodInf

        //Food's Like Button
        val btnLike = view.findViewById<ImageView>(R.id.btnLike)



        dialogInstance.setCancelable(true)
        foodSubFormdialog.setView(view)
        foodSubFormdialog.show()


        /**
         * Add Events
         * */
        val dbHelper = DBHelper(context)
        btnLike.setOnClickListener {
            dbHelper.likeAFood(foodID = food.FoodID!!, likeIndex = true)
            Toast.makeText(context, food.FoodName + " Is Liked", Toast.LENGTH_SHORT).show()
        }


    }


    fun initFormLikedFood(context: Context, food: MutableList<Food>)
    {
        //Call DialogAlert displaying detailed Food
        val foodSubFormdialog: AlertDialog.Builder = AlertDialog.Builder(context)
        val dialogInstance: AlertDialog = foodSubFormdialog.create()
        val factory: LayoutInflater = LayoutInflater.from(context)
        val view: View = factory.inflate(R.layout.activity_sub_form_liked_food, null)

        /**
         * Setting View
         **/
        //BUTTON


        //RECYCLE VIEW
        val recycleview = view.findViewById<RecyclerView>(R.id.food_lists)
        recycleview.layoutManager = LinearLayoutManager(view.context, LinearLayout.VERTICAL, false)

        listFood = mutableListOf()
        index = arrayListOf()

        for(i in food.indices)
        {
            if (food.get(i).IsDeleted == 1)
            {
                listFood!!.add(food.get(i))
                index?.add(i)
            }
        }
        val adapter = FoodAdapterRecycleView(foodlist = listFood!!)
        recycleview.adapter = adapter
        adapter.notifyDataSetChanged()

        dialogInstance.setCancelable(true)
        foodSubFormdialog.setView(view)
        foodSubFormdialog.show()




        /**
         * Add Events
         * */

        }


    }



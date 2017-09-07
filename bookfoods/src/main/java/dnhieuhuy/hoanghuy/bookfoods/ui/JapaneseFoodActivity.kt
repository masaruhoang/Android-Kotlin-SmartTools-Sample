package dnhieuhuy.hoanghuy.bookfoods.ui


import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewPager
import android.support.v7.app.AlertDialog
import android.webkit.WebView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView

import com.astuetz.PagerSlidingTabStrip
import dnhieuhuy.hoanghuy.bookfoods.FoodBooksActivity
import dnhieuhuy.hoanghuy.bookfoods.R
import dnhieuhuy.hoanghuy.bookfoods.adapter.FoodsFragmentPageAdapter
import dnhieuhuy.hoanghuy.bookfoods.model.FoodSource
import dnhieuhuy.hoanghuy.bookfoods.ui.SubFormView.SubFormView
import dnhieuhuy.hoanghuy.foodbooks.mainapplication.MainApplication
import kotlinx.android.synthetic.main.activity_japanese_food.*
import kotlin.properties.Delegates


class JapaneseFoodActivity : AppCompatActivity() {


    private var mViewPager: ViewPager? = null
    private var searchViewFood: AutoCompleteTextView? = null
    private var arrFoodName: ArrayList<String>? = null
    private var arrIndexFoodName: ArrayList<Int>? = null
    private var adapterFoodName: ArrayAdapter<String>? = null
    private var subFormView: SubFormView? = null
    /**Delegates**/
    private var mApp: MainApplication by Delegates.notNull()
    private var foodLists: FoodSource? = null
    private var indexTab: Int? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_japanese_food)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

       // supportActionBar?.hide()
       supportActionBar?.hide()
        // Get the ViewPager and set it's PageAdapter so that it can display items
        indexTab = intent.getIntExtra("OOO",0)
        mViewPager = findViewById(R.id.container)
        mViewPager!!.adapter = FoodsFragmentPageAdapter(supportFragmentManager)
        mViewPager!!.setCurrentItem(indexTab!!)

        //Give the PagerSlidingTabStrip the ViewPager
        val tabsStrip = findViewById<PagerSlidingTabStrip>(R.id.tabs)
        //Attach the view pager to the tab strip
        tabsStrip.setViewPager(mViewPager)

        addControls()
        addEvents()
    }

    private fun addEvents() {
         subFormView = SubFormView()

        //SEARCHVIEW AUTO COMPLETE TEXTVIEW
        searchViewFood?.setOnItemClickListener { adapterView, view, i, l ->
            for (j in foodLists!!.foodsListRS.indices)
            {
                val foodname: String = searchViewFood!!.text.toString()
                if( foodname.equals( foodLists?.foodsListRS?.get(j)?.FoodName))
                {
                    subFormView!!.initSubForm(this, foodLists!!.foodsListRS.get(j))
                    break
                }
            }
        }

        //DELETE TEXT
        btnDeleteSearchView.setOnClickListener {
            searchViewFood?.text = null
        }
    }

    private fun addControls() {
        mApp = application as MainApplication

        try {
            foodLists = mApp.dbHelper.getFoodsSubForm("ALLFOOD",0)
        }catch (ex: Exception)
        {
            ex.printStackTrace()
        }


        //AutoCompleteTextView SearchView
        searchViewFood = findViewById(R.id.searchviewFood)
        arrFoodName = arrayListOf()
        arrIndexFoodName = arrayListOf()

        for(i in foodLists!!.foodsListRS.indices)
        {
            arrFoodName?.add(foodLists!!.foodsListRS.get(i).FoodName.toString())
            arrIndexFoodName?.add(i)
        }

        adapterFoodName = ArrayAdapter(this,android.R.layout.simple_list_item_1,arrFoodName)
        searchViewFood!!.setAdapter(adapterFoodName)


    }




    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                onBackPressed()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                var subFormView = SubFormView()
                subFormView.initFormLikedFood(this, foodLists?.foodsListRS!!)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                aboutDialog()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_update -> {

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


    //======================NAVIGATION ABOUT ITEM============================
    //=======================================================================
    //=======================================================================
    @SuppressLint("RestrictedApi", "ResourceType")
    private fun aboutDialog() {
        val alert = AlertDialog.Builder(this)
        alert.setTitle("FOodBoOK")
        val webView = WebView(this)
        var about = "<p>A lightweight, easily use. Let's search Japanese food.</p>" +
                "<p>Developed by <a href='mailto:hoanghuy.masaru@gmail.com'>Hoang The Huy</a></p>" +
                "<p>Email: <a href='mailto:hoanghuy.masaru@gmail.com'>hoanghuy.masaru@gmail.com</a></p>" +
                "<p>Data provided by <a href='http://192.168.2.130/foodimformations/FoodService.asmx'>HoangHuy's Websevive</a>, under the <a href='http://gô'> Creative Commons license.</a>"
        //"<p>Icons are <a href='https://erikflowers.github.io/weather-icons/'>Weather Icons</a>, by <a href='http://www.twitter.com/artill'>Lukas Bischoff</a> and <a href='http://www.twitter.com/Erik_UX'>Erik Flowers</a>, under the <a href='http://scripts.sil.org/OFL'>SIL OFL 1.1</a> licence.";
        val ta = obtainStyledAttributes(intArrayOf(android.R.attr.textColorPrimary, R.attr.colorAccent))
        val textColor = String.format("#%06X", 0xFFFFFF and ta.getColor(0, Color.BLACK))
        val accentColor = String.format("#%06X", 0xFFFFFF and ta.getColor(1, Color.BLUE))
        ta.recycle()
        about = "<style media=\"screen\" type=\"text/css\">" +
                "body {\n" +
                "    color:" + textColor + ";\n" +
                "}\n" +
                "a:link {color:" + accentColor + "}\n" +
                "</style>" +
                about
        webView.setBackgroundColor(Color.TRANSPARENT)
        webView.loadData(about, "text/html", "UTF-8")
        alert.setView(webView, 32, 0, 32, 0)
        alert.setPositiveButton(R.string.dialog_ok, DialogInterface.OnClickListener { dialog, whichButton -> })
        alert.show()
    }
}

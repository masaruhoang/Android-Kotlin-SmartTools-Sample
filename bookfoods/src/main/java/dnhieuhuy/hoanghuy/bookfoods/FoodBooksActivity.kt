package dnhieuhuy.hoanghuy.bookfoods

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.FloatingActionButton
import android.view.MotionEvent
import android.view.View
import android.widget.RelativeLayout
import android.widget.Toast
import dnhieuhuy.hoanghuy.bookfoods.ui.JapaneseFoodActivity
import dnhieuhuy.hoanghuy.foodbooks.dbhelper.DBHelper
import kotlinx.android.synthetic.main.activity_main__foodbook.*
import kotlinx.android.synthetic.main.fab_layout.*


class FoodBooksActivity : AppCompatActivity() {

    //==================DECLARE PARA===================
    private var display_FAB: Boolean? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main__foodbook)
        supportActionBar?.hide()
        addControls()
        addEvents()
    }

    private fun addEvents() {
        //FAB
        fab.setOnClickListener {
            if (display_FAB == true)
            {
                displayFABs(display_FAB!!)
                display_FAB = false
            }
            else
            {
                displayFABs(display_FAB!!)
                display_FAB = true
            }
        }



        fab_sashimi.setOnClickListener {
            val intent = Intent(this, JapaneseFoodActivity::class.java)
            intent.putExtra("OOO",1)
            startActivity(intent)
        }

        fab_bread.setOnClickListener {
            Toast.makeText(this, "BREAD", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, JapaneseFoodActivity::class.java)
            intent.putExtra("OOO",11)
            startActivity(intent)
        }

        fab_noodles.setOnClickListener {
            Toast.makeText(this, "NOODLES", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, JapaneseFoodActivity::class.java)
            intent.putExtra("OOO",2)
            startActivity(intent)
        }

        fab_sushi.setOnClickListener {
            Toast.makeText(this, "SUSHI", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, JapaneseFoodActivity::class.java)
            intent.putExtra("OOO",0)
            startActivity(intent)
        }

        fab_tempura.setOnClickListener {
            Toast.makeText(this, "TEMPURA-FRIED", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, JapaneseFoodActivity::class.java)
            intent.putExtra("OOO",3)
            startActivity(intent)
        }

        fab_kushi.setOnClickListener {
            Toast.makeText(this, "Grilled-BBQ", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, JapaneseFoodActivity::class.java)
            intent.putExtra("OOO",6)
            startActivity(intent)
        }

        fab_hotpot.setOnClickListener {
            Toast.makeText(this, "HotPot", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, JapaneseFoodActivity::class.java)
            intent.putExtra("OOO",5)
            startActivity(intent)
        }

        fab_beverages.setOnClickListener {
            Toast.makeText(this, "Beverages", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, JapaneseFoodActivity::class.java)
            intent.putExtra("OOO",17)
            startActivity(intent)
        }

        fab_fruits.setOnClickListener {
            Toast.makeText(this, "Fruits", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, JapaneseFoodActivity::class.java)
            intent.putExtra("OOO",13)
            startActivity(intent)
        }

        fab_material.setOnClickListener {
            Toast.makeText(this, "Material", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, JapaneseFoodActivity::class.java)
            intent.putExtra("OOO",15)
            startActivity(intent)
        }

    }

    private fun displayFABs(display: Boolean) {
        if(display == true)
        {
            fab_sashimi.show()
            fab_bread.show()
            fab_noodles.show()
            fab_sushi.show()
            fab_tempura.show()
            fab_kushi.show()
            fab_hotpot.show()
            fab_beverages.show()
            fab_fruits.show()
            fab_material.show()
        }
        else
        {
            fab_sashimi.hide()
            fab_bread.hide()
            fab_noodles.hide()
            fab_sushi.hide()
            fab_tempura.hide()
            fab_kushi.hide()
            fab_hotpot.hide()
            fab_beverages.hide()
            fab_fruits.hide()
            fab_material.hide()
        }

    }

    private fun addControls() {
        //COPY DATABASE INTO DEVICE
        var dbHelperDBCopy = DBHelper(this)
        dbHelperDBCopy.copyDBToMobile()

        //VIEWS
        display_FAB = true
        


    }

   

}

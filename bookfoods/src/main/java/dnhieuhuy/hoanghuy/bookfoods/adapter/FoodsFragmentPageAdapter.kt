package dnhieuhuy.hoanghuy.bookfoods.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import dnhieuhuy.hoanghuy.bookfoods.ui.FoodBookFragment

/**
 * Created by Administrator on 15/07/2017.
 */
class FoodsFragmentPageAdapter(fm: FragmentManager): FragmentPagerAdapter(fm)
{
    internal val PAGE_COUNT = 18
    private val tabTitle = arrayOf("Sushi", "Sashimi", "Noodles", "Tempura-Fried Food", "Appetizer",
            "Hotpot", "Grilled Food", "Pickles", "RiceBowl-Bentou", "Salad-Soup",
            "National Dish", "Bread", "Sweets" , "Fruits", "Vegetables",
            "Material-Seasioning" , "Alcoholic Beverages", "Tea-SoftDrink")
    private val tabArticlesID = arrayOf("SUSIT", "SASIT", "NOODT", "TPRAT", "APPET",
            "HPOTT", "GRILT", "PICKT", "RIBET", "SASOT",
            "GODIT", "BREAT", "CAKET" , "FRUST", "VEFRT",
            "MATET" , "ALDRT", "SODRT")
    override fun getItem(position: Int): Fragment {
        return FoodBookFragment.newInstance(tabArticlesID.get(position))
    }

    override fun getCount(): Int {
        return PAGE_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence {
        return tabTitle[position]
    }
}
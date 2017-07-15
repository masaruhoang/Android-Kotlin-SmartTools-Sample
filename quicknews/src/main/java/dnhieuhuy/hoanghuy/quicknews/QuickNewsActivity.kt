package dnhieuhuy.hoanghuy.quicknews

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import com.astuetz.PagerSlidingTabStrip
import dnhieuhuy.hoanghuy.quicknews.adapter.ArticlesFragmentPageAdapter

class QuickNewsActivity : AppCompatActivity() {

    private var mViewPager: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quick_news)

        // Get the ViewPager and set it's PageAdapter so that it can display items
        mViewPager = findViewById(R.id.container) as ViewPager
        mViewPager!!.adapter = ArticlesFragmentPageAdapter(supportFragmentManager)

        //Give the PagerSlidingTabStrip the ViewPager
        val tabsStrip = findViewById(R.id.tabs) as PagerSlidingTabStrip
        //Attach the view pager to the tab strip
        tabsStrip.setViewPager(mViewPager)
    }
}

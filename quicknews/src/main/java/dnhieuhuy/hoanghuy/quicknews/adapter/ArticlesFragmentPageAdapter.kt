package dnhieuhuy.hoanghuy.quicknews.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.administrator.myapplication.tabLayout.ArticlesTabs


/**
 * Created by Administrator on 15/07/2017.
 */
class ArticlesFragmentPageAdapter(fm: FragmentManager): FragmentPagerAdapter(fm)
{
    internal val PAGE_COUNT = 8
    private val tabTitle = arrayOf("News", "Sports", "Gaming", "Technology",
            "Business", "Entertainments", "Science", "Politics")
    private val tabArticlesID = arrayOf("bbc-news", "espn", "ign", "the-verge",
            "the-wall-street-journal", "buzzfeed", "national-geographic", "breitbart-news")
    override fun getItem(position: Int): Fragment {
        return ArticlesTabs.newInstance(tabArticlesID.get(position))
    }

    override fun getCount(): Int {
        return PAGE_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence {
        return tabTitle[position]
    }
}
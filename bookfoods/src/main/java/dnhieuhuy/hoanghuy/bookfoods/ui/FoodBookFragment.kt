package dnhieuhuy.hoanghuy.bookfoods.ui

import android.annotation.SuppressLint
import android.support.v4.app.LoaderManager
import android.support.v4.content.CursorLoader
import android.support.v4.content.Loader
import android.arch.lifecycle.LifecycleFragment
import android.content.Context
import android.database.Cursor
import android.os.Bundle
import android.view.*
import android.widget.GridView
import android.widget.TextView
import dnhieuhuy.hoanghuy.bookfoods.R
import dnhieuhuy.hoanghuy.bookfoods.model.FoodSource
import dnhieuhuy.hoanghuy.bookfoods.ui.SubFormView.SubFormView
import dnhieuhuy.hoanghuy.foodbooks.adapter.FoodCursorAdapter
import dnhieuhuy.hoanghuy.foodbooks.mainapplication.MainApplication
import kotlin.properties.Delegates

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FoodBookFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [FoodBookFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FoodBookFragment :  LoaderManager.LoaderCallbacks<Cursor>, LifecycleFragment() {


    /**
     * Declaring variables is in HERE
     * **/

    private var mPage: String = ""
    private var grFood: GridView? = null
    private var foodLists: FoodSource? = null


    /**Delegates**/
    private var mApp: MainApplication by Delegates.notNull()
    private var mAdapter: FoodCursorAdapter by Delegates.notNull()

    @SuppressLint("MissingSuperCall")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPage = arguments.getString(ARG_PAGE)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_food_book, container, false)
        addControls(view)
        addEvents(view)
        return view
    }


    private fun addEvents(view: View) {
        foodLists = mApp.dbHelper.getFoodsSubForm(mPage,1)
        var subFormView = SubFormView()

        grFood?.setOnItemClickListener{ adapterView, view, i, l ->
                subFormView.initSubForm(this.context, foodLists!!.foodsListRS.get(i))
        }
    }

    private fun addControls(view: View) {

        FoodBookFragment.mPAGE = mPage

        //Setting RecycleView
        try {
            grFood = view.findViewById(R.id.grFood)
                mApp = activity.application as MainApplication
                mAdapter = FoodCursorAdapter(this.context, mApp.dbHelper.getFoods(mPage))
                grFood?.adapter = mAdapter




            //init loader
            loaderManager.initLoader(0, null, this)


        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    //Conpanion Object
    companion object{
        val ARG_PAGE = "ARG_PAGE"
        var mPAGE = ""
        fun newInstance(page: String): FoodBookFragment
        {
            val args = Bundle()
            args.putString(ARG_PAGE, page)
            val fragment = FoodBookFragment()
            fragment.arguments = args
            return fragment
        }

        class DBCursorLoader(val app: MainApplication, context: Context): CursorLoader(context)
        {
            override fun loadInBackground(): Cursor {
                return app.dbHelper.getFoods(mPAGE)
            }
        }
    }


    override fun onLoadFinished(loader: Loader<Cursor>?, cursor: Cursor?) {
        mAdapter.swapCursor(cursor)
    }

    override fun onCreateLoader(p0: Int, p1: Bundle?): Loader<Cursor> {
        return DBCursorLoader(mApp, this.context)
    }

    override fun onLoaderReset(p0: Loader<Cursor>?) {
        mAdapter.swapCursor(null)
    }
}


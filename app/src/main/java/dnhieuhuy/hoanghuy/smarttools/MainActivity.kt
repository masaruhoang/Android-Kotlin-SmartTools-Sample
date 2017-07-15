package dnhieuhuy.hoanghuy.smarttools

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import dnhieuhuy.hoanghuy.smarttools.flashlight.SetOnOffFlashLight
import dnhieuhuy.hoanghuy.smarttools.ui.gridview.adapter.GridItemAdapter
import dnhieuhuy.hoanghuy.smarttools.ui.gridview.support.SpaceItemDecorator
import dnhieuhuy.hoanghuy.spannedgridlayoutmanager.SpannedGridLayoutManager

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.recyclerView

class MainActivity : AppCompatActivity() {

    override fun onDestroy() {
        super.onDestroy()
        var setOnOffflash: SetOnOffFlashLight = SetOnOffFlashLight()
        setOnOffflash.turnOffFlash()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val staggeredGridLayoutManager = SpannedGridLayoutManager(
                orientation = SpannedGridLayoutManager.Orientation.VERTICAL, spans = 4)
        recyclerView.layoutManager = staggeredGridLayoutManager

        recyclerView.addItemDecoration(SpaceItemDecorator(left = 5, top = 5, right = 5, bottom = 5))

        val adapter = GridItemAdapter(this)
        recyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_settings ->
                return true
            else -> super.onOptionsItemSelected(item)
        }
    }
}

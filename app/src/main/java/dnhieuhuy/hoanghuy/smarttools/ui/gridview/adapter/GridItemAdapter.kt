package dnhieuhuy.hoanghuy.smarttools.ui.gridview.adapter

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import dnhieuhuy.hoanghuy.smarttools.MainActivity
import dnhieuhuy.hoanghuy.smarttools.R
import dnhieuhuy.hoanghuy.smarttools.flashlight.SetOnOffFlashLight
import dnhieuhuy.hoanghuy.smarttools.ui.gridview.support.GridItemView
import dnhieuhuy.hoanghuy.smarttools.ui.gridview.support.ItemAddEventsDesign


/**
 * Created by Jorge Martín on 24/5/17.
 */
class GridItemAdapter(mainActivity: MainActivity): RecyclerView.Adapter<GridItemViewHolder>() {

    private val mainActivity = mainActivity
    private val clickedItems: MutableList<Boolean>
    private val setOnOffLight = SetOnOffFlashLight()


    init {
        clickedItems = MutableList(itemCount, { false })
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    override fun onBindViewHolder(holder: GridItemViewHolder, position: Int) {
        val width = if (clickedItems[position]) 2 else 1
        val height = if (clickedItems[position]) 2 else 1

        //========================SETTING VIEW========================
        ItemAddEventsDesign.itemDesign(holder.itemView,position, width,height)

        //========================ITEM EVENT CLICK===============================

        holder.itemView.setOnClickListener {
            clickedItems[position] = !clickedItems[position]
            ItemAddEventsDesign.itemEventClick(position, setOnOffLight, mainActivity)
            notifyItemChanged(position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return 30
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): GridItemViewHolder {
        val gridItemView = GridItemView(parent!!.context)

        return GridItemViewHolder(gridItemView)
    }

}

class GridItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
}
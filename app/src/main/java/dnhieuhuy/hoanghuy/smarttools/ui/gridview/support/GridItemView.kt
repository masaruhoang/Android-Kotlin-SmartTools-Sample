package dnhieuhuy.hoanghuy.smarttools.ui.gridview.support

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

/**
 * Created by Jorge Martín on 24/5/17.
 */
class GridItemView(context: Context?) : LinearLayout(context) {

    private var imgIcon: ImageView
    private var title: TextView

    init {
        setOrientation(LinearLayout.VERTICAL)
        title = TextView(context)
        title.gravity = Gravity.CENTER
        title.setTextSize(15F)
        title.setTextColor(Color.parseColor("#FFFFFF"))
        addView(title,160, 160)

        imgIcon = ImageView(context)
        imgIcon.setPadding(30,0,0,5)
        addView(imgIcon, 128, 128)
    }

    fun setTitle(title: String, iconBg: Int) {
        this.imgIcon.setImageResource(iconBg)
        this.title.text = title
        requestLayout()
    }

}
package dnhieuhuy.hoanghuy.quicknews.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

/**
 * Created by Administrator on 10/06/2017.
 */
/**
 * Extension functions
 */

/**
 * Inflate the layout
 * @param layoutRes layout resource id
 */

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

/**
 ** Easy toast function for Activity
 */
    fun FragmentActivity.toast(text: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, text, duration).show()}

    fun Fragment.toast(text: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this.context, text, duration).show()
}

package dnhieuhuy.hoanghuy.relaxsound.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import dnhieuhuy.hoanghuy.relaxsound.R
import dnhieuhuy.hoanghuy.relaxsound.SoundActivity
import dnhieuhuy.hoanghuy.relaxsound.User



class CustomRecyclerAdapter(val userList: ArrayList<User>, soundActivity: SoundActivity) : RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder>() {
    private var idSound: Int? = R.raw.dawnchorus
    private var soundActivity = soundActivity

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.sound_item, parent,false)
        var animation: Animation = AnimationUtils.loadAnimation(soundActivity, R.anim.scale_list)
        v.startAnimation(animation)
        return  ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int){
        val user: User = userList[position]
        holder?.txtName?.text = user.name

        holder?.itemView?.setOnClickListener {
            Log.d("MAINA: ", user.name)
            setPositionInf(user.address)
            soundActivity.idSoundCheck = user.address
            soundActivity.changeSoundByTapList()
        }



    }

    override fun getItemCount(): Int {
        return userList.size
    }



    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txtName = itemView.findViewById<TextView>(R.id.txtName)

    }

    fun setPositionInf(idSound: Int)
    {
        this.idSound =  idSound
    }

    fun getPositionInf(): Int? {
        return idSound
    }

}


package com.example.androidconstructer

import android.content.ClipData.Item
import androidx.core.graphics.ColorUtils
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.annotation.NonNull
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.androidconstructer.Data.ImageLoader

class ItemAdapter2(private val context: Context, private val items: List<ItemFav6>) : RecyclerView.Adapter<ItemAdapter2.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = items[position]

        holder.nameTextView.text = item.name
        if(item.sell=="null"){
            holder.sellTextView.text = ""
        }else{
            holder.sellTextView.text = item.sell
        }
        holder.priceTextView.text = item.price
        holder.infoTextView.text = item.little_info
        holder.Back.setBackgroundColor(Color.parseColor(item.back))
        holder.nameTextView.setTextColor(Color.parseColor(item.name_color))
        holder.infoTextView.setTextColor(Color.parseColor(item.info_color))
        holder.sellTextView.setTextColor(Color.parseColor(item.sell_color))
        holder.priceTextView.setTextColor(Color.parseColor(item.price_color))
        holder.nameTextView.textSize = item.name_size.toFloat()
        holder.bucks.setBackgroundColor(Color.parseColor(item.back))
        holder.infoTextView.textSize = item.info_size.toFloat()
        holder.sellTextView.textSize = item.sell_size.toFloat()
        holder.priceTextView.textSize = item.price_size.toFloat()

        val imageViews = listOf(
            holder.imageView1,
            holder.imageView2,
            holder.imageView3,
            holder.imageView4,
            holder.imageView5,
            holder.imageView6,
            holder.imageView7,
            holder.imageView8,
            holder.imageView9,
            holder.imageView10
        )


        imageViews.forEachIndexed { index, imageUrl ->

            imageViews[index].visibility = View.VISIBLE
            val imageUrl = item.imageUrls.getOrNull(index)
            if (imageUrl != null) {
                val imageLoader = ImageLoader(imageViews[index], context, imageUrl)
                imageLoader.loadImageFromUrl("https://untr.ru/Image/$imageUrl")
            } else {
                imageViews[index].visibility = View.GONE
            }

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val imageView1: ImageView = itemView.findViewById(R.id.item_image)
        val imageView2: ImageView = itemView.findViewById(R.id.item_image)
        val imageView3: ImageView = itemView.findViewById(R.id.item_image)
        val imageView4: ImageView = itemView.findViewById(R.id.item_image)
        val imageView5: ImageView = itemView.findViewById(R.id.item_image)
        val imageView6: ImageView = itemView.findViewById(R.id.item_image)
        val imageView7: ImageView = itemView.findViewById(R.id.item_image)
        val imageView8: ImageView = itemView.findViewById(R.id.item_image)
        val imageView9: ImageView = itemView.findViewById(R.id.item_image)
        val imageView10: ImageView = itemView.findViewById(R.id.item_image)
        val nameTextView: TextView = itemView.findViewById(R.id.item_name)
        val bucks:ConstraintLayout = itemView.findViewById(R.id.lolipop)
        val sellTextView: TextView = itemView.findViewById(R.id.item_sell)
        val priceTextView: TextView = itemView.findViewById(R.id.item_price)
        val infoTextView: TextView = itemView.findViewById(R.id.item_info)
        val Back: ConstraintLayout = itemView.findViewById(R.id.lolipop)


        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            val item = items[adapterPosition]
            val intent = Intent(context, ItemDetailsActivity::class.java)
            intent.putExtra("price",item.price.toString())
            intent.putExtra("full_info",item.full_info.toString())
            intent.putExtra("sell",item.sell.toString())
            intent.putExtra("image_url",item.imageUrls.toList().toString())
            intent.putExtra("name",item.name.toString())


            context.startActivity(intent)
        }
    }

    data class ItemFav6(
        val id:Int,
    val name: String,
    val price: String,
    val sell: String,
    val full_info: String,
    val little_info: String,
    val imageUrls: List<String>,
    val rating: Int,
    val back:String,
    val name_size: String,
    val name_color: String,
    val price_size: String,
    val price_color: String,
    val sell_size: String,
    val sell_color: String,
    val info_color: String,
    val info_size: String
    )



}

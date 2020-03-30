package fr.iutbourg.pokemoncardsexchange.ui.widget

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import fr.iutbourg.pokemoncardsexchange.R
import fr.iutbourg.pokemoncardsexchange.data.model.Card
import kotlinx.android.synthetic.main.card_view_holder.view.*

class PokedexViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val pokemonCardPicture = itemView.cardview[0] as ImageView
    private lateinit var target: CustomTarget
    fun bindPhoto(card: Card) {
        target = CustomTarget(pokemonCardPicture, card)
        Picasso.with(itemView.context).load(card.imageUrl).into(target)
    }

    companion object {
        /**
         * Create a new Instance of [FriendViewHolder]
         */
        fun create(parent: ViewGroup): PokedexViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(
                R.layout.card_view_holder,
                parent,
                false
            )
            return PokedexViewHolder(view)
        }
    }
}

class CustomTarget(
    private val pokemonCardPicture: ImageView,
    private var card: Card
) : Target {

    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
        pokemonCardPicture.setImageDrawable(BitmapUtils.applyWhiteAndDark(placeHolderDrawable))
    }

    override fun onBitmapFailed(errorDrawable: Drawable?) {
    }

    override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
        pokemonCardPicture.setImageBitmap(bitmap)
        card.owned?.let {
            if(it){
                pokemonCardPicture.setImageBitmap(bitmap)
            }
            else {
                pokemonCardPicture.setImageBitmap(bitmap)
                pokemonCardPicture.setImageDrawable(BitmapUtils.applySepiaEffect(pokemonCardPicture, bitmap))

            }
        }

    }

}
package fr.iutbourg.pokemoncardsexchange.ui.fragment

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import fr.iutbourg.pokemoncardsexchange.R
import fr.iutbourg.pokemoncardsexchange.data.model.Card
import kotlinx.android.synthetic.main.activity_pokedex.*
import kotlinx.android.synthetic.main.fragment_detail_card.*
import java.util.*

class DetailCardFragment(private val item: Card) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureView()
        requireActivity().myBottomAppBar.visibility = View.GONE
        requireActivity().researchFabMenuBar.visibility = View.GONE

    }

    private fun configureView() {
        pokemonName.text = item.name
        item.nationalPokedexNumber?.let {
            pokemonIdPokedex.text = pokemonIdPokedex.text.toString() + it
        }
        item.types?.let {
            pokemonType1Label.text = it[0]
            if (it.size > 1) {
                pokemonType2Label.text = it[1]
                pokemonType2.visibility = View.VISIBLE
            }
        }
        pokemonHP.text = pokemonHP.text.toString() + item?.hp
        Picasso.with(requireContext()).load(item.imageUrlHiRes).into(cardPicture)
        for (value in PokemonColor.values()) {
            item.types?.let {
                val string = it[0].toUpperCase(Locale.ROOT)
                if (string == "COLORLESS" || string == "LIGHTNING") {
                    cardRootView.setBackgroundColor(Color.parseColor(PokemonColor.valueOf(string).hex))
                } else if (string == value.name && string != "COLORLESS" && string != "LIGHTNING") {
                    cardRootView.setBackgroundColor(Color.parseColor(PokemonColor.valueOf(string).hex))
                }
            }
            if(item.types == null){
                cardRootView.setBackgroundColor(Color.parseColor(PokemonColor.valueOf("COLORLESS").hex))

            }

        }
        setUpLogoColorAttack()
        setUpBackgroundAttack()
        setUpSpecialCardEffect()
    }

    private fun setUpSpecialCardEffect() {
        item.text?.let {
            textSpecialCard.text = it[0]
        }
    }

    private fun setUpLogoColorAttack() {
        item.attacks?.let {
            attackContainer2.visibility = VISIBLE
            attackContainer1.visibility = VISIBLE
            for(attack in it.indices){
                it[attack].getCost()?.let {costs ->
                    for (view in costs.indices){
                        val relativeLayout = cardRootView[4 + attack] as RelativeLayout
                        val linearLayout = relativeLayout[0] as LinearLayout
                        val imageView = linearLayout[view] as ImageView
                        imageView.visibility = View.VISIBLE
                        imageView.setCustomImageDrawable(costs[view])
                    }
                }
            }
            if(it.size > 1) {
                labelAttackDamage2.text = it[1].getDamage()
                labelAttack2.text = it[1].getName()
            }
            else {
                labelAttack1.text = it[0].getName()
                labelAttackDamage1.text = it[0].getDamage()
            }
        }
//
//
//
//
//        var size = item.attacks?.get(0)?.getCost()?.size
//        size?.let {
//            for (view in 0 until it) {
//                val imageView = (energyContainer[view] as ImageView)
//                imageView.visibility = View.VISIBLE
//                imageView.setCustomImageDrawable(item.attacks?.get(0)?.getCost()?.get(view))
//            }
//        }
//
//        size = item.attacks?.let {
//            if(it.size > 1 ){
//                it[0].getCost()?.size
//                it[1].getCost()?.size
//            }
//            else{
//                0
//            }
//        }
//        if (size!! > 1) {
//            size.let {
//                for (view in 0 until it) {
//                    val imageView = (energyContainer2[view] as ImageView)
//                    imageView.visibility = View.VISIBLE
//                    imageView.setCustomImageDrawable(item.attacks?.get(1)?.getCost()?.get(view))
//                }
//            }
//
//        }
//        else{
//            attackContainer2.visibility = View.GONE
//        }
    }

    private fun setUpBackgroundAttack() {
        item.types?.let {list ->
            val color = PokemonColor.values().filter {
                it.name == list[0].toUpperCase(Locale.ROOT)
            }[0]
            attackContainer1.background.setTint(Color.parseColor(color.hex))
            attackContainer2.background.setTint(Color.parseColor(color.hex))
            attackContainer1.visibility = VISIBLE
            attackContainer2.visibility = VISIBLE
            labelAttack1.text = item.attacks?.get(0)?.getName()
            labelAttackDamage1.text = item.attacks?.get(0)?.getDamage()
            if (color.name != "COLORLESS" || color.name == "LIGHTNING") {
                labelAttack1.setTextColor(Color.WHITE)
                labelAttackDamage1.setTextColor(Color.WHITE)
                labelAttackDamage2.setTextColor(Color.WHITE)
                labelAttack2.setTextColor(Color.WHITE)
            }
        }

    }
}

private fun ImageView.setCustomImageDrawable(name: String?) {
    val drawable: Drawable
    if(name == "Free") {
        drawable =
            context.resources.getDrawable(
                resources.getIdentifier(
                    "colorless_logo",
                    "drawable",
                    context.packageName
                ),
                null
            )
    }
    else {
        drawable =
            context.resources.getDrawable(
                resources.getIdentifier(
                    name?.toLowerCase(Locale.ROOT) + "_logo",
                    "drawable",
                    context.packageName
                ),
                null
            )
    }
    this.setImageDrawable(drawable)
}

enum class PokemonColor(val hex: String) {
    COLORLESS("#A8A77A"),
    FIRE("#EE8130"),
    WATER("#6390F0"),
    LIGHTNING("#F7D02C"),
    GRASS("#7AC74C"),
    ICE("#96D9D6"),
    FIGHTING("#C22E28"),
    POISON("#A33EA1"),
    GROUND("#E2BF65"),
    FLYING("#A98FF3"),
    PSYCHIC("#5A3954"),
    BUG("#A6B91A"),
    ROCK("#B6A136"),
    GHOST("#735797"),
    DRAGON("#614A25"),
    DARKNESS("#2D2E2B"),
    METAL("#B7B7CE"),
    FAIRY("#D685AD")
}

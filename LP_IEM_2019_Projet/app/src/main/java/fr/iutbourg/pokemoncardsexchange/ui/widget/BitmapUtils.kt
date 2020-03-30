package fr.iutbourg.pokemoncardsexchange.ui.widget

import android.graphics.Bitmap
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.widget.ImageView


class BitmapUtils {

    companion object{
        fun applySepiaEffect(view: ImageView, bitmap: Bitmap?): Drawable{
            val drawable: Drawable = BitmapDrawable(view.resources, bitmap)

            val matrixA = ColorMatrix()
            // making image B&W
            matrixA.setSaturation(0f)

            val matrixB = ColorMatrix()
            // applying scales for RGB color values
            matrixB.setScale(1f, .95f, .82f, 1.0f)
            matrixA.setConcat(matrixB, matrixA)

            val filter = ColorMatrixColorFilter(matrixA)
            drawable.colorFilter = filter
            return drawable
        }

        fun applyWhiteAndDark(
            placeHolderDrawable: Drawable?
        ): Drawable? {
            val matrixA = ColorMatrix()
            // making image B&W
            matrixA.setSaturation(0f)
            val filter = ColorMatrixColorFilter(matrixA)
            placeHolderDrawable?.colorFilter = filter
            return placeHolderDrawable
        }
    }
}
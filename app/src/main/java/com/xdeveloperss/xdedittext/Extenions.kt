package com.xdeveloperss.xdedittext

import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.StateListDrawable
import android.util.StateSet


enum class GradientTypes(val gradientType:Int) {
    LINE(GradientDrawable.LINE),LINEAR_GRADIENT(GradientDrawable.LINEAR_GRADIENT),
    OVAL(GradientDrawable.OVAL),RADIAL_GRADIENT(GradientDrawable.RADIAL_GRADIENT),
    RECTANGLE(GradientDrawable.RECTANGLE),RING(GradientDrawable.RING),
    SWEEP_GRADIENT(GradientDrawable.SWEEP_GRADIENT)
}
fun getDrawable(
    color: Int,
    borderColor: Int = Color.TRANSPARENT,
    borderWidth: Int = 0,
    topLeft: Float = 0f,
    topRight: Float = 0f,
    bottomRight: Float = 0f,
    bottomLeft: Float = 0f
): GradientDrawable =
    GradientDrawable().also {
        it.setColor(color)
        it.setStroke(borderWidth.dp, borderColor)
        it.cornerRadii = floatArrayOf(
            topLeft,
            topLeft,
            topRight,
            topRight,
            bottomRight,
            bottomRight,
            bottomLeft,
            bottomLeft
        )
    }

fun getGradientDrawable(
    intArray: IntArray,
    orientation: GradientDrawable.Orientation = GradientDrawable.Orientation.LEFT_RIGHT,
    borderColor: Int = Color.TRANSPARENT,
    gradientType: GradientTypes = GradientTypes.RECTANGLE,
    borderWidth: Int = 0,
    topLeft: Float = 0f,
    topRight: Float = 0f,
    bottomRight: Float = 0f,
    bottomLeft: Float = 0f
): GradientDrawable = GradientDrawable(
    orientation,
    intArray
).also {
    it.setStroke(borderWidth, borderColor)
    it.gradientType = gradientType.gradientType
    it.cornerRadii = floatArrayOf(
        topLeft,
        topLeft,
        topRight,
        topRight,
        bottomRight,
        bottomRight,
        bottomLeft,
        bottomLeft
    )
}

fun getFilledDrawable(
    color: Int,
    radius:  Float = 0f
): GradientDrawable = getDrawable(
    color,
    topLeft = radius,
    topRight = radius,
    bottomRight = radius,
    bottomLeft = radius
)

fun getFilledGradientDrawable(
    colors: IntArray,
    radius: Float = 0f,
    orientation: GradientDrawable.Orientation
): GradientDrawable = getGradientDrawable(
    colors,
    orientation,
    topLeft = radius,
    topRight = radius,
    bottomRight = radius,
    bottomLeft = radius
)

fun getBorderedDrawable(
    borderColor: Int,
    borderWidth: Int = 0,
    fillColor: Int = Color.TRANSPARENT,
    radius: Float = 0f
): GradientDrawable = getDrawable(
    fillColor,
    borderColor,
    borderWidth,
    radius,
    topRight = radius,
    bottomRight = radius,
    bottomLeft = radius
)

fun getLayeredDrawable(drawables:Array<Drawable>) = LayerDrawable(drawables)

fun getSelectorDrawable(pressedColor:Int,bgColor:Int,radius:Int=0): StateListDrawable {
    val drawable = StateListDrawable()
    drawable.addState(intArrayOf(android.R.attr.state_checked), getDrawable(pressedColor,radius))
    drawable.addState(StateSet.WILD_CARD, getDrawable(bgColor,radius))
    return drawable
}


val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()
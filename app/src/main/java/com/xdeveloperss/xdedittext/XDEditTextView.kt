package com.xdeveloperss.xdedittext

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.LinearLayout

class XDEditTextView: FrameLayout {

    private lateinit var editText: EditText
    private var mText: String =  ""

    var textXD: String
        get() = mText
        set(prefix) {
            mText = prefix
            invalidate()
        }

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }

    private fun init(context: Context, attributeSet: AttributeSet?) {
        init()
    }

    private fun init(){
        this.background = getFilledDrawable(Color.RED, 20f)
        editText = EditText(this.context)
        editText.hint = "Hint Text"
        editText.setText(this.textXD)
        editText.setBackgroundColor(Color.TRANSPARENT)
        editText.layoutParams =
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        editText.textAlignment = TEXT_ALIGNMENT_CENTER
        editText.setPadding(20, 20, 20, 20)

        this.addView(editText)
    }
}
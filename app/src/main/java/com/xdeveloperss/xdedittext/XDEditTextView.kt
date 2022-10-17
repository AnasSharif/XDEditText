package com.xdeveloperss.xdedittext

import android.content.Context
import android.graphics.Color
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout


class XDEditTextView: FrameLayout, View.OnClickListener, TextWatcher {

    private lateinit var editText: EditText

    private lateinit var endIcon: ImageView

    private lateinit var startIcon: ImageView

    var textXD: String
        get() = ""
        set(txt) {
            this.editText.setText(txt)
        }

    var hintXD: String
        get() = "Hint"
        set(hint) {
            this.editText.hint = hint
        }

    var textSizeXD: Float
        get() = 15.0f
        set(size) {
            this.editText.textSize = size
        }

    var textColorXD: Int
        get() = Color.WHITE
        set(color) {
            this.editText.setTextColor(color)
        }

    var backgroundColorXD: Int
        get() = context.getColor(R.color.gray)
        set(color) {
           this.background = getFilledDrawable(color, roundCornerXD)
        }

    var roundCornerXD: Float
        get() =  20f
        set(corner) {
            getFilledDrawable(backgroundColorXD, corner)
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
        removeTextImageView()
    }

    private fun init(){
        this.background = getFilledDrawable(backgroundColorXD, roundCornerXD)
        editText = EditText(this.context)
        editText.setBackgroundColor(Color.TRANSPARENT)
        editText.hint = this.hintXD
        editText.maxLines = 1
        editText.ellipsize = TextUtils.TruncateAt.END
        editText.isSingleLine = true
        editText.layoutParams =
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        editText.textAlignment = TEXT_ALIGNMENT_CENTER
        editText.setPadding(5, 5, 5, 5)

        this.addView(editText)

        editText.addTextChangedListener(this)


        editText.setOnKeyListener(OnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                v.hideKeyboard()
                v.clearFocus()
                return@OnKeyListener true
            }
            false
        })
    }
    private fun removeTextImageView(){
        endIcon = ImageView(this.context)
        val params =  LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        params.gravity = Gravity.END or Gravity.CENTER
        params.marginEnd = 5.dp
        endIcon.layoutParams = params
        endIcon.setDrawableX(R.drawable.ic_remove)
        endIcon.setDrawableColorX(Color.WHITE)
        endIcon.visibility = INVISIBLE

        endIcon.setOnClickListener(this)
        this.addView(endIcon)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        endIcon.visibility = (s.isEmpty() then INVISIBLE) ?: VISIBLE
    }

    override fun afterTextChanged(s: Editable?) {

    }

    override fun onClick(v: View) {
        if (v == this.endIcon){
            this.textXD = ""
        }
    }
}
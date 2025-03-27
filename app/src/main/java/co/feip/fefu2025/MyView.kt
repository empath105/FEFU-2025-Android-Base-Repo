package co.feip.fefu2025

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.compose.ui.graphics.Color

val pastelColors = listOf(
    Color(0xFFFFE0B2),
    Color(0xFFFFCCBC),
    Color(0xFFBBDEFB),
    Color(0xFFD1C4E9),
    Color(0xFFF8BBD0)
)

class MyView : LinearLayout {

    private lateinit var genreNameTextView: TextView

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        LayoutInflater.from(context).inflate(R.layout.sample_my_view, this, true)
        genreNameTextView = findViewById(R.id.textview)

        attrs?.let {
            val typedArray: TypedArray = context.obtainStyledAttributes(it, R.styleable.MyView, 0, 0)
            try {
                val genreName = typedArray.getString(R.styleable.MyView_genreName)
                val backgroundColor = typedArray.getColor(R.styleable.MyView_backgroundColor, 0)
                val cornerRadius = typedArray.getDimension(R.styleable.MyView_cornerRadius, 0f)

                setGenreName(genreName)
                randomBackground()

//                 Установка округлённой рамки
                val backgroundDrawable = GradientDrawable().apply {
                    setColor(backgroundColor)
                    setCornerRadius(cornerRadius)
                }
                background = backgroundDrawable


            } finally {
                typedArray.recycle()
            }
        }
    }

    fun setGenreName(genreName: String?) {
        genreNameTextView.text = genreName ?: "Жанр аниме"
    }

    fun randomBackground(): Color {
        return pastelColors.random()
    }

}
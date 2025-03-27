package co.feip.fefu2025

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb

class MainActivity : ComponentActivity() {
    private lateinit var flexBoxLayout: FlexBoxLayout
    private var itemCount = 0
    private val genres = listOf("Сёнен", "Приключения", "Ужасы", "Комедия", "Повседневность", "Школа", "Музыка", "Спорт")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity)

        flexBoxLayout = findViewById(R.id.flexBoxLayout)
        val button: Button = findViewById(R.id.button)

        button.setOnClickListener {
            addNewAnimeGenreView()
        }
    }

    private fun addNewAnimeGenreView() {
        itemCount++
        val genreName = genres[(itemCount - 1) % genres.size ]
        val animeGenreView = MyView(this).apply {
            setGenreName(genreName)
            val backgroundColor = randomBackground().toArgb()
            val cornerRadius = resources.getDimension(R.dimen.corner_radius)

            // Создание и установка фона с округленными углами
            val backgroundDrawable = GradientDrawable().apply {
                setColor(backgroundColor)
                setCornerRadius(cornerRadius)
                setStroke(4, Color.Gray.toArgb())
            }
            background = backgroundDrawable
            val params = ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT).apply {
                setMargins(0, 2.toPx(context), 10.toPx(context), 10.toPx(context))
            }
            layoutParams = params
        }

        flexBoxLayout.addFlexItem(animeGenreView)
    }
}

private fun Int.toPx(context: Context): Int {
    return (this * context.resources.displayMetrics.density).toInt()
}
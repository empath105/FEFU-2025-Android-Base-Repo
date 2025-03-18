package co.feip.fefu2025

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import kotlin.random.Random

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
            val backgroundColor = Random.nextInt(0xFF000000.toInt(), 0xFFFFFFFF.toInt())
            val cornerRadius = resources.getDimension(R.dimen.corner_radius)

            // Создание и установка фона с округленными углами
            val backgroundDrawable = GradientDrawable().apply {
                setColor(backgroundColor)
                setCornerRadius(cornerRadius)
            }
            background = backgroundDrawable
        }

        flexBoxLayout.addFlexItem(animeGenreView)
    }
}
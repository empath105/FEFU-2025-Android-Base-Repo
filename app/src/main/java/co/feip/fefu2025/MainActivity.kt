import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import co.feip.fefu2025.presentation.navigaition.Graph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Graph()
        }
    }
}
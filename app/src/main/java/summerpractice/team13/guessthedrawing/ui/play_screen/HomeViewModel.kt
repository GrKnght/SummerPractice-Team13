package summerpractice.team13.guessthedrawing.ui.play_screen
import android.util.TypedValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import summerpractice.team13.guessthedrawing.R

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Implement Game"
    }
    val text: LiveData<String> = _text
}
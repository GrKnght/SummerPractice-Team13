package summerpractice.team13.guessthedrawing.ui.settings_screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SettingsViewModel : ViewModel() {


    val value: MutableLiveData<Float> by lazy {
        MutableLiveData<Float>()
    }


}
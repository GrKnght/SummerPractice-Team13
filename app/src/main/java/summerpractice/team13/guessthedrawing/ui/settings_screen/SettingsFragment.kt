package summerpractice.team13.guessthedrawing.ui.settings_screen

import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import summerpractice.team13.guessthedrawing.R
import summerpractice.team13.guessthedrawing.databinding.FragmentSettingsBinding
import summerpractice.team13.guessthedrawing.mvp.presenters.AppPreferences
import summerpractice.team13.guessthedrawing.mvp.presenters.change_difficulty_presenter.ChangeDifficultyPresenter
import summerpractice.team13.guessthedrawing.mvp.presenters.change_difficulty_presenter.IChangeDifficultyPresenter
import summerpractice.team13.guessthedrawing.mvp.views.change_time_view.IChangeDifficultyView
import summerpractice.team13.guessthedrawing.utils.LocaleUtils
import summerpractice.team13.guessthedrawing.utils.PlayerSaver


class SettingsFragment : Fragment(), IChangeDifficultyView {

    // TODO: Не переводит hint в textInputLayout
    // TODO: Не переводит текст в окне с выбором сложности
    // TODO: Не переводит текст в bottomNavigation
    // TODO: Подправить и подобрать переводы к каждому слову

    private lateinit var mp: MediaPlayer
    private lateinit var ichangeDifficultyPresenter: IChangeDifficultyPresenter
    private lateinit var autoCompleteTextView: AutoCompleteTextView
    private lateinit var autoCompleteTextViewLang: AutoCompleteTextView
    private lateinit var arrayAdapter: ArrayAdapter<String>
    private lateinit var textField: TextInputLayout
    private lateinit var textFieldLang: TextInputLayout

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onResume() {
        super.onResume()
        val difficulties = resources.getStringArray(R.array.difficultyLevels)
        arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, difficulties)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)

        val languages = resources.getStringArray(R.array.languages)
        arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, languages)
        binding.autoCompleteTextViewLang.setAdapter(arrayAdapter)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        context?.let { AppPreferences.init(it) }
        context?.let { LocaleUtils.setLocaleLanguage(AppPreferences.languageCode.toString(), it) }
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        ichangeDifficultyPresenter = ChangeDifficultyPresenter(this)
        val view = binding.root

        // Values
        val playMusic: Button = view.findViewById(R.id.btn_play_music)
        val stopMusic: Button = view.findViewById(R.id.btn_stop_music)

        // PlayerSaver.instance


        // if (AppPreferences.instanceNull == true) {

        mp = MediaPlayer.create(context, R.raw.music)

        println("---------${mp}-------------")
        mp.isLooping = true
        mp.setVolume(0.5f, 0.5f)

        //AppPreferences.instanceNull = false

        //}
        playMusic.setOnClickListener {
            if (mp.isPlaying) {
                mp.start()
            } else {
                mp.start()
            }
            AppPreferences.playMusicButtonEnabled = false
            AppPreferences.stopMusicButtonEnabled = true
            playMusic.isEnabled = AppPreferences.playMusicButtonEnabled
            stopMusic.isEnabled = AppPreferences.stopMusicButtonEnabled
            PlayerSaver.list.add(mp)

        }

        stopMusic.setOnClickListener {
            if (PlayerSaver.list.last().isPlaying) {
                PlayerSaver.list.last().pause()
            } else {
                PlayerSaver.list.last().pause()
            }
            AppPreferences.playMusicButtonEnabled = true
            AppPreferences.stopMusicButtonEnabled = false
            playMusic.isEnabled = AppPreferences.playMusicButtonEnabled
            stopMusic.isEnabled = AppPreferences.stopMusicButtonEnabled
        }


        textField = binding.textField
        autoCompleteTextView = textField.editText as AutoCompleteTextView
        autoCompleteTextView.setText(AppPreferences.mode) // default Easy
        autoCompleteTextView.setOnItemClickListener { _, _, position, _ ->
            ichangeDifficultyPresenter.switchDifficulty(position)
        }

        textFieldLang = binding.languagesTF
        autoCompleteTextViewLang = textFieldLang.editText as AutoCompleteTextView
        autoCompleteTextViewLang.setText(AppPreferences.language) //default English
        autoCompleteTextViewLang.setOnItemClickListener { _, _, position, _ ->
            if (position == 0) {
                AppPreferences.languageCode = "en"
                AppPreferences.language = "English"
                val l = AppPreferences.languageCode

                context?.let {
                    if (l != null) {
                        LocaleUtils.setLocaleLanguage(l, it)
                    }
                }

                // Refresh screen
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    fragmentManager?.beginTransaction()?.detach(this)?.commitAllowingStateLoss()
                    fragmentManager?.beginTransaction()?.attach(this)?.commitAllowingStateLoss()
                } else {
                    fragmentManager?.beginTransaction()?.detach(this)?.attach(this)?.commit()
                }

            } else if (position == 1) {
                AppPreferences.language = "Русский"
                AppPreferences.languageCode = "ru"
                val l = AppPreferences.languageCode
                context?.let {
                    if (l != null) {
                        LocaleUtils.setLocaleLanguage(l, it)
                    }
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    fragmentManager?.beginTransaction()?.detach(this)?.commitNow()
                    fragmentManager?.beginTransaction()?.attach(this)?.commitNow()
                } else {
                    fragmentManager?.beginTransaction()?.detach(this)?.attach(this)?.commit()
                }
            }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        //PlayerSaver.list.clear()


        Log.d("Test", "CalledOnDestroyView")

    }

    override fun showTimeToast(message: String) {
        val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
        toast.show()
    }


}
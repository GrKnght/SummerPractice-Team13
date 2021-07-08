package summerpractice.team13.guessthedrawing.ui.settings_screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.Fragment

import com.google.android.material.textfield.TextInputLayout

import summerpractice.team13.guessthedrawing.R
import summerpractice.team13.guessthedrawing.databinding.FragmentSettingsBinding
import summerpractice.team13.guessthedrawing.mvp.presenters.AppPreferences
import summerpractice.team13.guessthedrawing.mvp.presenters.change_difficulty_presenter.ChangeDifficultyPresenter
import summerpractice.team13.guessthedrawing.mvp.presenters.change_difficulty_presenter.IChangeDifficultyPresenter
import summerpractice.team13.guessthedrawing.mvp.views.change_time_view.IChangeDifficultyView

class SettingsFragment : Fragment(), IChangeDifficultyView {

    private lateinit var ichangeDifficultyPresenter: IChangeDifficultyPresenter
    private lateinit var autoCompleteTextView: AutoCompleteTextView
    private lateinit var arrayAdapter: ArrayAdapter<String>
    private lateinit var textField: TextInputLayout

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onResume() {
        super.onResume()
        val difficulties = resources.getStringArray(R.array.difficultyLevels)
        arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, difficulties)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)
        Log.d("Test", "CalledResume")

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        context?.let { AppPreferences.init(it) }
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        ichangeDifficultyPresenter = ChangeDifficultyPresenter(this)

        val view = binding.root

        textField = binding.textField

        //val sliderMusic = binding.sliderMusic

        autoCompleteTextView = textField.editText as AutoCompleteTextView

        autoCompleteTextView.setText(AppPreferences.mode) // default Easy

        autoCompleteTextView.setOnItemClickListener { _, _, position, _ ->
            ichangeDifficultyPresenter.switchDifficulty(position)


        }


        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.d("Test", "CalledOnDestroyView")

    }

    override fun showTimeToast(message: String) {
        val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
        toast.show()
    }


}
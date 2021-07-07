package summerpractice.team13.guessthedrawing.ui.settings_screen

import android.os.Bundle
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
import summerpractice.team13.guessthedrawing.mvp.presenters.change_time_presenter.IChangeTimePresenter
import summerpractice.team13.guessthedrawing.mvp.views.change_time_view.IChangeTimeView

class SettingsFragment : Fragment(), IChangeTimeView {

    private lateinit var ichangeTimePresenter: IChangeTimePresenter


    private lateinit var autoCompleteTextView: AutoCompleteTextView
    private lateinit var arrayAdapter: ArrayAdapter<String>


    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!


    override fun onResume() {
        super.onResume()

        val difficulties = resources.getStringArray(R.array.difficultyLevels)
        arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, difficulties)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        context?.let { AppPreferences.init(it) }

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)

        val testButton = binding.testSharedButton
        val testView = binding.testShared

        val view = binding.root


        val textField: TextInputLayout = binding.textField

        autoCompleteTextView = textField.editText as AutoCompleteTextView



        autoCompleteTextView.setOnItemClickListener { _, _, position, _ ->
            when (position) {
                0 -> {
                    AppPreferences.time = 15
                    testView.text = AppPreferences.time.toString()

                }
                1 -> {
                    AppPreferences.time = 20
                    testView.text = AppPreferences.time.toString()

                }
                2 -> {
                    AppPreferences.time = 25
                    testView.text = AppPreferences.time.toString()
                }
            }


        }




        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun showToastDownBorder(message: String) {
        val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
        toast.show()
    }

    override fun showToastUpBorder(message: String) {
        val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
        toast.show()

    }

}
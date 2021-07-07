package summerpractice.team13.guessthedrawing.ui.settings_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputLayout
import summerpractice.team13.guessthedrawing.R
import summerpractice.team13.guessthedrawing.mvp.presenters.AppPreferences
import summerpractice.team13.guessthedrawing.mvp.presenters.change_time_presenter.ChangeTimePresenter
import summerpractice.team13.guessthedrawing.mvp.presenters.change_time_presenter.IChangeTimePresenter
import summerpractice.team13.guessthedrawing.mvp.views.IChangeTimeView

class SettingsFragment : Fragment(), IChangeTimeView {

    private lateinit var ichangeTimePresenter: IChangeTimePresenter

    private val model: SettingsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        context?.let { AppPreferences.init(it) }

        val root = inflater.inflate(R.layout.fragment_settings, container, false)


        // Расписан ArrayAdapter для dropdown menu
        val difficulties = resources.getStringArray(R.array.difficultyLevels)
        val textField: TextInputLayout = root.findViewById(R.id.textField)
        val adapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, difficulties)
        (textField.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        ichangeTimePresenter = ChangeTimePresenter(this)

        val increaseFB: FloatingActionButton = root.findViewById(R.id.incrementFB)
        val decreaseFB: FloatingActionButton = root.findViewById(R.id.decrementFB)
        val secondsTW: TextView = root.findViewById(R.id.secondsTW)

        // created for test
        val testButton: Button = root.findViewById(R.id.testSharedButton)
        val testView: TextView = root.findViewById(R.id.testShared)

        decreaseFB.setOnClickListener {
            ichangeTimePresenter.decrementTime()
            model.text.value = ichangeTimePresenter.i.toString() + " sec"
            // записываю в память зеачение времени
            AppPreferences.time = ichangeTimePresenter.i


        }
        increaseFB.setOnClickListener {
            ichangeTimePresenter.incrementTime()
            model.text.value = ichangeTimePresenter.i.toString() + " sec"
            // записываю в память зеачение времени
            AppPreferences.time = ichangeTimePresenter.i



        }

        testButton.setOnClickListener {
            // беру из памяти значение и передаю в textView (чтобы взять нужно AppPreferences.time)
            testView.text = AppPreferences.time.toString()

        }
        val nameObserver = Observer<String> { newName ->
            // Update the UI, in this case, a TextView.
            secondsTW.text = newName
        }
        model.text.observe(viewLifecycleOwner, nameObserver)


        return root
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
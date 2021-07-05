package summerpractice.team13.guessthedrawing.ui.settings_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.floatingactionbutton.FloatingActionButton
import summerpractice.team13.guessthedrawing.R
import summerpractice.team13.guessthedrawing.mvp.presenters.AppPreferences
import summerpractice.team13.guessthedrawing.mvp.presenters.ChangeTimePresenter
import summerpractice.team13.guessthedrawing.mvp.views.ChangeTimeView

class SettingsFragment : Fragment(), ChangeTimeView {


    private lateinit var changeTimePresenter: ChangeTimePresenter

    private val model: SettingsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        context?.let { AppPreferences.init(it) }

        val root = inflater.inflate(R.layout.fragment_settings, container, false)

        changeTimePresenter = ChangeTimePresenter(this)

        val increaseFB: FloatingActionButton = root.findViewById(R.id.incrementFB)
        val secondsTW: TextView = root.findViewById(R.id.secondsTW)

        val testButton: Button = root.findViewById(R.id.testSharedButton)
        val testView: TextView = root.findViewById(R.id.testShared)


        val decreaseFB: FloatingActionButton = root.findViewById(R.id.decrementFB)
        decreaseFB.setOnClickListener {
            changeTimePresenter.decrementTime()
            model.text.setValue(changeTimePresenter.counter.toString() + " sec")

        }
        increaseFB.setOnClickListener {
            changeTimePresenter.incrementTime()
            model.text.value = changeTimePresenter.counter.toString() + " sec"
            AppPreferences.time = changeTimePresenter.counter

        }

        testButton.setOnClickListener {
            testView.text =  changeTimePresenter.counter.toString()

        }
        val nameObserver = Observer<String> { newName ->
            // Update the UI, in this case, a TextView.
            secondsTW.text = newName
        }
        model.text.observe(viewLifecycleOwner, nameObserver)


        return root
    }

    override fun showToastDownBorder() {
        val toast = Toast.makeText(context, "MIN", Toast.LENGTH_SHORT)
        toast.show()
    }

    override fun showToastUpBorder() {
        val toast = Toast.makeText(context, "MAX", Toast.LENGTH_SHORT)
        toast.show()

    }

}
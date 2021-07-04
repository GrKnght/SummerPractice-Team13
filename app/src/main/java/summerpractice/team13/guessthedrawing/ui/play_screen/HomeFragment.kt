package summerpractice.team13.guessthedrawing.ui.play_screen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import summerpractice.team13.guessthedrawing.R
import summerpractice.team13.guessthedrawing.mvp.presenters.AnswerCheckPresenter
import summerpractice.team13.guessthedrawing.mvp.views.AnswerCheckView

class HomeFragment : Fragment(), AnswerCheckView {

    //private lateinit var homeViewModel: HomeViewModel

    private lateinit var answerCheckPresenter: AnswerCheckPresenter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_play, container, false)

        val button: Button = root.findViewById(R.id.TestButton)

        val editText: EditText = root.findViewById(R.id.editTestText)

        answerCheckPresenter = AnswerCheckPresenter(this)

        button.setOnClickListener {
            context?.applicationContext?.let { it1 ->
                answerCheckPresenter.chekAnswer(
                    editText.text.toString(),
                    it1
                )
            }

        }


        return root
    }


    override fun showTrueIcon(context: Context) {
        val inflater = layoutInflater
        val views = inflater.inflate(R.layout.green_toast_item, null)
        val toast = Toast(context)
        toast.duration = Toast.LENGTH_SHORT
        toast.view = views

        toast.show()

    }

    override fun showFalseIcon(context: Context) {
        val inflater = layoutInflater
        val views = inflater.inflate(R.layout.red_toast_item, null)
        val toast = Toast(context)
        toast.duration = Toast.LENGTH_SHORT
        toast.view = views

        toast.show()

    }
}
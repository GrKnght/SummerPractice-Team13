package summerpractice.team13.guessthedrawing.ui.play_screen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import summerpractice.team13.guessthedrawing.R
import summerpractice.team13.guessthedrawing.mvp.presenters.answerCheckPresenter.AnswerCheckPresenter
import summerpractice.team13.guessthedrawing.mvp.presenters.answerCheckPresenter.IAnswerCheckPresenter
import summerpractice.team13.guessthedrawing.mvp.views.answerCheckView.IAnswerCheckView

class HomeFragment : Fragment(), IAnswerCheckView {

    private lateinit var ianswerCheckPresenter: IAnswerCheckPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_play, container, false)
        val button: Button = root.findViewById(R.id.btn_guess)
        val editText: EditText = root.findViewById(R.id.et_answer)
        val imageView: ImageView = root.findViewById(R.id.iv_picture)

        val testFixButton: Button = root.findViewById(R.id.TestFixButton)

        ianswerCheckPresenter = AnswerCheckPresenter(this)
        button.setOnClickListener {
            context?.applicationContext?.let { it1 ->
                ianswerCheckPresenter.checkAnswer(
                    editText.text.toString().lowercase(),
                    it1,
                    resources.getResourceEntryName(ianswerCheckPresenter.getDrawableId(imageView)),
                    imageView
                )
            }
            // очищает поле после ответа
            editText.text.clear()
        }

        //TODO: кнопка Start при старте должна выполнять этот код
        testFixButton.setOnClickListener {
            ianswerCheckPresenter.getRandomPicture(imageView)

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
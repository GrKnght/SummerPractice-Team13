package summerpractice.team13.guessthedrawing.ui.play_screen

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.android.material.progressindicator.LinearProgressIndicator
import summerpractice.team13.guessthedrawing.R
import summerpractice.team13.guessthedrawing.mvp.presenters.AppPreferences
import summerpractice.team13.guessthedrawing.mvp.presenters.answer_check_presenter.AnswerCheckPresenter
import summerpractice.team13.guessthedrawing.mvp.presenters.answer_check_presenter.IAnswerCheckPresenter
import summerpractice.team13.guessthedrawing.mvp.views.answer_check_view.IAnswerCheckView

class HomeFragment : Fragment(), IAnswerCheckView {

    private lateinit var ianswerCheckPresenter: IAnswerCheckPresenter
    private lateinit var root: View


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        context?.let { AppPreferences.init(it) }

        root = inflater.inflate(R.layout.fragment_play, container, false)
        val button: Button = root.findViewById(R.id.btn_guess)
        val editText: EditText = root.findViewById(R.id.et_answer)
        val imageView: ImageView = root.findViewById(R.id.iv_picture)

        val testFixButton: Button = root.findViewById(R.id.TestFixButton)

        val progressIndicator: LinearProgressIndicator = root.findViewById(R.id.progress_indicator)
        val view_timer: Chronometer = root.findViewById(R.id.view_timer)
        val testTextView:TextView= root.findViewById(R.id.testTextView)

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


            // TODO: globalTimer - ЭТО ТАЙМЕР, КОТОРЫЙ ДОЛЖЕН БЫТЬ ГЛОБАЛЬНО ДОСТУПНЫМ (+СОХРАНЯЕМЫМ)
            var globalTimer: Int = 30;
            // КОД ТАЙМЕРА
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                view_timer.isCountDown = true
            }
            view_timer.base = SystemClock.elapsedRealtime() + globalTimer * 1000
            view_timer.start()
        }

        // КОД ТАЙМЕРА
        view_timer.setOnChronometerTickListener {
            val elapsedMillis: Long = view_timer.base - SystemClock.elapsedRealtime()

            testTextView.text = (elapsedMillis / 1000).toString()
            progressIndicator.progress = (elapsedMillis / 1000).toInt()
        }

        //TODO: кнопка Start при старте должна выполнять 1-ую строку, кнопка Try Again - 2-ую
        testFixButton.setOnClickListener {
            ianswerCheckPresenter.getRandomPicture(imageView)
            // ОСТАНАВЛИВАЕТ ТАЙМЕР
            view_timer.stop()
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
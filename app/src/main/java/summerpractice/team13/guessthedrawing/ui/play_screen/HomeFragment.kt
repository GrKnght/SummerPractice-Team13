package summerpractice.team13.guessthedrawing.ui.play_screen

import android.animation.ValueAnimator
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.google.android.material.textfield.TextInputLayout
import summerpractice.team13.guessthedrawing.R
import summerpractice.team13.guessthedrawing.mvp.presenters.AppPreferences
import summerpractice.team13.guessthedrawing.mvp.presenters.answer_check_presenter.AnswerCheckPresenter
import summerpractice.team13.guessthedrawing.mvp.presenters.answer_check_presenter.IAnswerCheckPresenter
import summerpractice.team13.guessthedrawing.mvp.views.answer_check_view.IAnswerCheckView
import summerpractice.team13.guessthedrawing.utils.LocaleUtils


class HomeFragment : Fragment(), IAnswerCheckView {

    private lateinit var ianswerCheckPresenter: IAnswerCheckPresenter
    private lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        context?.let { AppPreferences.init(it) }

        context?.let { LocaleUtils.setLocaleLanguage(AppPreferences.languageCode.orEmpty(), it) }
        root = inflater.inflate(R.layout.fragment_play, container, false)

        // Values
        val coins: TextView = root.findViewById(R.id.tv_coin)
        val pictureImageView: ImageView = root.findViewById(R.id.iv_picture)
        val guessButton: Button = root.findViewById(R.id.btn_guess)

        val answerEditText: EditText = root.findViewById(R.id.et_answer)
        val answerTextInput: TextInputLayout = root.findViewById(R.id.ti_answer)

        val lostCoinTextView: TextView = root.findViewById(R.id.tv_lost_coin)
        val universalButton: Button = root.findViewById(R.id.btn_universal)

        val coinImageView: ImageView = root.findViewById(R.id.iv_coin)
        val coinsAnimated: TextView = root.findViewById(R.id.tv_coin_animated)

        val progressIndicator: LinearProgressIndicator = root.findViewById(R.id.progress_indicator)
        val chronometer: Chronometer = root.findViewById(R.id.chronometer)
        val timeRemainingTextView: TextView = root.findViewById(R.id.tv_time_remaining)
        val mainSeparator: View = root.findViewById(R.id.separator_main)

        // Default
        elementsNotVisible(
            guessButton,
            answerEditText,
            answerTextInput,
            pictureImageView,
            progressIndicator,
            timeRemainingTextView,
            universalButton,
            mainSeparator,
            coinImageView,
            coins
        )
        lostCoinTextView.isVisible=false

        // Инициализация макс. кол-ва рисунков
        AppPreferences.maxPicturesCount = 39
        AppPreferences.openedPicturesCount = 10

        // По нажатию кнопки на клавиатуре автоматически нажимается кнопка "Guess"
        answerEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                guessButton.performClick()

            }
            false
        }

        ianswerCheckPresenter = AnswerCheckPresenter(this)
        guessButton.setOnClickListener {
            context?.applicationContext?.let { it1 ->
                ianswerCheckPresenter.checkAnswer(
                    it1,
                    answerEditText.text.toString().lowercase(),
                    resources.getResourceEntryName(
                        ianswerCheckPresenter.getDrawableId(
                            pictureImageView
                        )
                    ),
                    pictureImageView,
                    answerEditText,
                    chronometer,
                    progressIndicator,
                    guessButton,
                    coins,
                    coinsAnimated
                )
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                chronometer.isCountDown = true
            }
        }

        chronometer.setOnChronometerTickListener {
            val elapsedMillis: Long = chronometer.base - SystemClock.elapsedRealtime()

            timeRemainingTextView.text = getString(R.string.time_remaining, (elapsedMillis / 1000).toString())
            progressIndicator.progress = (elapsedMillis / 1000).toInt()

            if (progressIndicator.progress <= 0) {
                // останавливает хронометер
                chronometer.stop()

                // задержка в 1 секунду
                val handler = Handler()
                handler.postDelayed({

                    elementsNotVisible(
                        guessButton,
                        answerEditText,
                        answerTextInput,
                        pictureImageView,
                        progressIndicator,
                        timeRemainingTextView,
                        universalButton,
                        mainSeparator,
                        coinImageView,
                        coins
                    )
                    lostCoinTextView.isVisible = true
                    // Появляется и исчезает текст о потере монетки
                    val valueAnimator = ValueAnimator.ofFloat(0f, 1f)
                    valueAnimator.duration = 500
                    valueAnimator.addUpdateListener { animation ->
                        val alpha = animation.animatedValue as Float
                        lostCoinTextView.alpha = alpha
                    }
                    valueAnimator.start()

                    // если время вышло, то вычитаем 1 монету
                    if (AppPreferences.coins!! > 0)
                        AppPreferences.coins = AppPreferences.coins?.minus(1)

                    // обновляем textview монет
                    coins.text = AppPreferences.coins.toString()

                    // меняет текст кнопки "Start"
                    universalButton.text = getString(R.string.try_again)

                }, 1000)
            }
        }

        universalButton.setOnClickListener {
            elementsVisible(
                guessButton,
                answerEditText,
                answerTextInput,
                pictureImageView,
                progressIndicator,
                timeRemainingTextView,
                universalButton,
                mainSeparator,
                coinImageView,
                coins
            )
            lostCoinTextView.isVisible = false

            // обновляем textview монет
            coins.text = AppPreferences.coins.toString()

            // Показывает рандомный рисунок
            ianswerCheckPresenter.getRandomPicture(pictureImageView)

            // Запускает хронометер
            chronometer.base = SystemClock.elapsedRealtime() + AppPreferences.time * 1000
            chronometer.start()
            progressIndicator.max = AppPreferences.time

            // Очищает поле
            answerEditText.text.clear()
        }

        return root
    }

    private fun elementsVisible(
        guessButton: Button,
        answerEditText: EditText,
        answerTextInput: TextInputLayout,
        pictureImageView: ImageView,
        progressIndicator: LinearProgressIndicator,
        timeRemainingTextView: TextView,
        universalButton: Button,
        mainSeparator: View,
        coinImageView: ImageView,
        coins: TextView
    ) {

        guessButton.isVisible = true
        answerEditText.isVisible = true
        answerTextInput.isVisible = true
        pictureImageView.isVisible = true
        progressIndicator.isVisible = true
        timeRemainingTextView.isVisible = true
        mainSeparator.isVisible = true
        coinImageView.isVisible = true
        coins.isVisible = true

        universalButton.isVisible = false
    }

    private fun elementsNotVisible(
        guessButton: Button,
        answerEditText: EditText,
        answerTextInput: TextInputLayout,
        pictureImageView: ImageView,
        progressIndicator: LinearProgressIndicator,
        timeRemainingTextView: TextView,
        universalButton: Button,
        mainSeparator: View,
        coinImageView: ImageView,
        coins: TextView
    ) {

        guessButton.isVisible = false
        answerEditText.isVisible = false
        answerTextInput.isVisible = false
        pictureImageView.isVisible = false
        progressIndicator.isVisible = false
        timeRemainingTextView.isVisible = false
        mainSeparator.isVisible = false
        coinImageView.isVisible = false
        coins.isVisible = false

        universalButton.isVisible = true
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
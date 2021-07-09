package summerpractice.team13.guessthedrawing.mvp.presenters.answer_check_presenter

import android.animation.ValueAnimator
import android.content.Context
import android.os.Handler
import android.os.SystemClock
import android.widget.*
import com.google.android.material.progressindicator.LinearProgressIndicator
import summerpractice.team13.guessthedrawing.R
import summerpractice.team13.guessthedrawing.mvp.presenters.AppPreferences
import summerpractice.team13.guessthedrawing.mvp.views.answer_check_view.IAnswerCheckView

class AnswerCheckPresenter(private var IAnswerCheckView: IAnswerCheckView) : IAnswerCheckPresenter {

    private var cards = arrayOf(
        R.drawable.brain, R.drawable.beach, R.drawable.bread,
        R.drawable.burger, R.drawable.calculator, R.drawable.castle,
        R.drawable.clarinet, R.drawable.clock, R.drawable.compass,
        R.drawable.cup, R.drawable.donut, R.drawable.dragon, R.drawable.ear,
        R.drawable.elephant, R.drawable.fish, R.drawable.flower, R.drawable.frog,
        R.drawable.headphones, R.drawable.helmet, R.drawable.key, R.drawable.mailbox,
        R.drawable.mouse, R.drawable.mouth, R.drawable.parachute, R.drawable.parrot,
        R.drawable.passport, R.drawable.pear, R.drawable.pencil, R.drawable.phone,
        R.drawable.pillow, R.drawable.pizza, R.drawable.rainbow, R.drawable.saw,
        R.drawable.shark, R.drawable.shovel, R.drawable.snowflake, R.drawable.sun,
        R.drawable.tv, R.drawable.violin, R.drawable.watermelon
    )

    override fun checkAnswer(
        context: Context,
        answer: String,
        imageName: String,
        imageView: ImageView,
        editText: EditText,
        chronometer: Chronometer,
        progressIndicator: LinearProgressIndicator,
        button: Button,
        coins: TextView,
        coinsAnimated: TextView
    ) {
        if (answer == imageName) {
            IAnswerCheckView.showTrueIcon(context)
            AppPreferences.coins = AppPreferences.coins?.plus(1)
            coins.text = AppPreferences.coins.toString()

            // Появляется и исчезает текст о +1 монете
            val valueAnimator = ValueAnimator.ofFloat(0f, 1f, 0f)
            valueAnimator.duration = 3000
            valueAnimator.addUpdateListener { animation ->
                val alpha = animation.animatedValue as Float
                coinsAnimated.alpha = alpha
            }
            valueAnimator.start()

            // останавливаем таймер, выключаем кнопки перед задержкой
            chronometer.stop()
            editText.isEnabled = false
            button.isClickable = false
            val handler = Handler()
            handler.postDelayed({
                // обратно всё включаем после задержки
                editText.isEnabled = true
                button.isClickable = true

                getRandomPicture(imageView)

                // Запускает хронометер заново в случае правильного ответа
                chronometer.base = SystemClock.elapsedRealtime() + AppPreferences.time * 1000
                chronometer.start()
                progressIndicator.max = AppPreferences.time

                // Очищает поле после правильного ответа
                editText.text.clear()
            }, 1600)

        } else {
            IAnswerCheckView.showFalseIcon(context)
        }
    }

    override fun getDrawableId(imageView: ImageView): Int {
        return imageView.tag as Int
    }

    override fun getRandomPicture(imageView: ImageView) {
        // рандом от 0 до N-го рисунка (N - выставляется через покупку в магазине)
        val rand = (0..AppPreferences.openedPicturesCount!!).random()
        imageView.setImageResource(cards[rand])
        imageView.tag = cards[rand]
    }
}
package summerpractice.team13.guessthedrawing.mvp.presenters.answer_check_presenter

import android.content.Context
import android.os.SystemClock
import android.widget.Chronometer
import android.widget.EditText
import android.widget.ImageView
import com.google.android.material.progressindicator.LinearProgressIndicator
import summerpractice.team13.guessthedrawing.R
import summerpractice.team13.guessthedrawing.mvp.presenters.AppPreferences
import summerpractice.team13.guessthedrawing.mvp.views.answer_check_view.IAnswerCheckView

class AnswerCheckPresenter(private var IAnswerCheckView: IAnswerCheckView) : IAnswerCheckPresenter {

    private var maxDrawings = 39

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
        progressIndicator:LinearProgressIndicator
    ) {
        if (answer == imageName) {
            IAnswerCheckView.showTrueIcon(context)
            getRandomPicture(imageView)

            // Запускает хронометер заново в случае правильного ответа
            chronometer.base = SystemClock.elapsedRealtime() + AppPreferences.time * 1000
            chronometer.start()
            progressIndicator.max = AppPreferences.time

            // Очищает поле после правильного ответа
            editText.text.clear()

        } else {
            IAnswerCheckView.showFalseIcon(context)
        }
    }

    override fun getDrawableId(imageView: ImageView): Int {
        return imageView.tag as Int
    }

    override fun getRandomPicture(imageView: ImageView) {
        val rand = (0..maxDrawings).random()
        imageView.setImageResource(cards[rand])
        imageView.tag = cards[rand]
    }
}
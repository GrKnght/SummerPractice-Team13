package summerpractice.team13.guessthedrawing.mvp.presenters.answerCheckPresenter

import android.content.Context
import android.widget.ImageView
import summerpractice.team13.guessthedrawing.R
import summerpractice.team13.guessthedrawing.mvp.views.answerCheckView.IAnswerCheckView

class AnswerCheckPresenter(private var IAnswerCheckView: IAnswerCheckView) : IAnswerCheckPresenter {

    // TODO: maxDrawings - это макс. возможное кол-во рисунков. Сделать его глобально доступным для settings.
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
        answer: String,
        context: Context,
        imageName: String,
        imageView: ImageView
    ) {
        if (answer == imageName) {
            IAnswerCheckView.showTrueIcon(context)
            val n = (0..maxDrawings).random()

            imageView.setImageResource(cards[n])
            imageView.tag = cards[n]

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
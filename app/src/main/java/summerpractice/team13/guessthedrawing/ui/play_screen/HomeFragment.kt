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
/* PREVIOUS CODE */
//import summerpractice.team13.guessthedrawing.mvp.presenters.AnswerCheckPresenter
import summerpractice.team13.guessthedrawing.mvp.views.AnswerCheckView

class HomeFragment : Fragment(), AnswerCheckView {

    //private lateinit var homeViewModel: HomeViewModel



    /* PREVIOUS CODE */
    //private lateinit var answerCheckPresenter: AnswerCheckPresenter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_play, container, false)
        val button: Button = root.findViewById(R.id.TestButton)
        val editText: EditText = root.findViewById(R.id.editTestText)
        val imageView : ImageView = root.findViewById(R.id.TestImageView)

        /* PREVIOUS CODE */
        //answerCheckPresenter = AnswerCheckPresenter(this)
//        button.setOnClickListener {
//            context?.applicationContext?.let { it1 ->
//                answerCheckPresenter.chekAnswer(
//                    editText.text.toString(),
//                    it1
//                )
//            }
//
//        }


        button.setOnClickListener {
            context?.applicationContext?.let { it1 ->
                cheсkAnswer(editText.text.toString(), it1, "Hello", imageView)
            }

        }


        return root
    }

    var cards= arrayOf(R.drawable.brain, R.drawable.beach, R.drawable.bread,
        R.drawable.burger, R.drawable.calculator, R.drawable.castle,
        R.drawable.clarinet, R.drawable.clock, R.drawable.compass,
        R.drawable.cup, R.drawable.donut, R.drawable.dragon, R.drawable.ear,
        R.drawable.elephant, R.drawable.fish, R.drawable.flower, R.drawable.frog,
        R.drawable.headphones, R.drawable.helmet, R.drawable.key, R.drawable.mailbox,
        R.drawable.mouse, R.drawable.mouth, R.drawable.parachute, R.drawable.parrot,
        R.drawable.passport, R.drawable.pear, R.drawable.pencil, R.drawable.phone,
        R.drawable.pillow, R.drawable.pizza, R.drawable.rainbow, R.drawable.saw,
        R.drawable.shark, R.drawable.shovel, R.drawable.snowflake, R.drawable.sun,
        R.drawable.tv, R.drawable.violin, R.drawable.watermelon)

    private fun getDrawableId(iv: ImageView): Int {
        return iv.tag as Int
    }

    // TODO: вернуть checkAnswer в отдельный класс.
    // TODO: maxDrawings - это макс. возможное кол-во рисунков. Сделать его глобально доступным для settings.
    var maxDrawings = 39
    fun cheсkAnswer(answer: String, context: Context, imageName : String, imageView: ImageView) {
        if (answer == imageName) {
            showTrueIcon(context)
            var n = (0..maxDrawings).random()
            imageView.setImageResource(cards[n])
            imageView.tag = cards[n]

        } else {
            showFalseIcon(context)
        }

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
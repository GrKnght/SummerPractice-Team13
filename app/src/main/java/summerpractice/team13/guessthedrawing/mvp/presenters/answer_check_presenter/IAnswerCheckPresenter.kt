package summerpractice.team13.guessthedrawing.mvp.presenters.answer_check_presenter

import android.content.Context
import android.widget.*
import com.google.android.material.progressindicator.LinearProgressIndicator

interface IAnswerCheckPresenter {

    fun checkAnswer(
        context: Context,
        answer: String,
        imageName: String,
        imageView: ImageView,
        editText: EditText,
        chronometer: Chronometer,
        progressIndicator: LinearProgressIndicator,
        button: Button,
        coins:TextView
    )

    fun getDrawableId(imageView: ImageView): Int
    fun getRandomPicture(imageView: ImageView)


}
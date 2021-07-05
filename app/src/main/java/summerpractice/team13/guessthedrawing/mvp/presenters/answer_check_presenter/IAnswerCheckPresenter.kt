package summerpractice.team13.guessthedrawing.mvp.presenters.answer_check_presenter

import android.content.Context
import android.widget.ImageView

interface IAnswerCheckPresenter {

    fun checkAnswer(answer: String, context: Context, imageName: String, imageView: ImageView)
    fun getDrawableId(imageView: ImageView) :Int
    fun getRandomPicture(imageView: ImageView)



}
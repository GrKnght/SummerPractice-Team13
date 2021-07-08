package summerpractice.team13.guessthedrawing.mvp.presenters.change_difficulty_presenter

import summerpractice.team13.guessthedrawing.mvp.presenters.AppPreferences
import summerpractice.team13.guessthedrawing.mvp.views.change_time_view.IChangeDifficultyView

class ChangeDifficultyPresenter(private var iChangeDifficultyView: IChangeDifficultyView) :
    IChangeDifficultyPresenter {
    override fun switchDifficulty(position: Int) {
        when (position) {
            0 -> {
                AppPreferences.time = 25
                AppPreferences.mode = "Easy"
                iChangeDifficultyView.showTimeToast(AppPreferences.time.toString())

            }
            1 -> {
                AppPreferences.time = 20
                AppPreferences.mode = "Normal"

                iChangeDifficultyView.showTimeToast(AppPreferences.time.toString())


            }
            2 -> {
                AppPreferences.time = 15
                AppPreferences.mode = "Hard"
                iChangeDifficultyView.showTimeToast(AppPreferences.time.toString())

            }
        }

    }


}
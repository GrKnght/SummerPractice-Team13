package summerpractice.team13.guessthedrawing.mvp.presenters

import summerpractice.team13.guessthedrawing.mvp.views.ChangeTimeView

class ChangeTimePresenter(private var changeTimeView: ChangeTimeView) {

    var counter = 15

    fun incrementTime() {
        counter += 1
        if (counter > 25) {
            changeTimeView.showToastUpBorder()
            counter -= 1
        }

    }

    fun decrementTime() {
        counter -= 1
        if (counter < 5) {
            changeTimeView.showToastDownBorder()
            counter += 1
        }
    }
}
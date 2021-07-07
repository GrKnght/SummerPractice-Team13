package summerpractice.team13.guessthedrawing.mvp.presenters.change_time_presenter

import summerpractice.team13.guessthedrawing.mvp.views.change_time_view.IChangeTimeView

var counter = 20

class ChangeTimePresenter(private var ichangeTimeView: IChangeTimeView, override var i: Int = counter) : IChangeTimePresenter {

    override fun incrementTime() {
        i += 1
        if (i > 25) {
            ichangeTimeView.showToastUpBorder("MAX")
            i -= 1
        }

    }

    override fun decrementTime() {
        i -= 1
        if (i < 5) {
            ichangeTimeView.showToastDownBorder("MIN")
            i += 1
        }
    }
}
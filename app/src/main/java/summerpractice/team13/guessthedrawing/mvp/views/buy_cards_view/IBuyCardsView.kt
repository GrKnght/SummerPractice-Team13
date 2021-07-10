package summerpractice.team13.guessthedrawing.mvp.views.buy_cards_view

import android.widget.TextView

interface IBuyCardsView {

    fun onFailed(message: String)
    fun onSuccess(message: String)

    fun updateAvailablePictures(textView: TextView)
}
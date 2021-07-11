package summerpractice.team13.guessthedrawing.mvp.presenters.buy_cards_presenter

import android.widget.TextView

interface IBuyCardsPresenter {


    fun textUpdateWithAnimation(coinsAnimated: TextView, decrementValue: String)

    fun onBuyCards(
        coinsAnimated: TextView,
        picturesAvailableTextView: TextView,
        coinsTextView: TextView,
        coinsValue: Int,
        picturesValue: Int,
        enabledButton: Boolean
    ): Boolean


}
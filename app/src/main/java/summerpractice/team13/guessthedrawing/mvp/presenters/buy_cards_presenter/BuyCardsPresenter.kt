package summerpractice.team13.guessthedrawing.mvp.presenters.buy_cards_presenter

import android.animation.ValueAnimator
import android.widget.TextView
import summerpractice.team13.guessthedrawing.mvp.presenters.AppPreferences
import summerpractice.team13.guessthedrawing.mvp.views.buy_cards_view.IBuyCardsView

class BuyCardsPresenter(private var iBuyCardsView: IBuyCardsView) : IBuyCardsPresenter {


    override fun onBuyCards(
        coinsAnimated: TextView,
        picturesAvailableTextView: TextView,
        coinsTextView: TextView, coinsValue: Int, picturesValue: Int, enabledButton: Boolean
    ) :Boolean{

        if (conditionIsTrue(coinsValue, picturesValue)) {

            if (!enabledButton) {
                textUpdateWithAnimation(coinsAnimated, "$coinsValue")

                AppPreferences.coins = AppPreferences.coins?.minus(coinsValue)
                // присваиваем Z монет
                AppPreferences.openedPicturesCount = picturesValue - 1

                iBuyCardsView.updateAvailablePictures(picturesAvailableTextView)
                // обновляем текст монет
                coinsTextView.text = AppPreferences.coins.toString()
                iBuyCardsView.onSuccess("Bought!")
                return true

            } else {
                iBuyCardsView.onFailed("Buy the previous one!")
            }

        } else {
            iBuyCardsView.onFailed("Not enough coins!")
        }
        return false

    }

    private fun conditionIsTrue(
        coinsValue: Int,
        picturesValue: Int
    ): Boolean {
        // AppPreferences.coins!! >= 30 && AppPreferences.openedPicturesCount!! < 30 && !AppPreferences.twentyButtonEnabled!!

        return AppPreferences.coins!! >= coinsValue && AppPreferences.openedPicturesCount!! < picturesValue

    }

    override fun textUpdateWithAnimation(coinsAnimated: TextView, decrementValue: String) {
        coinsAnimated.text = "-$decrementValue"
        val valueAnimator = ValueAnimator.ofFloat(0f, 1f, 0f)
        valueAnimator.duration = 3000
        valueAnimator.addUpdateListener { animation ->
            val alpha = animation.animatedValue as Float
            coinsAnimated.alpha = alpha
        }
        valueAnimator.start()

    }


}
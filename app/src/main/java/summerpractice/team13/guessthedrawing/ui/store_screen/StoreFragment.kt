package summerpractice.team13.guessthedrawing.ui.store_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import summerpractice.team13.guessthedrawing.R
import summerpractice.team13.guessthedrawing.mvp.presenters.AppPreferences
import summerpractice.team13.guessthedrawing.mvp.presenters.buy_cards_presenter.BuyCardsPresenter
import summerpractice.team13.guessthedrawing.mvp.views.buy_cards_view.IBuyCardsView

class StoreFragment : Fragment(), IBuyCardsView {
    private val presenter = BuyCardsPresenter(this)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_store, container, false)

        // Values
        val twentyButton: Button = root.findViewById(R.id.btn_twenty)
        val thirtyButton: Button = root.findViewById(R.id.btn_thirty)
        val fortyButton: Button = root.findViewById(R.id.btn_forty)
        val picturesAvailableTextView: TextView = root.findViewById(R.id.testTextView)
        val coinsTextView: TextView = root.findViewById(R.id.tv_coin)
        val coinsAnimated: TextView = root.findViewById(R.id.tv_coin_animated)

        val testButton: Button = root.findViewById(R.id.testbutton)

        coinsTextView.text = AppPreferences.coins.toString()
        updateAvailablePictures(picturesAvailableTextView)

        if (AppPreferences.twentyButtonEnabled == false)
            twentyButton.isEnabled = false

        if (AppPreferences.thirtyButtonEnabled == false)
            thirtyButton.isEnabled = false

        if (AppPreferences.fortyButtonEnabled == false)
            fortyButton.isEnabled = false

        if (AppPreferences.openedPicturesCount == AppPreferences.maxPicturesCount) {
            twentyButton.isEnabled = false
            thirtyButton.isEnabled = false
            fortyButton.isEnabled = false
        }

        testButton.setOnClickListener {
            AppPreferences.coins = AppPreferences.coins?.plus(10)
            coinsTextView.text = AppPreferences.coins.toString()
        }

        twentyButton.setOnClickListener {
            if (
                presenter.onBuyCards(
                    coinsAnimated,
                    picturesAvailableTextView,
                    coinsTextView,
                    20,
                    20,
                    false
                )
            ) {
                twentyButton.isEnabled = false
                AppPreferences.twentyButtonEnabled = false
            }

        }

        thirtyButton.setOnClickListener {
            if (
                presenter.onBuyCards(
                    coinsAnimated,
                    picturesAvailableTextView,
                    coinsTextView,
                    30,
                    30,
                    AppPreferences.twentyButtonEnabled!!
                )
            ) {
                thirtyButton.isEnabled = false
                AppPreferences.thirtyButtonEnabled = false
            }
        }

        fortyButton.setOnClickListener {
            if (
                presenter.onBuyCards(
                    coinsAnimated,
                    picturesAvailableTextView,
                    coinsTextView,
                    40,
                    40,
                    AppPreferences.thirtyButtonEnabled!!
                )
            ) {
                fortyButton.isEnabled = false
                AppPreferences.fortyButtonEnabled = false
            }

        }

        return root
    }

    override fun onFailed(message: String) {

        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()


    }

    override fun onSuccess(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun updateAvailablePictures(textView: TextView) {
        textView.text = getString(
            R.string.available_pictures,
            (AppPreferences.openedPicturesCount?.plus(1)).toString(),
            (AppPreferences.maxPicturesCount?.plus(1)).toString()
        )
    }

}

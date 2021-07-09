package summerpractice.team13.guessthedrawing.ui.store_screen

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import summerpractice.team13.guessthedrawing.R
import summerpractice.team13.guessthedrawing.mvp.presenters.AppPreferences

class StoreFragment : Fragment() {

    private lateinit var storeViewModel: StoreViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        storeViewModel =
            ViewModelProvider(this).get(StoreViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_store, container, false)


        //val textView: TextView = root.findViewById(R.id.text_store)
//        storeViewModel.text.observe(viewLifecycleOwner, {
//            textView.text = it
//        })

        // Values
        val twentyButton: Button = root.findViewById(R.id.btn_twenty)
        val thirtyButton: Button = root.findViewById(R.id.btn_thirty)
        val fortyButton: Button = root.findViewById(R.id.btn_forty)
        val picturesAvailableTextView: TextView = root.findViewById(R.id.testTextView)
        val coinsTextView: TextView = root.findViewById(R.id.tv_coin)
        val coinsAnimated: TextView = root.findViewById(R.id.tv_coin_animated)

        val testButton: Button = root.findViewById(R.id.testbutton)

        coinsTextView.text = AppPreferences.coins.toString()

        updatePicturesAvailable(picturesAvailableTextView)

        testButton.setOnClickListener {
            AppPreferences.coins = AppPreferences.coins?.plus(10)
            coinsTextView.text = AppPreferences.coins.toString()
        }


        twentyButton.setOnClickListener {
            if (AppPreferences.coins!! >= 20 && AppPreferences.openedPicturesCount!! < 20) {

                storeButtonAction(
                    coinsAnimated,
                    picturesAvailableTextView,
                    coinsTextView,
                    twentyButton,
                    "-20",
                    20,
                    19
                )

            }
        }

        thirtyButton.setOnClickListener {
            if (AppPreferences.coins!! >= 30 && AppPreferences.openedPicturesCount!! < 30 && !twentyButton.isEnabled) {

                storeButtonAction(
                    coinsAnimated,
                    picturesAvailableTextView,
                    coinsTextView,
                    thirtyButton,
                    "-30",
                    30,
                    29
                )

            }
        }

        fortyButton.setOnClickListener {
            if (AppPreferences.coins!! >= 40 && AppPreferences.openedPicturesCount!! < 40 && !thirtyButton.isEnabled) {

                storeButtonAction(
                    coinsAnimated,
                    picturesAvailableTextView,
                    coinsTextView,
                    fortyButton,
                    "-40",
                    40,
                    39
                )

            }
        }

        return root
    }

    private fun storeButtonAction(
        coinsAnimated: TextView,
        picturesAvailableTextView: TextView,
        coinsTextView: TextView,
        button: Button,
        decrementText: String,
        moneyDecrementValue: Int,
        picturesOpenedValue: Int
    ) {
        // Появляется и исчезает текст о трате монеток
        coinsAnimated.text = decrementText
        val valueAnimator = ValueAnimator.ofFloat(0f, 1f, 0f)
        valueAnimator.duration = 3000
        valueAnimator.addUpdateListener { animation ->
            val alpha = animation.animatedValue as Float
            coinsAnimated.alpha = alpha
        }
        valueAnimator.start()

        // вычитаем N монет
        AppPreferences.coins = AppPreferences.coins?.minus(moneyDecrementValue)
        // присваиваем Z монет
        AppPreferences.openedPicturesCount = picturesOpenedValue

        updatePicturesAvailable(picturesAvailableTextView)

        // обновляем текст монет
        coinsTextView.text = AppPreferences.coins.toString()

        button.isEnabled = false
    }

   private fun updatePicturesAvailable(textView: TextView) {
       textView.text = getString(
           R.string.available_pictures,
           AppPreferences.openedPicturesCount.toString(),
           (AppPreferences.maxPicturesCount?.plus(1)).toString()
       )
   }

}

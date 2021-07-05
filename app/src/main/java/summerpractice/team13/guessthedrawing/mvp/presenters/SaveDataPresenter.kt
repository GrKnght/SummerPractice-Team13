package summerpractice.team13.guessthedrawing.mvp.presenters

import android.content.Context
import android.content.SharedPreferences
import android.widget.TextView

open class SaveDataPresenter(context: Context) {

    private val APP_PREFERENCES = "settings"

    private val TIME = "time"

    private lateinit var sharedPreferences: SharedPreferences



    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)


    }

    fun saveData(time: Int) {


        val edit: SharedPreferences.Editor = sharedPreferences.edit()
        edit.putInt(TIME, time)
        edit.apply()

    }


    fun loadData(textView: TextView) {
        textView.setText(sharedPreferences.getInt(APP_PREFERENCES, 1))


    }
}

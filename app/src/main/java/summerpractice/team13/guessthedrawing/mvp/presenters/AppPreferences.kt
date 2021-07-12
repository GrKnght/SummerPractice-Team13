package summerpractice.team13.guessthedrawing.mvp.presenters

import android.content.Context
import android.content.SharedPreferences

object AppPreferences {
    private const val NAME = "settings"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }


    var time: Int
        get() = preferences.getInt("temp", 15)
        set(value) = preferences.edit {
            it.putInt("temp", value)
        }

    var mode: String?
        get() = preferences.getString("mode", "Easy")
        set(value) = preferences.edit {
            it.putString("mode", value)
        }
    var language: String?
        get() = preferences.getString("lang", "English")
        set(value) = preferences.edit {
            it.putString("lang", value)
        }

    var languageCode: String?
        get() = preferences.getString("langCode", "en")
        set(value) = preferences.edit {
            it.putString("langCode", value)
        }

    var coins: Int?
        get() = preferences.getInt("coin", 0)
        set(value) = preferences.edit {
            it.putInt("coin", value!!)
        }

    var maxPicturesCount: Int?
        get() = preferences.getInt("maxPictures", 39)
        set(value) = preferences.edit {
            it.putInt("maxPictures", value!!) // 40 - кол-во элементов в массиве рисунков
        }

    var openedPicturesCount: Int?
        get() = preferences.getInt("openedPictures", 9)
        set(value) = preferences.edit {
            it.putInt("openedPictures", value!!)
        }

    var twentyButtonEnabled: Boolean?
        get() = preferences.getBoolean("twentyButtonEnabled", true)
        set(value) = preferences.edit {
            it.putBoolean("twentyButtonEnabled", value!!)
        }

    var thirtyButtonEnabled: Boolean?
        get() = preferences.getBoolean("thirtyButtonEnabled", true)
        set(value) = preferences.edit {
            it.putBoolean("thirtyButtonEnabled", value!!)
        }

    var fortyButtonEnabled: Boolean?
        get() = preferences.getBoolean("fortyButtonEnabled", true)
        set(value) = preferences.edit {
            it.putBoolean("fortyButtonEnabled", value!!)
        }

    var playMusicButtonEnabled: Boolean
        get() = preferences.getBoolean("playMusicButtonEnabled", false)
        set(value) = preferences.edit {
            it.putBoolean("playMusicButtonEnabled", value)
        }

    var stopMusicButtonEnabled: Boolean
        get() = preferences.getBoolean("stopMusicButtonEnabled", true)
        set(value) = preferences.edit {
            it.putBoolean("stopMusicButtonEnabled", value)
        }

}
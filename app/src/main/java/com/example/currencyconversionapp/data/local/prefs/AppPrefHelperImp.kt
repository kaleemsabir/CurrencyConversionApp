package com.example.currencyconversionapp.data.local.prefs

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.core.content.edit
import com.example.currencyconversionapp.utils.Constants.APP_PREF_TIME_STAMP
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppPrefHelperImp @Inject constructor(
    @ApplicationContext val context: Context,
    ):AppPrefHelper {
    private val mPrefs: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)
    override fun saveTimeStamp(timeStamp :Long) {
        mPrefs.edit { putLong(APP_PREF_TIME_STAMP, timeStamp) }
    }

    override fun getTimeStamp() = mPrefs.getLong(APP_PREF_TIME_STAMP,0)


}
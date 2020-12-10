package com.melrose1994.wechatstep.ui

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import com.melrose1994.wechatstep.BuildConfig
import com.melrose1994.wechatstep.consts.KEY_DATA_ENABLE
import com.melrose1994.wechatstep.consts.PACKAGE_NAME_WE_CHAT
import splitties.views.dsl.core.setContentView

/**
 * @author Melrose
 * @since 1.0.0
 */
class ConfigActivity:AppCompatActivity() {



    private lateinit var mUi:ConfigUi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(ConfigUi(this).also { mUi = it })

        mUi.switch.setOnCheckedChangeListener { _, isChecked ->
            sendBroadcast(Intent(BuildConfig.ACTION).apply {
                putExtra(KEY_DATA_ENABLE,isChecked)
            })
        }
    }


    override fun onDestroy() {
        super.onDestroy()
    }
}
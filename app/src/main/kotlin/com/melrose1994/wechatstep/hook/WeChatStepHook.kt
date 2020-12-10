package com.melrose1994.wechatstep.hook

import android.app.Activity
import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.melrose1994.wechatstep.BuildConfig
import com.melrose1994.wechatstep.R
import com.melrose1994.wechatstep.consts.KEY_DATA_ENABLE
import com.melrose1994.wechatstep.consts.TAG
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage
import splitties.toast.longToast
import splitties.toast.toast

/**
 * @author Melrose
 * @since 1.0.0
 */
class WeChatStepHook(private val params: XC_LoadPackage.LoadPackageParam) {
    companion object{
        @Volatile var  isHooked = false
        @Volatile var mApplication:Application?=null
    }

    private var multiple = 4

    private var mEnable = false

    private var STEP = 1000
    private val mCallback = object :XC_MethodHook(){
        override fun beforeHookedMethod(param: MethodHookParam?) {
            super.beforeHookedMethod(param)
            if (param == null)return
            if (mEnable){
                val fArr = param.args[1] as FloatArray
                val f = (param.args[1] as FloatArray)[0]
                fArr[0] =f + multiple * STEP
                multiple++
            }
        }
    }

    fun hook(){
        if (!isHooked){
            isHooked = true
            Log.d(TAG, "hook: start hook!")
            hookApplication()
            val eventClz = XposedHelpers.findClass("android.hardware.SystemSensorManager\$SensorEventQueue",params.classLoader)
            XposedBridge.hookAllMethods(eventClz,"dispatchSensorEvent",mCallback)
        }
    }

    inner class ConfigBroadcastReceiver: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            intent?.let {
               mEnable = it.getBooleanExtra(KEY_DATA_ENABLE,true)
               Log.d(TAG,"onReceive:$mEnable")

            }
        }
    }


    protected fun hookApplication() {
        XposedHelpers.findAndHookMethod(Application::class.java, "onCreate",
            object : XC_MethodHook() {
                override fun afterHookedMethod(param: MethodHookParam) {
                    super.afterHookedMethod(param)
                    Log.d(TAG,"WeChat application onCreated!")
                    mApplication = param.thisObject as Application
                    val filter = IntentFilter()
                    filter.addAction(BuildConfig.ACTION)
                    mApplication?.registerReceiver(ConfigBroadcastReceiver(),filter)
                }

            })
    }



}
package com.melrose1994.wechatstep.hook

import android.util.Log
import com.melrose1994.wechatstep.consts.PACKAGE_NAME_WE_CHAT
import com.melrose1994.wechatstep.consts.TAG
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.callbacks.XC_LoadPackage

/**
 * @author Melrose
 * @since 1.0.0
 */
class XposedHookLoadPackageImpl : IXposedHookLoadPackage {
    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam?) {
       if (lpparam?.packageName==PACKAGE_NAME_WE_CHAT&&lpparam.processName == PACKAGE_NAME_WE_CHAT)WeChatStepHook(lpparam).hook()
    }
}
package com.melrose1994.wechatstep.ui

import android.content.Context
import android.view.View
import splitties.views.dsl.core.*
import splitties.views.textColorResource
import splitties.views.textResource
import com.melrose1994.wechatstep.R
import splitties.dimensions.dip
import splitties.views.dsl.appcompat.switch
import splitties.views.gravityCenterVertical

/**
 * @author Melrose
 * @since 1.0.0
 */
class ConfigUi (override val ctx:Context):Ui{



    val switch = switch ()

    val switchLayout = horizontalLayout {
        gravity = gravityCenterVertical
        add(textView {
         textColorResource = android.R.color.white
         textSize = 14f
         textResource = R.string.config_switch
        },lParams())

        add(switch,lParams{
            startMargin = dip(10)
        })
    }




    val parent = verticalLayout {
        val padding = dip(10)
        setPadding(padding,padding,padding,padding)
        add(textView {
            textColorResource = android.R.color.white
            textSize = 14f
            textResource = R.string.tips_use
        },lParams { topMargin = dip(20) })
        add(switchLayout,lParams{
            startMargin = dip(10)
            topMargin = dip(30)
        })

    }
    override val root: View
        get() = parent
}
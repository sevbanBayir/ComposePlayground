package com.sevban.composetutorialssevbanbayir.canvas.weightpicker

sealed interface LineType {
    object Normal: LineType
    object FiveStep: LineType
    object TenStep: LineType
}
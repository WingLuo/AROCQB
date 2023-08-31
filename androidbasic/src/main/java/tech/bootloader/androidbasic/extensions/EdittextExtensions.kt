package com.lingkj.basic.extensions

import android.view.KeyEvent
import android.widget.EditText



/**
 * 插入一个字符
 */
fun EditText.addChar(data: String) {
    val index = this.selectionStart
    val editable = this.text
    editable.insert(index, data)
}

/**
 * 删除光标前的字符
 */
fun EditText.deleteChar() {
//    val index = this.selectionStart
//    val editable = this.text
//    editable.delete(index - 1, index)

    val keyCode = KeyEvent.KEYCODE_DEL
    val keyEventDown = KeyEvent(KeyEvent.ACTION_DOWN, keyCode)
    val keyEventUp = KeyEvent(KeyEvent.ACTION_UP, keyCode)
    this.onKeyDown(keyCode, keyEventDown)
    this.onKeyUp(keyCode, keyEventUp)

}



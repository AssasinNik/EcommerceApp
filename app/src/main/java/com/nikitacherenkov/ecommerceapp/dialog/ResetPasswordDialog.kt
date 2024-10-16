package com.nikitacherenkov.ecommerceapp.dialog

import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.nikitacherenkov.ecommerceapp.R

fun Fragment.setupBottomSheetDialog(
    onSendClick:(String) -> Unit
){
    val dialog = BottomSheetDialog(requireContext(),R.style.DialogStyle)
    val view = layoutInflater.inflate(R.layout.reset_password_dialog, null)
    dialog.setContentView(view)
    dialog.behavior.state=BottomSheetBehavior.STATE_EXPANDED
    dialog.show()

    val edEmail=view.findViewById<EditText>(R.id.ed_email)
    val buttonSend = view.findViewById<AppCompatButton>(R.id.bt_send)
    val buttonCancel = view.findViewById<AppCompatButton>(R.id.bt_cancel)

    buttonSend.setOnClickListener {
        val email = edEmail.text.toString().trim()
        onSendClick(email)
        dialog.dismiss()
    }
    buttonCancel.setOnClickListener {
        dialog.dismiss()
    }
}
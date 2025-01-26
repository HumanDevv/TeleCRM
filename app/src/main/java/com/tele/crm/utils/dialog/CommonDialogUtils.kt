package com.tele.crm.utils.dialog

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.res.Resources
import android.graphics.Insets
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.util.DisplayMetrics
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowInsets
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat

import com.google.android.material.bottomsheet.BottomSheetDialog
import com.tele.crm.R
import com.tele.crm.databinding.NetworkDialogBinding
import com.tele.crm.utils.extension.isNetworkConnected
import com.tele.crm.utils.extension.setDebouncedOnClickListener

object CommonDialogUtils {

    interface DialogClick {
        fun onClick()
        fun onCancel()
    }




/*    fun showAppLogOutDialog(
        context: Context,
        title: String,
        msg: String,
        okText: String,
        onClick: () -> Unit
    ) {
        val alertDialog = BottomSheetDialog(context, R.style.BottomSheetDialogTheme)
        val layoutInflater = LayoutInflater.from(context)
        val binding = AppLogoutDialogBinding.inflate(layoutInflater, null, false)

        with(binding) {

            tvTitle.text = title
            tvSubtitle.text = msg
            btnOK.text = okText

            btnOK.setDebouncedOnClickListener {
                onClick()
                alertDialog.dismiss()
            }

            btnCancel.setDebouncedOnClickListener {
                alertDialog.dismiss()
            }

            ivClose.setDebouncedOnClickListener {
                alertDialog.dismiss()
            }
        }

        alertDialog.apply {
            setContentView(binding.root)
            window?.setBackgroundDrawable(
                ColorDrawable(
                    ContextCompat.getColor(
                        context,
                        R.color.half_transparent
                    )
                )
            )
            setCanceledOnTouchOutside(true)
            setCancelable(true)
        }.also { it.show() }
    }*/



    fun networkDialog(context: Activity): Dialog {
        val binding: NetworkDialogBinding =
            NetworkDialogBinding.inflate(LayoutInflater.from(context), null, false)
        val dialog = Dialog(context, R.style.DialogAnimationTheme)
        binding.btnRetry.setDebouncedOnClickListener {
            if (context.isNetworkConnected()) {
                dialog.dismiss()
            } else {
                Toast.makeText(context, "No internet connection.", Toast.LENGTH_SHORT).show()
            }
        }
        dialog.setContentView(binding.root)
        dialog.setCancelable(false)
        return dialog
    }


    private fun getDeviceWidth(): Int = Resources.getSystem().displayMetrics.widthPixels

    @Suppress("unused")
    fun isEmailValid(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    /**
     * Show the soft input.
     *
     * @param activity The activity.
     */
    @Suppress("unused", "DEPRECATION")
    fun showSoftInput(activity: Activity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = activity.currentFocus
        if (view == null) {
            view = View(activity)
            view.isFocusable = true
            view.isFocusableInTouchMode = true
            view.requestFocus()
        }
        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED)
    }

    /**
     * Hide the soft input.
     *
     * @param activity The activity.
     */
    @Suppress("unused")
    fun hideSoftInput(activity: Activity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = activity.currentFocus
        if (view == null) view = View(activity)
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    /**
     * Hide the soft input.
     */
    @Suppress("unused")
    fun hideSoftInput(view: View) {
        val imm = view.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        if (imm.isActive)
            imm.hideSoftInputFromWindow(view.windowToken, 0)
    }


    @Suppress("DEPRECATION")
    fun getScreenWidth(mContext: Activity): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics = mContext.windowManager.currentWindowMetrics
            val insets: Insets =
                windowMetrics.windowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
            windowMetrics.bounds.width() - insets.left - insets.right
        } else {
            val displayMetrics = DisplayMetrics()
            mContext.windowManager.defaultDisplay.getMetrics(displayMetrics)
            displayMetrics.widthPixels
        }
    }


}
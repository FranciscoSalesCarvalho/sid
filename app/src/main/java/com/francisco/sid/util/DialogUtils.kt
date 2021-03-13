package com.francisco.sid.util

import android.app.ProgressDialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.text.TextUtils
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import com.francisco.sid.R

class DialogUtils {

    private var mProgressDialog: ProgressDialog? = null
    private var mContext: Context? = null

    fun showProgressDialog(context: Context, message: String, cancelable: Boolean) {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialog(context)
        }

        if (mContext == null) {
            mContext = context
        }

        if (TextUtils.isEmpty(message)) {
            showProgressDialog(context, cancelable)
        } else {
            if (context is AppCompatActivity) {
                mContext = context
                val activity: AppCompatActivity = context

                mProgressDialog?.run {
                    if (!isShowing && !activity.isFinishing) {
                        setMessage(message)
                        isIndeterminate = true
                        setCancelable(cancelable)
                        show()
                    }
                }
            }
        }
    }

    fun showProgressDialog(context: Context, cancelable: Boolean) {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialog(context)
        }

        if (mContext == null) {
            mContext = context
        }

        mProgressDialog?.run {
            if (!isShowing) {
                show()
                setContentView(R.layout.progress_util)
                isIndeterminate = true
                setCancelable(cancelable)

                this.window?.run {
                    setGravity(Gravity.CENTER)
                    setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
                }
            }
        }
    }

    fun hideProgressDialog() {
        var canDismiss = true
        mContext?.let {
            if (mContext is AppCompatActivity) {
                val activity: AppCompatActivity = (mContext as AppCompatActivity)
                canDismiss = !activity.isFinishing && !activity.isDestroyed
            }
        }
        mProgressDialog?.run {
            if (canDismiss)
                this.dismiss()
        }
    }
}
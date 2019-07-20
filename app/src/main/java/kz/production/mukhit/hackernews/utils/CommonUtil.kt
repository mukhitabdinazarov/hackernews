package kz.production.mukhit.hackernews.utils

import android.app.ProgressDialog
import android.content.Context
import kz.production.mukhit.hackernews.R


object CommonUtil {

    fun showLoadingDialog(context: Context?): ProgressDialog {
        val progressDialog = ProgressDialog(context)
        progressDialog.let {
            it.show()
//            it.window?.setBackgroundDrawable(Color.TRANSPARENT)
            it.setContentView(R.layout.progress_dialog)
            it.isIndeterminate = true
            it.setCancelable(false)
            it.setCanceledOnTouchOutside(false)
            return it
        }
    }

}
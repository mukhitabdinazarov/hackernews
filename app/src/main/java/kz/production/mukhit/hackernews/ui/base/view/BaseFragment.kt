package kz.production.mukhit.hackernews.ui.base.view

import android.app.ProgressDialog
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import dagger.android.support.AndroidSupportInjection
import kz.production.mukhit.hackernews.utils.CommonUtil

/**
 * Created by mukhit on 15/11/18.
 */
abstract class BaseFragment : Fragment(), MVPView {

    private var parentActivity: BaseActivity? = null
    private var progressDialog: ProgressDialog? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BaseActivity) {
            val activity = context as BaseActivity?
            this.parentActivity = activity
            activity?.onFragmentAttached()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDependencyInjection()
        setHasOptionsMenu(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    override fun hideProgress() {
        if (progressDialog != null && progressDialog?.isShowing!!) {
            progressDialog?.cancel()
        }
    }

    override fun showProgress() {
        hideProgress()
        progressDialog = CommonUtil.showLoadingDialog(this.context)
    }

    override fun showMessage(message: String) {
        if (parentActivity != null) {
            parentActivity!!.showMessage(message)
        }
    }

    fun getBaseActivity() = parentActivity

    private fun performDependencyInjection() = AndroidSupportInjection.inject(this)

    interface CallBack {
        fun onFragmentAttached()
        fun onFragmentDetached(tag: String)
    }

    abstract fun setUp()

    override fun isNetworkAvailable(): Boolean {
        val cm = parentActivity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?

        val networkInfo = cm!!.activeNetworkInfo

        return if (networkInfo != null && networkInfo.isConnected) {
            true
        } else false
    }
}
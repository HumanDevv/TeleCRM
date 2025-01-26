package com.tele.crm

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.tele.crm.databinding.ActivityMainBinding
import com.tele.crm.databinding.ProgressDialogBinding
import com.tele.crm.presentation.lead.LeadFragment
import com.tele.crm.utils.extension.visible

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navigationController: NavController
    private lateinit var navHostFragment: Fragment

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigationController =
            (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment).navController
        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)!!
        setupNavigation()
        onBackPressedDispatcher.addCallback {
            val fragment = navHostFragment.childFragmentManager.primaryNavigationFragment
            if (fragment is LeadFragment) {
                finishAffinity()
                /* if (snackBar.isShown) {
                     finishAffinity()
                 } else {
                     snackBar.show()
                 }*/
            } else {
                navigationController.popBackStack()
                if (navigationController.currentBackStack.value.isEmpty()) {
                    finish()
                }
            }
        }

    }

    private fun setupNavigation() {
        navigationController.addOnDestinationChangedListener { _, destination, _ ->
            //hideProgress()
            when (destination.id) {

                R.id.leadFragment -> {
                    binding.bottomNav.menu.getItem(0).isChecked = true
                    binding.bottomNav.visible(true)

                }

                R.id.callFragment -> {
                    binding.bottomNav.menu.getItem(1).isChecked = true
                    binding.bottomNav.visible(true)

                }

                R.id.profileFragment -> {
                    binding.bottomNav.menu.getItem(2).isChecked = true
                    binding.bottomNav.visible(true)

                }

                else->{
                    binding.bottomNav.visibility=View.GONE
                }
            }


        }

        binding.bottomNav.setOnItemSelectedListener { item ->
            val navOptions: NavOptions = NavOptions.Builder()
                .setPopUpTo(R.id.leadFragment, false)
                .setEnterAnim(R.anim.nav_slide_in_left)
                .setExitAnim(R.anim.nav_slide_out_left)
                .setPopEnterAnim(R.anim.nav_slide_in_left)
                .setPopExitAnim(R.anim.nav_slide_out_right)
                .build()


            when (item.itemId) {
                R.id.leadFragment -> {
                    navigationController.navigate(R.id.goToHome)
                }

                R.id.callFragment -> {
                    navigationController.navigate(R.id.callFragment, bundleOf(), navOptions)
                }

                R.id.profileFragment -> {
                    navigationController.navigate(R.id.settingFragment, bundleOf(), navOptions)
                }


            }

            true
        }


    }

    private fun animateBottomNavHeight(view: View, fromHeight: Int, toHeight: Int) {
        val animator = ValueAnimator.ofInt(fromHeight.dpToPx(), toHeight.dpToPx())
        animator.addUpdateListener { valueAnimator ->
            val params = view.layoutParams
            params.height = valueAnimator.animatedValue as Int
            view.layoutParams = params
        }
        animator.duration = 300 // Animation duration in milliseconds
        animator.start()
    }

    private fun Int.dpToPx(): Int {
        return (this * Resources.getSystem().displayMetrics.density).toInt()
    }

    private var progressDialog: Dialog? = null

    fun showProgress() {
        if (progressDialog == null) {
            progressDialog = Dialog(this)
            if (progressDialog!!.window != null) {
                val window = progressDialog!!.window
                window!!.setLayout(
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.WRAP_CONTENT
                )
                window.setGravity(Gravity.CENTER)
                progressDialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
            val binding = ProgressDialogBinding.inflate(layoutInflater, null, false)
            progressDialog!!.setContentView(binding.root)
            Glide.with(this).load(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_background)
                .into(binding.imgLoading)
            progressDialog!!.setCancelable(false)
            progressDialog!!.setCanceledOnTouchOutside(false)
            progressDialog!!.show()
        } else {
//            if(progressDialog!!.isShowing().not())
            progressDialog!!.show()
        }
    }

    fun hideProgress() {
        if (progressDialog != null) {
            progressDialog!!.dismiss()
        }
    }
}

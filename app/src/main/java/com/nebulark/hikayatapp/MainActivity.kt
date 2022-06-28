 package com.nebulark.hikayatapp

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

 @Suppress("DEPRECATION")
 class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        //set status bar color

        //set status bar color
        if (Build.VERSION.SDK_INT >= 21) {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = this.resources.getColor(R.color.app_color)
        }
        //setupActionBarWithNavController(findNavController(R.id.fragmentContainerView))
    }

     override fun onSupportNavigateUp(): Boolean {

         val navController = findNavController(R.id.fragmentContainerView)

         return navController.navigateUp() || super.onSupportNavigateUp()
     }
}
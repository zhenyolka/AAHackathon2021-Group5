package com.example.thingder

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.thingder.activities.login.LoginActivity
import com.example.thingder.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_fragment_container)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_main,
                R.id.navigation_mine_liked_things,
                R.id.navigation_my_things,
                R.id.navigation_liked_things
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.logout -> {
                showLogoutDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showLogoutDialog() {
        val builder = AlertDialog.Builder(this)
            .setTitle(getString(R.string.logout_alert_dialog_title))
            .setMessage(getString(R.string.logout_alert_dialog_message))

        builder.setPositiveButton(getString(R.string.logout_alert_dialog_pos_btn_text)) { dialog, which ->
            logout()
        }
        builder.setNegativeButton(getString(R.string.logout_alert_dialog_neg_btn_text)) { dialog, which ->
            //Do nothing
        }

        val alertDialog = builder.create()
        alertDialog.show()
    }

    private fun logout() {
        intent = Intent(this, LoginActivity::class.java)
        Firebase.auth.signOut()
        Toast.makeText(applicationContext, getString(R.string.logout_success_toast_text), Toast.LENGTH_SHORT).show()
        startActivity(intent)
    }
}
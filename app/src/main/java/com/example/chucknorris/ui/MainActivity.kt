package com.example.chucknorris.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.findNavController
import com.example.chucknorris.R
import com.example.chucknorris.ui.core.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpActionBar()
    }

    private fun setUpActionBar() {

        findNavController(R.id.nav_host_fragment).addOnDestinationChangedListener { _, destination, bundle ->
            this.supportActionBar?.setDisplayHomeAsUpEnabled(destination.id == R.id.factFragment)
            bundle?.getString("category")?.let {
                this.supportActionBar?.title = getString(R.string.fact_label, it)
            } ?: run {
                this.supportActionBar?.title = destination.label
            }
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}

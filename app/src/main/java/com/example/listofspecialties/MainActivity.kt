package com.example.listofspecialties

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.listofspecialties.presentation.screeen.workersspeciality.view.WorkersSpecialityFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigateToWorkersSpecialityPage()
    }

    private fun navigateToWorkersSpecialityPage() {
        supportFragmentManager.beginTransaction()
            .add(
                R.id.fragment_container,
                WorkersSpecialityFragment.newInstance(),
                WorkersSpecialityFragment.FRAGMENT_NAME
            )
            .commitAllowingStateLoss()
    }
}
package com.ismailmesutmujde.kotlinworkmanagerapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.ismailmesutmujde.kotlinworkmanagerapp.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    private lateinit var bindingMain : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        val view = bindingMain.root
        setContentView(view)

        bindingMain.buttonMake.setOnClickListener {

            val workingCondition = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            val request = OneTimeWorkRequestBuilder<MyWorker>()
                .setInitialDelay(10, TimeUnit.SECONDS)
                .setConstraints(workingCondition)  // Disconnect the internet from the emulator and try it
                .build()
            WorkManager.getInstance(this).enqueue(request)

            WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.id)
                .observe(this){
                    val state = it.state.name
                    Log.e("Background Process Result", state)

                }

        }

    }
}
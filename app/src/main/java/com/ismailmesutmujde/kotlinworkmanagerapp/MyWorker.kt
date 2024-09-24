package com.ismailmesutmujde.kotlinworkmanagerapp

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(appContext: Context, workerParams:WorkerParameters) : Worker(appContext, workerParams) {

    override fun doWork(): Result {
        val sum = 10 + 20
        Log.e("Background Process Result", sum.toString())
        return Result.success()
    }
}
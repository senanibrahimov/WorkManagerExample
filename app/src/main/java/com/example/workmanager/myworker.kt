package com.example.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class myworker(context:Context,workerParameters: WorkerParameters):Worker(context,workerParameters) {
    override fun doWork(): Result {

        val toplama=30+10

        Log.e("toplam","${toplama.toString()}")

        return  Result.success()
    }


}
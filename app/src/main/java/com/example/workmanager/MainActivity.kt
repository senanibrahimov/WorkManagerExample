package com.example.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.workmanager.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
     lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.button.setOnClickListener {
         // onetimerequest()

            periodicrequest()
        }


    }


    fun onetimerequest(){
        val sert=Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val request= OneTimeWorkRequestBuilder<PeriodicWorker>().
        setConstraints(sert).build()




        WorkManager.getInstance(this).enqueue(request)

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.id).observe(this){

            var durum=it.state.name

            Log.e("durum","${durum.toString()}")
        }
    }

    fun periodicrequest(){


        val request1= PeriodicWorkRequestBuilder<PeriodicWorker>(15,TimeUnit.MINUTES).build()



        WorkManager.getInstance(this).enqueue(request1)

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(request1.id).observe(this){

            val state=it.state.name
            Log.e("state","${state}")
        }

    }
}
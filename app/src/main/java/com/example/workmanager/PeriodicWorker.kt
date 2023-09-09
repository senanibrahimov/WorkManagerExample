package com.example.workmanager

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters

class PeriodicWorker(contect:Context,workerParameters: WorkerParameters):Worker(contect,workerParameters) {
    override fun doWork(): Result {


         createnotification()

        return Result.success()
    }

    fun createnotification() {

        val builder:NotificationCompat.Builder
        val manager=applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val intent=Intent(applicationContext,MainActivity::class.java)

        val gedilecekintent=PendingIntent.getActivity(applicationContext,1,intent,PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)


        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){

             var channelad="mychanel"
             var channelid="mychannelss"
             var kanaldescrip="descrip"
              var priority=NotificationManager.IMPORTANCE_HIGH
             var channel:NotificationChannel?=manager.getNotificationChannel(channelid)

            if (channel==null){

                channel= NotificationChannel(channelid,channelad,priority)
                channel.description=kanaldescrip

                manager.createNotificationChannel(channel)
            }


            builder=NotificationCompat.Builder(applicationContext,channelid)
            builder.setContentTitle("Mynotification")
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_android_black_24dp)
                .setContentText("bu proqram example ucun yaradilmisdi")
                .setContentIntent(gedilecekintent)

        }
        else{

           builder=NotificationCompat.Builder(applicationContext)
            builder.setContentTitle("Mynotification").setSmallIcon(R.drawable.ic_android_black_24dp)
                .setContentText("bu proqram example ucun yaradilmisdi")
                    .setContentIntent(gedilecekintent). setAutoCancel(true)
                    .priority=Notification.PRIORITY_HIGH
        }

        manager.notify(1,builder.build())
    }

}
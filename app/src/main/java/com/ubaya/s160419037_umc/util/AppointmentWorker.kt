package com.ubaya.s160419037_umc.util

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class AppointmentWorker (val context: Context, val workerParams: WorkerParameters): Worker(context, workerParams) {
    override fun doWork(): Result {
        NotificationHelper(context)
            .createNotification(
                inputData.getString("title").toString(),
                inputData.getString("message").toString(),
                inputData.getString("long_message").toString(),
                inputData.getString("url_photo").toString()
            )
        return Result.success()
    }
}
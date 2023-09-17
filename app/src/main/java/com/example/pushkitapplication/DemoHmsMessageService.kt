package com.example.pushkitapplication

import android.os.Bundle
import android.util.Log
import com.huawei.hms.push.HmsMessageService
import com.huawei.hms.push.RemoteMessage

class DemoHmsMessageService : HmsMessageService() {
    override fun onNewToken(token: String, bundle: Bundle) {
        // Obtain a push token.
        Log.i("TAG", "have received refresh token:$token")

        // Check whether the token is null.
        if (token.isNotEmpty()) {
            refreshedTokenToServer(token)
        }
    }

    private fun refreshedTokenToServer(token: String) {
        Log.i("TAG", "sending refreshed token to server. token:$token")
    }

    override fun onMessageReceived(message: RemoteMessage?) {
        Log.i("TAG", "onMessageReceived is called")

        // Check whether the message is empty.
        if (message == null)
        {
            Log.e("TAG", "Received message entity is null!")
            return
        }

        val data = message.data
        val dataOfMap = message.dataOfMap
        val s = dataOfMap["key1"]
        // Obtain the message content.
        Log.i("TAG", """getData: ${message.data}        
        getFrom: ${message.from}        
        getTo: ${message.to}        
        getMessageId: ${message.messageId}
        getSentTime: ${message.sentTime}           
        getDataMap: ${message.dataOfMap}
        getMessageType: ${message.messageType}   
        getTtl: ${message.ttl}        
        getToken: ${message.token}""".trimIndent())

        val judgeWhetherIn10s = false
        // If the message is not processed within 10 seconds, create a job to process it.
        if (judgeWhetherIn10s) {
            startWorkManagerJob(message)
        } else {
            // Process the message within 10 seconds.
            processWithin10s(message)
        }
    }
    private fun startWorkManagerJob(message: RemoteMessage?) {
        Log.d("TAG", "Start new Job processing.")
    }
    private fun processWithin10s(message: RemoteMessage?) {
        Log.d("TAG", "Processing now.")
    }
}

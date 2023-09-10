package com.example.pushkitapplication

import android.os.Bundle
import android.util.Log
import com.huawei.hms.push.HmsMessageService

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
}
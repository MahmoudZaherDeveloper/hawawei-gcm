package com.example.pushkitapplication

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.pushkitapplication.ui.theme.PushKitApplicationTheme
import com.huawei.hms.aaid.HmsInstanceId
import com.huawei.hms.common.ApiException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PushKitApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                    getToken(this)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PushKitApplicationTheme {
        Greeting("Android")
    }
}

private fun getToken(mainActivity: MainActivity) {
    // Create a thread.
    object : Thread() {
        override fun run() {
            try {
                // Obtain the app ID from the agconnect-services.json file.
                val appId = "109111765"

                // Set tokenScope to HCM.
                val tokenScope = "HCM"
                val token = HmsInstanceId.getInstance(mainActivity).getToken(appId, tokenScope)
                Log.i("TAG", "get token:$token")

                // Check whether the token is null.
                if (!TextUtils.isEmpty(token)) {
                    sendRegTokenToServer(token)
                }
            } catch (e: ApiException) {
                Log.e("TAG", "get token failed, $e")
            }
        }
    }.start()
}

private fun sendRegTokenToServer(token: String) {
    Log.i("TAG", "sending token to server. token:$token")
}

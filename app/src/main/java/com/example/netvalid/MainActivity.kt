package com.example.netvalid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.net.Inet4Address
import java.net.InetAddress

class MainActivity : AppCompatActivity() {
    val dataScope = CoroutineScope(Dispatchers.IO)

    fun analysisNet() {
        // 这种方式如果ping不通 会阻塞一分钟左右
        // 也是要放在另一个线程里面ping
        try {
            val addr: InetAddress = InetAddress.getByName("www.baidu.com")
            if (!addr.isLoopbackAddress() && addr is Inet4Address) {
                Log.d("morse", "analysisNet onSuccess ")
            } else {
                Log.d("morse", "analysisNet onFailure 0")
            }
        } catch (e: Throwable) {
            Log.d("morse", "analysisNet onFailure 1 $e")
        }
    }

    fun pingNet() {
        try {
            if (InetAddress.getByName("119.29.29.29").isReachable(3000)) {
                Log.d("morse", "pingNet onSuccess")
            } else {
                Log.d("morse", "pingNet onFailure")
            }
        } catch (e: Throwable) {
            Log.d("morse", "pingNet onFailure")
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dataScope.launch {

            while (true){
                pingNet()
                delay(5000)
            }

        }
    }
}
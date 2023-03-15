package com.example.myappnotifications

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {
    private val edtDeviceToken: EditText by lazy { findViewById<EditText>(R.id.edt_device_token) }
    private val edtTitle: EditText by lazy { findViewById<EditText>(R.id.edt_title) }
    private val edtMessage: EditText by lazy { findViewById<EditText>(R.id.edt_message) }
    private val btnSend: Button by lazy { findViewById<Button>(R.id.btn_send) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("asda", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }
            // Get new FCM registration token
            val token = task.result

            // Log and toast
            Log.d("dasdasd", token)
            Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()
            edtDeviceToken.setText(token)

        })

        btnSend.setOnClickListener {
            val notificationsReq = NotificationsRequest()
            notificationsReq.data?.message = edtMessage.text.toString()
            notificationsReq.data?.title = edtTitle.text.toString()
            notificationsReq.to = edtDeviceToken.text.toString()
            val token ="key=AAAAov6wOyM:APA91bHIn3U-10U0eqqnJ1sdXLjP_DMVdCx7seyR_eWhP9nWzWbTDWK-IEFIR2a1JJ1dUbyWdosyjQ8RmwBidP-A4IrUm1sOWvp6YQkHdkD9-6BABaWL8nS-5Db9GaRmnaKQM1LbQ_ck"
            postNotifications(token,notificationsReq)
        }
    }

    private fun postNotifications(token: String, notificationsRequest: NotificationsRequest) {
        RetrofitClient.apiConfig.postNotifications(token, notificationsRequest)
            .enqueue(object : retrofit2.Callback<NotificationsResponse> {
                override fun onResponse(
                    call: Call<NotificationsResponse>,
                    response: Response<NotificationsResponse>
                ) {
                    val it = response.isSuccessful
                    if (!it) {
                        return
                    }
                    Toast.makeText(this@MainActivity, "Thanh cong", Toast.LENGTH_SHORT).show()
                    Log.e("asdsdsda", "onResponse:${response.body()} ")
                }

                override fun onFailure(call: Call<NotificationsResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "$t", Toast.LENGTH_SHORT).show()
                    Log.e("TAG", "onFailure: $t")
                }

            })
    }
}
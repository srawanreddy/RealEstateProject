package com.example.sravanreddy.realestateproject.view.activity

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.sravanreddy.realestateproject.R
import com.example.sravanreddy.realestateproject.common.Constants
import com.firebase.client.ChildEventListener
import com.firebase.client.DataSnapshot
import com.firebase.client.Firebase
import com.firebase.client.FirebaseError

class SellerChat : AppCompatActivity() {
    private lateinit var messageBox : EditText
    private lateinit var sendMessageButton : ImageButton
    private lateinit var reference_buyer : Firebase
    private lateinit var reference_seller : Firebase
    private lateinit var layout : LinearLayout
    private lateinit var layout_2 : RelativeLayout
    private lateinit var scrollView : ScrollView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seller_chat)

            Firebase.setAndroidContext(this)
        reference_buyer = Firebase("https://realestateproject-882e2.firebaseio.com/"+"Buyer_seller")
        reference_seller = Firebase("https://realestateproject-882e2.firebaseio.com/"+"seller_buyer")

        layout = findViewById(R.id.layout1_seller)
        layout_2 = findViewById(R.id.layout2_seller)
        scrollView = findViewById(R.id.scrollView_seller)
        messageBox = findViewById(R.id.et_messagebox_chat_window_seller)
        sendMessageButton = findViewById(R.id.button_sendMessage_chat_window_seller)
        sendMessageButton.setOnClickListener(this::onClickListener)
        reference_seller.addChildEventListener(object : ChildEventListener {
            override fun onCancelled(p0: FirebaseError?) {

            }

            override fun onChildMoved(p0: DataSnapshot?, p1: String?) {

            }

            override fun onChildChanged(p0: DataSnapshot?, p1: String?) {

            }

            override fun onChildAdded(p0: DataSnapshot?, p1: String?) {
                val map = p0!!.getValue(Map::class.java)
                val message = map.get("Message").toString()
                val userName = map.get("User").toString()

                if (userName == Constants.USERNAME) {
                    addMessageBox(message, 1)
                } else {
                    notifyUser()
                    addMessageBox(message, 2)
                }
            }

            override fun onChildRemoved(p0: DataSnapshot?) {
            }
        })
    }


    private fun notifyUser() {
        val mBuilder = NotificationCompat.Builder(this.applicationContext, "notify_001")
        // val ii = Intent(getApplicationContext(), RootActivity::class.java)
        //  val pendingIntent = PendingIntent.getActivity(mContext, 0, ii, 0)

        val intent = Intent(this, PropertyDetails::class.java)
        intent.putExtra("Called From", 1)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT)


        val bigText = NotificationCompat.BigTextStyle()
        //bigText.bigText(verseurl)
        bigText.setBigContentTitle("1 New Notification")
        bigText.setSummaryText("New Notification From Realestate")

        mBuilder.setContentIntent(pendingIntent)
        mBuilder.setSmallIcon(R.drawable.ic_comment_black_24dp)
        mBuilder.setContentTitle("1 New Notification")
        mBuilder.setContentText("New Notification From Realestate")
        mBuilder.priority = Notification.PRIORITY_MAX
        mBuilder.setStyle(bigText)

        val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("notify_001",
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT)
            mNotificationManager.createNotificationChannel(channel)
        }

        mNotificationManager.notify(0, mBuilder.build())
    }

    private fun addMessageBox( message : String, type: Int) {
        val textView = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            TextView(this)
        } else {
            TODO("VERSION.SDK_INT < M")
        }
        textView.setText(message)

        val lp2 = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        lp2.weight = 1.0f
        textView.setBackgroundResource(R.drawable.message_snippet_backgorund);
        textView.setPadding(20, 10, 20, 10)

        textView.setTextColor(resources.getColor(R.color.colorWhite))
        textView.textSize = 20.0f
        if (type === 2) {
            lp2.gravity = Gravity.RIGHT
        } else {
            lp2.gravity = Gravity.LEFT

        }
        lp2.setMargins(8,8,8,8)
        textView.layoutParams = lp2
        layout.addView(textView)
        scrollView.fullScroll(View.FOCUS_DOWN)
    }
    public fun onClickListener(view : View?){
        if(!messageBox.text.toString().isEmpty()){
            val map = HashMap<String, String>()
            map.put("Message", messageBox.getText().toString());
            map.put("User", Constants.SELLERNAME);
            reference_buyer.push().setValue(map)
            reference_seller.push().setValue(map)
            messageBox.setText("")
            //fab.setImageDrawable(getDrawable(R.drawable.ic_close_black_24dp))
            //fab.setBackgroundColor(resources.getColor(R.color.colorPrimaryDark))

        }
    }
}

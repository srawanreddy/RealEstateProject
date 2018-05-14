package com.example.sravanreddy.realestateproject.view.fragment

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.example.sravanreddy.realestateproject.R
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.view.Gravity
import android.widget.*
import com.example.sravanreddy.realestateproject.common.Constants
import com.example.sravanreddy.realestateproject.view.activity.PropertyDetails
import com.firebase.client.ChildEventListener
import com.firebase.client.DataSnapshot
import com.firebase.client.Firebase
import com.firebase.client.FirebaseError


class ChatDialogFragment : DialogFragment() {
    private lateinit var messageBox : EditText
    private lateinit var sendMessageButton : ImageButton
    private lateinit var reference_buyer : Firebase
    private lateinit var reference_seller : Firebase
    private lateinit var layout : LinearLayout
    private lateinit var layout_2 : RelativeLayout
    private lateinit var scrollView : ScrollView
    private lateinit var closeDialogButton : ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Firebase.setAndroidContext(context)
        }
        reference_buyer = Firebase("https://realestateproject-882e2.firebaseio.com/"+"Buyer_seller")
        reference_seller = Firebase("https://realestateproject-882e2.firebaseio.com/"+"seller_buyer")

    }

    override fun onResume() {
        super.onResume()
        dialog.window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
       var view : View = inflater!!.inflate(R.layout.dialog_fragment_chat_window, container, false)
        var window : Window = dialog.window
        window.setGravity(Gravity.TOP or Gravity.LEFT)
        val params = window.attributes
        params.x = 20
        params.y = 1450
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.setBackgroundDrawable(context.getDrawable(R.drawable.layout_background))
        }
        params.dimAmount = 0f
        window.attributes = params as android.view.WindowManager.LayoutParams
        layout = view.findViewById(R.id.layout1)
        layout_2 = view.findViewById(R.id.layout2)
        scrollView = view.findViewById(R.id.scrollView)
        messageBox = view.findViewById(R.id.et_messagebox_chat_window)
        sendMessageButton = view.findViewById(R.id.button_sendMessage_chat_window)
        closeDialogButton = view.findViewById(R.id.imageButton_closeButton)

        closeDialogButton.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                dismiss()
            }
        })
        sendMessageButton.setOnClickListener(this::onClickListener)
        reference_buyer.addChildEventListener(object : ChildEventListener{
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
        return view
    }

    private fun notifyUser() {
        val mBuilder = NotificationCompat.Builder(activity.applicationContext, "notify_001")
        // val ii = Intent(getApplicationContext(), RootActivity::class.java)
        //  val pendingIntent = PendingIntent.getActivity(mContext, 0, ii, 0)

        val intent = Intent(activity.baseContext, PropertyDetails::class.java)
        intent.putExtra("Called From", 1)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(activity.baseContext, 0 /* Request code */, intent,
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

        val mNotificationManager = activity.baseContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


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
            TextView(context)
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
        if (type === 1) {
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
        map.put("User", Constants.USERNAME);
        reference_buyer.push().setValue(map)
        reference_seller.push().setValue(map)
        messageBox.setText("")
        //fab.setImageDrawable(getDrawable(R.drawable.ic_close_black_24dp))
        //fab.setBackgroundColor(resources.getColor(R.color.colorPrimaryDark))

    }
    }
}
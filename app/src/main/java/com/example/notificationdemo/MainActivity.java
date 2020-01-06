package com.example.notificationdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Person;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName() ;
    private static  NotificationManager nm;
    private static int NOTIFICATION_ID = 0x123;
    private static String CHNALE_ID = "lwl";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nm = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        String name = "测试 Chanel";
        NotificationChannel channel = new NotificationChannel(CHNALE_ID,name,NotificationManager.IMPORTANCE_HIGH);
        channel.setDescription("测试Channel 的描述信息");
        channel.enableLights(true);
        channel.setLightColor(Color.RED);
        channel.enableVibration(true);

        nm.createNotificationChannel(channel);
    }

    public void send(View v){
        Intent intent = new Intent(MainActivity.this,OtherActivity.class);

        PendingIntent pi = PendingIntent.getActivity(MainActivity.this,
                                                0,intent,0);

        Person p = new Person.Builder()
                        .setName("孙悟空")
                        .setIcon(Icon.createWithResource(this,
                                                        R.drawable.ic_launcher_background))
                        .build();
        Notification.MessagingStyle messagingStyle = new Notification.MessagingStyle(p);
        messagingStyle.setConversationTitle("一条通知");
        Notification.MessagingStyle.Message message = new Notification.MessagingStyle.Message(
                                                          "恭喜你加薪了",
                                                            System.currentTimeMillis(),p);
        Notification.MessagingStyle.Message message2 = new Notification.MessagingStyle.Message(
                "恭喜你JIAN薪了",
                System.currentTimeMillis(),p);
        messagingStyle.addMessage(message);
        messagingStyle.addMessage(message2);
        Notification notify = new Notification.Builder(MainActivity.this,CHNALE_ID)
//                              .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setStyle(messagingStyle)
                .setContentIntent(pi)
                .build();
        nm.notify(NOTIFICATION_ID,notify);

    }
    public void del(View v){
        nm.cancel(NOTIFICATION_ID);
    }

}

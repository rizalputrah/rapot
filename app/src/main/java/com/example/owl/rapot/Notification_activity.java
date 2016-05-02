package com.example.owl.rapot;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Notification_activity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_activity);
    }

    public void createNotification(View view){
        Intent intent = new Intent(this,Notification_activity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(),intent,0);

        Notification notification = new Notification.Builder(this)
                .setContentTitle("New Mail From" + "test@gmail.com")
                .setContentText("Subject").setSmallIcon(R.drawable.book)
                .setContentIntent(pendingIntent)
                .addAction(R.drawable.user,"Contact",pendingIntent).build();
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        nm.notify(0,notification);
    }
}

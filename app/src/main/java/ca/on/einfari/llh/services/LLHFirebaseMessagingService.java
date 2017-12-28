/*
    LLHFirebaseMessagingService.java
    Final Project

    Revision History:
        Gonzalo Ramos Zúñiga, 2017.12.26: Created
 */

package ca.on.einfari.llh.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import ca.on.einfari.llh.R;
import ca.on.einfari.llh.activities.QuoteListActivity;

public class LLHFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getNotification() != null) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(
                    getApplicationContext()).setAutoCancel(true).setContentIntent(PendingIntent.
                    getActivity(getApplicationContext(), 0, new Intent(getApplicationContext(),
                            QuoteListActivity.class), PendingIntent.FLAG_ONE_SHOT)).
                    setContentTitle("LLH").setContentText(remoteMessage.getNotification().
                    getBody()).setSmallIcon(R.drawable.ic_stat_name).setSound(RingtoneManager.
                    getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
            NotificationManager notificationManager = (NotificationManager) getSystemService(
                    Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0, builder.build());
        }
    }

}
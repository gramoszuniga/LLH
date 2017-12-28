/*
    LLHFirebaseInstanceIdService.java
    Final Project

    Revision History:
        Gonzalo Ramos Zúñiga, 2017.12.25: Created
 */

package ca.on.einfari.llh.services;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import ca.on.einfari.llh.preferences.LLHSharedPreferences;

public class LLHFirebaseInstanceIdService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        LLHSharedPreferences.getSharedPreferences(getApplicationContext()).
                setRegistrationToken(FirebaseInstanceId.getInstance().getToken());
    }

}
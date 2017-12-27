/*
    LLHSharedPreferences.java
    Final Project

    Revision History:
        Gonzalo Ramos Zúñiga, 2017.12.25: Created
 */

package ca.on.einfari.llh.preferences;

import android.content.Context;
import android.content.SharedPreferences;

public class LLHSharedPreferences {

    private static final String SHARED_PREFERENCES_NAME = "LLHSharedPreferences";
    private static final String REGISTRATION_TOKEN = "registrationToken";

    private static Context context;
    private static LLHSharedPreferences instance;

    private LLHSharedPreferences(Context context) {
        this.context = context;
    }

    public static LLHSharedPreferences getSharedPreferences(Context context) {
        if (instance == null) {
            instance = new LLHSharedPreferences(context);
        }
        return instance;
    }

    public void setRegistrationToken(String registrationToken) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(REGISTRATION_TOKEN, registrationToken);
        editor.apply();
    }

    public String getRegistrationToken() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME,
                Context.MODE_PRIVATE);
        return sharedPreferences.getString(REGISTRATION_TOKEN, null);
    }

}
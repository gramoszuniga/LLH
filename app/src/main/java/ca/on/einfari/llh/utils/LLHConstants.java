/*
    Constants.java
    Final Project

    Revision History:
        Gonzalo Ramos Zúñiga, 2017.12.27: Created
 */

package ca.on.einfari.llh.utils;

import okhttp3.MediaType;

public class LLHConstants {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final String FCM_LEGACY_SERVER_KEY = "AIzaSyDNGCrlj3PhxOf6ew8wsKDfzpJ3tOd3xqU";
    public static final String FCM_API = "https://fcm.googleapis.com/fcm/send";
    public static final double FENCE_SECTION_DIVISOR = 8;
    public static final int FENCE_BOARDS_FACTOR = 18;
    public static final int TWO_BY_FOURS_FACTOR = 2;
    public static final int TWO_BY_SIXES_LATTICE_FACTOR = 4;
    public static final int TWO_BY_SIXES_FACTOR = 2;
    public static final int FENCE_BRACKETS_FACTOR = 4;
    public static final double BUILDING_FORMS_DIVISOR = 2;
    public static final int CONCRETE_FACTOR = 2;

}
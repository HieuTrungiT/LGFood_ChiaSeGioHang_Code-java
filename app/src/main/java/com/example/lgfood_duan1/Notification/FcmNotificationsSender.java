package com.example.lgfood_duan1.Notification;

import android.app.Activity;
import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.lgfood_duan1.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FcmNotificationsSender  {

    String userFcmToken;
    String title;
    String body;
    String img;
    Context mContext;
    Activity mActivity;


    private RequestQueue requestQueue;
    private final String postUrl = "https://fcm.googleapis.com/fcm/send";
    private final String fcmServerKey = "AAAA2tr6fpc:APA91bH6C_O8AIiJaLbmpvT49UQVdkdTB0r1_zV6NDx_J0fLNShwGDW_3HDjibwA3901Xr1Rhp8wQnnnhLLY4bwcF_4HpWRI8r0zZBHXjhv9t-H5n8Y9_nLFocgVoWgAGCxXndHWPIsO";

    public FcmNotificationsSender(String userFcmToken, String title, String body, String img, Context mContext, Activity mActivity) {
        this.userFcmToken = userFcmToken;
        this.title = title;
        this.body = body;
        this.img = img;
        this.mContext = mContext;
        this.mActivity = mActivity;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void SendNotifications() {

        requestQueue = Volley.newRequestQueue(mActivity);
        JSONObject mainObj = new JSONObject();
        try {
            mainObj.put("to", userFcmToken);
            JSONObject notiObject = new JSONObject();
            notiObject.put("title", title);
            notiObject.put("body", body);
            notiObject.put("icon", "heart"); // enter icon that exists in drawable only
            notiObject.put("image_sp",img);


            mainObj.put("notification", notiObject);


            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, postUrl, mainObj, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    // code run is got response

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // code run is got error

                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {


                    Map<String, String> header = new HashMap<>();
                    header.put("content-type", "application/json");
                    header.put("authorization", "key=" + fcmServerKey);
                    return header;


                }
            };
            requestQueue.add(request);


        } catch (JSONException e) {
            e.printStackTrace();
        }




    }
}

package vn.edu.usth.touristtracetracking.storage;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import vn.edu.usth.touristtracetracking.User;

public class SharePrefManager {

    private static final String SHARED_PREF_NAME = "my_shared_preff";
    private static SharePrefManager mInstance;
    private Context mContext;

    private SharePrefManager(Context mContext){
        this.mContext = mContext;
    }

    public static synchronized SharePrefManager getInstance(Context mContext){
        if(mInstance == null ){
            mInstance = new SharePrefManager(mContext);
        }
        return mInstance;
    }

    public void saveUser(User user) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Log.i("ABCDE", user.getFirstName() + " from saveUser()");

        editor.putInt("id", user.getId());
        editor.putString("firstName", user.getFirstName());
        editor.putString("lastName", user.getLastName());
        editor.putString("birthday", user.getBirthday());
        editor.putString("city", user.getCity());
        editor.putString("country", user.getCountry());
        editor.putString("nationality", user.getNationality());
        editor.putString("email", user.getEmail());
        editor.putString("phone", user.getPhone());

        editor.apply();
    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        if (sharedPreferences.getInt("id", -1) != -1){
            return true;
        }
        return false;
    }

    public User getUser() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        User user = new User(
                sharedPreferences.getInt("id", -1),
                sharedPreferences.getString("firstName", null),
                sharedPreferences.getString("lastName", null),
                sharedPreferences.getString("birthday", null),
                sharedPreferences.getString("city", null),
                sharedPreferences.getString("country", null),
                sharedPreferences.getString("nationality", null),
                sharedPreferences.getString("email", null),
                sharedPreferences.getString("phone", null)
        );

        Log.i("ABCDE", user.getFirstName() + " from getUser()");
        return user;
    }

    public void clear() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.clear();
        editor.apply();
    }
}
package foodzamo.user.com.shikshatask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import foodzamo.user.com.shikshatask.Admin.FirstActivity;
import foodzamo.user.com.shikshatask.Employee.EmployeeLocation;
import foodzamo.user.com.shikshatask.Employee.TrackLocation;
import foodzamo.user.com.shikshatask.User.EnterAddress;
import foodzamo.user.com.shikshatask.User.Login;

public class SplashScreen extends Activity {
    private FirebaseAuth firebaseAuth;
    private FirebaseUser mFirebaseUser;

    SharedPreferences sharedpreferences_full_address;
    public static final String mypreference_full_address = "mypreference_full_address";
    public static final String full_address = "full_address";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);    // Removes title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);    // Removes notification bar


        setContentView(R.layout.activity_splash_screen);
        firebaseAuth=FirebaseAuth.getInstance();
        mFirebaseUser = firebaseAuth.getCurrentUser();

        sharedpreferences_full_address = getSharedPreferences(mypreference_full_address,
                Context.MODE_PRIVATE);

        // Start timer and launch main activity
        IntentLauncher launcher = new IntentLauncher();
        launcher.start();
    }

    private class IntentLauncher extends Thread {
        @Override
        /**
         * Sleep for some time and than start new activity.
         */
        public void run() {
            try {
                // Sleeping
                Thread.sleep(3*1000);
            } catch (Exception e) {
                //Log.e(TAG, e.getMessage());
            }
            Intent intent;
            // Start main activity
           /*if(sharedpreferences_flag.contains(flag)) {
                Intent i = new Intent(getApplicationContext(), MerchActivity.class);
               i.putExtra("key", sharedpreferences_flag.getString(restname,""));
                startActivity(i);
            }
            else
            {
                intent = new Intent(SplashScreen.this, ListRestr.class);
                SplashScreen.this.startActivity(intent);
                SplashScreen.this.finish();
            }*/
           /* mFirebaseUser = firebaseAuth.getCurrentUser();
            if(mFirebaseUser==null)
              startActivity(new Intent(getApplicationContext(),Login.class));
            else {
               /* if(!sharedpreferences_full_address.getString(full_address,"").equals(""))
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                else
                startActivity(new Intent(getApplicationContext(), TrackLocation.class));
            }*/
            startActivity(new Intent(getApplicationContext(),Integrate.class));
        }
    }
}

package foodzamo.user.com.shikshatask.Employee;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import foodzamo.user.com.shikshatask.GPSTracking;
import foodzamo.user.com.shikshatask.Integrate;
import foodzamo.user.com.shikshatask.Location.FetchData;
import foodzamo.user.com.shikshatask.MainActivity;
import foodzamo.user.com.shikshatask.R;
import foodzamo.user.com.shikshatask.SplashScreen;
import foodzamo.user.com.shikshatask.User.Login;

public class TrackLocation extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {
    Button trac, not;
    GPSTracking gps;
    //Define a request code to send to Google Play services
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private double currentLatitude;
    private double currentLongitude;
    String large = "";


    SharedPreferences sharedpreferences_loc;
    public static final String mypreference_loc = "savedata";
    public static final String my_loc = "my_loc";
    public static final String my_cat = "my_cat";

    SharedPreferences sharedpreferences_number;
    public static final String mypreference_number = "savedata";
    public static final String my_number = "mynumber";
    public static final String my_name = "myname";

    double lattitude, longitude;
    FetchData fetchData;
    String mes;

    ProgressDialog progressDialog;
    TextView desc;

    Location loc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_location);

        trac = (Button) findViewById(R.id.show_my_location);
        not = (Button) findViewById(R.id.notifications);

        fetchData = new FetchData();
        desc = (TextView) findViewById(R.id.description);

        desc.setText("Location will be updated for every 30 seconds!!! U can also track your location by button clicking");


        progressDialog = new ProgressDialog(this);

        sharedpreferences_loc = getSharedPreferences(mypreference_loc,
                Context.MODE_PRIVATE);
        sharedpreferences_number = getSharedPreferences(mypreference_number,
                Context.MODE_PRIVATE);

        // check if GPS enabled
        mGoogleApiClient = new GoogleApiClient.Builder(getApplicationContext())
                // The next two lines tell the new client that “this” current class will handle connection stuff
                .addConnectionCallbacks(TrackLocation.this)
                .addOnConnectionFailedListener(TrackLocation.this)
                //fourth line adds the LocationServices API endpoint from GooglePlayServices
                .addApi(LocationServices.API)
                .build();

        // Create the LocationRequest object
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(3 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000);


        // Start timer and launch main activity
        IntentLauncher launcher = new IntentLauncher();
        launcher.start();


        not.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), EmployeeNotify.class));
            }
        });

        trac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String output = fetchData.getCurrentLocationViaJSON(TrackLocation.this, lattitude, longitude, progressDialog, sharedpreferences_loc, my_loc);

                SharedPreferences.Editor editor = sharedpreferences_loc.edit();
                editor.putString(my_cat, mes);
                editor.putString(my_loc, output);
                editor.commit();

                Toast.makeText(getApplicationContext(), "Current Location: " + output, Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        //Now lets connect to the API
        mGoogleApiClient.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(this.getClass().getSimpleName(), "onPause()");

        //Disconnect from API onPause()
        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            mGoogleApiClient.disconnect();
        }


    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        //Log.d("values",large);


        //Toast.makeText(this, currentLatitude + " \n " + currentLongitude + "", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {


        //Log.d("values",large);


        //Toast.makeText(this, currentLatitude + " WORKS " + currentLongitude + "", Toast.LENGTH_LONG).show();
        //Toast.makeText(getApplicationContext(),"Location Changed!",Toast.LENGTH_SHORT).show();


    }



    private class IntentLauncher extends Thread {
        @Override
        /**
         * Sleep for some time and than start new activity.
         */
        public void run() {
            try {
                // Sleeping
                Thread.sleep(10 * 1000);
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

            if (ActivityCompat.checkSelfPermission(TrackLocation.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(TrackLocation.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);



            if (location == null) {
               // LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

            } else {
                //If everything went fine lets get latitude and longitude
                currentLatitude = location.getLatitude();
                currentLongitude = location.getLongitude();

                large=large+"Latitude: "+currentLatitude;
                large=large+"\nLongitude: "+currentLongitude+"\n";
                //text.setText(large);

                lattitude= currentLatitude;
                longitude= currentLongitude;
                loc=location;


                String output=fetchData.getCurrentLocationViaJSON(TrackLocation.this,lattitude,longitude,progressDialog,sharedpreferences_loc,my_loc);

                String details="Name: "+sharedpreferences_number.getString(my_name,"")+"\nNumber: "+sharedpreferences_number.getString(my_number,"");
                String url="https://shikshatask.firebaseio.com/Mechanics/Users/"+sharedpreferences_number.getString(my_number,"")+"/location";
                details=details+"\nCurrentLocation:\n"+output;

                final DatabaseReference db = FirebaseDatabase.getInstance().getReferenceFromUrl(url);

                db.setValue(details);
                // Start timer and launch main activity
                IntentLauncher launcher = new IntentLauncher();
                launcher.start();
                //Log.d("values",large);



                //Toast.makeText(getApplicationContext(), "Updated values\n"+lattitude+"\n"+longitude, Toast.LENGTH_LONG).show();
            }

        }
    }
    @Override
    public void onBackPressed()
    {
        finish();
        startActivity(new Intent(getApplicationContext(), Integrate.class));
    }
}

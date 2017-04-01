package foodzamo.user.com.shikshatask;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Cord extends Activity {
    Button getloc;
    TextView lati;
    TextView longi;
    TextView address;

    LocationManager location_manager;
    String getLatitude;
    String getLongitude;

    double x;
    double y;

    Geocoder geocoder;
    List<Address> addresses;
    Location loc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cord);

        getloc = (Button) findViewById(R.id.getlocation);
        lati = (TextView) findViewById(R.id.latitude);
        longi = (TextView) findViewById(R.id.longitude);
        address = (TextView) findViewById(R.id.address);
        location_manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        getloc.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
// TODO Auto-generated method stub

                LocationListener listner = new MyLocationListner();
                location_manager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER, 0, 0, listner);

            }
        });

    }

    public class MyLocationListner implements LocationListener {

        @SuppressWarnings("static-access")
        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        @Override
        public void onLocationChanged(Location location) {
// TODO Auto-generated method stub

            getLatitude = "" + location.getLatitude();
            getLongitude = "" + location.getLongitude();

            lati.setText(getLatitude);
            longi.setText(getLongitude);

            x = location.getLatitude();
            y = location.getLongitude();

            try {
                geocoder = new Geocoder(Cord.this, Locale.ENGLISH);
                addresses = geocoder.getFromLocation(x, y, 1);
                StringBuilder str = new StringBuilder();
                if (geocoder.isPresent()) {
                    Toast.makeText(getApplicationContext(),
                            "geocoder present", Toast.LENGTH_SHORT).show();
                    Address returnAddress = addresses.get(0);

                    String localityString = returnAddress.getLocality();
                    String city = returnAddress.getCountryName();
                    String region_code = returnAddress.getCountryCode();
                    String zipcode = returnAddress.getPostalCode();

                    str.append(localityString + "");
                    str.append(city + "" + region_code + "");
                    str.append(zipcode + "");

                    address.setText(str);
                    Toast.makeText(getApplicationContext(), str,
                            Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(),
                            "geocoder not present", Toast.LENGTH_SHORT).show();
                }

// } else {
// Toast.makeText(getApplicationContext(),
// "address not available", Toast.LENGTH_SHORT).show();
// }
            } catch (IOException e) {
// TODO Auto-generated catch block

                Log.e("tag", e.getMessage());
            }

        }

        @Override
        public void onProviderDisabled(String arg0) {
// TODO Auto-generated method stub

        }

        @Override
        public void onProviderEnabled(String arg0) {
// TODO Auto-generated method stub

        }

        @Override
        public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
// TODO Auto-generated method stub

        }

    }


}

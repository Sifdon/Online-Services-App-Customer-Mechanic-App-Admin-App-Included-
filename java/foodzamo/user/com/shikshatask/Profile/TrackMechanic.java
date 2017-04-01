package foodzamo.user.com.shikshatask.Profile;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import foodzamo.user.com.shikshatask.HomeActivity;
import foodzamo.user.com.shikshatask.R;

public class TrackMechanic extends AppCompatActivity {
TextView textView;
    ProgressDialog progressDialog;

    SharedPreferences sharedpreferences_number;
    public static final String mypreference_number = "savedata";
    public static final String my_number = "mynumber";
    public static final String my_name = "myname";

    String num="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_mechanic);

        progressDialog=new ProgressDialog(this);
        textView=(TextView)findViewById(R.id.mechanic_location);
        sharedpreferences_number = getSharedPreferences(mypreference_number,
                Context.MODE_PRIVATE);

       num=sharedpreferences_number.getString(my_number,"");

        progressDialog.setMessage("Fetching location...Please wait...");
        progressDialog.show();
        progressDialog.setCancelable(false);

        DatabaseReference databaseReference111= FirebaseDatabase.getInstance().
                getReferenceFromUrl("https://shikshatask.firebaseio.com/Users/"+num+"/myorders");
        databaseReference111.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String  x=(String) dataSnapshot.getValue();
                //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();

                if(x.equals("no orders!"))
                {
                  get_alert_one();
                }
                else
                {
                    DatabaseReference databaseReference11= FirebaseDatabase.getInstance().
                            getReferenceFromUrl("https://shikshatask.firebaseio.com/Users/"+num+"/track_service");
                    databaseReference11.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String  x2=(String) dataSnapshot.getValue();
                            //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();




                            if(x2.equals("no")){
                                get_alert_two();
                            }
                            else
                            {

                                DatabaseReference databaseReference111= FirebaseDatabase.getInstance().
                                        getReferenceFromUrl("https://shikshatask.firebaseio.com/Mechanics/Users/"+x2+"/location");
                                databaseReference111.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        String  x=(String) dataSnapshot.getValue();
                                        //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();


                                         textView.setText(x);
                                        progressDialog.dismiss();
                                        //textView.setText("My Notifications");
                                        //notification.setText(x);
                                        //notifications_icon.setImageResource(R.drawable.notifications_bell);

                                        //textView.setText(s);
                                        //progressDialog.dismiss();
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                        //textView.setText("Some error occured please try again!");
                                    }
                                });
                            }

                            //notifications.setText(x);


                            //textView.setText("My Notifications");
                            //notification.setText(x);
                            //notifications_icon.setImageResource(R.drawable.notifications_bell);

                            //textView.setText(s);
                            //progressDialog.dismiss();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            //textView.setText("Some error occured please try again!");
                        }
                    });
                }



                //textView.setText("My Notifications");
                //notification.setText(x);
                //notifications_icon.setImageResource(R.drawable.notifications_bell);

                //textView.setText(s);
                //progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //textView.setText("Some error occured please try again!");
            }
        });


    }

    public void get_alert_one()
    {
        AlertDialog alertDialog = new AlertDialog.Builder(
                TrackMechanic.this).create();

        // Setting Dialog Title
        alertDialog.setTitle("Sorry!");
        alertDialog.setCancelable(false);
        // Setting Dialog Message
        alertDialog.setMessage("Seems like you did not request for a service. Please request for one!");

        // Setting Icon to Dialog
        alertDialog.setIcon(R.drawable.tick);

        // Setting OK Button
        alertDialog. setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }
    public void get_alert_two()
    {
        AlertDialog alertDialog = new AlertDialog.Builder(
                TrackMechanic.this).create();

        // Setting Dialog Title
        alertDialog.setTitle("Sorry!");
        alertDialog.setCancelable(false);
        // Setting Dialog Message
        alertDialog.setMessage("Currently we did not assign any mechanic to you. Please wait for sometime. You will get notified with Mechanic details.");

        // Setting Icon to Dialog
        alertDialog.setIcon(R.drawable.tick);

        // Setting OK Button
        alertDialog. setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }
}

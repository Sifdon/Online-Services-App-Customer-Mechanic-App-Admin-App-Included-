package foodzamo.user.com.shikshatask.Profile;

import android.app.ProgressDialog;
import android.content.Context;
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
import foodzamo.user.com.shikshatask.MainActivity;
import foodzamo.user.com.shikshatask.R;

public class Notifications extends AppCompatActivity {
TextView notifications;
    ProgressDialog progressDialog;

    SharedPreferences sharedpreferences_number;
    public static final String mypreference_number = "savedata";
    public static final String my_number = "mynumber";
    public static final String my_name = "myname";

    HomeActivity homeActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        progressDialog=new ProgressDialog(this);
        notifications=(TextView)findViewById(R.id.notifications);
        homeActivity=new HomeActivity();

        sharedpreferences_number = getSharedPreferences(mypreference_number,
                Context.MODE_PRIVATE);

        String num=sharedpreferences_number.getString(my_number,"");

        progressDialog.setMessage("Loading notifications...");
        progressDialog.show();
        DatabaseReference databaseReference111= FirebaseDatabase.getInstance().
                getReferenceFromUrl("https://shikshatask.firebaseio.com/Users/"+num+"/1Notifications");
        databaseReference111.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String  x=(String) dataSnapshot.getValue();
                //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                progressDialog.dismiss();

            notifications.setText(x);


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
    @Override
    public void onBackPressed()
    {
        finish();
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
    }


}

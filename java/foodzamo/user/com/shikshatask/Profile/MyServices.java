package foodzamo.user.com.shikshatask.Profile;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import foodzamo.user.com.shikshatask.R;

public class MyServices extends AppCompatActivity {
TextView textView;

    SharedPreferences sharedpreferences_number;
    public static final String mypreference_number = "savedata";
    public static final String my_number = "mynumber";
    public static final String my_name = "myname";

    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_services);

        textView=(TextView)findViewById(R.id.textView);
        sharedpreferences_number = getSharedPreferences(mypreference_number,
                Context.MODE_PRIVATE);

        String num=sharedpreferences_number.getString(my_number,"");

        progressDialog=new ProgressDialog(this);

        progressDialog.setMessage("Loading your requests...");
        progressDialog.show();
        progressDialog.setCancelable(true);

        DatabaseReference databaseReference111= FirebaseDatabase.getInstance().
                getReferenceFromUrl("https://shikshatask.firebaseio.com/Users/"+num+"/myorders");
        databaseReference111.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String  x=(String) dataSnapshot.getValue();
                //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();
                progressDialog.dismiss();

                textView.setText(x);


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
}

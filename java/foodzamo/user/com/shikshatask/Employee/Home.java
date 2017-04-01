package foodzamo.user.com.shikshatask.Employee;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import foodzamo.user.com.shikshatask.R;

public class Home extends AppCompatActivity {
Button button_show_my_loc,notify;

    SharedPreferences sharedpreferences_number;
    public static final String mypreference_number = "savedata";
    public static final String my_number = "mynumber";
    public static final String my_name = "myname";

    SharedPreferences sharedpreferences_loc;
    public static final String mypreference_loc = "savedata";
    public static final String my_loc = "my_loc";
    public static final String my_cat = "my_cat";

    ProgressDialog progressDialog;
    String data="",cat="",url="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        button_show_my_loc=(Button)findViewById(R.id.show_my_location);
        notify=(Button)findViewById(R.id.notifications);
        sharedpreferences_number = getSharedPreferences(mypreference_number,
                Context.MODE_PRIVATE);

        sharedpreferences_loc = getSharedPreferences(mypreference_loc,
                Context.MODE_PRIVATE);

        progressDialog=new ProgressDialog(this);

        /*categories.add("Electrician");
        categories.add("Plumber");
        categories.add("Carpenter");
        categories.add("AC Repair");
        categories.add("Instant Driver");
        categories.add("Maid on Demand");
        categories.add("Car Cleaning");
        categories.add("Home Cleaning");*/

        cat=sharedpreferences_loc.getString(my_cat,"");

        if(cat.equals("Electrician"))
        url="https://shikshatask.firebaseio.com/Employees/Electrician/details";
        if(cat.equals("Plumber"))
            url="https://shikshatask.firebaseio.com/Employees/Plumber/details";
        if(cat.equals("Carpenter"))
            url="https://shikshatask.firebaseio.com/Employees/Carpenter/details";
        if(cat.equals("AC Repair"))
            url="https://shikshatask.firebaseio.com/Employees/ACRepair/details";
        if(cat.equals("Instant Driver"))
            url="https://shikshatask.firebaseio.com/Employees/InstantDriver/details";
        if(cat.equals("Maid on Demand"))
            url="https://shikshatask.firebaseio.com/Employees/MaidOnService/details";
        if(cat.equals("Car Cleaning"))
            url="https://shikshatask.firebaseio.com/Employees/CarCleaning/details";
        if(cat.equals("Home Cleaning"))
            url="https://shikshatask.firebaseio.com/Employees/HomeCleaning/details";


        data="Name: "+sharedpreferences_number.getString(my_name,"")+"\nNumber: "+sharedpreferences_number.getString(my_number,"");
        data=data+"\nCurrent Location:\n"+sharedpreferences_loc.getString(my_loc,"");
        String num=sharedpreferences_number.getString(my_number,"");


        final DatabaseReference db1= FirebaseDatabase.getInstance().getReference().child("Mechanics").child("Users").child(num);
        db1.child("location").setValue(data);

        progressDialog.setMessage("Adding your details to our database....");
        progressDialog.show();
        progressDialog.setCancelable(false);


        final DatabaseReference db111= FirebaseDatabase.getInstance().
                getReferenceFromUrl(url);
        db111.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String  x=(String) dataSnapshot.getValue();
                //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();

              String fin=data+"\n--------------\n"+x;
                db111.setValue(fin);

                progressDialog.dismiss();

                startActivity(new Intent(getApplicationContext(),TrackLocation.class));


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


        //String s=sharedpreferences_loc.getString(my_cat,"")+sharedpreferences_loc.getString(my_loc,"");

        //Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();

        button_show_my_loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),sharedpreferences_loc.getString(my_loc,""),Toast.LENGTH_SHORT).show();
            }
        });





    }
}

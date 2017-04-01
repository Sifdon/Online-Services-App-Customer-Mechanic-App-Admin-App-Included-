package foodzamo.user.com.shikshatask.Services;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import foodzamo.user.com.shikshatask.R;

public class SendRequest extends AppCompatActivity {
EditText editText;
    Button button;

    String pos;

    String data="";

    String[] role={"Electrician Needed",
                    "Plumber Needed","Carpenter Needed","AC Repair",
    "Instant Driver","Maid On Service","Car Cleaning","Home Cleaning"};

    SharedPreferences sharedpreferences_number;
    public static final String mypreference_number = "savedata";
    public static final String my_number = "mynumber";
    public static final String my_name = "myname";

    SharedPreferences sharedpreferences_full_address;
    public static final String mypreference_full_address = "mypreference_full_address";
    public static final String full_address = "full_address";

    ProgressDialog progressDialog;

    String req="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_request);

        button=(Button)findViewById(R.id.place_request);
        editText=(EditText)findViewById(R.id.description);
        progressDialog=new ProgressDialog(this);

        sharedpreferences_number = getSharedPreferences(mypreference_number,
                Context.MODE_PRIVATE);

        sharedpreferences_full_address = getSharedPreferences(mypreference_full_address,
                Context.MODE_PRIVATE);

        Bundle bundle=getIntent().getExtras();
        pos=bundle.getString("key");

       int x= Integer.parseInt(pos);

        data="Problem: "+role[x]+"\n";

        req=data;
        data=data+"Name: "+sharedpreferences_number.getString(my_name,"")+"\nPhone Number: "+sharedpreferences_number.getString(my_number,"");
        data=data+"\nAddress:\n"+sharedpreferences_full_address.getString(full_address,"");


        DatabaseReference databaseReference111= FirebaseDatabase.getInstance().
                getReferenceFromUrl("https://shikshatask.firebaseio.com/dummy");
        databaseReference111.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String  x=(String) dataSnapshot.getValue();
                //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();




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

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(editText.getText().toString().length()==0)
                {
                    Toast.makeText(getApplicationContext(),"Please enter something!",Toast.LENGTH_SHORT).show();
                    return;
                }
                data=data+"\nProblem Description:\n"+editText.getText().toString();

                req=req+"Problem Description:\n"+editText.getText().toString();

                progressDialog.setMessage("Sending your request please wait...");
                progressDialog.setCancelable(false);
                progressDialog.show();

                DatabaseReference databaseReference111= FirebaseDatabase.getInstance().
                        getReferenceFromUrl("https://shikshatask.firebaseio.com/Services/data");
                databaseReference111.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String  x=(String) dataSnapshot.getValue();
                        //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();

                req=data;
               data=data+"\n---------------------------------------\n";

                        data=data+x;

                        String num=sharedpreferences_number.getString(my_number,"");

                        DatabaseReference dbb=FirebaseDatabase.getInstance().getReferenceFromUrl("https://shikshatask.firebaseio.com/Users/"+num+"/myorders");
                        dbb.setValue(req);

                        DatabaseReference db=FirebaseDatabase.getInstance().getReferenceFromUrl("https://shikshatask.firebaseio.com/Services/data");
                        db.setValue(data);

                        DatabaseReference db2=FirebaseDatabase.getInstance().getReferenceFromUrl("https://shikshatask.firebaseio.com/Users/"+num+"/1Notifications");
                        db2.setValue("Thanks for your request. We will get back to you soon!");

                        progressDialog.dismiss();


                        startActivity(new Intent(getApplicationContext(),ThankYou.class));
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
        });






    }
}

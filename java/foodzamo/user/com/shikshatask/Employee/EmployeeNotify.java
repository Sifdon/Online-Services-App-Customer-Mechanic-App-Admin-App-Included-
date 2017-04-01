package foodzamo.user.com.shikshatask.Employee;

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

public class EmployeeNotify extends AppCompatActivity {
TextView textView;

    SharedPreferences sharedpreferences_number;
    public static final String mypreference_number = "savedata";
    public static final String my_number = "mynumber";
    public static final String my_name = "myname";

    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_notify);

        textView=(TextView)findViewById(R.id.notifications);
        progressDialog=new ProgressDialog(this);
        sharedpreferences_number = getSharedPreferences(mypreference_number,
                Context.MODE_PRIVATE);

        progressDialog.setMessage("Loading notifications....");
        progressDialog.show();
        progressDialog.setCancelable(false);

        String  num=sharedpreferences_number.getString(my_number,"");

        String url="https://shikshatask.firebaseio.com/Mechanics/Users/"+num+"/1Notifications";


        DatabaseReference databaseReference111= FirebaseDatabase.getInstance().
                getReferenceFromUrl(url);
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
}

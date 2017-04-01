package foodzamo.user.com.shikshatask.Admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import foodzamo.user.com.shikshatask.R;

public class SeeRequests extends AppCompatActivity {
TextView textView;

    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_requests);

        textView=(TextView)findViewById(R.id.see_requests);
        progressDialog=new ProgressDialog(this);

        progressDialog.setMessage("Loading requests...");
        progressDialog.show();
        progressDialog.setCancelable(false);

        DatabaseReference databaseReference111= FirebaseDatabase.getInstance().
                getReferenceFromUrl("https://shikshatask.firebaseio.com/Services/data");
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
    @Override
    public void onBackPressed()
    {
        startActivity(new Intent(getApplicationContext(),FirstActivity.class));
    }
}

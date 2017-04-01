package foodzamo.user.com.shikshatask.Admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import foodzamo.user.com.shikshatask.Employee.TrackLocation;
import foodzamo.user.com.shikshatask.R;

public class EmpList extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
String s="", url="";

    TextView textView;
    Button button;

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_list);

        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        textView=(TextView)findViewById(R.id.show_results);
        button=(Button)findViewById(R.id.go_next);
        progressDialog=new ProgressDialog(this);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Select Category");
        categories.add("Electrician");
        categories.add("Plumber");
        categories.add("Carpenter");
        categories.add("AC Repair");
        categories.add("Instant Driver");
        categories.add("Maid on Demand");
        categories.add("Car Cleaning");
        categories.add("Home Cleaning");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        spinner.setPrompt("Select Category");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(s.equals("")||s.equals("Select Category"))
                {
                    Toast.makeText(getApplicationContext(),"Please select category",Toast.LENGTH_SHORT).show();
                    return;
                }
                progressDialog.setMessage("Fetching results...");
                progressDialog.show();

                if(s.equals("Electrician"))
                    url="https://shikshatask.firebaseio.com/Employees/Electrician/details";
                if(s.equals("Plumber"))
                    url="https://shikshatask.firebaseio.com/Employees/Plumber/details";
                if(s.equals("Carpenter"))
                    url="https://shikshatask.firebaseio.com/Employees/Carpenter/details";
                if(s.equals("AC Repair"))
                    url="https://shikshatask.firebaseio.com/Employees/ACRepair/details";
                if(s.equals("Instant Driver"))
                    url="https://shikshatask.firebaseio.com/Employees/InstantDriver/details";
                if(s.equals("Maid on Demand"))
                    url="https://shikshatask.firebaseio.com/Employees/MaidOnService/details";
                if(s.equals("Car Cleaning"))
                    url="https://shikshatask.firebaseio.com/Employees/CarCleaning/details";
                if(s.equals("Home Cleaning"))
                    url="https://shikshatask.firebaseio.com/Employees/HomeCleaning/details";




                final DatabaseReference db111= FirebaseDatabase.getInstance().
                        getReferenceFromUrl(url);
                db111.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String  x=(String) dataSnapshot.getValue();
                        //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();

                        //String fin=data+"\n--------------\n"+x;
                        //db111.setValue(fin);
                        textView.setText(x);
                        progressDialog.dismiss();


                        //startActivity(new Intent(getApplicationContext(),TrackLocation.class));


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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        s=parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onBackPressed()
    {
        finish();
        startActivity(new Intent(getApplicationContext(),FirstActivity.class));
    }
}

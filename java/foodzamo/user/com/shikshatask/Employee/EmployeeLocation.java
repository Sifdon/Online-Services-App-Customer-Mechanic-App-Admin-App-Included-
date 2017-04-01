package foodzamo.user.com.shikshatask.Employee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import foodzamo.user.com.shikshatask.GPSTracking;
import foodzamo.user.com.shikshatask.MainActivity;
import foodzamo.user.com.shikshatask.R;

public class EmployeeLocation extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
String s="";

    Button button;
    GPSTracking gps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_location);

        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        button=(Button)findViewById(R.id.go_next);

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

                gps=new GPSTracking(EmployeeLocation.this);
                if(s.equals("")||s.equals("Select Category"))
                {
                    Toast.makeText(getApplicationContext(),"Select your category",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!gps.canGetLocation()) {
                    gps.showSettingsAlert();
                    return;
                }
                else
                    startActivity(new Intent(getApplicationContext(), MainActivity.class).putExtra("key",s));
                    //Toast.makeText(getApplicationContext(),"Ok",Toast.LENGTH_SHORT).show();

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
}

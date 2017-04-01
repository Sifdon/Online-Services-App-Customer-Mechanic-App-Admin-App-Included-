package foodzamo.user.com.shikshatask.Admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import foodzamo.user.com.shikshatask.R;

public class AssignWork extends AppCompatActivity {
EditText customer_number,employee_number;
    Button assign_work;

    String cust_number="",emp_number="";
    ProgressDialog progressDialog;

    String emp_data="",cust_data="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_work);

        customer_number=(EditText)findViewById(R.id.edit_customer_number);
        employee_number=(EditText)findViewById(R.id.edit_employee_number);
        assign_work=(Button)findViewById(R.id.button_assign_work);
        progressDialog=new ProgressDialog(this);

        assign_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cust_number=customer_number.getText().toString();
                emp_number=employee_number.getText().toString();

                if(cust_number.equals("")||emp_number.equals("")||cust_number.length()<10||emp_number.length()<10)
                {
                    Toast.makeText(getApplicationContext(),"Please fill the details",Toast.LENGTH_SHORT).show();
                    return;
                }

                progressDialog.setMessage("Assigning work...");
                progressDialog.show();
                progressDialog.setCancelable(false);

               String url1="https://shikshatask.firebaseio.com/Users/"+cust_number+"/myorders";

                final DatabaseReference db111= FirebaseDatabase.getInstance().
                        getReferenceFromUrl(url1);
                db111.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String  x=(String) dataSnapshot.getValue();
                        //Toast.makeText(MessageActivity.this,s,Toast.LENGTH_LONG).show();

                        //String fin=data+"\n--------------\n"+x;
                        //db111.setValue(fin);
                        //textView.setText(x);
                        String url2="https://shikshatask.firebaseio.com/Users/"+cust_number+"/track_service";
                        DatabaseReference db2= FirebaseDatabase.getInstance().
                                getReferenceFromUrl(url2);
                        db2.setValue(emp_number);

                        String url22="https://shikshatask.firebaseio.com/Mechanics/Users/"+emp_number+"/1Notifications";
                        DatabaseReference db22= FirebaseDatabase.getInstance().
                                getReferenceFromUrl(url22);
                        db22.setValue(x);

                        String  url5="https://shikshatask.firebaseio.com/Users/"+cust_number+"/1Notifications";
                        DatabaseReference db5= FirebaseDatabase.getInstance().
                                getReferenceFromUrl(url5);

                        db5.setValue("We have assigned a mechanic to you please track mechanic!");

                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Success!",Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(getApplicationContext(),FirstActivity.class));



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

package foodzamo.user.com.shikshatask.User;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import foodzamo.user.com.shikshatask.Employee.EmployeeLocation;
import foodzamo.user.com.shikshatask.Employee.Home;
import foodzamo.user.com.shikshatask.R;

public class OtpActivity extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    public static final String mypreference = "savedetails";
    public static final String name = "name";
    public static final String mail= "mail";
    public static final String number = "number";
    public static final String rand= "rand";

    String user_number="",user_mail="",user_password="",user_rand="";

    EditText editText_otp;
    Button button;
    ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference db;


    SharedPreferences sharedpreferences_number;
    public static final String mypreference_number = "savedata";
    public static final String my_number = "mynumber";
    public static final String my_name = "myname";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        sharedpreferences_number = getSharedPreferences(mypreference_number,
                Context.MODE_PRIVATE);

        String s=sharedpreferences.getString(number,"")+sharedpreferences.getString(mail,"")+
                sharedpreferences.getString(name,"")+sharedpreferences.getString(rand,"");

        //Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();

        progressDialog=new ProgressDialog(this);
        firebaseAuth=FirebaseAuth.getInstance();
        db= FirebaseDatabase.getInstance().getReference().child("Mechanics").child("Users");


        editText_otp=(EditText)findViewById(R.id.edit_text_otp);
        button=(Button)findViewById(R.id.button_register);




        user_number=sharedpreferences.getString(number,"");
        user_mail=sharedpreferences.getString(mail,"");
        user_password=sharedpreferences.getString(name,"");
        user_rand=sharedpreferences.getString(rand,"");

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

                if(isInternetOn()==0)
                {
                    Toast.makeText(getApplicationContext(),"No Connection!",Toast.LENGTH_SHORT).show();
                    return;
                }
                String x=editText_otp.getText().toString();

                if(editText_otp.getText().toString().length()==0)
                {
                    Toast.makeText(getApplicationContext(),"Please enter something",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(x.equals(user_rand))
                {
                    SharedPreferences.Editor editor = sharedpreferences_number.edit();

                    editor.putString(my_number, user_number);
                    editor.putString(my_name,user_password);
                    editor.commit();

                    registerUser();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Wrong OTP... Please try again!",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });




    }

    private void registerUser()
    {




        String pss="1234567890";
        //if validations are okk then proceed
        progressDialog.setMessage("Registering Employee");
        progressDialog.setCancelable(false);
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(user_mail,pss)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //user is successfully logged in
                            //we will start the profile activity here
                            ////right now lets display only toast
                            Toast.makeText(getApplicationContext(), "Successfully Registered!", Toast.LENGTH_SHORT).show();



                            final DatabaseReference current_data = db.child(user_number);


                            //display mail id and use mail id to know the user details or by firebase UID
                            //add data here

                            current_data.child("1Phone Number").setValue(user_number);
                            current_data.child("1Mail Id").setValue(user_mail);
                            current_data.child("1Notifications").setValue("You are an employee at our company");
                            current_data.child("1Name").setValue(user_password);
                            current_data.child("location").setValue("no");





                            final DatabaseReference phone_numbers_data = FirebaseDatabase.getInstance().getReference().child("PhoneNumbers");
                            phone_numbers_data.child(user_number).setValue("abc");

                            progressDialog.dismiss();


                            //startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            startActivity(new Intent(getApplicationContext(),EmployeeLocation.class));


                            //promo codes










                            //Toast.makeText(Login.this,s,Toast.LENGTH_SHORT).show();

                            //finish();
                            //Intent intent = new Intent(R.this, UserProfile.class);
                            //startActivity(intent);

                        } else {
                            if(task.getException().getMessage().equals("The email address is already in use by another account.")) {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Email already registered!", Toast.LENGTH_SHORT).show();

                            }
                            else
                            {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Network error!", Toast.LENGTH_SHORT).show();
                            }


                        }
                    }
                });

    }

    @Override
    public void onBackPressed()
    {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Go to Login Screen?");
        alertDialogBuilder
                .setMessage("Are you sure ?")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                SharedPreferences.Editor editor = sharedpreferences.edit();


                                editor.remove(name);
                                editor.remove(mail);
                                editor.remove(number);
                                editor.remove(rand);
                                editor.commit();

                                finish();

                                startActivity(new Intent(getApplicationContext(), Login.class));
                            }
                        })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }
    public final int isInternetOn() {

        // get Connectivity Manager object to check connection
        ConnectivityManager connec =
                (ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        // Check for network connections
        if ( connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {

            // if connected with internet

            //Toast.makeText(this, " Connected ", Toast.LENGTH_LONG).show();
            return 1;

        } else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {

            //Toast.makeText(this, " Not Connected ", Toast.LENGTH_LONG).show();
            return 0;
        }
        return 0;
    }
}

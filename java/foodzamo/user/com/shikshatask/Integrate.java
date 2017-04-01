package foodzamo.user.com.shikshatask;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import foodzamo.user.com.shikshatask.Admin.FirstActivity;
import foodzamo.user.com.shikshatask.Employee.TrackLocation;
import foodzamo.user.com.shikshatask.User.Login;

public class Integrate extends AppCompatActivity {
Button admin_app,mechanic_app;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser mFirebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integrate);

        admin_app=(Button)findViewById(R.id.admin_app);
        mechanic_app=(Button)findViewById(R.id.mechanic_app);
        firebaseAuth=FirebaseAuth.getInstance();
        mFirebaseUser = firebaseAuth.getCurrentUser();

        admin_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), FirstActivity.class));
            }
        });

        mechanic_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebaseUser = firebaseAuth.getCurrentUser();
                if(mFirebaseUser==null)
                    startActivity(new Intent(getApplicationContext(),Login.class));
                else {
               /* if(!sharedpreferences_full_address.getString(full_address,"").equals(""))
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                else*/
                    startActivity(new Intent(getApplicationContext(), TrackLocation.class));
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Exit Application?");
        alertDialogBuilder
                .setMessage("Are you sure ?")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                                homeIntent.addCategory(Intent.CATEGORY_HOME);
                                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(homeIntent);
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
}

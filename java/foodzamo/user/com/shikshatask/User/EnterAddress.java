package foodzamo.user.com.shikshatask.User;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import foodzamo.user.com.shikshatask.HomeActivity;
import foodzamo.user.com.shikshatask.MainActivity;
import foodzamo.user.com.shikshatask.Mechanic;
import foodzamo.user.com.shikshatask.R;

public class EnterAddress extends AppCompatActivity {
TextView other_details;
    EditText editText_address;
    Button button_save_address;

    SharedPreferences sharedpreferences_number;
    public static final String mypreference_number = "savedata";
    public static final String my_number = "mynumber";
    public static final String my_name = "myname";

    SharedPreferences sharedpreferences_full_address;
    public static final String mypreference_full_address = "mypreference_full_address";
    public static final String full_address = "full_address";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_address);

        other_details=(TextView)findViewById(R.id.other_details);
        editText_address=(EditText)findViewById(R.id.address);
        button_save_address=(Button)findViewById(R.id.save_address);

        sharedpreferences_number = getSharedPreferences(mypreference_number,
                Context.MODE_PRIVATE);

        sharedpreferences_full_address = getSharedPreferences(mypreference_full_address,
                Context.MODE_PRIVATE);

        String u_name=sharedpreferences_number.getString(my_name,"");
        String u_number=sharedpreferences_number.getString(my_number,"");

        other_details.setText("Name: "+u_name+"\nNumber: "+u_number);

        button_save_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(editText_address.getText().toString().length()<=10)
                {
                    Toast.makeText(getApplicationContext(),"Enter valid address",Toast.LENGTH_SHORT).show();
                    return;
                }


                SharedPreferences.Editor editor = sharedpreferences_full_address.edit();

                editor.putString(full_address,editText_address.getText().toString());
                editor.commit();





                Toast.makeText(getApplicationContext(),"Address Saved Successfully!",Toast.LENGTH_SHORT).show();

                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                //SharedPreferences.Editor editor=new E



            }
        });




    }
}

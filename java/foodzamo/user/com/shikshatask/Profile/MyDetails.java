package foodzamo.user.com.shikshatask.Profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import foodzamo.user.com.shikshatask.R;

public class MyDetails extends AppCompatActivity {
TextView textView;

    SharedPreferences sharedpreferences_number;
    public static final String mypreference_number = "savedata";
    public static final String my_number = "mynumber";
    public static final String my_name = "myname";


    SharedPreferences sharedpreferences_full_address;
    public static final String mypreference_full_address = "mypreference_full_address";
    public static final String full_address = "full_address";

    String details="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_details);

        textView=(TextView)findViewById(R.id.my_details);
        sharedpreferences_number = getSharedPreferences(mypreference_number,
                Context.MODE_PRIVATE);

        sharedpreferences_full_address = getSharedPreferences(mypreference_full_address,
                Context.MODE_PRIVATE);

        details="Name: "+sharedpreferences_number.getString(my_name,"")+"\n"+"Phone Number: "+sharedpreferences_number.getString(my_number,"");
        details=details+"\n\nAddress:"+sharedpreferences_full_address.getString(full_address,"");

        textView.setText(details);


    }
}

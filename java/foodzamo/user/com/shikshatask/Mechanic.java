package foodzamo.user.com.shikshatask;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Mechanic extends AppCompatActivity {
    SharedPreferences sharedpreferences_number;
    public static final String mypreference_number = "savedata";
    public static final String my_number = "mynumber";
    public static final String my_name = "myname";

    SharedPreferences sharedpreferences_full_address;
    public static final String mypreference_full_address = "mypreference_full_address";
    public static final String full_address = "full_address";

    TextView show_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanic);

        show_text=(TextView)findViewById(R.id.show_text);

        sharedpreferences_number = getSharedPreferences(mypreference_number,
                Context.MODE_PRIVATE);

        sharedpreferences_full_address = getSharedPreferences(mypreference_full_address,
                Context.MODE_PRIVATE);

        String s="Name: "+sharedpreferences_number.getString(my_name,"")+"\n";
        s=s+"Number: "+sharedpreferences_number.getString(my_number,"")+"\n";
        s=s+"Address: "+sharedpreferences_full_address.getString(full_address,"");

        show_text.setText(s);

    }
}

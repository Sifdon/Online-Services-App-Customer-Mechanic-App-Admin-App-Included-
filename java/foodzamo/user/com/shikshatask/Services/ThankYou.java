package foodzamo.user.com.shikshatask.Services;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import foodzamo.user.com.shikshatask.HomeActivity;
import foodzamo.user.com.shikshatask.MainActivity;
import foodzamo.user.com.shikshatask.R;

public class ThankYou extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);
    }
    @Override
    public void onBackPressed()
    {
        finish();
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
    }
}

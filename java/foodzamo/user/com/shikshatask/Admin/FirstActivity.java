package foodzamo.user.com.shikshatask.Admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import foodzamo.user.com.shikshatask.Integrate;
import foodzamo.user.com.shikshatask.R;

public class FirstActivity extends AppCompatActivity {
Button button_see_request,button_emp_list,button_assign;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);



        button_see_request=(Button)findViewById(R.id.see_requests);
        button_emp_list=(Button)findViewById(R.id.employees_list);
        button_assign=(Button)findViewById(R.id.assign_work);

        button_see_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SeeRequests.class));
            }
        });

        button_emp_list.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(getApplicationContext(),EmpList.class));
    }
});
        button_assign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AssignWork.class));
            }
        });




    }
    @Override
    public void onBackPressed()
    {
        finish();
        startActivity(new Intent(getApplicationContext(), Integrate.class));
    }
}

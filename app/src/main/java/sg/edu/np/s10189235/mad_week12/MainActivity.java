package sg.edu.np.s10189235.mad_week12;

import android.content.Intent;
import android.content.SharedPreferences;
import android.service.autofill.UserData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener{

    private static final String TAG = "MainActivity.java";

    private TextView tv_NewUser;
    MyDBHandler dbHandler = new MyDBHandler(this,null,null,1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_NewUser =(TextView) findViewById(R.id.txtNewUser);
        tv_NewUser.setOnTouchListener(this);
    }

    public boolean onTouch(View v, MotionEvent event)
    {
        Log.v(TAG, "Touch Start");
        Intent intent = new Intent(MainActivity.this, Main3Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        return true;
    }

    public void onClick(View v)
    {
        final EditText etPassword = (EditText) findViewById(R.id.txtUsername);
        final EditText etUsername =(EditText) findViewById(R.id.txtPassword);

        Log.v(TAG, "login with: "+ etUsername.getText().toString()+", "+ etPassword.getText().toString());
        //if(isValidUsername(etUsername.getText().toString())&& isValidPassword(etPassword.getText().toString()))
        if(isvalidUser(etUsername.getText().toString(), etPassword.getText().toString()))
        {
            Intent intent = new Intent(MainActivity.this,Main4Activity.class);
            Toast.makeText(MainActivity.this,"Valid User",Toast.LENGTH_LONG).show();
            startActivity(intent);
        }
        else{
            Toast.makeText(MainActivity.this, "Invalid User", Toast.LENGTH_LONG).show();
        }
    }

    public boolean isvalidUser(String userName, String password)
    {
        UserData dbData = dbHandler.findUser(userName);
        Log.v(TAG,"Running Checks... ..."+ dbData.getMyUserName()+": "+dbData.getMyPassword()
        +"VS "+ userName+": "+password);
        if((dbData.getMyUserName().equals(userName))&& dbData.getMyPassword().equals(password))
        {
            return true;
        }
        return false;
    }

    /*public boolean isValidUsername(String userName)
    {
        sharedPreferences = getSharedPreferences(MY_GLOBAL_PREFS, MODE_PRIVATE);
        String SharedUsername = SharedPreferences.getString(MY_USERNAME, "");
        if(SharedUsername.equals(userName))
        {
            return true;
        }
        return false;
    }

    public boolean isValidPassword(String password)
    {
        sharedPreferences = getSharedPreferences(MY_GLOBAL_PREFS, MODE_PRIVATE);
        String SharedPassword = SharedPreferences.getString(MY_PASSWORD, "");
        if(SharedPassword.equals(password))
        {
            return true;
        }
        return false;
    }*/






}

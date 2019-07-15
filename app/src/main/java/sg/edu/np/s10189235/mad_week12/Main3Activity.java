package sg.edu.np.s10189235.mad_week12;

import android.service.autofill.UserData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main3Activity extends AppCompatActivity {

    MyDBHandler dbHandler = new MyDBHandler(this, null,null,1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void onCreateClick(View v)
    {
        final EditText etPassword = (EditText) findViewById(R.id.txtCreateUsername);
        final EditText etUsername = (EditText) findViewById(R.id.txtCreatePassword);

        if(isValidUsername(etUsername.getText().toString()) && isValidPassword(etPassword.getText().toString()) ){
            UserData dbData = dbHandler.findUser(etUsername.getText().toString());

            if(dbData==null)
            {
                String dbUsername = etUsername.getText().toString();
                String dbPassword = etPassword.getText().toString();
                UserData dbUserData = new UserData();
                dbUserData.setMyUserName(dbUsername);
                dbUserData.setMyPassword(dbPassword);
                dbHandler.addUser(dbUserData);
                Toast.makeText(Main3Activity.this, "UserCreated Successfully.", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(Main3Activity.this, "User Already Exist. \nPlease Try Again.", Toast.LENGTH_LONG).show();
            }
            finish();
        }
    }

    public boolean isValidPassword(String password){
        Pattern passwordPattern;
        Matcher passwordMatcher;

        final String PASSWORD_PATTERN ="^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%]).{6,12}$";

        passwordPattern = Pattern.compile(PASSWORD_PATTERN);
        passwordMatcher = passwordPattern.matcher(password);

        Log.v(TAG, "Create Password: " + passwordMatcher.matches());

        return  passwordMatcher.matches();


    }

    public boolean isValidUsername(String userName){
        Pattern userNamePattern;
        Matcher userNameMatcher;

        final String USERNAME_PATTERN ="^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%]).{6,12}$";
        userNamePattern = Pattern.compile(USERNAME_PATTERN);
        userNameMatcher= userNamePattern.matcher(userName);

        Log.v(TAG,"Create Username: "+ userNameMatcher.matches());

        return  userNameMatcher.matches();
    }
}

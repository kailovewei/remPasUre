package ykk.remember.com;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button login;
    private EditText accountEdit;
    private  EditText passwordEdit;
    private CheckBox rememberpass;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref= PreferenceManager.getDefaultSharedPreferences(this);
        accountEdit= (EditText) findViewById(R.id.account_Id);
        passwordEdit= (EditText) findViewById(R.id.password_Id);
        rememberpass= (CheckBox) findViewById(R.id.remember_Id);
        login= (Button) findViewById(R.id.login_Id);
        boolean isremember=pref.getBoolean("remember_password",false);
        if(isremember)
        {
            String account=pref.getString("account","");
            String password=pref.getString("password","");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            rememberpass.setChecked(true);
        }
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String account=accountEdit.getText().toString();
        String password=passwordEdit.getText().toString();
        if(account.equals("admin")&&password.equals("123456"))
        {
            editor=pref.edit();
            if(rememberpass.isChecked())
            {
                editor.putBoolean("remember_password",true);
                editor.putString("account",account);
                editor.putString("password",password);
            }
            else
            {
                editor.clear();
            }
            editor.commit();
            Intent intent=new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
        else
        {
            Toast.makeText(MainActivity.this,"account or password is invalid",Toast.LENGTH_SHORT).show();
        }
    }
}

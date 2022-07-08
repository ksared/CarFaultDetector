package com.example.carfaultdetector.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.carfaultdetector.R;
import com.example.carfaultdetector.ViewModel.LoginViewModel;
import com.example.carfaultdetector.model.User;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    EditText emailEdit;
    EditText passwordEdit;
    Button loginButton;
    TextView textView;
    Boolean zalogowano;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_dialog);
        zalogowano = false;

        //Intent intent = getIntent();
/* sprawdzanie czy ktos dotknal ekranu zeby przejsc na inny widok
        LinearLayout lLayout = (LinearLayout) findViewById(R.id.login_dialog);
        lLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(zalogowano){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
*/
        //View view = getLayoutInflater().inflate(R.layout.login_dialog, null);
        //View mainView = getLayoutInflater().inflate(R.layout.activity_main, null);

        emailEdit = findViewById(R.id.emailEdit);
        passwordEdit = findViewById(R.id.passwordEdit);
        textView = findViewById(R.id.textView);
        User user = new User(null, null);

        LoginViewModel loginViewModel;
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);

        loginButton = findViewById(R.id.login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> map = new HashMap<>();
                Log.d("emailEdit: ", emailEdit.getText().toString());
                map.put("email", emailEdit.getText().toString());
                map.put("password", passwordEdit.getText().toString());
                System.out.println("mapa: " + map);
                loginViewModel.getLoginName(map);
            }
        });

        loginViewModel.mutableLiveData.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean s) {
                System.out.println("Nastapila zmiana mutableLiveData: " + s);
                //any change in mlivedata
                if(s==true){
                    System.out.println("Login udany");

                    String email = loginViewModel.getName();
                    String name = loginViewModel.getEmail();

                    System.out.println("email: " + email + " name: " + name);

                    user.setEmail(email);
                    user.setName(name);
                    //zamiast dawac notyfikacje to zmieniam ekran
                    //builder.setTitle("Login successful");
                    //builder.setMessage(loginResult.getEmail() + "  " + loginResult.getName());
                    //builder.show();
                    zalogowano = s;
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("email", email);
                    intent.putExtra("name", name);
                    startActivity(intent);


                }
                else{
                    System.out.println("Login nieudany");
                    builder.setTitle("Login failed");
                    builder.setMessage("Wrong email or password");
                    builder.show();
                    zalogowano = s;
                }

                //builder.setTitle("Login successful");
                //builder.setMessage(loginViewModel.getEmail()+ "  " + loginViewModel.getName());
                //builder.show();
            }
        });
    }

}

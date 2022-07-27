package com.example.carfaultdetector.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.carfaultdetector.R;
import com.example.carfaultdetector.model.Global;
import com.example.carfaultdetector.model.User;

public class MainActivity extends AppCompatActivity {

//    private Retrofit retrofit;
  //  private RetrofitInterface retrofitInterface;
    //private String BaseURL = "http://10.0.2.2:3000";

    Button loginButton;
    Button signUpButton;
    Button workShopButton;
    Button faultButton;
    TextView textView;
    User user = new User(null, null);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setLoginButton();
        setSignUpButton();
        setWorkShopButton();
        setFaultButton();

    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        if(Global.userName == null){
            textView.setText("Niezalogowano");

        }
        else{
            textView.setText("Witaj " + Global.userName);
        }
   /*     if(b!=null){
            //System.out.println("Ekstrasy: " + b.get("email"));
            //textView.setText("Witaj " + b.get("name"));
            user.setName(b.get("name").toString());
            user.setEmail(b.get("email").toString());
            System.out.println("User: " + user.getEmail() + " " + user.getName());
            textView.setText("Witaj " + Global.userName);
        }
        else{
            System.out.println("nie ma ekstrasow");
            System.out.println("User: " + user.getEmail() + " " + user.getName());
        }
*/

    }

    private void setWorkShopButton(){
        workShopButton = findViewById(R.id.workshop);
        workShopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WorkshopsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setSignUpButton(){
        signUpButton = findViewById(R.id.signup);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setFaultButton(){
        faultButton = findViewById(R.id.fault);
        faultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FaultActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setLoginButton(){
        textView = findViewById(R.id.logintextview);
        loginButton = findViewById(R.id.login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


       /*
        LoginViewModel loginViewModel;
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginViewModel.getLoginName();
            }
        });

        loginViewModel.mutableLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                //any change in mlivedata
                textView.setText(s);
            }
        });
    }

    private LoginResult getLogin(){
        return new LoginResult("aaaa", "bbbb");
    }

    public void DisplaySomething(View view){
        textView.setText(getLogin().getName() + " " + getLogin().getEmail());

        */
    }
/*
    private void handleLoginDialog() {
        View view = getLayoutInflater().inflate(R.layout.login_dialog, null);
        View mainView = getLayoutInflater().inflate(R.layout.activity_main, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setView(view).show();

        Button loginBtn = view.findViewById(R.id.login);
        EditText emailEdit = view.findViewById(R.id.emailEdit);
        EditText passwordEdit = view.findViewById(R.id.passwordEdit);
        //TextView loginTextView = mainView.findViewById(R.id.logintextview);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> map = new HashMap<>();

                map.put("email", emailEdit.getText().toString());
                map.put("password", passwordEdit.getText().toString());

                Call<LoginResult> call = retrofitInterface.executeLogin(map);

                call.enqueue(new Callback<LoginResult>() {
                    @Override
                    public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                        if(response.code()==200){

                            LoginResult result = response.body();
                            setContentView(R.layout.activity_main);
                            //loginTextView.setText("Login");
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                            builder1.setTitle("Login successful");
                            builder1.setMessage(result.getEmail() + " " + result.getName());

                            builder1.show();
                        }
                        else if(response.code()==404){
                            Toast.makeText(MainActivity.this, "Wrong Credentials",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResult> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    private void handleSignupDialog() {

        View view = getLayoutInflater().inflate(R.layout.signup_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view).show();

        Button signupBtn = view.findViewById(R.id.signup);
        EditText nameEdit = view.findViewById(R.id.nameEdit);
        EditText emailEdit = view.findViewById(R.id.emailEdit);
        EditText passwordEdit = view.findViewById(R.id.passwordEdit);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> map = new HashMap<>();

                map.put("name", nameEdit.getText().toString());
                map.put("email", emailEdit.getText().toString());
                map.put("password", passwordEdit.getText().toString());

                Call<Void> call = retrofitInterface.executeSignup(map);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.code() == 200){
                            Toast.makeText(MainActivity.this,
                                    "Signed up successfully", Toast.LENGTH_LONG).show();
                        }
                        else if(response.code()==400){
                            Toast.makeText(MainActivity.this,
                                    "Already registered", Toast.LENGTH_LONG).show();
                        }
                        else if(response.code()==403){
                            Toast.makeText(MainActivity.this, "Too short password",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }
    */

}
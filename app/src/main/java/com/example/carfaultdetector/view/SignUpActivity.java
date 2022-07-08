package com.example.carfaultdetector.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.carfaultdetector.R;
import com.example.carfaultdetector.ViewModel.SignUpViewModel;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {

    EditText nameEdit;
    EditText emailEdit;
    EditText passwordEdit;
    Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_dialog);

        nameEdit = findViewById(R.id.nameEdit);
        emailEdit = findViewById(R.id.emailEdit);
        passwordEdit = findViewById(R.id.passwordEdit);
        signUpButton = findViewById(R.id.signup);

        AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);

        SignUpViewModel signUpViewModel;
        signUpViewModel = new ViewModelProvider(this).get(SignUpViewModel.class);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> map = new HashMap<>();
                map.put("name", nameEdit.getText().toString());
                map.put("email", emailEdit.getText().toString());
                map.put("password", passwordEdit.getText().toString());
                signUpViewModel.signUpUser(map);
            }
        });

        signUpViewModel.mutableLiveData.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer httpCode) {
                if(httpCode == 200){
                    builder.setTitle("Zarejestrowano użytkownika");
                    builder.setMessage(emailEdit.getText().toString() + "  " + nameEdit.getText().toString());
                    builder.show();
                }
                else if(httpCode == 400){
                    builder.setTitle("Błąd");
                    builder.setMessage("Taki użytkownik już istnieje");
                    builder.show();
                }
                else if(httpCode == 403){
                    builder.setTitle("Błąd");
                    builder.setMessage("Nieprawidłowy login lub hasło");
                    builder.show();
                }
                else{
                    builder.setTitle("Błąd");
                    builder.setMessage("Nie połączono z serwerem");
                    builder.show();
                }

            }
        });



    }
}
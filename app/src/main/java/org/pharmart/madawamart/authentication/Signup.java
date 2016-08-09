package org.pharmart.madawamart.authentication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;

import org.pharmart.madawamart.R;
import org.pharmart.madawamart.authentication.*;


public class Signup extends AppCompatActivity {
    private static final String TAG = "SignupActivity";
    private static final String FIREBASE_URL = "https://madawa-mart.firebaseio.com/";

    private EditText edt_name;
    private EditText edt_email;
    private EditText edt_pass;
    private EditText edt_age;
    private EditText edt_address;
    private Button btn_signup;
    private TextView txt_login;
    private FirebaseAuth mAuth;
    private String gender = "";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        edt_address = (EditText) findViewById(R.id.address_input);
        edt_age = (EditText) findViewById(R.id.age_input);
        edt_name = (EditText) findViewById(R.id.input_name);
        edt_email = (EditText) findViewById(R.id.input_email);
        edt_pass = (EditText) findViewById(R.id.input_password);
        btn_signup = (Button) findViewById(R.id.btn_signup);
        txt_login = (TextView) findViewById(R.id.link_login);



        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        btn_signup.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(Signup.this,
                R.style.AuthTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Attempting To Create Account...");
        progressDialog.show();

        String address = edt_address.getText().toString();
        String age = edt_age.getText().toString();
        String name = edt_name.getText().toString();
        final String email = edt_email.getText().toString();
        final String password = edt_pass.getText().toString();

        // TODO: Implement your own signup logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        mAuth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            onSignupSuccess();
                                            progressDialog.dismiss();
                                            startActivity(new Intent(Signup.this, Login.class));

                                        } else {
                                            onSignupFailed();
                                            progressDialog.dismiss();
                                            Log.w(TAG, "signInWithEmail", task.getException());
                                            Toast.makeText(Signup.this, "Authentication failed.",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });


                    }
                }, 3000);
    }


    public void onSignupSuccess() {
        btn_signup.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        btn_signup.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = edt_name.getText().toString();
        String email = edt_email.getText().toString();
        String password = edt_pass.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            edt_name.setError("at least 3 characters");
            valid = false;
        } else {
            edt_name.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edt_email.setError("enter a valid email address");
            valid = false;
        } else {
            edt_email.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            edt_pass.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            edt_pass.setError(null);
        }

        return valid;
    }

    public void onRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_males:
                if (checked)
                    gender="male";
                    break;
            case R.id.radio_females:
                if (checked)
                    gender="female";
                    break;
        }

    }
}
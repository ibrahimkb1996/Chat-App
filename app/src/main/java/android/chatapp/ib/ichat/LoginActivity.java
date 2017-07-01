package android.chatapp.ib.ichat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {


    private TextInputLayout lemail, lpass;
    private Button log_but;
    private FirebaseAuth mAuth;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pd = new ProgressDialog(this);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.login_appbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lemail = (TextInputLayout) findViewById(R.id.log_email);
        lpass = (TextInputLayout) findViewById(R.id.log_pass);
        log_but = (Button) findViewById(R.id.log_but);

        mAuth = FirebaseAuth.getInstance();

        log_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pd.setMessage("Loging you in..");
                pd.show();

                String email = lemail.getEditText().getText().toString().trim();
                String pass = lpass.getEditText().getText().toString().trim();

                if(TextUtils.isEmpty(email))
                    lemail.setError("Empty Field");
                else if(TextUtils.isEmpty(pass))
                    lpass.setError("Empty Field");
                else signin(email,pass);
            }
        });

    }

    private void signin(String email, String pass) {
        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (task.isSuccessful()) {
                                pd.dismiss();
                            Intent mainintent = new Intent(LoginActivity.this,MainActivity.class);
                            mainintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(mainintent);
                            finish();

                        }else {

                            pd.hide();
                            Toast.makeText(LoginActivity.this,"Error",Toast.LENGTH_SHORT).show();


                        }

                        // ...
                    }
                });
    }
}
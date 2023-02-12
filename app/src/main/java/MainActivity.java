package michaelosullivan.uottawa.skincancerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.PatternsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText emailT, passwordT;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailT = findViewById(R.id.emailLogin);
        passwordT = findViewById(R.id.passwordLogin);
        mAuth = FirebaseAuth.getInstance();
    }

    // Navigate to create account activity
    public void onCreateAccountButton(View view) {
        //Application Context and Activity
        startActivity(new Intent(getApplicationContext(), CreateAccount.class));
    }

    // Navigate to testing activity if user is validated
    public void onLoginButton(View view) {
        String email = emailT.getText().toString().trim();
        String password = passwordT.getText().toString().trim();

        if(!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches() || email.isEmpty()){
            emailT.setError("Please enter a valid email");
            emailT.requestFocus();
            return;
        }
        if(password.isEmpty()){
            passwordT.setError("Please enter a password");
            passwordT.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(getApplicationContext(), GetTested.class));
                }
                else{
                    Log.d("Error", task.getException().getMessage());
                    Toast.makeText(
                            MainActivity.this,
                            "Could not find an account with those credentials",
                            Toast.LENGTH_LONG
                    ).show();
                }
            }
        });
    }
}
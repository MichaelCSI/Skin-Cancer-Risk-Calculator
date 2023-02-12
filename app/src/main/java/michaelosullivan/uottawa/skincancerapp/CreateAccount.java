package michaelosullivan.uottawa.skincancerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CreateAccount extends AppCompatActivity {

    private EditText emailT, passwordT;
    private Button createAccount;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        emailT = findViewById(R.id.emailCreate);
        passwordT = findViewById(R.id.passwordCreate);
        createAccount = findViewById(R.id.clientCreateButton);
        mAuth = FirebaseAuth.getInstance();
    }

    // Validate fields and create account if email is not in use
    public void onCreateAccountButton(View view){

        String email = emailT.getText().toString().trim();
        String password = passwordT.getText().toString().trim();

        // Firebase authentication for new user
        // https://firebase.google.com/docs/auth/android/password-auth
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(
                                    CreateAccount.this,
                                    "Authentication success.",
                                    Toast.LENGTH_LONG
                            ).show();
                            finish();
                        } else{
                            Log.d("Client creation error", task.getException().getMessage());
                            Toast.makeText(
                                    CreateAccount.this,
                                    task.getException().getMessage(),
                                    Toast.LENGTH_LONG
                            ).show();
                        }
                    }
                });
    }
}
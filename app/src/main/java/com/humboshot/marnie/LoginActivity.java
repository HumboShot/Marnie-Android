package com.humboshot.marnie;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.auth0.android.Auth0;
import com.auth0.android.authentication.AuthenticationAPIClient;
import com.auth0.android.authentication.AuthenticationException;
import com.auth0.android.callback.BaseCallback;
import com.auth0.android.provider.WebAuthProvider;
import com.auth0.android.result.Credentials;


public class LoginActivity extends Activity {

    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Bind views
        final EditText emailEditText = (EditText) findViewById(R.id.emailEditext);
        final EditText passwordEditText = (EditText) findViewById(R.id.passwordEditext);
        Button loginButton = (Button) findViewById(R.id.loginButton);

        //Hardcoded values for debugging
        emailEditText.setText("mm@mm.com");
        passwordEditText.setText("123");

        // Add the onClick listener to the database login
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Show a progress dialog to block the UI while the request is being made.
                login(emailEditText.getText().toString(), passwordEditText.getText().toString());
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        //Check if the result belongs to a pending web authentication
        if (WebAuthProvider.resume(intent)) {
            return;
        }
        super.onNewIntent(intent);
    }

    private void login(String email, String password) {
        Auth0 auth0 = new Auth0(getString(R.string.auth0_client_id), getString(R.string.auth0_domain));
        AuthenticationAPIClient client = new AuthenticationAPIClient(auth0);

        progress = ProgressDialog.show(this, null, "Logging in..", true, false);
        progress.show();

        String connectionName = "Username-Password-Authentication";
        client.login(email, password, connectionName)
                .start(new BaseCallback<Credentials, AuthenticationException>() {
                    @Override
                    public void onSuccess(Credentials payload) {
                        progress.dismiss();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }

                    @Override
                    public void onFailure(final AuthenticationException error) {
                        progress.dismiss();
                        //Show error to the user
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
    }
}

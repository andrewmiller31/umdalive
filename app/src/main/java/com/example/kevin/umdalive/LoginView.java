package com.example.kevin.umdalive;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

public class LoginView extends AppCompatActivity {



    private GoogleApiClient mGoogleApiClient;
    private TextView mStatusTextView;
    private ProgressDialog mProgressDialog;


    Presenter presenter; //there shouldn't be an error here after merging with the Presenter branch to gain the presenter class

    @Override
    protected void onCreate(Bundle savedInstanceState) {




        // Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this.)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            // Button listeners
        findViewById(R.id.sign_in_button).setOnClickListener((View.OnClickListener) this);
        SignInButton signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        // Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.



        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

       // presenter = new Presenter(this);
    }
    /*
    This is the onClick for the Login button
    current functionality: currently all this does is check for "@" symbol and go to next page.
    The next page is the Main Activity.

    Replace with OAuth
     */

    public void login(View view) {
        EditText emailEntry = (EditText) findViewById(R.id.email_input);
        String emailCheckTemp = emailEntry.getText().toString();
        if(emailCheckTemp.indexOf('@') != -1) {
            Intent intent = new Intent(this, MainView.class);
            startActivity(intent);
        }
        else Toast.makeText(getApplicationContext(),"Sorry, this is not a valid email.", Toast.LENGTH_SHORT).show();
    }

    //this function gets called in the model, so it needs to be linked up via presenter
    private void updateUI(boolean signedIn) {
        if (signedIn) {
            findViewById(R.id.sign_in_button).setVisibility(View.GONE);
            findViewById(R.id.sign_out_and_disconnect).setVisibility(View.VISIBLE);
        } else {
            mStatusTextView.setText(R.string.signed_out);

            findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
            findViewById(R.id.sign_out_and_disconnect).setVisibility(View.GONE);
        }
}

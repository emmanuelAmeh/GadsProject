package com.example.android.gadsproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SubmissionActivity extends AppCompatActivity implements SubmitDialog.SubmitDialogListener {

    private EditText tvFirstName;
    private EditText tvLastName;
    private EditText tvEmail;
    private EditText tvGithubLink;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().hide();

        tvFirstName = findViewById(R.id.tv_firstname);
        tvLastName = findViewById(R.id.tv_lastname);
        tvEmail = findViewById(R.id.tv_email);
        tvGithubLink = findViewById(R.id.tv_github_link);
        btnSubmit = findViewById(R.id.btn_submit);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }

    private void openDialog() {
        String firstname = tvFirstName.getText().toString();
        String lastname = tvLastName.getText().toString();
        String email = tvEmail.getText().toString();
        String githubLink = tvGithubLink.getText().toString();


        SubmitDialog submitDialog = new SubmitDialog(email, firstname, lastname, githubLink);
        submitDialog.show(getSupportFragmentManager(), "submit dialog");
    }

    @Override
    public void successView() {
        DialogSuccess dialogSuccess = new DialogSuccess();
        dialogSuccess.show(getSupportFragmentManager(), "Success Dialog");
    }

    @Override
    public void notSuccessView() {
        DialogNotSuccessful dialogNotSuccessful = new DialogNotSuccessful();
        dialogNotSuccessful.show(getSupportFragmentManager(), "Not Success Dialog");
    }
}

package com.example.android.gadsproject;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SubmitDialog extends AppCompatDialogFragment {
    private String email;
    private String fName;
    private String lName;
    private String github;
    private SubmitDialogListener mSubmitDialogListener;

    public SubmitDialog(String email, String fName, String lName, String github) {
        this.email = email;
        this.fName = fName;
        this.lName = lName;
        this.github = github;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);

        builder.setView(view)
                .setCancelable(true);

        Button submitBtn = view.findViewById(R.id.submit_button);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendPost();
            }
        });

        return builder.create();
    }

    private void sendPost() {
        Retrofit gadsRetrofit = new Retrofit.Builder()
                .baseUrl("https://docs.google.com/forms/d/e/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceholderApi jsonPlaceholderApi = gadsRetrofit.create(JsonPlaceholderApi.class);

        int id = Integer.valueOf(email);

        Call<Post> call = jsonPlaceholderApi.createPost(email, fName, lName, github);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                mSubmitDialogListener.successView();
                Post postResponse = response.body();
                String content = "";
                content += "Code: " + response.code() + "\n";
                content += "Email: " + postResponse.getEmail() + "\n";
                content += "FName: " + postResponse.getfName() + "\n";
                content += "lName: " + postResponse.getlName() + "\n";
                content += "github: " + postResponse.getGithub() + "\n\n";
                Log.i("Submitdialog", content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                mSubmitDialogListener.notSuccessView();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mSubmitDialogListener = (SubmitDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement SubmitDialogListener");
        }
    }

    public interface SubmitDialogListener {
        void notSuccessView();

        void successView();
    }

}

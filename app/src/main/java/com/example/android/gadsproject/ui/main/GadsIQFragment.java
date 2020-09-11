package com.example.android.gadsproject.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.gadsproject.Gads;
import com.example.android.gadsproject.GadsAdapter;
import com.example.android.gadsproject.GadsIQ;
import com.example.android.gadsproject.GadsIQAdapter;
import com.example.android.gadsproject.JsonPlaceholderApi;
import com.example.android.gadsproject.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A placeholder fragment containing a simple view.
 */
public class GadsIQFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    private TextView mTextView;
    private GadsIQAdapter mGadsIQAdapter;
    private RecyclerView mRecyclerView;
    private List<GadsIQ> mGadsIQArrayList;
    private ArrayList<GadsIQ> mAllGadsIQArrayList;


    public static GadsIQFragment newInstance(int index) {
        GadsIQFragment fragment = new GadsIQFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = new ViewModelProvider(getActivity()).get(PageViewModel.class);
        pageViewModel.init();

    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        mRecyclerView = root.findViewById(R.id.recycler_view);
        mTextView = root.findViewById(R.id.textView);

        mRecyclerView = root.findViewById(R.id.recycler_view);
        mTextView = root.findViewById(R.id.textView);


        if (mGadsIQAdapter == null) {
            mGadsIQAdapter = new GadsIQAdapter();
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }

        Retrofit gadsRetrofit = new Retrofit.Builder()
                .baseUrl("https://gadsapi.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceholderApi jsonPlaceholderApi = gadsRetrofit.create(JsonPlaceholderApi.class);

        Call<List<GadsIQ>> call = jsonPlaceholderApi.getGadsSkillIq();


        mGadsIQArrayList = new ArrayList<>();
        mAllGadsIQArrayList = new ArrayList<>();


        call.enqueue(new Callback<List<GadsIQ>>() {
            @Override
            public void onResponse(Call<List<GadsIQ>> call, Response<List<GadsIQ>> response) {
                if (!response.isSuccessful()) {
                    mTextView.setText("Code: " + response.code());
                    return;
                }
                //  mTextView.setText("code: " + response.code());


                mGadsIQArrayList = response.body();


                for (GadsIQ gad : mGadsIQArrayList) {
                    String name = gad.getName();
                    int score = gad.getScore();
                    String country = gad.getCountry();
                    String url = gad.getUrl();

                    mAllGadsIQArrayList.add(new GadsIQ(name, score, country, url));
                }
                mGadsIQAdapter.submitList(mAllGadsIQArrayList);
                mRecyclerView.setAdapter(mGadsIQAdapter);

            }

            @Override
            public void onFailure(Call<List<GadsIQ>> call, Throwable t) {
                mTextView.setText("Code: " + t.getMessage());

            }
        });
        return root;
    }

}
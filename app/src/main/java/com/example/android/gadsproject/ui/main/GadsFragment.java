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
public class GadsFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    private TextView mTextView;
    private GadsAdapter mGadsAdapter;
    private RecyclerView mRecyclerView;
    private List<Gads> mGadsArrayList;
    private ArrayList<Gads> mAllGadsArrayList;


    public static GadsFragment newInstance(int index) {
        GadsFragment fragment = new GadsFragment();
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
        if (mGadsAdapter == null) {
            mGadsAdapter = new GadsAdapter();
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }

        Retrofit gadsRetrofit = new Retrofit.Builder()
                .baseUrl("https://gadsapi.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceholderApi jsonPlaceholderApi = gadsRetrofit.create(JsonPlaceholderApi.class);

        Call<List<Gads>> call = jsonPlaceholderApi.getGadsHours();

        final LiveData<Gads> gadsData = new MutableLiveData<>();

        mGadsArrayList = new ArrayList<>();
        mAllGadsArrayList = new ArrayList<>();

        call.enqueue(new Callback<List<Gads>>() {
            @Override
            public void onResponse(Call<List<Gads>> call, Response<List<Gads>> response) {
                if (!response.isSuccessful()) {
                    mTextView.setText("Code: " + response.code());
                    return;
                }
                //  mTextView.setText("code: " + response.code());


                mGadsArrayList = response.body();


                for (Gads gad : mGadsArrayList) {
                    String name = gad.getName();
                    int hours = gad.getHours();
                    String country = gad.getCountry();
                    String url = gad.getUrl();

                    mAllGadsArrayList.add(new Gads(name, hours, country, url));
                }
                mGadsAdapter.submitList(mAllGadsArrayList);
                mRecyclerView.setAdapter(mGadsAdapter);
                //mGadsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Gads>> call, Throwable t) {
                mTextView.setText("code: " + t.getMessage());

            }
        });

//       pageViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });*/
        return root;
    }

}
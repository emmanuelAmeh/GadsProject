package com.example.android.gadsproject.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android.gadsproject.Gads;
import com.example.android.gadsproject.GadsRepository;

public class PageViewModel extends ViewModel {

    private MutableLiveData<Gads> mGadsMutableLiveData;
    private GadsRepository mGadsRepository;

    public void init() {
        if (mGadsMutableLiveData != null) {
            return;
        }

        mGadsRepository = GadsRepository.getInstance();

        mGadsMutableLiveData = mGadsRepository.getGads();
    }

    public MutableLiveData<Gads> getGadsREpository() {
        return mGadsMutableLiveData;
    }
}
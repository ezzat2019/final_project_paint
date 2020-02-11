package com.example.programmer.ordertasksjava.recivers;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends AndroidViewModel {
    private MutableLiveData<Integer>liveData;

    public MyViewModel(@NonNull Application application) {
        super(application);
        liveData=new MutableLiveData<>();
    }
    void addInt(Integer integer)
    {
        liveData.setValue(integer);
    }

    public LiveData<Integer> getLiveData() {
        return liveData;
    }
}

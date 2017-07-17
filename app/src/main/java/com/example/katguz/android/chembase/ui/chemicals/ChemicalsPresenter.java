package com.example.katguz.android.chembase.ui.chemicals;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.katguz.android.chembase.model.Chemical;
import com.example.katguz.android.chembase.model.PropertyTable;
import com.example.katguz.android.chembase.model.events.HideProgress;
import com.example.katguz.android.chembase.model.events.ShowProgress;
import com.example.katguz.android.chembase.network.ApiClient;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;


public class ChemicalsPresenter {


    private ApiClient apiClient;
    private Context context;
    private SharedPreferences preferences;

    private PropertyTable propertyTable;


    private ChemicalsMvpView view;
    private ChemicalsAdapter adapter;


    public void setAdapter(ChemicalsAdapter adapter) {
        this.adapter = adapter;
    }

    @Inject
    public ChemicalsPresenter(ApiClient apiClient) {
        this.apiClient = apiClient;
    }


    public void attach(ChemicalsMvpView view) {
        this.view = view;
    }

    public void detach() {
        this.view = null;
    }


    public void starDownloadData() {
        EventBus.getDefault().post(new ShowProgress());

        //TODO CID value from edit text
        apiClient.getService().getData(123).enqueue(new Callback<Chemical>() {
            @Override
            public void onResponse(Call<Chemical> call, Response<Chemical> response) {
                if (response.isSuccessful()) {


                   // adapter.setData();
/*
                    molecularFormula = String.valueOf(chemical.getPropertyTable().getProperties().get(0).getMolecularFormula());
                    molecularMass = String.valueOf(chemical.getPropertyTable().getProperties().get(0).getMolecularWeight());
                    cidNumber = chemical.getPropertyTable().getProperties().get(0).getCID();
                    inChINumber = chemical.getPropertyTable().getProperties().get(0).getInChI();*/


                    Timber.d("Response", response.body().getPropertyTable().getProperties().get(0).getCanonicalSMILES());
                    // adapter.setData();
                }
                EventBus.getDefault().post(new HideProgress());

            }


            @Override
            public void onFailure(Call<Chemical> call, Throwable t) {
                EventBus.getDefault().post(new HideProgress());
            }
        });
    }


}


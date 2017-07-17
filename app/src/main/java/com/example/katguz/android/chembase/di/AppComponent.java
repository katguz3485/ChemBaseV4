package com.example.katguz.android.chembase.di;

import com.example.katguz.android.chembase.ui.chemicals.ChemicalsFragment;

import javax.inject.Singleton;


@Singleton
@dagger.Component(modules = {AppModule.class})
public interface AppComponent {
/*
    void inject(DashboardActivity dashboardActivity);*/

  void inject(ChemicalsFragment chemicalsFragment);
}

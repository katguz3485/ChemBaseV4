package com.example.katguz.android.chembase.network;


import android.content.Context;
import android.net.Uri;

import static com.example.katguz.android.chembase.network.ApiClient.BASE_URL;

public class PicassoHelper implements PicassoService {

    public String url;

    public String getUrl() {
        return url;
    }

    private int cidValue;

    private String urlStringParametrized;

    private Context context;


    public PicassoHelper(Context context) {

    }

    public void setUrlPath() {

        cidValue = 23;

        urlStringParametrized = BASE_URL + "compound/cid/" + cidValue + "/PNG";

        url = Uri.parse(urlStringParametrized)
                .buildUpon()
                .build()
                .toString();
    }

    public int getCidValue() {
        return cidValue;
    }

    public void setCidValue(int cidValue) {
        this.cidValue = cidValue;
    }


}

package com.example.katguz.android.chembase.network;


import android.net.Uri;

import com.example.katguz.android.chembase.model.Property;

import static com.example.katguz.android.chembase.network.ApiClient.BASE_URL;

public class PicassoHelper implements PicassoService {

    public String url;

    public String getUrl() {
        return url;
    }

    Property property;

     int cidValue;

    private String urlStringParametrized;

    public void setUrlPath() {

        property=new Property();
        property.setCidValue(23);
        property.getCidValue();

        urlStringParametrized = BASE_URL + "compound/cid/" + property.getCidValue() + "/PNG";

        url = Uri.parse(urlStringParametrized)
                .buildUpon()
                .build()
                .toString();
    }

}

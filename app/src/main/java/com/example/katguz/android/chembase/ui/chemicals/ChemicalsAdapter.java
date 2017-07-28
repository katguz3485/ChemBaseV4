package com.example.katguz.android.chembase.ui.chemicals;


import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.katguz.android.chembase.R;
import com.example.katguz.android.chembase.model.Property;
import com.example.katguz.android.chembase.model.PropertyTable;
import com.example.katguz.android.chembase.network.ApiClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class ChemicalsAdapter extends RecyclerView.Adapter<ChemicalsAdapter.ChemicalsHolder> {


    private List<Property> properties = new ArrayList<>();

    PropertyTable propertyTable = new PropertyTable();


    @Inject
    Context context;

    private ApiClient apiClient;


    @Inject
    ChemicalsPresenter presenter = new ChemicalsPresenter(apiClient);


    @Inject
    public ChemicalsAdapter(Context context) {
        this.context = context;

    }


    public void setData(List<Property> data) {
        propertyTable.getProperties();
        properties.clear();
        properties.addAll(data);

        notifyDataSetChanged();

    }

 /*   public void setBm() {
        presenter.getImage();
        notifyDataSetChanged();
    }
*/

    @Override
    public ChemicalsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Timber.e("onCreateViewHolder");


        return new ChemicalsHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_adapter, parent, false));

    }

    @Override
    public void onBindViewHolder(ChemicalsHolder holder, int position) {
        Timber.e("onBindViewHolder");

        holder.setChemical(properties.get(position));

    }

    @Override
    public int getItemCount() {
        return properties.size();
    }


    public class ChemicalsHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.chemicalFormula)
        ImageView chemicalFormula;

        @BindView(R.id.name)
        TextView name;


        @BindView(R.id.cidNumber)
        TextView cidNumber;

        @BindView(R.id.molecularWeight)
        TextView molcular_weight;


        public ChemicalsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setChemical(Property property) {
            // presenter = new ChemicalsPresenter(apiClient);

            Timber.e("setChemical");
            name.setText(property.getIUPACName());
            cidNumber.setText(property.getCID());
            molcular_weight.setText(property.getMolecularFormula());
            // chemicalFormula.setImageBitmap(presenter.getImage());


            String urlStr = "https://pubchem.ncbi.nlm.nih.gov/rest/pug/compound/cid/2244/PNG";
            String url = Uri.parse(urlStr)
                    .buildUpon()
                    .build()
                    .toString();

            Picasso.with(context)
                    .load(url)
                    .into(chemicalFormula);


            // chemicalFormula.setImageBitmap(bm);


        }


    }
}




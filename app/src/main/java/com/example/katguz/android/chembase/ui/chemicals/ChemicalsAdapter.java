package com.example.katguz.android.chembase.ui.chemicals;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.katguz.android.chembase.R;
import com.example.katguz.android.chembase.model.Chemical;
import com.example.katguz.android.chembase.model.Property;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChemicalsAdapter extends RecyclerView.Adapter<ChemicalsAdapter.ChemicalsHolder> {

   private List<Property> properties = new ArrayList<>();

    private Chemical chemical= new Chemical();

   public void setData(List<Property> data) {
        properties.clear();
        properties.addAll(data);
        notifyDataSetChanged();

    }




    @Override
    public ChemicalsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChemicalsHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_adapter, parent, false));

    }

    @Override
    public void onBindViewHolder(ChemicalsHolder holder, int position) {
        // holder.
        holder.setChemical(properties.get(position));
    }

    @Override
    public int getItemCount() {
        return properties.size();
    }


    public class ChemicalsHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        TextView name;

        //TODO Add some new textviews
        @BindView(R.id.cidNumber)
        TextView cidNumber;

        public ChemicalsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setChemical(Property property) {
            name.setText(property.getCanonicalSMILES());
            cidNumber.setText(property.getCID());
          /*
            textView1.setText(property.getCID());
            textView.setText(property.getCanonicalSMILES());*/

        }
    }
}

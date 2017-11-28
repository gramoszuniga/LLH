/*
    MaterialsRecyclerViewAdapter.java
    Assignment 2

    Revision History:
        Gonzalo Ramos Zúñiga, 2017.11.27: Created
 */

package ca.on.einfari.llh.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ca.on.einfari.llh.R;
import ca.on.einfari.llh.data.MaterialsListWithProduct;

public class MaterialsRecyclerViewAdapter extends RecyclerView.Adapter<MaterialsRecyclerViewAdapter.
        ViewHolder> {

    private List<MaterialsListWithProduct> materialsList;

    public MaterialsRecyclerViewAdapter(List<MaterialsListWithProduct> materialsList) {
        this.materialsList = materialsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.
                listitem_material, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.description.setText(materialsList.get(i).product.get(0).getDescription());
        viewHolder.quantity.setText("Qty: " + materialsList.get(i).materialsList.
                getQuantity());
        viewHolder.unit.setText(materialsList.get(i).product.get(0).getUnit());
    }

    @Override
    public int getItemCount() {
        return materialsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView description;
        TextView quantity;
        TextView unit;

        ViewHolder(View itemView) {
            super(itemView);
            this.description = itemView.findViewById(R.id.tvDescription);
            this.quantity = itemView.findViewById(R.id.tvQuantity);
            this.unit = itemView.findViewById(R.id.tvUnit);
        }
    }

}
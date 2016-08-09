package org.pharmart.madawamart;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.pharmart.madawamart.models.DrugModel;
import org.pharmart.madawamart.models.OrdersModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Orders extends Fragment {

    private RecyclerView orderList;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;


    public Orders() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_orders, container, false);

        orderList = (RecyclerView) view.findViewById(R.id.my_orders);
        mLayoutManager = new LinearLayoutManager(getActivity());
        orderList.setLayoutManager(mLayoutManager);

        OrdersModel order = new OrdersModel();
        order.setDrugName("Drug name: Paracetamol Tabs");
        order.setOrderStatus("STATUS: PENDING");
        order.setPharmacyName("From: Luguruni Dispensary");
        List<OrdersModel> orders = new ArrayList<>();
        orders.add(order);

        mAdapter = new OrdersAdapter(getActivity(),orders);
        orderList.setAdapter(mAdapter);
        return view;

    }

    public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder> {
        private LayoutInflater inflater;

        List<OrdersModel> data = Collections.emptyList();

        public OrdersAdapter(Context context, List<OrdersModel> data) {
            this.data = data;
            inflater = LayoutInflater.from(context);
        }


        protected  class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            private TextView drugName;
            private TextView pharmacyName;
            private Button orderStatus;

            public ViewHolder(View itemView) {
                super(itemView);
                drugName = (TextView) itemView.findViewById(R.id.drug_title);
                pharmacyName = (TextView) itemView.findViewById(R.id.pharmacy_name);
                orderStatus = (Button) itemView.findViewById(R.id.order_status);

            }

        }


        @Override
        public OrdersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.custom_order_item, parent,false);
            ViewHolder holder = new ViewHolder(view);

            return holder;
        }

        @Override
        public void onBindViewHolder(OrdersAdapter.ViewHolder holder, int position) {
            OrdersModel current = data.get(position);
            holder.drugName.setText(current.drugName);
            holder.pharmacyName.setText(current.pharmacyName);
            holder.orderStatus.setText(current.orderStatus);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }





}

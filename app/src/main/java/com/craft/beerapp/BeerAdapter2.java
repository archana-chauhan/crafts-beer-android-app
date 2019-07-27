package com.craft.beerapp;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

//public class BeerAdapter2 {
//}
//

public class BeerAdapter2 extends RecyclerView.Adapter<BeerAdapter2.PersonViewHolder> implements Filterable {
    List<Beer> persons;

    private Context context;
    private List<Beer> contactList;
    private List<Beer> contactListFiltered;
    private ContactsAdapterListener listener;

    BeerAdapter2(List<Beer> persons) {
        this.persons = persons;
    }


    public BeerAdapter2(Context context, List<Beer> contactList, ContactsAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.contactList = contactList;
        this.contactListFiltered = contactList;
    }


    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_beers, parent, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, final int position) {

        final Beer contact = contactListFiltered.get(position);

        holder.personName.setText(contact.getName());
        holder.personAge.setText(contact.getStyle());
    }

    @Override
    public int getItemCount() {
//        if (persons != null) {
//            return persons.size();
//        }
        return contactListFiltered.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
//                    persons = contactList;
                } else {
                    List<Beer> filteredList = new ArrayList<>();
                    for (Beer row : contactList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase()) || row.getStyle().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    contactListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = contactListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                contactListFiltered = (ArrayList<Beer>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface ContactsAdapterListener {
        void onContactSelected(Beer contact);
    }

    public class PersonViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView personName;
        TextView personAge;

        public PersonViewHolder(View itemView) {
            super(itemView);
//            cv = (CardView) itemView.findViewById(R.id.textViewTitle);
            personName = (TextView) itemView.findViewById(R.id.textViewTitle);
            personAge = (TextView) itemView.findViewById(R.id.textViewShortDesc);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onContactSelected(contactListFiltered.get(getAdapterPosition()));
                }
            });


        }
    }
}
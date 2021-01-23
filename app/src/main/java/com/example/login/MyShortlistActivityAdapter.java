package com.example.login;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.BreakIterator;
import java.util.ArrayList;

public class MyShortlistActivityAdapter extends RecyclerView.Adapter<MyShortlistActivityAdapter.viewHolder> {

    ArrayList<PersonProfile> personProfiles;
    final Context context;

    public MyShortlistActivityAdapter(ArrayList<PersonProfile> personProfiles, Context context) {
        this.personProfiles = personProfiles;
        this.context = context;
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.frame_profile, parent, false);
        return new MyShortlistActivityAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final viewHolder holder, int position) {
        final PersonProfile personProfile=personProfiles.get(position);
        int pos1=personProfiles.indexOf(personProfile);

        Picasso.with(context).load(personProfile.getPersonImg().replace("http","https")).fit().centerInside().into(holder.personImg);
        //holder.note.setText(carBookingModel.getNote());

       // holder.personImg.setImageResource(personProfile.getPersonImg());

        holder.personName.setText(personProfile.getPersonName());
        holder.personAge.setText(personProfile.getPersonAge());
        holder.personReligion.setText(personProfile.getPersonReligion());
        holder.personCast.setText(personProfile.getPersonCast());
        holder.personLocation.setText(personProfile.getPersonLocation());
        holder.personOccupation.setText(personProfile.getPersonOccupation());



    }

    @Override
    public int getItemCount() {
        return personProfiles.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView personImg;
        TextView personName;
        TextView personAge;
        TextView personReligion;
        TextView personLocation;
        TextView personOccupation;
        TextView personCast;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            personImg=itemView.findViewById(R.id.personImg);
            personName=itemView.findViewById(R.id.personName);
            personAge=itemView.findViewById(R.id.personAge);
            personReligion=itemView.findViewById(R.id.personReligion);
            personCast=itemView.findViewById(R.id.personCast);
            personLocation=itemView.findViewById(R.id.personLocation);
            personOccupation=itemView.findViewById(R.id.personOccupation);


        }
    }
}

package com.example.generalaeronotics;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Locale;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{
    private List<Model_class> users;
    private List<Image_Model> image_url;
    private Context con;


    public Adapter(List<Model_class> users, List<Image_Model> image_url, Context con) {
        this.users = users;
        this.image_url = image_url;
        this.con = con;
    }

    public Adapter(){}




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(con).inflate(R.layout.recycer_view_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String longi=users.get(position).getAddress().getGeo().getLng();
        String lati=users.get(position).getAddress().getGeo().getLat();
        holder.name.setText(users.get(position).getName());
        String address=String.valueOf(users.get(position).getAddress().getStreet()+", "+users.get(position).getAddress().getSuite()+", "+
                users.get(position).getAddress().getCity());
        holder.id.setText(String.valueOf(users.get(position).getId()));
        holder.address.setText(address);
        holder.longitude.setText("Longi: "+longi);
        holder.latitude.setText("Lati: "+lati);
        String url=image_url.get(position).getThumbnailUrl();

        /*Glide.with(con)
                .load(url)
                .dontTransform()
                .placeholder(R.drawable.error_image)
                .fitCenter()
                .dontAnimate()
                .into(holder.image);*/
        Picasso.get()
                .load(url)
                .placeholder(R.drawable.error_image)
                .fit()
                .error(R.drawable.error_image)
                .into(holder.image);
        holder.singleitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog=new AlertDialog.Builder(con);
                dialog.setTitle("Select an option to navigate.")
                        .setCancelable(true)
                        .setPositiveButton("Google Maps", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?q=loc:%f,%f", Double.parseDouble(lati),Double.parseDouble(longi));
                                Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse(uri));
                                intent.setPackage("com.google.android.apps.maps");
                                con.startActivity(intent);
                            }
                        }).setNegativeButton("Local Map", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent=new Intent(con,MapsActivity.class);
                        intent.putExtra("lati",Double.parseDouble(lati));
                        intent.putExtra("longi",Double.parseDouble(longi));
                        con.startActivity(intent);
                    }
                });
                dialog.create();
                dialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView image;
        private TextView name;
        private TextView address;
        private TextView id;
        private RelativeLayout singleitem;
        private TextView longitude;
        private TextView latitude;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image=itemView.findViewById(R.id.user_image);
            name=itemView.findViewById(R.id.user_name);
            address=itemView.findViewById(R.id.user_address);
            id=itemView.findViewById(R.id.id);
            singleitem=itemView.findViewById(R.id.single_item);
            latitude=itemView.findViewById(R.id.lati);
            longitude=itemView.findViewById(R.id.longi);
        }


    }


}

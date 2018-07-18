package com.akeditzz.musicplayer.musiclist.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.akeditzz.musicplayer.R;
import com.akeditzz.musicplayer.musiclist.interfaces.ISongList;
import com.akeditzz.musicplayer.musiclist.model.MusicModel;

import java.util.ArrayList;

public class SongListAdapter extends RecyclerView.Adapter<SongListAdapter.ViewHolder> implements Filterable {

    //Declaration
    private Context context;
    private ArrayList<MusicModel> originalList;
    private ArrayList<MusicModel> musicFilterList;
    private ISongList mCallBack;

    //Constructor
    public SongListAdapter(Context context, ArrayList<MusicModel> originalList, ISongList mCallBack) {
        this.context = context;
        this.originalList = originalList;
        this.musicFilterList = new ArrayList<>();
        this.musicFilterList.addAll(originalList);
        this.mCallBack = mCallBack;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.songitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // setting data
        holder.tvSongName.setText(musicFilterList.get(position).getSongName());
        holder.tvArtistName.setText(musicFilterList.get(position).getArtistName());
        holder.tvSongDuration.setText(musicFilterList.get(position).getEndTime());
        holder.ivThumb.setImageResource(musicFilterList.get(position).getThumbnail());
    }

    @Override
    public int getItemCount() {
        return musicFilterList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString.isEmpty()) {
                    musicFilterList = originalList;
                } else {
                    ArrayList<MusicModel> filteredList = new ArrayList<>();
                    for (MusicModel row : originalList) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getSongName().toLowerCase().contains(charString.toLowerCase()) || row.getArtistName().toLowerCase().contains(charString)) {
                            filteredList.add(row);
                        }
                    }
                    musicFilterList = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = musicFilterList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                musicFilterList = (ArrayList<MusicModel>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        //Declaration
        TextView tvSongName;
        TextView tvArtistName;
        TextView tvSongDuration;
        ImageView ivThumb;

        ViewHolder(View itemView) {
            super(itemView);
            //Initialization
            tvSongName = itemView.findViewById(R.id.tv_song_name);
            tvArtistName = itemView.findViewById(R.id.tv_artist_name);
            tvSongDuration = itemView.findViewById(R.id.tv_song_duration);
            ivThumb = itemView.findViewById(R.id.iv_thumb);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //sending data through interface
                    mCallBack.musicModel(musicFilterList.get(getAdapterPosition()));
                }
            });
        }
    }


}

package test.sdrc.com.mobilewebservicedemo.adapter;

/**
 * Created by SDRC_DEV on 05-07-2017.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import test.sdrc.com.mobilewebservicedemo.R;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.MyViewHolder> {



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);

        }
    }


    public TestAdapter() {

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.test_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

       /// holder.title.setText(movie.getTitle());
       // holder.genre.setText(movie.getGenre());
        //holder.year.setText(movie.getYear());
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}

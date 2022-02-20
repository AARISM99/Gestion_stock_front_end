package com.example.scolarite.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scolarite.Model.Article;
import com.example.scolarite.R;

import java.util.List;

public class RecycleAdapter   extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {

  Context context;
  List<Article> articleList;

  public RecycleAdapter(Context context, List<Article> articleList) {
    this.context = context;
    this.articleList = articleList;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    if(articleList.size()>0  && articleList!=null){
      Article article = articleList.get(position);
      holder.id_id.setText(article.getId().toString());
      holder.id_label.setText(article.getLabel());
      holder.id_pu.setText(String.valueOf(article.getPu()));
    }else {
      return;
    }
  }

  @Override
  public int getItemCount() {
    return articleList.size();
  }

  public  class  ViewHolder extends RecyclerView.ViewHolder {
    TextView id_id,id_label,id_pu;

    public ViewHolder(View itemView) {
      super(itemView);
      id_id = itemView.findViewById(R.id.id_id);
      id_label = itemView.findViewById(R.id.id_label);
      id_pu = itemView.findViewById(R.id.id_pu);
    }
  }
}

package com.example.scolarite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scolarite.Api.Methods;
import com.example.scolarite.Api.RetrofitClient;
import com.example.scolarite.Controller.RecycleAdapter;
import com.example.scolarite.Model.Article;
import com.example.scolarite.Model.Categories;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {
  public static final String DESIGNATION = "";
  private static final String TAG = "MainActivity";
  private Button buttonadd;
  private TextView designation;
  private RecyclerView recyclerView;
  private RecycleAdapter recycleAdapter;
  private Spinner spinner;
  private Categories categorie;
    @Override
  protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      this.recyclerView =  findViewById(R.id.recycler_view) ;
      this.spinner = (Spinner) findViewById(R.id.is_spinner);
      fetchCategories();
     // fetchdata();
      setRecycleView();
      this.designation = (TextView) findViewById(R.id.id_designation);
      this.buttonadd =(Button) findViewById(R.id.add);
      this.buttonadd.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          openActivityAdd();
        }
      });
  }
  private void setSpinner( List<Categories> categories){
    ArrayAdapter<Categories> adapter  = new ArrayAdapter<Categories>(this, android.R.layout.simple_spinner_item,categories);
      //ArrayAdapter.createFromResource(
      //this,R.array.Categories,android.R.layout.simple_spinner_item);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner.setAdapter(adapter);
    spinner.setOnItemSelectedListener(this);
  }

  private void setRecycleView() {

      RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
      recyclerView.setLayoutManager(layoutManager);
      recyclerView.setItemAnimator(new DefaultItemAnimator());

  }


  public void openActivityAdd(){
      String designationValue = this.designation.getText().toString();
      Intent intent = new Intent(this,add_article.class);
      intent.putExtra(this.DESIGNATION,categorie);
      startActivity(intent);
    }
    private void fetchCategories(){
      Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
      Call<List<Categories>> call = methods.getAllCatgories();
      call.enqueue(new Callback<List<Categories>>() {
        @Override
        public void onResponse(Call<List<Categories>> call, Response<List<Categories>> response) {
          if(!response.isSuccessful()){
            Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_SHORT).show();
            return ;
          }
          List<Categories> categoriesList = (List<Categories>) response.body();
          setSpinner(categoriesList);
        }

        @Override
        public void onFailure(Call<List<Categories>> call, Throwable t) {
          Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
        }
      });
    }
    private void fetchdata(){

      Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
      Call<List<Article>> call = methods.getAll();

      call.enqueue(new Callback <List<Article>>() {
        @Override
        public void onResponse(Call<List<Article>> call, Response <List<Article>> response) {
         if(!response.isSuccessful()){
           Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_SHORT).show();
           return ;
         }
         List<Article> articleList = (List<Article>) response.body();
          recycleAdapter = new RecycleAdapter(MainActivity.this,articleList);
          recyclerView.setAdapter(recycleAdapter);
        }

        @Override
        public void onFailure(Call <List<Article>> call, Throwable t) {
          Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
        }
      });
    }
    private void filter(int id_des){
      Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
      Call<List<Article>> call = methods.filter(id_des);

      call.enqueue(new Callback <List<Article>>() {
        @Override
        public void onResponse(Call<List<Article>> call, Response <List<Article>> response) {
          if(!response.isSuccessful()){
            Toast.makeText(MainActivity.this, String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
            return ;
          }
          List<Article> articleList = (List<Article>) response.body();
          recycleAdapter = new RecycleAdapter(MainActivity.this,articleList);
          recyclerView.setAdapter(recycleAdapter);
        }

        @Override
        public void onFailure(Call <List<Article>> call, Throwable t) {
          Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
        }
      });

    }

  @Override
  public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
      categorie = (Categories) adapterView.getItemAtPosition(i);
      Toast.makeText(MainActivity.this, String.valueOf(categorie.getDesignation()), Toast.LENGTH_SHORT).show();
      designation.setText(adapterView.getItemAtPosition(i).toString());
      filter(categorie.getId());

  }

  @Override
  public void onNothingSelected(AdapterView<?> adapterView) {

  }
}


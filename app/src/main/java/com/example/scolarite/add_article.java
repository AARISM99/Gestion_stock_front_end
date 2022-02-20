package com.example.scolarite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scolarite.Api.Methods;
import com.example.scolarite.Api.RetrofitClient;
import com.example.scolarite.Model.Article;
import com.example.scolarite.Model.Categories;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class add_article extends AppCompatActivity {
  private   EditText label,price;
  private  Button cancelbtn,addbtn;
  private TextView designation;
  private  Categories categories;
  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_article);
       // Intent intent = getIntent();
         this.categories = getIntent().getParcelableExtra(MainActivity.DESIGNATION);


        this.designation = (TextView) findViewById(R.id.designation);
        this.designation.setText(this.categories.getDesignation());
        // cancel button
         this.cancelbtn = (Button) findViewById(R.id.cancel_button);
         cancelbtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            openMainActivity();
          }
        });


        // Label
         this.label = (EditText) findViewById(R.id.labeled);
        // price
         this.price = (EditText) findViewById(R.id.unit_price);
        // add button
         this.addbtn = (Button) findViewById(R.id.add_button);
        addbtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            try {
              String priceValue =   price.getText().toString();
              String labelValue = label.getText().toString();
              addArticle(labelValue,Integer.parseInt(priceValue),categories.getId());
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        });
    }


    public void openMainActivity(){
      Intent intent = new Intent(this,MainActivity.class);
      intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
      startActivity(intent);
    }
    private void addArticle(String label,int pu, int id_des){
      Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
      Article article = new Article(label.toString(),pu,id_des);
      Call<Article> call = methods.postArticle(article);
      call.enqueue(new Callback<Article>() {
        @Override
        public void onResponse(Call<Article> call, Response<Article> response) {
         if(response.isSuccessful()){
           Toast.makeText(add_article.this, "Successfully added a new article", Toast.LENGTH_SHORT).show();
         }
        }
        @Override
        public void onFailure(Call<Article> call, Throwable t) {
          Toast.makeText(add_article.this, "Failed to add the article", Toast.LENGTH_SHORT).show();
        }
      });
    }
}

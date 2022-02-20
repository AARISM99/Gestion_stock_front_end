package com.example.scolarite.Controller;

import com.example.scolarite.Model.Categories;

public interface ICCategories {
  Categories getAll();
  void createCat();
  void deleteCategories();
  void updateCat();
}


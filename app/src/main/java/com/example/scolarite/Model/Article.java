package com.example.scolarite.Model;

public class Article implements  IArticle {
  private int id;
  private String label;
  private int pu;
  private int iddes;

  public Article(int id, String label, int pu, int id_cat) {
    this.id = id;
    this.label = label;
    this.pu = pu;
    this.iddes = id_cat;
  }

  public Article(String label, int pu, int id_cat) {
    this.label = label;
    this.pu = pu;
    this.iddes = id_cat;
  }

  public Article() {
  }

  @Override
  public String getLabel() {
    return this.label;
  }

  @Override
  public int getIdcatogorie() {
    return this.iddes;
  }

  @Override
  public int getPu() {
    return this.pu;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public void setPu(int pu) {
    this.pu = pu;
  }
}

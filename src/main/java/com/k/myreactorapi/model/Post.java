package com.k.myreactorapi.model;

import org.springframework.data.annotation.Id;

public class Post {
  @Id
  private Integer id;
  private String author;
  private String article;

  public Post() {
  }

  public Post(Integer id, String author, String article) {
    this.id = id;
    this.author = author;
    this.article = article;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getArticle() {
    return article;
  }

  public void setArticle(String article) {
    this.article = article;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Override public String toString() {
    return "Post{" + "id=" + id
        + ", author='" + author + '\''
        + ", article='" + article + '\''
        + '}';
  }
}

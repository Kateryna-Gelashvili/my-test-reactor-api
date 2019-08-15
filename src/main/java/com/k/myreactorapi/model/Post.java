package com.k.myreactorapi.model;

public class Post {
  private Integer id;
  private String author;
  private String article;

  public Post() {
  }

  public Post(Integer id, String author, String article) {
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

  @Override public String toString() {
    final StringBuilder sb = new StringBuilder("Post{");
    sb.append("author='").append(author).append('\'');
    sb.append(", article='").append(article);
    sb.append('}');
    return sb.toString();
  }
}

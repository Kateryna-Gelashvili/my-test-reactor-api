package com.k.myreactorapi.model;

import java.time.Instant;

public class Post {
  private String author;
  private String article;
  private Instant createdAt;

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

  public Instant getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }

  @Override public String toString() {
    final StringBuilder sb = new StringBuilder("Post{");
    sb.append("author='").append(author).append('\'');
    sb.append(", article='").append(article).append('\'');
    sb.append(", createdAt=").append(createdAt);
    sb.append('}');
    return sb.toString();
  }
}

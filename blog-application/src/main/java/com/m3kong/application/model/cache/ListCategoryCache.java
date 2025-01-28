package com.m3kong.application.model.cache;

import com.m3kong.application.model.dto.Category;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ListCategoryCache implements Serializable {
  private static final long serialVersionUID = 1L; // Khuyến nghị thêm serialVersionUID
  private Long version;
  private List<Category> categories;

  public ListCategoryCache withClone(List<Category> categories) {
    this.categories = categories;
    return this;
  }

  public ListCategoryCache withVersion(Long version) {
    this.version = version;
    return this;
  }
}

package com.m3kong.application.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class Category implements Serializable {
  private static final long serialVersionUID = 1L; // Khuyến nghị thêm serialVersionUID
  private int id;
  private String name;
  private String localizeName;
  private String localizeCode;
  private String slug;
}

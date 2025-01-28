package com.m3kong.application.Utils;

import java.text.Normalizer;
import java.util.Locale;

public class SlugUtil {

  public static String toSlug(String input) {
    if (input == null || input.isEmpty()) {
      return "";
    }

    // Chuyển thành chữ thường
    String slug = input.toLowerCase(Locale.ROOT);

    // Chuẩn hóa ký tự tiếng Việt sang không dấu
    slug = Normalizer.normalize(slug, Normalizer.Form.NFD);
    slug = slug.replaceAll("\\p{M}", ""); // Loại bỏ dấu

    // Loại bỏ ký tự đặc biệt (giữ lại chữ cái, số và khoảng trắng)
    slug = slug.replaceAll("[^a-z0-9\\s-]", "");

    // Thay khoảng trắng bằng dấu gạch ngang
    slug = slug.replaceAll("\\s+", "-");

    // Xóa dấu gạch ngang thừa ở đầu/cuối
    slug = slug.replaceAll("^-|-$", "");

    return slug;
  }
}

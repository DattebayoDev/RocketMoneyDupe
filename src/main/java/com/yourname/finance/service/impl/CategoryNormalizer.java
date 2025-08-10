package com.yourname.finance.service.impl;

import com.yourname.finance.model.Category;
import com.yourname.finance.service.ICategoryNormalizer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CategoryNormalizer implements ICategoryNormalizer {

  private static final Map<Category, List<String>> mappings =
      Map.of(
          Category.FOOD,
          List.of("grocery", "supermarket"),
          Category.TRANSPORTATION,
          List.of("transport", "lyft", "uber"));

  @Override
  public Category normalize(String raw) {
    if (raw == null) return Category.UNCATEGORIZED;
    String lower = raw.toLowerCase();

    for (Map.Entry<Category, List<String>> entry : mappings.entrySet()) {
      for (String value : entry.getValue()) {
        if (lower.contains(value)) {
          return entry.getKey();
        }
      }
    }
      return Category.UNCATEGORIZED;
  }
}


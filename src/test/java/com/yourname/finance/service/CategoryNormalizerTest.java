package com.yourname.finance.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.yourname.finance.model.Category;
import com.yourname.finance.service.impl.CategoryNormalizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CategoryNormalizerTest {
  private CategoryNormalizer normalizer;

  @BeforeEach
  void setUp() {
    normalizer = new CategoryNormalizer();
  }

  @Test
  void testNormalizeFoodCategory() {
    String raw = "supermarket";
    Category expected = Category.FOOD;
    Category result = normalizer.normalize(raw);
    assertEquals(expected, result);
  }

  @Test
  void testNormalizeUncategorized() {
    String raw = "random stuff";
    Category expected = Category.UNCATEGORIZED;
    Category result = normalizer.normalize(raw);
    assertEquals(expected, result);
  }
}

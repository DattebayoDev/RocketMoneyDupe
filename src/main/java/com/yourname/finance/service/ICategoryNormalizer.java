package com.yourname.finance.service;

import com.yourname.finance.model.Category;

public interface ICategoryNormalizer {

    Category normalize(String raw);
}


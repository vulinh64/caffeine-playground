package com.vulinh.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CacheConstant {

    public static final String CAFFEINE_PROPERTIES = "spring.cache.caffeine";

    public static final String FIND_BY_ID_CACHE = "find-by-id-cache";
    public static final String FIND_BY_ID_GEN_BEAN = "find-by-id-bean";

    public static final String FIND_ALL_PAGING = "find-all-paging-cache";
    public static final String FIND_ALL_PAGING_GEN_BEAN = "find-all-paging-bean";
}
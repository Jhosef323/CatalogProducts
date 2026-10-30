package com.catalogproducts.catalogproducts;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class OrdMap<K, E> {
    public Map<K, E> orderMap(Map<K, E> map, Comparator<Map.Entry<K, E>> comparator){
        if(map == null || map.isEmpty()){
            return new LinkedHashMap<>();
        }

        return map.entrySet().stream()
                .sorted(comparator)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }
}


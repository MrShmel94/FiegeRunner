package com.example.fiegerunner.entity;

import java.util.List;

public record ProcessDetail(
        Integer id,
        List<Integer> values
) {
}

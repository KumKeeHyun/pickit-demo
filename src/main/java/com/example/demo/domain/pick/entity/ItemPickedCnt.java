package com.example.demo.domain.pick.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemPickedCnt {

    private Long itemId;
    private Long pickedCnt;
}

package com.example.demo.pick;

import com.example.demo.user.Picker;
import lombok.Getter;

@Getter
public class PickEvent {

    private final Pick pick;
    private final Picker picker;

    public PickEvent(Pick pick, Picker picker) {
        this.pick = pick;
        this.picker = picker;
    }

}

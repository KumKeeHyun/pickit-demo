package com.example.demo.pick;

import com.example.demo.user.Picker;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PickerPick {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Picker picker;

    @ManyToOne
    @JoinColumn
    private Pick pick;

    public PickerPick(Picker picker, Pick pick) {
        this(null, picker, pick);
    }
}

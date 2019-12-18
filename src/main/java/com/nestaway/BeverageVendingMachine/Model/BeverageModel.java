package com.nestaway.BeverageVendingMachine.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="beverage", schema="bvm")
public class BeverageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="b_id")
    private int b_id;

    @Column(name="b_name")
    private String b_name;

    @Column(name="status")
    private boolean status;

    @OneToMany
    private List<IngredientsModel> ingredientsModel;
}

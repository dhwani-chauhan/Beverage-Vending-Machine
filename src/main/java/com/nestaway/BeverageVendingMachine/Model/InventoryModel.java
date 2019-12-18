package com.nestaway.BeverageVendingMachine.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="inventory", schema = "bvm")
public class InventoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="i_id")
    private int i_id;

    @Column(name="i_name")
    private String i_name;

    @Column(name="total_quantity")
    private int total_quantity;

    @OneToOne
    private IngredientsModel ingredientsModel;
}

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
@Table(name="ingredients", schema="bvm")
public class IngredientsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="in_id")
    private int in_id;

    @Column(name="b_id")
    private  int b_id;

    @Column(name="i_id")
    private int i_id;

    @Column(name="quantity_required")
    private int quantity_required;

    @ManyToOne
    private BeverageModel beverageModel;

    @OneToOne
    private InventoryModel inventoryModel;

}

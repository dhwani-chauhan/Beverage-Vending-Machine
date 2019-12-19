package com.nestaway.BeverageVendingMachine.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private Long inId;

    @Column(name="quantity_required")
    private Integer quantityRequired;

    @ManyToOne
    @JsonBackReference
    private BeverageModel beverageModel;

    @ManyToOne
    private InventoryModel inventoryModel;

}

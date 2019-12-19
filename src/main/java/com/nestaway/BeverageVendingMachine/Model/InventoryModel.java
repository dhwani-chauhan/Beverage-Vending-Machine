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
    private Long iId;

    @Column(name="i_name")
    private String iName;

    @Column(name="total_quantity")
    private Integer totalQuantity;

}

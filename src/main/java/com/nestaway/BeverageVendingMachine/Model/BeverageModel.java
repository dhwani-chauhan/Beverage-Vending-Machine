package com.nestaway.BeverageVendingMachine.Model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString
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
}

package com.nestaway.BeverageVendingMachine.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private Long bId;

    @Column(name="b_name")
    private String bName;

    @Column(name="status")
    private boolean status;

    @OneToMany(mappedBy = "beverage")
    @JsonManagedReference
    private List<IngredientsModel> ingredientsModel;
}

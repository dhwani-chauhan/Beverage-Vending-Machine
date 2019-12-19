package com.nestaway.BeverageVendingMachine.Repository;

import com.nestaway.BeverageVendingMachine.Model.IngredientsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientsRepo extends JpaRepository<IngredientsModel, Long> {
}

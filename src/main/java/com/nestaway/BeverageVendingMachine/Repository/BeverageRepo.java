package com.nestaway.BeverageVendingMachine.Repository;

import com.nestaway.BeverageVendingMachine.Model.BeverageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeverageRepo extends JpaRepository<BeverageModel, Long> {
}

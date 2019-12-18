package com.nestaway.BeverageVendingMachine.Repository;

import com.nestaway.BeverageVendingMachine.Model.InventoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepo extends JpaRepository<InventoryModel, Integer> {
}

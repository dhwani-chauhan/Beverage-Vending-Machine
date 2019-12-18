package com.nestaway.BeverageVendingMachine.Service;

import com.nestaway.BeverageVendingMachine.Model.InventoryModel;
import com.nestaway.BeverageVendingMachine.Repository.InventoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepo inventory;

    public InventoryModel addInventory(InventoryModel inven){
        return inventory.save(inven);
    }

    public InventoryModel updateInventory(InventoryModel inven) {
        return inventory.save(inven);
    }

    public void deleteInventory(InventoryModel inven){
        inventory.delete(inven);
    }

    public List<InventoryModel> showAll(){
       return inventory.findAll();
    }

    public Optional<InventoryModel> findByIid(int id){
        return   inventory.findById(id);
    }

}

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
    private InventoryRepo inventoryRepo;

    public InventoryModel addInventory(InventoryModel inventory) throws Exception{
        if(inventory == null) {
            throw new Exception("No inventory received");
        }
        if(inventory.getIName() == null || inventory.getTotalQuantity() == null)
            throw new Exception("Some value(s) are missing, Kindly refer to the manual");
        return inventoryRepo.save(inventory);
    }

    public InventoryModel updateInventory(InventoryModel inventory) throws Exception {
        if(inventory == null) {
            throw new Exception("No inventory received");
        }
        InventoryModel inventories1 = findByIid(inventory.getIId());

        if(inventory.getIName() == null)
            inventory.setIName(inventories1.getIName());

        if(inventory.getTotalQuantity() == null)
            inventory.setTotalQuantity(inventories1.getTotalQuantity());

        return inventoryRepo.save(inventory);
    }

    public boolean deleteInventory(Long id) throws Exception{
        InventoryModel inventories = findByIid(id);
        try {
            inventoryRepo.delete(inventories);
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }

    public List<InventoryModel> showAll() throws Exception{
        List<InventoryModel> listOfInventories = inventoryRepo.findAll();
        if(listOfInventories.isEmpty()) {
            throw new Exception("NO Inventories Found");
        }
        return listOfInventories;
    }

    public InventoryModel findByIid(Long id) throws Exception{
        Optional<InventoryModel> inventories = inventoryRepo.findById(id);
        if(!inventories.isPresent())
            throw new Exception("No such inventory exist");
        return inventories.get();
    }

    public InventoryModel updateQuantity(Long inventoryId, Integer quantity) throws Exception {
        InventoryModel inventories = findByIid(inventoryId);
        inventories.setTotalQuantity(inventories.getTotalQuantity() + quantity);
        return inventoryRepo.save(inventories);
    }

    public boolean checkEmpty(Long inventoryId) throws Exception {
        try {
            InventoryModel inventories = findByIid(inventoryId);
            return inventories.getTotalQuantity() == 0;
        }
        catch (NullPointerException ex) {
            throw new Exception("No such inventory exist");
        }
    }

    public boolean checkQuantity(Long inventoryId, Integer quantity) throws Exception {
        try {
            InventoryModel inventories = findByIid(inventoryId);
            return inventories.getTotalQuantity() >= quantity;
        }
        catch (NullPointerException ex) {
            throw new Exception("No such inventory exist");
        }
    }

    public List<InventoryModel> getAllEmptiedInventories() throws Exception {
        try {
            List<InventoryModel> list = showAll();
            list.removeIf(n -> (n.getTotalQuantity() > 1));
            if(list.isEmpty())
                throw new Exception("Stock is available");
            return list;
        }
        catch (NullPointerException ex) {
            throw new Exception("Unexpected Error");
        }
    }

    public InventoryModel reduceInventories(InventoryModel inventories, Integer reduction) {
        inventories.setTotalQuantity(Math.abs(inventories.getTotalQuantity() - reduction));
        return inventoryRepo.save(inventories);
    }
}

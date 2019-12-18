package com.nestaway.BeverageVendingMachine.Controller;

import com.nestaway.BeverageVendingMachine.Model.InventoryModel;
import com.nestaway.BeverageVendingMachine.Service.InventoryService;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/inventory")
public class InventoryCont {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping(path="/add", consumes = "application/json")
    public InventoryModel addInventory(@RequestBody InventoryModel inventory) throws Exception{
        if(inventory == null){
            throw new Exception("No Inventory Received");
        }
        return inventoryService.addInventory(inventory);
    }

    @PostMapping(path="/update", consumes = "application/json")
    public InventoryModel updateInventory(@RequestBody InventoryModel inventory) throws Exception{
        if(inventory == null){
            throw new Exception("No Inventory Received");
        }
        return inventoryService.updateInventory(inventory);
    }

    @DeleteMapping(path="/delete", consumes = "application/json")
    public void deleteInventory(@RequestBody InventoryModel inventory) throws Exception{
        if(inventory == null){
            throw new Exception("No Inventory Received");
        }
        inventoryService.deleteInventory(inventory);
    }

    @PostMapping(path="/find/{id}", produces="application/json")
    public ResponseEntity findById(@RequestBody int id)throws Exception{
        Optional<InventoryModel> inventory = inventoryService.findByIid(id);
        if(inventory == null){
            throw new Exception("No Match Found Of given Id");
        }
        return ResponseEntity.ok(inventory);
    }

    @PostMapping(path="/findall", produces="application/json")
    public List<InventoryModel> showAll() throws Exception{
        List<InventoryModel> listofInventory =inventoryService.showAll();
        if(listofInventory == null){
            throw new Exception("No Inventory Found");
        }
        return listofInventory;
    }
}

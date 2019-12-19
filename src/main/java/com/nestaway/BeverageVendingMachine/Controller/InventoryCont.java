package com.nestaway.BeverageVendingMachine.Controller;

import com.nestaway.BeverageVendingMachine.Model.InventoryModel;
import com.nestaway.BeverageVendingMachine.Service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/inventory")
public class InventoryCont {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping(path="/add")
    public ResponseEntity addInventory(@RequestBody InventoryModel inventory) {
        try {
            InventoryModel added = inventoryService.addInventory(inventory);
            return correctResponse(added,HttpStatus.OK,HttpStatus.OK.value(),"Success",HttpStatus.OK);
        }
        catch(Exception ex) {
            return errorResponse(ex);
        }
    }

    @PutMapping(path="/update")
    public ResponseEntity updateInventory(@RequestBody InventoryModel inventory) {
        try {
            InventoryModel added = inventoryService.updateInventory(inventory);
            return correctResponse(added,HttpStatus.OK,HttpStatus.OK.value(),"Success",HttpStatus.OK);
        }
        catch (Exception ex) {
            return errorResponse(ex);
        }
    }

    @DeleteMapping(path="/delete/{param}")
    public ResponseEntity deleteInventory(@PathVariable Long parameter) throws Exception{
        try {
            if (inventoryService.deleteInventory(parameter)) {
                return correctResponse("Inventory deleted successfully",HttpStatus.OK,HttpStatus.OK.value(),"Success",HttpStatus.OK);
            } else {
                return correctResponse("Inventory not deleted",HttpStatus.BAD_REQUEST,HttpStatus.BAD_REQUEST.value(),"No such Inventory exist",HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception ex){
            return errorResponse(ex);
        }
    }

    @PostMapping(path="/find/{id}", produces="application/json")
    public ResponseEntity findById(@RequestBody Long id){
        try {
            InventoryModel inv = inventoryService.findByIid(id);
            return correctResponse(inv,HttpStatus.OK,HttpStatus.OK.value(),"Success",HttpStatus.OK);
        }
        catch (Exception ex){
            return errorResponse(ex);
        }
    }

    @GetMapping(path = "/increaseQuantity/{inventory}/{quantity}")
    public ResponseEntity updateQuantity(@PathVariable Long inventory, @PathVariable Integer quantity) {
        try {
            InventoryModel inventories = inventoryService.updateQuantity(inventory, quantity);
            if (inventories == null) {
                return correctResponse("Null",HttpStatus.NO_CONTENT,HttpStatus.NO_CONTENT.value(),"No Such Inventory Exist",HttpStatus.NO_CONTENT);
            } else {
                return correctResponse(inventories,HttpStatus.OK,HttpStatus.OK.value(),"Success",HttpStatus.OK);
            }
        }
        catch (Exception ex) {
            return errorResponse(ex);
        }
    }

    @GetMapping(path = "/checkEmpty/{inventory}")
    public ResponseEntity checkEmpty(@PathVariable Long inventory) {
        try {
            HashMap<Object, Object> obj = new HashMap<>();
            boolean output = inventoryService.checkEmpty(inventory);
            obj.put("value", output);
            String message;
            if(!output) {
                obj.put("inventory", inventoryService.findByIid(inventory));
                message = "Inventory is not empty";
            }
            else
                message = "Inventory is empty";
            return correctResponse(obj,false,HttpStatus.OK.value(),message,HttpStatus.OK);
        }
        catch (Exception ex) {
            return errorResponse(ex);
        }
    }

    @GetMapping(path = "/checkQuantity/{inventory}/{quantity}")
    public ResponseEntity checkQuantity(@PathVariable Long inventory, @PathVariable Integer quantity) {
        try {
            HashMap<Object, Object> obj = new HashMap<>();
            boolean output = inventoryService.checkQuantity(inventory, quantity);
            obj.put("value",output);
            String message;
            if (output) {
                obj.put("inventory", inventoryService.findByIid(inventory));
                message = "Inventory is sufficient";
            } else {
                message = "Inventory is not sufficient";
            }
            return correctResponse(obj,false,HttpStatus.OK.value(),message,HttpStatus.OK);
        }
        catch (Exception ex) {
            return errorResponse(ex);
        }
    }

    @GetMapping(path = "/emptyInventories")
    public ResponseEntity getEmptyInventories() {
        try {
            List<InventoryModel> list = inventoryService.getAllEmptiedInventories();
            return correctResponse(list,false,HttpStatus.OK.value(),"Success",HttpStatus.OK);
        }
        catch(Exception ex) {
            return errorResponse(ex);
        }
    }

    @GetMapping("/")
    public ResponseEntity getAllInventories() {
        HashMap<Object, Object> response = new HashMap<>();
        try {
            List<InventoryModel> list = inventoryService.showAll();
            return correctResponse(list,HttpStatus.OK,HttpStatus.OK.value(),"Success",HttpStatus.OK);
        }
        catch(Exception ex) {
            return errorResponse(ex);
        }
    }

    public ResponseEntity correctResponse(Object value, Object error, int statusCode, String message, HttpStatus status) {
        HashMap<Object, Object> response = new HashMap<>();
        response.put("value", value);
        response.put("error", error);
        response.put("status", statusCode);
        response.put("message", message);
        return ResponseEntity.status(status).body(response);
    }

    public ResponseEntity errorResponse(Exception ex) {
        HashMap<Object, Object> response = new HashMap<>();
        response.put("timestamp", new Date());
        response.put("error", HttpStatus.INTERNAL_SERVER_ERROR);
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}

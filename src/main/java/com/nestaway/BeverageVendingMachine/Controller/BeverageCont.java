package com.nestaway.BeverageVendingMachine.Controller;

import com.nestaway.BeverageVendingMachine.Model.BeverageModel;
import com.nestaway.BeverageVendingMachine.Model.IngredientsModel;
import com.nestaway.BeverageVendingMachine.Model.InventoryModel;
import com.nestaway.BeverageVendingMachine.Service.BeverageService;
import com.nestaway.BeverageVendingMachine.Service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/beverage")
public class BeverageCont {

    @Autowired
    private BeverageService beverageService;

    @Autowired
    private InventoryService inventoryService;

    @PostMapping(path = "/add")
    public ResponseEntity addBeverage(@RequestBody BeverageModel beverage) {
        try {
            BeverageModel beverage1 = beverageService.addBeverage(beverage);
            return correctResponse(beverage1,HttpStatus.OK,HttpStatus.OK.value(),"Success",HttpStatus.OK);
        }
        catch (Exception ex) {
            return errorResponse(ex);
        }
    }

    @PutMapping(path = "/update")
    public ResponseEntity updateBeverage(@RequestBody BeverageModel beverage) {
        try {
            BeverageModel beverage1 = beverageService.updateBeverage(beverage);
            return correctResponse(beverage1,HttpStatus.OK,HttpStatus.OK.value(),"Success",HttpStatus.OK);
        }
        catch (Exception ex) {
            return errorResponse(ex);
        }
    }

    @DeleteMapping(path="/delete/{param}")
    public ResponseEntity deleteBeverage(@PathVariable Long param) {
        try {
            beverageService.deleteBeverage(param);
            return correctResponse("Beverage deleted successfully",HttpStatus.OK,HttpStatus.OK.value(),"Success",HttpStatus.OK);
        }
        catch (Exception ex) {
            return errorResponse(ex);
        }
    }

    @GetMapping(path="/find/{id}")
    public ResponseEntity findById(@RequestBody Long id) throws Exception{
        try {
            BeverageModel beverage = beverageService.findById(id);
            return correctResponse(beverage,HttpStatus.OK,HttpStatus.OK.value(),"Success",HttpStatus.OK);
        }
        catch (Exception ex) {
            return errorResponse(ex);
        }
    }

    @GetMapping("/")
    public ResponseEntity getAllBeverages() {
        try {
            List<BeverageModel> list = beverageService.showAll();
            return correctResponse(list,HttpStatus.OK, HttpStatus.OK.value(),"Success",HttpStatus.OK);
        }
        catch (Exception ex) {
            return errorResponse(ex);
        }
    }

    @GetMapping("/available/{id}")
    public ResponseEntity checkAvailability(@PathVariable Long id) {
        try {
            boolean output = beverageService.checkAvailability(id);
            return correctResponse(output,HttpStatus.OK,HttpStatus.OK.value(),"Success",HttpStatus.OK);
        }
        catch (Exception ex) {
            return errorResponse(ex);
        }
    }

    @GetMapping("/available")
    public ResponseEntity checkAvailability() {
        try {
            List<BeverageModel> beverageList = beverageService.checkAvailability();
            return correctResponse(beverageList,HttpStatus.OK,HttpStatus.OK.value(),"Success",HttpStatus.OK);
        }
        catch (Exception ex) {
            return errorResponse(ex);
        }
    }

    @GetMapping("/order/{id}")
    public ResponseEntity orderBeverage(@PathVariable Long id) {
        try {
            if(!beverageService.checkAvailability(id)) {
                List<BeverageModel> beverageList = beverageService.checkAvailability();
                return correctResponse(beverageList,HttpStatus.BAD_REQUEST,HttpStatus.BAD_REQUEST.value(),"Requested Beverage is not available. Here are the beverages which are available",HttpStatus.BAD_REQUEST);
            }
            else {
                BeverageModel beverage = beverageService.findById(id);
                List<IngredientsModel> ingredientsList = beverage.getIngredientsModel();
                for(IngredientsModel i : ingredientsList) {
                    InventoryModel inventories = inventoryService.reduceInventories(i.getInventoryModel(),i.getQuantityRequired());
                    inventoryService.updateInventory(inventories);
                }
                beverageService.updateList();
                return correctResponse(beverage,HttpStatus.OK,HttpStatus.OK.value(),"Success",HttpStatus.OK);
            }
        }
        catch(Exception ex) {
            return errorResponse(ex);
        }
    }

    private ResponseEntity errorResponse(Exception ex) {
        HashMap<Object, Object> response = new HashMap<>();
        response.put("timestamp", new Date());
        response.put("error", HttpStatus.INTERNAL_SERVER_ERROR);
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    private ResponseEntity correctResponse(Object value, Object error, int statusCode, String message, HttpStatus status) {
        HashMap<Object, Object> response = new HashMap<>();
        response.put("value", value);
        response.put("error", error);
        response.put("status", statusCode);
        response.put("message", message);
        return ResponseEntity.status(status).body(response);
    }
}

package com.nestaway.BeverageVendingMachine.Controller;

import com.nestaway.BeverageVendingMachine.Model.BeverageModel;
import com.nestaway.BeverageVendingMachine.Service.BeverageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/beverage")
public class BeverageCont {

    @Autowired
    private BeverageService beverageService;

    @PostMapping(path = "/add", consumes = "application/json")
    public BeverageModel addBeverage(@RequestBody BeverageModel beverage) throws Exception {
        if(beverage == null){
            throw new Exception("No Beverage Received");
        }
        return beverageService.addBeverage(beverage);
    }

    @PostMapping(path = "/update", consumes = "application/json")
    public BeverageModel updateBeverage(@RequestBody BeverageModel beverage) throws Exception {
        if(beverage == null){
            throw new Exception("No Beverage Received");
        }
        return beverageService.updateBeverage(beverage);
    }

    @DeleteMapping(path="/delete")
    public void deleteBeverage(@RequestBody BeverageModel beverage) throws Exception{
        if(beverage == null){
            throw new Exception("No Beverage Received");
        }
        beverageService.deleteBeverage(beverage);
    }

    @PostMapping(path="/find/{id}", produces = "application/json")
    public ResponseEntity findById(@RequestBody int id) throws Exception{
        Optional<BeverageModel> beverage = beverageService.findById(id);
        if(beverage == null){
            throw new Exception("No Match Found Of Given Id");
        }
        return ResponseEntity.ok(beverage);
    }

    @PostMapping(path="/findall", produces = "application/json")
    public List<BeverageModel> showAll() throws Exception{
        List<BeverageModel> listofBeverage = beverageService.showAll();
        if(listofBeverage == null){
            throw new Exception("No Beverage Found");
        }
        return listofBeverage;

    }
}

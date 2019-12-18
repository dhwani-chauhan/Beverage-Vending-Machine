package com.nestaway.BeverageVendingMachine.Controller;

import com.nestaway.BeverageVendingMachine.Model.IngredientsModel;
import com.nestaway.BeverageVendingMachine.Service.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/ingredients")
public class IngredientsCont {

    @Autowired
    private IngredientsService ingredientsService;

    @PostMapping(path="/add", consumes = "application/json")
    public IngredientsModel addIngredients(@RequestBody IngredientsModel ingredients) throws Exception{
        if(ingredients == null){
            throw new Exception("No Ingredients Received");
        }
        return ingredientsService.addIngredients(ingredients);
    }

    @PostMapping(path="/update", consumes = "application/json")
    public IngredientsModel updateIngredients(@RequestBody IngredientsModel ingredients) throws Exception{
        if(ingredients == null){
            throw new Exception("No Ingredients Received");
        }
        return ingredientsService.updateIngredients(ingredients);
    }

    @DeleteMapping(path="/add", consumes = "application/json")
    public void deleteIngredients(@RequestBody IngredientsModel ingredients) throws Exception{
        if(ingredients == null){
            throw new Exception("No Ingredients Received");
        }
        ingredientsService.deleteIngredients(ingredients);
    }

    @PostMapping(path="/findall", produces="application/json")
    public List<IngredientsModel> showAll() throws Exception{
        List<IngredientsModel> listofIngredient = ingredientsService.showAll();
        if(listofIngredient == null){
            throw new Exception("No Ingredients Found");
        }
        return listofIngredient;
    }

    @PostMapping(path = "find/{id}", produces = "application/json")
    public ResponseEntity findByBid(@RequestBody int id) throws Exception{
        IngredientsModel ingredients = ingredientsService.findByBId(id);
        if(ingredients == null){
            throw new Exception("No Ingredients Found For Given Beverage");
        }
        return ResponseEntity.ok(ingredients);
    }
}

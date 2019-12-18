package com.nestaway.BeverageVendingMachine.Service;

import com.nestaway.BeverageVendingMachine.Model.IngredientsModel;
import com.nestaway.BeverageVendingMachine.Repository.IngredientsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientsService {

    @Autowired
    private IngredientsRepo ingredients;
    InventoryService inventory;

    public IngredientsModel addIngredients(IngredientsModel ingredient){
        return ingredients.save(ingredient);
    }

    public IngredientsModel updateIngredients(IngredientsModel ingredient){
        return ingredients.save(ingredient);
    }

    public void deleteIngredients(IngredientsModel ingredient){
        ingredients.delete(ingredient);
    }

    public List<IngredientsModel> showAll() throws Exception{
        return ingredients.findAll();

    }

    public IngredientsModel findByBId(int id) throws Exception{
        return ingredients.findByB_id(id);
    }
}

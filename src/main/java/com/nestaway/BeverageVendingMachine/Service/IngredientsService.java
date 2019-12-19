package com.nestaway.BeverageVendingMachine.Service;

import com.nestaway.BeverageVendingMachine.Model.IngredientsModel;
import com.nestaway.BeverageVendingMachine.Repository.IngredientsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientsService {

    @Autowired
    private IngredientsRepo ingredientsRepo;

    public IngredientsModel addIngredients(IngredientsModel ingredients) throws Exception{
        if(ingredients == null)
            throw new Exception("No value provided");
        if(ingredients.getBeverageModel() == null || ingredients.getInventoryModel() == null || ingredients.getQuantityRequired() == null)
            throw new Exception("Required Parameters are not provided, please check the manual");
        return ingredientsRepo.save(ingredients);
    }

    public IngredientsModel updateIngredients(IngredientsModel ingredients) throws Exception{
        if(ingredients == null)
            throw new Exception("NO value provided");
        IngredientsModel ingredients1 = findById(ingredients.getInId());
        if(ingredients.getQuantityRequired() == null)
            ingredients.setQuantityRequired(ingredients1.getQuantityRequired());
        if(ingredients.getInventoryModel() == null)
            ingredients.setInventoryModel(ingredients1.getInventoryModel());
        if(ingredients.getBeverageModel() == null)
            ingredients.setBeverageModel(ingredients1.getBeverageModel());
        return ingredientsRepo.save(ingredients);
    }

    public boolean deleteIngredients(Long id) throws Exception{
        IngredientsModel ingredients = findById(id);
        try {
            ingredientsRepo.delete(ingredients);
            return false;
        }
        catch (Exception ex) {
            return false;
        }
    }

    public IngredientsModel findById(Long id) throws Exception {
        Optional<IngredientsModel> ingredients = ingredientsRepo.findById(id);
        if(!ingredients.isPresent())
            throw new Exception("No such ingredient exist");
        return ingredients.get();
    }

    public List<IngredientsModel> showAll() throws Exception{
        List<IngredientsModel> ingredientsList = ingredientsRepo.findAll();
        if(ingredientsList.isEmpty())
            throw new Exception("No ingredients inserted");
        return ingredientsList;

    }
}

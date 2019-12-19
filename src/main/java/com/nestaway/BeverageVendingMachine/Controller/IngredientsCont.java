package com.nestaway.BeverageVendingMachine.Controller;

import com.nestaway.BeverageVendingMachine.Model.IngredientsModel;
import com.nestaway.BeverageVendingMachine.Service.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path="/ingredients")
public class IngredientsCont {

    @Autowired
    private IngredientsService ingredientsService;

    @PostMapping(path="/add")
    public ResponseEntity addIngredients(@RequestBody IngredientsModel ingredients){
        try {
            IngredientsModel ingredients1 = ingredientsService.addIngredients(ingredients);
            return correctResponse(ingredients1, HttpStatus.OK, HttpStatus.OK.value(),"Success",HttpStatus.OK);
        }
        catch (Exception ex) {
            return errorResponse(ex);
        }
    }

    @PutMapping(path="/update")
    public ResponseEntity updateIngredients(@RequestBody IngredientsModel ingredients){
        try {
            IngredientsModel ingredients1 = ingredientsService.updateIngredients(ingredients);
            return correctResponse(ingredients1, HttpStatus.OK, HttpStatus.OK.value(),"Success",HttpStatus.OK);
        }
        catch (Exception ex) {
            return errorResponse(ex);
        }
    }

    @DeleteMapping(path="/delete/{param}")
    public ResponseEntity deleteIngredients(@PathVariable Long param) {
        try {
            boolean output = ingredientsService.deleteIngredients(param);
            return correctResponse(output, HttpStatus.OK, HttpStatus.OK.value(),"Success",HttpStatus.OK);
        }
        catch (Exception ex) {
            return errorResponse(ex);
        }
    }

    @GetMapping(path="/findall")
    public ResponseEntity showAll(){
        try {
            List<IngredientsModel> ingredientsList = ingredientsService.showAll();
            return correctResponse(ingredientsList, HttpStatus.OK, HttpStatus.OK.value(),"Success",HttpStatus.OK);
        }
        catch (Exception ex) {
            return errorResponse(ex);
        }
    }

    @GetMapping(path = "find/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        try {
            IngredientsModel ingredients = ingredientsService.findById(id);
            return correctResponse(ingredients, HttpStatus.OK, HttpStatus.OK.value(),"Success",HttpStatus.OK);
        }
        catch (Exception ex) {
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

package com.nestaway.BeverageVendingMachine.Service;

import com.nestaway.BeverageVendingMachine.Model.BeverageModel;
import com.nestaway.BeverageVendingMachine.Model.IngredientsModel;
import com.nestaway.BeverageVendingMachine.Repository.BeverageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeverageService {

    @Autowired
    private BeverageRepo beverageRepo;

    public BeverageModel addBeverage(BeverageModel beverage) throws Exception{
        if(beverage == null)
            throw new Exception("No Beverage Sent");
        if(beverage.getBName() == null)
            throw new Exception("Some value(s) are missing, Please check the manual properly");
        return beverageRepo.save(beverage);
    }

    public BeverageModel updateBeverage(BeverageModel beverage) throws Exception{
        BeverageModel beverage1 = findById(beverage.getBId());
        if(beverage1 == null)
            throw new Exception("No such Beverage exist");
        if(beverage.getBName() == null)
            beverage.setBName(beverage1.getBName());
        if(beverage.getIngredientsModel() == null)
            beverage.setIngredientsModel(beverage1.getIngredientsModel());
        return beverageRepo.save(beverage);
    }

    public boolean deleteBeverage(Long id) throws Exception{
        BeverageModel beverage = findById(id);
        beverageRepo.delete(beverage);
        return true;
    }

    public BeverageModel findById(Long id) throws Exception{
        Optional<BeverageModel> beverage = beverageRepo.findById(id);
        if(!beverage.isPresent())
            throw new Exception(("No Such beverage exist"));
        return beverage.get();
    }

    public List<BeverageModel> showAll() throws Exception{
        List<BeverageModel> beverageList = beverageRepo.findAll();
        if(beverageList.isEmpty())
            throw new Exception("No Beverage is present");
        return beverageList;
    }

    public boolean checkAvailability(Long id) throws Exception {
        BeverageModel beverage = findById(id);
        return beverage.isStatus();
    }

    public List<BeverageModel> checkAvailability() throws Exception {
        List<BeverageModel> beverageList = showAll();
        beverageList.removeIf(beverage -> (!beverage.isStatus()));
        if(beverageList.isEmpty())
            throw new Exception("No Beverages are available");
        return beverageList;
    }

    public BeverageModel toggleAvailability(BeverageModel beverage) throws Exception {
        List<IngredientsModel> ingredientsList = beverage.getIngredientsModel();
        for(IngredientsModel i : ingredientsList) {
            if(i.getInventoryModel().getTotalQuantity() < i.getQuantityRequired()) {
                beverage.setStatus(false);
                break;
            }
        }
        return beverageRepo.save(beverage);
    }

    public void updateList() throws Exception {
        List<BeverageModel> beverageList = checkAvailability();
        for(BeverageModel b : beverageList) {
            toggleAvailability(b);
        }
    }
}

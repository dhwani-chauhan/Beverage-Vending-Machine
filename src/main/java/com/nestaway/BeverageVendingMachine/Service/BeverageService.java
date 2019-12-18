package com.nestaway.BeverageVendingMachine.Service;

import com.nestaway.BeverageVendingMachine.Model.BeverageModel;
import com.nestaway.BeverageVendingMachine.Repository.BeverageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeverageService {

    @Autowired
    private BeverageRepo beverage;

    public BeverageModel addBeverage(BeverageModel bev){
        return beverage.save(bev);
    }

    public BeverageModel updateBeverage(BeverageModel bev){
        return beverage.save(bev);
    }

    public void deleteBeverage(BeverageModel bev) throws Exception{
        beverage.delete(bev);
    }

    public Optional<BeverageModel> findById(int id) throws Exception{
        return beverage.findById(id);
    }

    public List<BeverageModel> showAll() throws Exception{
        return beverage.findAll();
    }
}

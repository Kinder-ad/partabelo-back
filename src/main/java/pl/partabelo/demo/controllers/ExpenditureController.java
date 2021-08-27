package pl.partabelo.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.partabelo.demo.model.Expenditure;
import pl.partabelo.demo.repository.IExpenditureRepository;
import pl.partabelo.demo.services.IExpenditureService;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("api/expenditure")
public class ExpenditureController {

    @Autowired
    private IExpenditureService expenditureService;

    @GetMapping
    public ResponseEntity<?> getExpenditures(){
        return new ResponseEntity<>(this.expenditureService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveExpenditure(@RequestBody Expenditure expenditure){
        return new ResponseEntity<>(this.expenditureService.save(expenditure), HttpStatus.CREATED);
    }

    @DeleteMapping("{expenditureId}")
    public ResponseEntity<?> deleteExpenditure(@PathVariable Long expenditureId){
        this.expenditureService.delete(expenditureId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

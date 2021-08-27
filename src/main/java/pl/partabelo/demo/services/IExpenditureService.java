package pl.partabelo.demo.services;

import pl.partabelo.demo.model.Expenditure;
import pl.partabelo.demo.model.User;

import java.util.List;

public interface IExpenditureService {

    Expenditure save(Expenditure expenditure);

    List<Expenditure> getAll();

    void delete(Long id);

}

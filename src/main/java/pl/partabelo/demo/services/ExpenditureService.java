package pl.partabelo.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.partabelo.demo.model.Expenditure;
import pl.partabelo.demo.model.User;
import pl.partabelo.demo.repository.IExpenditureRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExpenditureService implements IExpenditureService{

    @Autowired
    IExpenditureRepository expenditureService;

    @Override
    public Expenditure save(Expenditure expenditure) {
        expenditure.setCreateTime(LocalDateTime.now());
        return this.expenditureService.save(expenditure);
    }

    @Override
    public List<Expenditure> getAll() {
        return this.expenditureService.findAllByOrderByIdAsc();
    }

    @Override
    public void delete(Long id) {
        this.expenditureService.deleteById(id);
    }

}

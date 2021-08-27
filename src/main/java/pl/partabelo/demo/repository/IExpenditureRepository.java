package pl.partabelo.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.partabelo.demo.model.Expenditure;
import pl.partabelo.demo.model.User;

import java.util.List;

@Repository
public interface IExpenditureRepository extends JpaRepository<Expenditure, Long> {

    @Transactional
    void deleteById(Long id);

    List<Expenditure> findAllByOrderByIdAsc();
}

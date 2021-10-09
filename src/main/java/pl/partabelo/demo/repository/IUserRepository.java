package pl.partabelo.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.partabelo.demo.model.Role;
import pl.partabelo.demo.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Transactional
    void deleteUserByEmail(String email);

    List<User> findAllByOrderByIdAsc();

    @Transactional
    @Modifying
    @Query("update User u set u.firstName = ?1, u.lastName = ?2, u.email = ?3, u.role = ?4, u.isPaid = ?5 where u.id = ?6")
    void updateUser(String firstName, String lastName, String email, Role role, boolean paid, Long id);



    @Modifying
    @Transactional
    @Query("update User set role= :role where email = :email")
    void changeRoleUser(@Param("email") String email, @Param("role") Role role);

    @Transactional
    void deleteUserById(Long id);


}

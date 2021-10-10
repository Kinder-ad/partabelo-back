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
    @Query("update User u set u.firstName = ?1, u.lastName = ?2, u.email = ?3, u.role = ?4, u.isPaid = ?5, u.isRequest = ?6 where u.id = ?7")
    void updateUser(String firstName, String lastName, String email, Role role, boolean paid, boolean isRequest, Long id);

    @Transactional
    @Modifying
    @Query("update User u set u.isRequest = ?1 where u.id = ?2")
    void setRequestUser(boolean isRequest, Long id);


    @Modifying
    @Transactional
    @Query("update User set role= :role where email = :email")
    void changeRoleUser(@Param("email") String email, @Param("role") Role role);

    @Transactional
    void deleteUserById(Long id);


}

package pl.partabelo.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.partabelo.demo.model.Role;
import pl.partabelo.demo.model.TrackHistory;
import pl.partabelo.demo.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Transactional
    void deleteUserByUsername(String username);

    List<User> findAllByOrderByIdAsc();

    @Transactional
    @Modifying
    @Query("update User u set u.firstName = ?1, u.lastName = ?2, u.username = ?3, u.role = ?4, u.isPaid = ?5, u.isRequest = ?6 where u.id = ?7")
    void updateUser(String firstName, String lastName, String username, Role role, boolean paid, boolean isRequest, Long id);

    @Transactional
    @Modifying
    @Query("update User u set u.isRequest = ?1 where u.id = ?2")
    void setRequestUser(boolean isRequest, Long id);


    @Modifying
    @Transactional
    @Query("update User set role= :role where username = :username")
    void changeRoleUser(@Param("username") String username, @Param("role") Role role);


    @Transactional
    void deleteUserById(Long id);


}

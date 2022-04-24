package pl.partabelo.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.partabelo.demo.model.TrackHistory;
import pl.partabelo.demo.model.Test;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ISongRepository extends JpaRepository<TrackHistory,Long> {

    @Query("select new pl.partabelo.demo.model.Test(t.trackName,u.username) from User u JOIN u.tracks t")
    ArrayList<Test> getAll();
}
//    SELECT th.track_name, th.added_date_time, u.first_name, u.last_name
//        FROM track_history th JOIN users_tracks ut ON th.id=ut.tracks_id
//        JOIN users u ON ut.user_id = u.id
//        ORDER BY added_date_time ASC;

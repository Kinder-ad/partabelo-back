package pl.partabelo.demo.repository;

import pl.partabelo.demo.Models.PlaylistModel.TrackInQueue;
import pl.partabelo.demo.Models.PlaylistModel.TrackJson;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class QueueRepository {

    private List<TrackInQueue> localQueue = new ArrayList<>();

    public void addTrackToLocalQueue(TrackInQueue trackJson){
            this.localQueue.add(trackJson);
    }
    public void removeFirstElementFromQueue(){
        this.localQueue.remove(0);
    }

    public List<TrackInQueue> getLocalQueue() {
        return localQueue;
    }

    public void setLocalQueue(List<TrackInQueue> localQueue) {
        this.localQueue = localQueue;
    }

    public void deleteById(Long id){
        this.localQueue.remove(id);
    }
}

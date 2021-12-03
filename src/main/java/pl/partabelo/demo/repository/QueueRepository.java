package pl.partabelo.demo.repository;

import pl.partabelo.demo.Models.PlaylistModel.TrackJson;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class QueueRepository {

    private List<TrackJson> localQueue = new ArrayList<>();

    public void addTrackToLocalQueue(TrackJson trackJson){
            this.localQueue.add(trackJson);
    }
    public void removeFirstElementFromQueue(){
        this.localQueue.remove(0);
    }

    public List<TrackJson> getLocalQueue() {
        return localQueue;
    }

    public void setLocalQueue(List<TrackJson> localQueue) {
        this.localQueue = localQueue;
    }
}

package pl.partabelo.demo.repository;

import pl.partabelo.demo.Models.PlaylistModel.TrackInQueue;
import pl.partabelo.demo.Models.PlaylistModel.TrackJson;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

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

    public void deleteByUri(String uri) {
        int index = IntStream.range(0, this.localQueue.size())
                .filter(i -> this.localQueue.get(i).getTrackJson().getUri().equals(uri))
                .findFirst()
                .orElse(-1);
        this.localQueue.remove(index);
    }

    public void clearQueue() {
        this.localQueue = new ArrayList<>();
    }
}

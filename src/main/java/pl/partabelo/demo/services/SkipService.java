package pl.partabelo.demo.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.partabelo.demo.Models.CurrentTruckModels.CurrentTrackSimpler;
import pl.partabelo.demo.Models.PlaylistModel.TrackInQueue;
import pl.partabelo.demo.model.Role;
import pl.partabelo.demo.model.User;

@Service
public class SkipService {
    private int voteCounter = 0;
    private int limitOfVotes = 3;
    private CurrentTrackSimpler votedToSkipTrack;
    private final QueueService queueService;

    public SkipService(QueueService queueService) {
        this.queueService = queueService;
    }


    public boolean addUserVote(User user){
        if(!isTheSameTrackWhatVoted()) this.clearVote();

        int votesToAdd = 1;
        if(user.getRole() == Role.SUPERDJ){
            votesToAdd = 2;
        }

        if(this.voteCounter+votesToAdd >= limitOfVotes && !this.queueService.checkIfFifteenSecondToEnd()){
            this.clearVote();
            this.queueService.addFirstTrackFromLocalQueueToSpotifyQueue();
            return true;
        }
        if(!this.queueService.checkIfFifteenSecondToEnd()){
            this.voteCounter+=votesToAdd;
            return true;
        }else{
            this.clearVote();
            return false;
        }
    }

    @Scheduled(fixedDelay = 2000)
    public boolean isTheSameTrackWhatVoted(){
        var track = this.queueService.currentTrackService.getCurrentTrack();
        if(this.votedToSkipTrack == null){
            this.votedToSkipTrack = track;
            return true;
        }
        if(track.getName().equals(this.votedToSkipTrack.getName())){
            return true;
        }else{
            this.clearVote();
            this.votedToSkipTrack = track;
            return false;
        }
    }

    public void clearVote(){
        this.voteCounter = 0;
    }

    public int getVoteCounter() {
        return voteCounter;
    }

    public void setVoteCounter(int voteCounter) {
        this.voteCounter = voteCounter;
    }

    public CurrentTrackSimpler getVotedToSkipTrack() {
        return votedToSkipTrack;
    }

    public void setVotedToSkipTrack(CurrentTrackSimpler votedToSkipTrack) {
        this.votedToSkipTrack = votedToSkipTrack;
    }

    public QueueService getQueueService() {
        return queueService;
    }

    public int getLimitOfVotes() {
        return limitOfVotes;
    }

    public void setLimitOfVotes(int limitOfVotes) {
        this.limitOfVotes = limitOfVotes;
    }
}

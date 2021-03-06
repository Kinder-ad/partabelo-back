package pl.partabelo.demo.services;

import pl.partabelo.demo.Models.CurrentTruckModels.CurrentTrackSimpler;
import pl.partabelo.demo.Models.PlaylistModel.TrackInQueue;
import pl.partabelo.demo.Models.PlaylistModel.TrackJson;
import pl.partabelo.demo.repository.QueueRepository;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class QueueService {

    final
    SpotifyApiService spotifyApiService;
    final
    CurrentTrackService currentTrackService;
    final
    QueueRepository queueRepository;

    private LocalTime date = LocalTime.now();


    public QueueService(SpotifyApiService spotifyApiService, CurrentTrackService currentTrackService, QueueRepository queueRepository) {
        this.spotifyApiService = spotifyApiService;
        this.currentTrackService = currentTrackService;
        this.queueRepository = queueRepository;
    }

    @Scheduled(fixedDelay = 3000)
    private void checkerIfCanAddToSpotifyQueue() {
        if(this.spotifyApiService.getJwt()==null || this.currentTrackService.getCurrentTrack() == null) return;
//      Check if playing
        if (this.currentTrackService.getCurrentTrack().getPlaying()) {
//          Check if local queue isn't empty and time to wait has passed
            if (this.queueRepository.getLocalQueue().size() > 0 && this.date.isBefore(LocalTime.now())) {
//              Check if there are 10 seconds left to the end of the music
                if (this.checkIfTwelveSecondToEnd()) {
                    this.date = LocalTime.now().plusSeconds(15);
                    addSongToSpotifyQueue(this.queueRepository.getLocalQueue().get(0).getTrackJson());
                    this.queueRepository.removeFirstElementFromQueue();
                }
            }
        } else {
        }
    }

    public void addFirstTrackFromLocalQueueToSpotifyQueue(){
        this.date = LocalTime.now().plusSeconds(10);
        if(this.queueRepository.getLocalQueue().size() == 0 ){
            this.currentTrackService.skipSong();
        }else {

            this.spotifyApiService.templator(
                    "https://api.spotify.com/v1/me/player/queue?uri=" + this.queueRepository.getLocalQueue().get(0).getTrackJson().getUri() + "&device_id=" + this.spotifyApiService.getCurrentDevice().getId(),
                    HttpMethod.POST,
                    void.class
            );
            this.currentTrackService.skipSong();
            this.queueRepository.removeFirstElementFromQueue();
        }
    }

    private void addSongToSpotifyQueue(TrackJson trackJson) {
        this.spotifyApiService.templator(
                "https://api.spotify.com/v1/me/player/queue?uri=" + trackJson.getUri() + "&device_id=" + this.spotifyApiService.getCurrentDevice().getId(),
                HttpMethod.POST,
                void.class
        );
    }

    public boolean checkIfTwelveSecondToEnd() {
        CurrentTrackSimpler currentTrack = this.currentTrackService.getCurrentTrack();
        if (currentTrack == null) return true;
        if (currentTrack.getDurationMs() - currentTrack.getProgressMs() < 12000) return true;
        else return false;
    }
    public boolean checkIfFifteenSecondToEnd() {
        CurrentTrackSimpler currentTrack = this.currentTrackService.getCurrentTrack();
        if (currentTrack == null) return true;
        if (currentTrack.getDurationMs() - currentTrack.getProgressMs() < 15000) return true;
        else return false;
    }


    public void addToLocalQueue(TrackInQueue trackJson) {
        if (checkIfNotInLocalQueueAndInLimit(trackJson.getTrackJson())) {
            this.queueRepository.addTrackToLocalQueue(trackJson);
        }
    }

    public boolean checkIfNotInLocalQueueAndInLimit(TrackJson trackJson) {
        if (this.queueRepository.getLocalQueue().stream().noneMatch((o) -> o.getTrackJson().getName().equals(trackJson.getName()))
                && this.queueRepository.getLocalQueue().size() < 10) {
            return true;
        } else {
            return false;
        }
    }


    //Getters and Setters


    public List<TrackInQueue> getLocalQueue() {
        return this.queueRepository.getLocalQueue();
    }
    public void deleteTrackByUriFromQueue(String uri){
        this.queueRepository.deleteByUri(uri);
    }

    public void clearQueue() {
        this.queueRepository.clearQueue();
    }

    public LocalTime getDate() {
        return date;
    }

    public void setDate(LocalTime date) {
        this.date = date;
    }
}

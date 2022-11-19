package application.hibernate.services;

import java.util.List;

import application.hibernate.entities.Audio;

public interface AudioService {
    Audio saveAudioBook(Audio audio, Long userId, Long bookId);

    Audio savePodcast(Audio audio, Long userId);

    List<Audio> getAllAudios();
}

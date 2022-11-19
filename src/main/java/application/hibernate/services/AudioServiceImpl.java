package application.hibernate.services;

import java.util.List;

import application.hibernate.entities.Audio;
import application.hibernate.repos.AudioRepository;

public class AudioServiceImpl implements AudioService {
    AudioRepository audioRepository = new AudioRepository();

    @Override
    public Audio saveAudioBook(Audio audio, Long userId, Long bookId) {
        return audioRepository.save(audio, userId, bookId);
    }

    @Override
    public Audio savePodcast(Audio audio, Long userId) {
        /** Indexing starts at 1 when creating entries so this is safe */
        return audioRepository.save(audio, userId, 0L);
    }

    @Override
    public List<Audio> getAllAudios() {
        return audioRepository.findAll();
    }
}

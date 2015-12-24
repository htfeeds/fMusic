package com.htf.fmusic.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htf.fmusic.exceptions.SongPlaylistNotFoundException;
import com.htf.fmusic.models.SongPlaylist;
import com.htf.fmusic.repositories.SongPlaylistRepository;

/**
 * @author HTFeeds
 */
@Service
public class SongPlaylistServiceImpl implements SongPlaylistService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SongPlaylistServiceImpl.class);

    private final SongPlaylistRepository repository;

    @Autowired
    SongPlaylistServiceImpl(SongPlaylistRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SongPlaylist> findAll() {
        LOGGER.info("Finding all songPlaylist entries.");

        List<SongPlaylist> songPlaylistEntries = repository.findAll();
        LOGGER.info("Found {} songPlaylist entries", songPlaylistEntries.size());

        return songPlaylistEntries;
    }

    @Override
    @Transactional(readOnly = true)
    public SongPlaylist findById(Integer id) {
        LOGGER.info("Finding songPlaylist entry by using id: {}", id);

        SongPlaylist songPlaylistEntry = findSongPlaylistEntryById(id);
        LOGGER.info("Found songPlaylist entry: {}", songPlaylistEntry);

        return songPlaylistEntry;
    }

    @Override
    @Transactional
    public SongPlaylist create(SongPlaylist newEntry) {
        LOGGER.info("Creating a new songPlaylist entry by using information: {}", newEntry);

        SongPlaylist created = repository.save(newEntry);
        LOGGER.info("Created a new songPlaylist entry: {}", created);

        return created;
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        LOGGER.info("Deleting a songPlaylist entry with id: {}", id);

        SongPlaylist deleted = findSongPlaylistEntryById(id);
        LOGGER.debug("Found songPlaylist entry: {}", deleted);

        repository.delete(deleted);
        LOGGER.info("Deleted user entry: {}");
    }

    @Override
    @Transactional
    public SongPlaylist update(SongPlaylist updatedEntry) {
        LOGGER.info("Updating the information of a songPlaylist entry by using information: {}", updatedEntry);

        SongPlaylist updated = findSongPlaylistEntryById(updatedEntry.getId());
        updated.update(updatedEntry.getSong(), updatedEntry.getPlaylist(), updatedEntry.getOrder());
        repository.flush();

        LOGGER.info("Updated the information of the songPlaylist entry: {}", updated);
        return updated;
    }

    private SongPlaylist findSongPlaylistEntryById(Integer id) {
        Optional<SongPlaylist> songPlaylistResult = repository.findOne(id);
        return songPlaylistResult.orElseThrow(() -> new SongPlaylistNotFoundException(id));
    }

}
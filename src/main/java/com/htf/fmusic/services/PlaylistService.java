package com.htf.fmusic.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.htf.fmusic.models.Artist;
import com.htf.fmusic.models.Playlist;
import com.htf.fmusic.models.Song;

/**
 * @author HTFeeds
 */
public interface PlaylistService extends BaseService<Playlist, Integer> {

    public Playlist update(Playlist updated);

    public Playlist addSongs(Integer id, List<Song> songs);

    public List<Playlist> getSlideActivedPlaylists();

    public Page<Playlist> getHomePlaylists();

    public Playlist getLatestTopPlaylist(String country);

    public List<Playlist> getUserPlaylists(String username);

    public Page<Playlist> getAllOfficialAndCollectionPlaylists(int page);

    public Page<Playlist> getPlaylistByGenreName(String genreName, int page);

    public List<Playlist> getRelatedPlaylists(Artist artist);

}

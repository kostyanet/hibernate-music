import dao.AlbumDao;
import dao.ArtistDao;
import dao.TrackDao;
import entity.Album;
import entity.Artist;
import entity.Track;

import java.util.Arrays;
import java.util.List;

public class App {
    private final TrackDao trackDao = new TrackDao();
    private final AlbumDao albumDao = new AlbumDao();
    private final ArtistDao artistDao = new ArtistDao();

    public static void main(String[] args) {
        App app = new App();
        app.createTracks();
        app.createAlbums();
        app.createAndLinkArtists();

        System.exit(0);
    }

    public void createTracks() {
        for (int i = 0; i < 20; i++) {
            trackDao.create(new Track("Track #" + i + 1));
        }
    }

    public void createAlbums() {
        List<Track> tracks = trackDao.listAll();

        Album a1 = new Album("Album #1");
        a1.setTracks(tracks.subList(0, 5));
        albumDao.create(a1);

        Album a2 = new Album("Album #2");
        a2.setTracks(tracks.subList(6, 13));
        albumDao.create(a2);

        Album a3 = new Album("Album #3");
        a3.setTracks(tracks.subList(14, 17));
        albumDao.create(a3);
    }

    public void createAndLinkArtists() {
        Artist art1 = new Artist("Artist Name #1");
        Artist art2 = new Artist("Artist Name #2");
        Artist art3 = new Artist("Artist Name #3");
        artistDao.create(art1);
        artistDao.create(art2);
        artistDao.create(art3);

        Album album1 = albumDao.getById(1);
        Album album2 = albumDao.getById(2);
        Album album3 = albumDao.getById(3);

        album1.setArtists(Arrays.asList(art1, art2));
        album2.setArtists(Arrays.asList(art2, art3));
        album3.setArtists(Arrays.asList(art3));

        art1.setAlbums(Arrays.asList(album1));
        art2.setAlbums(Arrays.asList(album1, album2));
        art3.setAlbums(Arrays.asList(album3, album2));

        albumDao.update(album1);
        albumDao.update(album2);
        albumDao.update(album3);

        artistDao.update(art1);
        artistDao.update(art2);
        artistDao.update(art3);
    }
}

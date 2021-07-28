import dao.*;
import entity.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
    private final TrackDao trackDao = new TrackDao();
    private final AlbumDao albumDao = new AlbumDao();
    private final ArtistDao artistDao = new ArtistDao();
    private final CustomerDao customerDao = new CustomerDao();
    private final OrderDao orderDao = new OrderDao();

    public static void main(String[] args) {
        App app = new App();
        app.createTracks();
        app.createAlbums();
        app.createAndLinkArtists();
        app.createCustomers();
        app.createAndLinkOrders();
        app.insertTrackButch();

        System.exit(0);
    }

    public void createTracks() {
        for (int i = 0; i < 20; i++) {
            trackDao.create(new Track("Track #" + (i + 1)));
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

    public void createCustomers() {
        Customer c1 = new Customer("Cus Name #1");
        Customer c2 = new Customer("Ctm Name #2");
        Customer c3 = new Customer("Ctm Name #3");
        customerDao.create(c1);
        customerDao.create(c2);
        customerDao.create(c3);
    }

    public void createAndLinkOrders() {
        Album album1 = albumDao.getById(1);
        Album album2 = albumDao.getById(2);
        Album album3 = albumDao.getById(3);

        Track tr1 = trackDao.getById(17);
        Track tr2 = trackDao.getById(18);
        Track tr3 = trackDao.getById(19);
        Track tr4 = trackDao.getById(20);

        Order o1 = new Order(3, Arrays.asList(album1, album2), null);
        Order o2 = new Order(2, Arrays.asList(album2), Arrays.asList(tr1, tr2, tr3));
        Order o3 = new Order(1, null, Arrays.asList(tr4, tr3));

        orderDao.create(o1);
        orderDao.create(o2);
        orderDao.create(o3);
    }

    public void insertTrackButch() {
        trackDao.butchCreate((int) 2e5);
    }
}

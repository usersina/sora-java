package application.hibernate.util;

import java.util.Arrays;
import java.util.Date;

import org.hibernate.Session;

import application.hibernate.entities.Audio;
import application.hibernate.entities.Book;
import application.hibernate.entities.Designation;
import application.hibernate.entities.Genre;
import application.hibernate.entities.Reviewer;
import application.hibernate.entities.User;
import application.hibernate.entities.composite.ArtworkRating;
import application.hibernate.services.AudioService;
import application.hibernate.services.AudioServiceImpl;
import application.hibernate.services.BookService;
import application.hibernate.services.BookServiceImpl;
import application.hibernate.services.DesignationService;
import application.hibernate.services.DesignationServiceImpl;
import application.hibernate.services.GenreService;
import application.hibernate.services.GenreServiceImpl;
import application.hibernate.services.ReviewerService;
import application.hibernate.services.ReviewerServiceImpl;
import application.hibernate.services.UserService;
import application.hibernate.services.UserServiceImpl;
import application.hibernate.services.composite.ArtworkRatingService;
import application.hibernate.services.composite.ArtworkRatingServiceImpl;

/**
 * This is a helper class used to feed initial data into the database.
 * This can be very convenient for dropping/re-creating the database and testing
 * without manually having to add the data.
 */
public class DatabaseSeeder {
	Session session;

	DesignationService designationService;
	GenreService genreService;
	UserService userService;
	ReviewerService reviewerService;
	BookService bookService;
	AudioService audioService;

	ArtworkRatingService artworkRatingService;

	public DatabaseSeeder() {
		this.session = HibernateUtil.getSessionFactory().openSession();

		designationService = new DesignationServiceImpl();
		genreService = new GenreServiceImpl();
		userService = new UserServiceImpl();
		reviewerService = new ReviewerServiceImpl();
		bookService = new BookServiceImpl();
		audioService = new AudioServiceImpl();

		artworkRatingService = new ArtworkRatingServiceImpl();
	}

	public void populateDatabase() {
		System.out.println("------------- Populating base data -------------");

		// Create designations
		designationService.saveDesignation(new Designation("Fan", "You never published any content", 0));
		designationService.saveDesignation(new Designation("Novice", "You published very little content", 2));
		designationService.saveDesignation(new Designation("Amateur", "You published some content", 5));
		Designation novelist = designationService
				.saveDesignation(new Designation("Novelist", "You published enough books to be called a novelist", 20));
		designationService.saveDesignation(new Designation("Quill-Driver", "You published a great deal of book", 30));

		// Create genres
		genreService.saveGenre(new Genre("Fantasy", "Supernatural and out of this world"));
		genreService.saveGenre(new Genre("Romance", "It's all about relations"));
		Genre science = genreService
				.saveGenre(new Genre("Science", "Related to the collective knowledge we share as humans"));
		genreService.saveGenre(new Genre("Sci-fi", "Set out in the supposedly not so distance future"));

		// Create users
		User johnUser = userService
				.saveUser(new User("John", "Writer", "john.writer@gmail.com", "jwriter", "password", 340, novelist));
		User barrelUser = userService
				.saveUser(new User("Barrel", "Reader", "barrel.reader@gmail.com", "breader", "password", 120));

		// Create reviewer
		Reviewer kyoumaReviewer = reviewerService.saveReviewer(
				new Reviewer("Kyouma", "Viewer", "kyouma.viewer@gmail.com", "hououin", "password", 0, new Date()));

		// Create two books for John
		Book timeTravelBook = bookService.saveBook(
				new Book(180, "https://some-site-or-service/time-travel-for-dummies.pdf", johnUser,
						"Time Travel for Dummies",
						"The all-in one Time Travel book that even toddlers should be able to understand",
						250, Arrays.asList(science)),
				johnUser.getId());

		// Add audio for the first book
		audioService.saveAudioBook(
				new Audio(50, "https://path-to-some-mp3-server/time-travel-for-dummies.mp3",
						"Reading out the book for you to make time travel even simpler",
						22500, timeTravelBook),
				johnUser.getId(), timeTravelBook.getId());

		// Add a podcast to the reviewer
		audioService.savePodcast(
				new Audio(25, "https://path-to-some-mp3-server/discussing-time-traver-for-dummies.mp3",
						"Kyouma, your host will discuss Time Travel for Dummies", 1200),
				kyoumaReviewer.getId());

		// - "Kyouma Viewer"
		// . Create Interest
		// . Review "Time Travel for Dummies"
		// - "Barrel Reader"
		// . Add "Time Travel for Dummies" to user-collection
		// . Create review for "Time Travel for Dummies"

		// Create interests and reviews for "Kyouma Viewer"

		// "Barrel Reader": Add book to user-collections

		// "Barrel Reader": Create review for "Time Travel for Dummies"
		artworkRatingService.saveArtworkRating(
				new ArtworkRating(5, "This book has some interesting things, but also a tag of sci-fi should be added.",
						barrelUser, timeTravelBook));
		System.out.println(artworkRatingService.getAllArtworkRatings());
	}
}

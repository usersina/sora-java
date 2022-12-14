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
import application.hibernate.entities.composite.ArtworkReview;
import application.hibernate.entities.composite.UserCollection;
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
import application.hibernate.services.composite.ArtworkReviewService;
import application.hibernate.services.composite.ArtworkReviewServiceImpl;
import application.hibernate.services.composite.UserCollectionService;
import application.hibernate.services.composite.UserCollectionServiceImpl;

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

	ArtworkReviewService artworkReviewService;
	ArtworkRatingService artworkRatingService;
	UserCollectionService userCollectionService;

	public DatabaseSeeder() {
		this.session = HibernateUtil.getSessionFactory().openSession();

		designationService = new DesignationServiceImpl();
		genreService = new GenreServiceImpl();
		userService = new UserServiceImpl();
		reviewerService = new ReviewerServiceImpl();
		bookService = new BookServiceImpl();
		audioService = new AudioServiceImpl();

		artworkReviewService = new ArtworkReviewServiceImpl();
		artworkRatingService = new ArtworkRatingServiceImpl();
		userCollectionService = new UserCollectionServiceImpl();
	}

	public void populateDatabase() {
		System.out.println("------------- Populating base data -------------");

		// Create designations
		Designation fanDesignation = designationService
				.saveDesignation(new Designation("Fan", "You never published any content", 0));
		designationService.saveDesignation(new Designation("Novice", "You published very little content", 2));
		designationService.saveDesignation(new Designation("Amateur", "You published some content", 5));
		Designation novelist = designationService.saveDesignation(
				new Designation("Novelist", "You published enough books to be called a novelist", 20));
		designationService.saveDesignation(new Designation("Quill-Driver", "You published a great deal of book", 30));

		// Create genres
		Genre fantasyGenre = genreService.saveGenre(new Genre("Fantasy", "Supernatural and out of this world"));
		Genre romanceGenre = genreService.saveGenre(new Genre("Romance", "It's all about relations"));
		Genre scienceGenre = genreService
				.saveGenre(new Genre("Science", "Related to the collective knowledge we share as humans"));
		Genre scifiGenre = genreService
				.saveGenre(new Genre("Sci-fi", "Set out in the supposedly not so distance future"));

		// Create users
		User johnUser = userService
				.saveUser(new User("John", "Writer", "john.writer@gmail.com", "jwriter", "password", 340, novelist));
		User barrelUser = userService
				.saveUser(new User("Barrel", "Reader", "barrel.reader@gmail.com", "breader", "password", 120,
						fanDesignation,
						Arrays.asList(scienceGenre, romanceGenre, scienceGenre)));

		// Create reviewer
		Reviewer kyoumaReviewer = reviewerService.saveReviewer(
				new Reviewer("Kyouma", "Viewer", "kyouma.viewer@gmail.com", "hououin", "password", 0, new Date(),
						Arrays.asList(scienceGenre, fantasyGenre, scifiGenre)));

		// Create two books for John
		Book timeTravelBook = bookService.saveBook(
				new Book("Time Travel for Dummies", 180,
						"https://some-site-or-service/time-travel-for-dummies.pdf",
						"https://some-covers/image.png", johnUser,
						"The all-in one Time Travel book that even toddlers should be able to understand",
						250, Arrays.asList(scienceGenre)),
				johnUser.getId());
		bookService.saveBook(
				new Book("Lord of the Donuts", 260,
						"https://some-site-or-service/lord-of-the-donuts.pdf",
						"https://some-covers/image.png", johnUser,
						"In the magical world of Tunod, a strange curse is turning criminals into donuts",
						500, Arrays.asList(fantasyGenre)),
				johnUser.getId());

		// Add audio for the first book
		Audio timetravelAudio = audioService.saveAudioBook(
				new Audio("Time Travel - Audio", 50, "https://path-to-some-mp3-server/time-travel-for-dummies.mp3",
						"https://some-covers/image.png",
						"Reading out the book for you to make time travel even simpler",
						22500, timeTravelBook),
				johnUser.getId(), timeTravelBook.getId());
		timetravelAudio.setPublishedAt(new Date());
		audioService.update(timetravelAudio);

		// Add a podcast to the reviewer
		Audio timetravelPodcast = audioService.savePodcast(
				new Audio("Time Travel - Podcast", 25,
						"https://path-to-some-mp3-server/discussing-time-traver-for-dummies.mp3",
						"https://some-covers/image.png",
						"Kyouma, your host will discuss Time Travel for Dummies", 1200),
				kyoumaReviewer.getId());
		timetravelPodcast.setPublishedAt(new Date());
		audioService.update(timetravelPodcast);

		// "Kyouma Viewer" create review for "Time Travel for Dummies"
		artworkReviewService.saveArtworkReview(
				new ArtworkReview("This book is GOLDEN. Published!",
						"Hear my new Podcast about it if you're interested!",
						true, kyoumaReviewer, timeTravelBook));
		// Actually publish the book
		timeTravelBook.setPublishedAt(new Date());
		bookService.update(timeTravelBook);

		// "Barrel Reader": Add book to user-collections
		userCollectionService.saveUserCollection(new UserCollection(new Date(), barrelUser, timeTravelBook));

		// "Barrel Reader": Create review for "Time Travel for Dummies"
		artworkRatingService.saveArtworkRating(
				new ArtworkRating(5, "This book has some interesting things, but also a tag of sci-fi should be added.",
						barrelUser, timeTravelBook));
	}
}

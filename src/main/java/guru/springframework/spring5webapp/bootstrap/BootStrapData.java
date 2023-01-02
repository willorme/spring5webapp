package guru.springframework.spring5webapp.bootstrap;

import java.util.HashSet;
import java.util.Set;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;

@Component
public class BootStrapData implements CommandLineRunner{

	private final AuthorRepository authorRepository;
	private final BookRepository bookRepository;
	private final PublisherRepository publisherRepository;
	
	public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("Started in bootstrap");
		
		Author will = new Author("Will", "Orme");
		Book book1 = new Book("The Book", "397331");
		Set<Book> tempBooks = new HashSet<>();
		tempBooks.add(book1);
		will.setBooks(tempBooks);
		tempBooks.clear();
		Set<Author> tempAuthors = new HashSet<>();
		tempAuthors.add(will);
		book1.setAuthors(tempAuthors);
		tempAuthors.clear();
		
		authorRepository.save(will);
		bookRepository.save(book1);
		
		Author joshua = new Author("Joshua", "Bloch");
		Book effJava = new Book("Effective Java", "9409430");
		tempBooks.add(effJava);
		joshua.setBooks(tempBooks);
		tempBooks.clear();
		tempAuthors.add(joshua);
		effJava.setAuthors(tempAuthors);
		tempAuthors.clear();
		System.out.println("Temp books list: " + tempBooks + "\nTemp authors list: " + tempAuthors);
		
		authorRepository.save(joshua);
		bookRepository.save(effJava);
		
		Publisher publisher = new Publisher("XYZ Publishing", "272 Robert St", "St. Paul", "Minnesota", "55109");
		publisherRepository.save(publisher);
		
		System.out.println("Book count: " + bookRepository.count());
		System.out.println("Publisher count: " + publisherRepository.count());
		
	}

}

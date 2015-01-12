package com.healthycode.client;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.healthycode.domain.Article;
import com.healthycode.domain.Interview;
import com.healthycode.domain.Issue;
import com.healthycode.domain.Journal;
import com.healthycode.domain.Person;
import com.healthycode.relation.Issues;
import com.healthycode.relation.Subscriber;
import com.healthycode.service.JournalService;
import com.healthycode.vo.ArticleIssue;

public class GraphClient {
	private static JournalService journalService;
	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyyMMdd");

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		journalService = (JournalService) context.getBean("journalService");

		setupGraphDb();

		queryGraphNodes();

	}

	private static void queryGraphNodes() {
		try {
			Issue issue = journalService.getIssueForDate(dateFormat
					.parse("20150101"));
			System.out.println("Obtained issue " + issue.getName());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<ArticleIssue> articles = journalService
				.getArticlesAndIssuesForAuthor("Arunkumar Krishnamoorthy");
		for (ArticleIssue article : articles) {
			System.out.println(article.getArticle().getSummary()
					+ " featured in " + article.getIssue().getName()
					+ ", Issue date: " + article.getIssue().getIssueDate());
		}
		
		List<Issue> issues = journalService.getIssuesWithTemplate();
		for(Issue issue : issues) {
			System.out.println(issue.getName());
		}
	}

	private static void setupGraphDb() {
		try {

			Journal journal = new Journal();
			journal.setTitle("Healthy Code");

			Person publisher = new Person();
			publisher.setName("Siva Subramanian");
			publisher.setEmail("siva@durasoftindia.com");
			publisher.setMobile("09884294494");
			publisher.setAddress("DuraSoft");

			Person editor = new Person();
			editor.setName("Prabhu Sunderaraman");
			editor.setEmail("siva@durasoftindia.com");
			editor.setMobile("09884294494");
			editor.setAddress("DuraSoft");

			journal.setPublisher(publisher);
			journal.setEditor(editor);

			Person subscriber = new Person();
			subscriber.setName("Arunkumar Krishnamoorthy");
			subscriber.setEmail("arunram.krish@gmail.com");
			subscriber.setMobile("09449804064");
			subscriber.setAddress("Ram Software Engineering Labs");
			
			Person author = new Person();
			author.setName("Ravikant Shukla");
			author.setEmail("ravikantshukla5@gmail.com");
			author.setMobile("08050641876");
			author.setAddress("Ram Software Engineering Labs");

			Subscriber subscriberRel = new Subscriber();
			subscriberRel.setSubscribeOn(new Date());
			subscriberRel.setTillDate(new Date());
			subscriberRel.setType("Soft And Hard Copy");
			subscriberRel.setJournal(journal);
			subscriberRel.setPerson(subscriber);

			journal.getSubscribers().add(subscriberRel);
			
            Set<Person> authorSubscriber = new HashSet<Person>();
            authorSubscriber.add(subscriber);
            
            Article articleNeo4J = new Article();
    		articleNeo4J.setAuthors(authorSubscriber);
    		articleNeo4J.setTitle("Neo4J Basics");
    		articleNeo4J.setSummary("Working With Neo4J Graph Database");
    		articleNeo4J.setTags("Neo4J Basics");
    		
    		Set<Person> authors = new HashSet<Person>();
    		authors.add(subscriber);
    		authors.add(author);
    		
    		Article articleSpringDataNeo4J = new Article();
    		articleSpringDataNeo4J.setAuthors(authors);
    		articleSpringDataNeo4J.setTitle("Neo4J Basics");
    		articleSpringDataNeo4J.setSummary("Working With Spring Data Neo4J");
    		articleSpringDataNeo4J.setTags("Neo4J With Spring");
    		
    		Set<Article> articles = new HashSet<Article>();
    		articles.add(articleNeo4J);
    		articles.add(articleSpringDataNeo4J);
    		
    		Person interviewee = new Person();
    		interviewee.setName("Arthi");
    		interviewee.setEmail("arthi@gmail.com");
    		interviewee.setMobile("09449804064");
    		interviewee.setAddress("Wipro");

    		Interview interview = new Interview();
    		interview
    				.setSummary("Working with Wipro, has experience in IoTs and POC");
    		interview.setName("Interview With Arthi");
    		interview.setInterviewee(interviewee);
    		
    		Set<Interview> interviews = new HashSet<Interview>();
    		interviews.add(interview);
    		
			Issue issue = createIssue(articles,interviews);

			Issues issues = new Issues();
			issues.setIssue(issue);
			issues.setJournal(journal);
			issues.setIssueDate("Dec 2014");

			journal.getIssues().add(issues);

			// journalService.savePerson(publisher);
			// journalService.savePerson(editor);
			// journalService.savePerson(subscriber);
			// journalService.savePerson(interviewee);

			journalService.saveIssue(issue);

			journalService.saveJournal(journal);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static Issue createIssue(Set<Article> articles, Set<Interview> interviews) {
		Issue issue = new Issue();
		issue.setCoverPage("Dan Allen of AsciiDoc, Community Strategist");
		issue.setIssueNo(9);
		issue.setVolumeNo(1);
		issue.setName("Healthy Code Dec 2014 Issue");
		try {
			issue.setIssueDate(dateFormat.parse("20150101"));
		} catch (ParseException e) {
			issue.setIssueDate(new Date());
		}
		issue.setArticles(articles);
		issue.setInterviews(interviews);
		return issue;
	}

}

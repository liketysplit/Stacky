package sprint2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class GroupTest {

	/**
	 * This method should test that a group is constructed properly with the values
	 * it is initialized with
	 * 
	 */
	@Test
	void testGroup() {
		String expectedTitle = "testTitle";
		String expectedDescription = "testDescription";
		LocalDateTime expectedDateCreated = LocalDateTime.now();

		Group g = new Group(expectedTitle, expectedDescription, expectedDateCreated);

		String actualTitle = g.getTitle();
		String actualDescription = g.getDescription();
		LocalDateTime actualDateCreated = g.getDateCreated();

		assertAll(() -> assertEquals(actualTitle, expectedTitle),
				  () -> assertEquals(actualDescription, expectedDescription),
				  () -> assertEquals(actualDateCreated, expectedDateCreated));
	}

	/**
	 * This method should test that a group returns the date it was created.
	 * 
	 */
	@Test
	void testGetDateCreated() {
		LocalDateTime expectedTime = LocalDateTime.parse("2018-07-01T08:00");
		Group g = new Group("TestGroup", "Description", expectedTime);
		LocalDateTime actualTime = g.getDateCreated();
		assertEquals(actualTime, expectedTime);
	}

	/**
	 * This method should test that a group returns the title of the group.
	 * 
	 */
	@Test
	void testGetTitle() {
		String expectedTitle = "This is a test Title";
		Group g = new Group(expectedTitle, "This is a test Description", LocalDateTime.now());
		String actualTitle = g.getTitle();
		assertEquals(actualTitle, expectedTitle);
	}

	/**
	 * This method should test that a group returns the description of the group.
	 * 
	 */
	@Test
	void testGetDescription() {
		String expectedDescription = "This is a test Description";
		Group g = new Group("This is a test Title", expectedDescription, LocalDateTime.now());
		String actualDescription = g.getDescription();
		assertEquals(actualDescription, expectedDescription);
	}

	/**
	 * This method should test that a group returns the number of members who have 
	 * joined it.
	 * 
	 */
	@Test
	void testGetNumMembers() {
		Member m1 = new Member("FirstName", "LastName", "ScreenName1", "m1@email.com",
				LocalDateTime.now());
		Member m2 = new Member("FirstName", "LastName", "ScreenName2", "m2@email.com",
				LocalDateTime.now());
		Member m3 = new Member("FirstName", "LastName", "ScreenName3", "m3@email.com",
				LocalDateTime.now());
		Member m4 = new Member("FirstName", "LastName", "ScreenName4", "m4@email.com",
				LocalDateTime.now());
		Group g = new Group("This is a test Title", "This is a test Description", LocalDateTime.now());
		m1.joinGroup(g, LocalDateTime.now());
		m2.joinGroup(g, LocalDateTime.now());
		m3.joinGroup(g, LocalDateTime.now());
		m4.joinGroup(g, LocalDateTime.now());
		int expectedNumMembers = 4;
		int actualNumMembers = g.getNumMembers();
		assertEquals(actualNumMembers, expectedNumMembers);
	}

	/**
	 * This method should test that a group returns the members who 
	 * have joined it, sorted alphabetically, first by last name, then 
	 * by first name.
	 * 
	 */
	@Test
	void testGetMembers() {
		LocalDateTime time = LocalDateTime.parse("2018-07-01T08:00");
		Member m1 = new Member("FirstName", "C_LastName", "ScreenName1", "m1@email.com", time);
		Member m2 = new Member("B_FirstName", "B_LastName", "ScreenName2", "m2@email.com", time);
		Member m3 = new Member("FirstName", "A_LastName", "ScreenName3", "m3@email.com", time);
		Member m4 = new Member("A_FirstName", "B_LastName", "ScreenName4", "m4@email.com", time);
		Group g = new Group("This is a test Title", "This is a test Description", LocalDateTime.now());
		
		m1.joinGroup(g, LocalDateTime.now());
		m2.joinGroup(g, LocalDateTime.now());
		m3.joinGroup(g, LocalDateTime.now());
		m4.joinGroup(g, LocalDateTime.now());
		
		ArrayList<Member> expectedMembers = new ArrayList<Member>();
		expectedMembers.add(m3);
		expectedMembers.add(m4);
		expectedMembers.add(m2);
		expectedMembers.add(m1);
		
		ArrayList<Member> actualMembers = (ArrayList<Member>) g.getMembers();
		assertEquals(actualMembers, expectedMembers);
	}

	/**
	 * This method should test that a group returns a member that has
	 * joined it using the member's email address.
	 * 
	 */
	@Test
	void testGetMember() {
		LocalDateTime time = LocalDateTime.parse("2018-07-01T08:00");
		String emailAddress = "FirstNameLastName@email.com";
		Member expectedMember = new Member("FirstName", "LastName", "ScreenName", emailAddress, time);
		Group g = new Group("This is a test Title", "This is a test Description", LocalDateTime.now());
		
		expectedMember.joinGroup(g, LocalDateTime.now());
		
		Member actualMember = g.getMember(emailAddress);
		
		assertEquals(actualMember, expectedMember);
	}
	
	 /**
	 * This method should test that a group returns null when the 
	 * email address passed in does not belong to any of its members
	 * 
	 */
	@Test
	void testGetMemberDoesNotExist() {
		String emailAddress = "FirstNameLastName@email.com";
		Group g = new Group("This is a test Title", "This is a test Description", LocalDateTime.now());
		
		Member actualMember = g.getMember(emailAddress);
		
		assertEquals(null, actualMember);
	}

	/**
	 * This method should test that a group adds a membership to its
	 * internal hashMap of memberships. This can be tested by adding a
	 * membership and then attempting to retrieve the member with 
	 * Group.getMember(String email).
	 * 
	 */
	@Test
	void testAddToMemberships() {
		LocalDateTime time = LocalDateTime.parse("2018-07-01T08:00");
		String email = "m1@email.com";
		Member m = new Member("FirstName", "LastName", "ScreenName", email, time);
		Group g = new Group("This is a test Title", "This is a test Description", time);
		Membership membership = new Membership(g, m, LocalDateTime.now());
		
		g.addToMemberships(membership);
		
		Member expectedMember = m;
		Member actualMember = g.getMember(email);
		
		assertEquals(actualMember, expectedMember);
	}

	/**
	 * This method should test that a group returns a list of all questions 
	 * asked in that group.
	 * 
	 */
	@Test
	void testGetQuestions() {
		LocalDateTime time = LocalDateTime.parse("2018-07-01T08:00");
		// 2018/10/1 0:00:00
		LocalDateTime dateCreatedOne = LocalDateTime.of(2018,10,1,0,0);
		// 2018/10/2 0:00:00
		LocalDateTime dateCreatedTwo = LocalDateTime.of(2018,10,2,0,0);
		// 2018/10/3 0:00:00
		LocalDateTime dateCreatedThree = LocalDateTime.of(2018,10,3,0,0);
		Group g = new Group("This is a test Title", "This is a test Description", time);

		Question q1 = new Question("title1", "asking q1", dateCreatedTwo);
		Question q2 = new Question("title2", "asking q2", dateCreatedThree);
		Question q3 = new Question("title3", "asking q3", dateCreatedOne);
		Question q4 = new Question("title4", "asking q4", LocalDateTime.now());

		g.addQuestion(q1);
		g.addQuestion(q2);
		g.addQuestion(q3);

		List<Question> expectedQuestions = new ArrayList<Question>();

		expectedQuestions.add(q2);
		expectedQuestions.add(q1);
		expectedQuestions.add(q3);

		List<Question> actualQuestions = (ArrayList<Question>) g.getQuestions();
		
		// As this is question is added after the getQuestions() method call
		// it should not appear in either of the array lists in the assertion
		g.addQuestion(q4);

		assertEquals(actualQuestions, expectedQuestions);

	}

	/**
	 * This method should test that a group returns a list of all answers 
	 * posted in that group.
	 * 
	 */
	@Test
	void testGetAnswers() {
		LocalDateTime time = LocalDateTime.parse("2018-07-01T08:00");
		// 2018/10/1 0:00:00
		LocalDateTime dateCreatedOne = LocalDateTime.of(2018,10,1,0,0);
		// 2018/10/2 0:00:00
		LocalDateTime dateCreatedTwo = LocalDateTime.of(2018,10,2,0,0);
		// 2018/10/3 0:00:00
		LocalDateTime dateCreatedThree = LocalDateTime.of(2018,10,3,0,0);
		Group g = new Group("This is a test Title", "This is a test Description", time);
		Question q1 = new Question("title1", "asking q1", time);
		
		Answer a1 = new Answer(q1, "answer1", dateCreatedTwo);
		Answer a2 = new Answer(q1, "answer2", dateCreatedThree);
		Answer a3 = new Answer(q1, "answer3", dateCreatedOne);
		Answer a4 = new Answer(q1, "answer4", LocalDateTime.now());

		g.addAnswer(a1);
		g.addAnswer(a2);
		g.addAnswer(a3);

		List<Answer> expectedAnswers = new ArrayList<Answer>();

		expectedAnswers.add(a2);
		expectedAnswers.add(a1);
		expectedAnswers.add(a3);

		List<Answer> actualAnswers = (ArrayList<Answer>) g.getAnswers();
		
		// As this is answer is added after the getAnswers() method call
		// it should not appear in either of the array lists in the assertion
		g.addAnswer(a4);		

		assertEquals(actualAnswers, expectedAnswers);
	}
	
	/**
	 * This method should test that a group returns a list of the n questions 
	 * asked in the group with the most points, sorted on the number of 
	 * points, highest first.
	 * 
	 */
	@Test
	void testGetQuestionsByPoints() {
		LocalDateTime time = LocalDateTime.parse("2018-07-01T08:00");
		Group g = new Group("This is a test Title", "This is a test Description", time);
		
		Question q1 = new Question("title1", "asking q1", time);
		Question q2 = new Question("title2", "asking q2", time);
		Question q3 = new Question("title3", "asking q3", time);
		Question q4 = new Question("title4", "asking q4", time);

		g.addQuestion(q1);
		g.addQuestion(q2);
		g.addQuestion(q3);
		g.addQuestion(q4);
		
		// Question Two has 3 points
		q2.upvote();
		q2.upvote();
		q2.upvote();
		
		// Question Three has 2 points
		q3.upvote();
		q3.upvote();
		
		// Question One has 1 point
		q1.upvote();

		List<Question> expectedQuestions = new ArrayList<Question>();

		expectedQuestions.add(q2);
		expectedQuestions.add(q3);
		expectedQuestions.add(q1);

		List<Question> actualQuestions = (ArrayList<Question>) g.getQuestionsByPoints(3);

		assertEquals(actualQuestions, expectedQuestions);

	}
	
	/**
	 * This method should test that a group returns a list of the n answers 
	 * posted to a particular question with the most points, sorted on the number of 
	 * points, highest first.
	 * 
	 */
	@Test
	void testGetAnswersByPoints() {
		LocalDateTime time = LocalDateTime.parse("2018-07-01T08:00");
		Group g = new Group("This is a test Title", "This is a test Description", time);		
		
		Question q1 = new Question("title1", "asking q1", time);
		Answer a1 = new Answer(q1, "answer1", time);
		Answer a2 = new Answer(q1, "answer2", time);
		Answer a3 = new Answer(q1, "answer3", time);
		Answer a4 = new Answer(q1, "answer4", time);
		
		q1.addAnswer(a1);
		q1.addAnswer(a2);
		q1.addAnswer(a3);
		q1.addAnswer(a4);

		g.addQuestion(q1);
		g.addAnswer(a1);
		g.addAnswer(a2);
		g.addAnswer(a3);
		g.addAnswer(a4);
		
		// Answer Two has 3 points
		a2.upvote();
		a2.upvote();
		a2.upvote();
		
		// Answer Three has 2 points
		a3.upvote();
		a3.upvote();
		
		// Answer One has 1 point
		a1.upvote();

		List<Answer> expectedAnswers = new ArrayList<Answer>();

		expectedAnswers.add(a2);
		expectedAnswers.add(a3);
		expectedAnswers.add(a1);

		List<Answer> actualAnswers = (ArrayList<Answer>) g.getAnswersByPoints(3, q1);

		assertEquals(actualAnswers, expectedAnswers);

	}
	
	
	/**
	 * This method should test that a group returns null when the 
	 * getAnswersByPoints() method is called but the question parameter
	 * passed in is not in the group that called the method.
	 * 
	 */
	@Test
	void testGetAnswersByPointsQuestionNotInGroup() {
		LocalDateTime time = LocalDateTime.parse("2018-07-01T08:00");
		Group g = new Group("This is a test Title", "This is a test Description", time);
		
		Question q1 = new Question("title1", "asking q1", time);
		Answer a1 = new Answer(q1, "answer1", time);
		Answer a2 = new Answer(q1, "answer2", time);
		Answer a3 = new Answer(q1, "answer3", time);
		Answer a4 = new Answer(q1, "answer4", time);

		// Question q1 is never added to Group g
		g.addAnswer(a1);
		g.addAnswer(a2);
		g.addAnswer(a3);
		g.addAnswer(a4);
		
		// Answer Two has 3 points
		a2.upvote();
		a2.upvote();
		a2.upvote();
		
		// Answer Three has 2 points
		a3.upvote();
		a3.upvote();
		
		// Answer One has 1 point
		a1.upvote();

		List<Answer> expectedAnswers = null;

		List<Answer> actualAnswers = (ArrayList<Answer>) g.getAnswersByPoints(3, q1);

		assertEquals(actualAnswers, expectedAnswers);

	}

	
	/**
	 * This method should test that a group adds a question to its
	 * internal arrayList of questions.
	 * 
	 * This is the same test as testGetQuestions because it calls addQuestion and
	 * this is the only way to compare
	 */
	@Test
	void testAddQuestion() {
		LocalDateTime time = LocalDateTime.parse("2018-07-01T08:00");
		// 2018/10/1 0:00:00
		LocalDateTime dateCreatedOne = LocalDateTime.of(2018,10,1,0,0);
		// 2018/10/2 0:00:00
		LocalDateTime dateCreatedTwo = LocalDateTime.of(2018,10,2,0,0);
		// 2018/10/3 0:00:00
		LocalDateTime dateCreatedThree = LocalDateTime.of(2018,10,3,0,0);
		Group g = new Group("This is a test Title", "This is a test Description", time);

		Question q1 = new Question("title1", "asking q1", dateCreatedThree);
		Question q2 = new Question("title2", "asking q2", dateCreatedTwo);
		Question q3 = new Question("title3", "asking q3", dateCreatedOne);

		g.addQuestion(q1);
		g.addQuestion(q2);
		g.addQuestion(q3);

		List<Question> expectedQuestions = new ArrayList<Question>();

		expectedQuestions.add(q1);
		expectedQuestions.add(q2);
		expectedQuestions.add(q3);

		List<Question> actualQuestions = (ArrayList<Question>) g.getQuestions();

		assertEquals(actualQuestions, expectedQuestions);
	}

	/**
	 * This method should test that a group adds an answer to its
	 * internal arrayList of answers.
	 * 
	 * This is the same test as testGetAnswers because it calls addAnswer and this
	 * is the only way to compare.
	 */
	@Test
	void testAddAnswer() {
		LocalDateTime time = LocalDateTime.parse("2018-07-01T08:00");
		// 2018/10/1 0:00:00
		LocalDateTime dateCreatedOne = LocalDateTime.of(2018,10,1,0,0);
		// 2018/10/2 0:00:00
		LocalDateTime dateCreatedTwo = LocalDateTime.of(2018,10,2,0,0);
		// 2018/10/3 0:00:00
		LocalDateTime dateCreatedThree = LocalDateTime.of(2018,10,3,0,0);
		Group g = new Group("This is a test Title", "This is a test Description", time);

		Question q1 = new Question("title1", "asking q1", time);
		Answer a1 = new Answer(q1, "answer1", dateCreatedThree);
		Answer a2 = new Answer(q1, "answer2", dateCreatedTwo);
		Answer a3 = new Answer(q1, "answer3", dateCreatedOne);

		g.addAnswer(a1);
		g.addAnswer(a2);
		g.addAnswer(a3);

		List<Answer> expectedAnswers = new ArrayList<Answer>();

		expectedAnswers.add(a1);
		expectedAnswers.add(a2);
		expectedAnswers.add(a3);

		List<Answer> actualAnswers = (ArrayList<Answer>) g.getAnswers();

		assertEquals(actualAnswers, expectedAnswers);
	}
	
	/**
	 * This method should test that a group returns the total number of questions that
	 * have been asked in that group.
	 * 
	 */
	@Test
	void testGetNumQuestions() {
		LocalDateTime time = LocalDateTime.parse("2018-07-01T08:00");
		Group g = new Group("This is a test Title", "This is a test Description", time);

		Question q1 = new Question("title1", "asking q1", time);
		Question q2 = new Question("title2", "asking q2", time);
		Question q3 = new Question("title3", "asking q3", time);

		g.addQuestion(q1);
		g.addQuestion(q2);
		g.addQuestion(q3);

		int expectedNumQuestions = 3;

		int actualNumQuestions = g.getNumQuestions();

		assertEquals(actualNumQuestions, expectedNumQuestions);
	}
	
	/**
	 * This method should test that a group returns the total number of answers
	 * that have been posted in that group.
	 * 
	 */
	@Test
	void testGetNumAnswers() {
		LocalDateTime time = LocalDateTime.parse("2018-07-01T08:00");
		Group g = new Group("This is a test Title", "This is a test Description", time);
		Member m1 = new Member("m1", "m1", "m1", "m1@email.com", time);

		Question q1 = new Question("title1", "asking q1", time);
		Question q2 = new Question("title2", "asking q2", time);
		Answer a1 = new Answer(q1, "answer1", time);
		Answer a2 = new Answer(q1, "answer2", time);
		Answer a3 = new Answer(q2, "answer3", time);
		
		m1.joinGroup(g, time);

//		g.addQuestion(q1);
//		g.addQuestion(q2);
		m1.addQuestion(g, q1, time);
		m1.addQuestion(g, q2, time);
		
		m1.addAnswer(g, a1.getQuestion(), a1, time);
		m1.addAnswer(g, a2.getQuestion(), a2, time);
		m1.addAnswer(g, a3.getQuestion(), a3, time);

		int expectedNumAnswers = 3;

		int actualNumAnswers = g.getNumAnswers();

		assertEquals(actualNumAnswers, expectedNumAnswers);
	}
	
	/**
	 * This method should test that a group returns a list of the n members who 
	 * have joined that group with the most activeness (questions asked + 
	 * answers posted), sorted on activeness, highest first.
	 * 
	 */
	@Test
	void testActiveMembers() {
		LocalDateTime time = LocalDateTime.parse("2018-07-01T08:00");
		Group g = new Group("This is a test Title", "This is a test Description", time);
		Member m1 = new Member("m1", "m1", "m1", "m1@email.com", time);
		Member m2 = new Member("m2", "m2", "m2", "m2@email.com", time);
		Member m3 = new Member("m3", "m3", "m3", "m3@email.com", time);
		
		Question q1 = new Question("question1", "asking q1", time);
		Question q2 = new Question("question2", "asking q2", time);
		Question q3 = new Question("question3", "asking q3", time);
		Answer a1 = new Answer(q1, "answer1", time);
		Answer a2 = new Answer(q1, "answer2", time);
		Answer a3 = new Answer(q1, "answer3", time);
		
		m1.joinGroup(g, time);
		m2.joinGroup(g, time);
		m3.joinGroup(g, time);
		
		// m2 asked 2 questions and posted 0 answers
		// m2 activeness  = 2
		m2.addQuestion(g, q1, time);
		m2.addQuestion(g, q3, time);
		
		// m1 asked 1 question and posted 2 answers to another question
		// m1 activeness = 3
		m1.addQuestion(g, q2, time);
		m1.addAnswer(g, a1.getQuestion(), a1, time);
		m1.addAnswer(g, a2.getQuestion(), a2, time);

		// m3 asked 0 questions and posted 1 answer
		// m3 activeness = 1
		m3.addAnswer(g, a3.getQuestion(), a3, time);
		
		ArrayList<Member> expectedMembers = (ArrayList<Member>) g.getActiveMembers(2);
		
		ArrayList<Member> actualMembers = new ArrayList<Member>();
		actualMembers.add(m1);
		actualMembers.add(m2);
		
		assertEquals(actualMembers, expectedMembers);
	}
	
	/**
	 * This method should test that a group returns a list of all members,
	 * sorted on activeness (questions asked + answers posted), 
	 * highest first.
	 * 
	 * The parameter given to getActiveMembers(int n) is greater than the total
	 * number of members in the group. This should not crash.
	 */
	@Test
	void testActiveMembersNGreaterThanTotalMembers() {
		LocalDateTime time = LocalDateTime.parse("2018-07-01T08:00");
		Group g = new Group("This is a test Title", "This is a test Description", time);
		Member m1 = new Member("m1", "m1", "m1", "m1@email.com", time);
		Member m2 = new Member("m2", "m2", "m2", "m2@email.com", time);
		Member m3 = new Member("m3", "m3", "m3", "m3@email.com", time);
		
		Question q1 = new Question("question1", "asking q1", time);
		Question q2 = new Question("question2", "asking q2", time);
		Question q3 = new Question("question3", "asking q3", time);
		Answer a1 = new Answer(q1, "answer1", time);
		Answer a2 = new Answer(q1, "answer2", time);
		Answer a3 = new Answer(q1, "answer3", time);
		
		m1.joinGroup(g, time);
		m2.joinGroup(g, time);
		m3.joinGroup(g, time);
		
		// m2 asked 2 questions and posted 0 answers
		// m2 activeness  = 2
		m2.addQuestion(g, q1, time);
		m2.addQuestion(g, q3, time);
		
		// m1 asked 1 question and posted 2 answers to another question
		// m1 activeness = 3
		m1.addQuestion(g, q2, time);
		m1.addAnswer(g, a1.getQuestion(), a1, time);
		m1.addAnswer(g, a2.getQuestion(), a2, time);

		// m3 asked 0 questions and posted 1 answer
		// m3 activeness = 1
		m3.addAnswer(g, a3.getQuestion(), a3, time);
		
		ArrayList<Member> expectedMembers = (ArrayList<Member>) g.getActiveMembers(4);
		
		ArrayList<Member> actualMembers = new ArrayList<Member>();
		actualMembers.add(m1);
		actualMembers.add(m2);
		actualMembers.add(m3);
		
		assertEquals(actualMembers, expectedMembers);
	}
	
	/**
	 * This method should test that a group returns a list of the n
	 * most recent questions asked in that group, sorted on
	 * date created, most recent first.
	 * 
	 */
	@Test
	void testGetQuestionsOverloaded() {
		LocalDateTime time = LocalDateTime.parse("2018-07-01T08:00");
		// 2018/10/1 0:00:00
		LocalDateTime dateCreatedOne = LocalDateTime.of(2018,10,1,0,0);
		// 2018/10/2 0:00:00
		LocalDateTime dateCreatedTwo = LocalDateTime.of(2018,10,2,0,0);
		// 2018/10/3 0:00:00
		LocalDateTime dateCreatedThree = LocalDateTime.of(2018,10,3,0,0);

		Group g = new Group("This is a test Title", "This is a test Description", time);
		Member m1 = new Member("m1", "m1", "m1", "m1@email.com", time);
		Question q1 = new Question("title1","asking q1", dateCreatedOne);
		Question q2 = new Question("title2","asking q2", dateCreatedTwo);
		Question q3 = new Question("title3","asking q3", dateCreatedThree);

		m1.joinGroup(g, time);

		m1.addQuestion(g, q1, time);
		m1.addQuestion(g, q2, time);
		m1.addQuestion(g, q3, time);
		
		ArrayList<Question> actualList = (ArrayList<Question>) g.getQuestions(2);
		
		ArrayList<Question> expectedList = new ArrayList<Question>();
		expectedList.add(q3);
		expectedList.add(q2);
		
		assertEquals(actualList, expectedList);
	}
	
	/**
	 * This method should test that a group returns all questions
	 * asked in that group, sorted on date created, most recent first.
	 * 
	 * The parameter given to getQuestions(int n) is greater than the total
	 * number of questions the group has. This should not crash.
	 */
	@Test
	void testGetQuestionsOverloadedNGreaterThanTotalQuestions() {
		LocalDateTime time = LocalDateTime.parse("2018-07-01T08:00");
		// 2018/10/1 0:00:00
		LocalDateTime dateCreatedOne = LocalDateTime.of(2018,10,1,0,0);
		// 2018/10/2 0:00:00
		LocalDateTime dateCreatedTwo = LocalDateTime.of(2018,10,2,0,0);
		// 2018/10/3 0:00:00
		LocalDateTime dateCreatedThree = LocalDateTime.of(2018,10,3,0,0);

		Group g = new Group("This is a test Title", "This is a test Description", time);
		Member m1 = new Member("m1", "m1", "m1", "m1@email.com", time);
		Question q1 = new Question("title1","asking q1", dateCreatedOne);
		Question q2 = new Question("title2","asking q2", dateCreatedTwo);
		Question q3 = new Question("title3","asking q3", dateCreatedThree);

		m1.joinGroup(g, time);

		m1.addQuestion(g, q1, time);
		m1.addQuestion(g, q2, time);
		m1.addQuestion(g, q3, time);
		
		ArrayList<Question> actualList = (ArrayList<Question>) g.getQuestions(4);
		
		ArrayList<Question> expectedList = new ArrayList<Question>();
		expectedList.add(q3);
		expectedList.add(q2);
		expectedList.add(q1);
		
		assertEquals(actualList, expectedList);
	}
	
	/**
	 * This method should test that a group returns a list of the n
	 * most recent answers posted in that group, sorted on
	 * date created, most recent first.
	 * 
	 */
	@Test
	void testGetAnswersOverloaded() {
		LocalDateTime time = LocalDateTime.parse("2018-07-01T08:00");
		// 2018/10/1 0:00:00
		LocalDateTime dateCreatedOne = LocalDateTime.of(2018,10,1,0,0);
		// 2018/10/2 0:00:00
		LocalDateTime dateCreatedTwo = LocalDateTime.of(2018,10,2,0,0);
		// 2018/10/3 0:00:00
		LocalDateTime dateCreatedThree = LocalDateTime.of(2018,10,3,0,0);

		Group g = new Group("This is a test Title", "This is a test Description", time);
		Member m1 = new Member("m1", "m1", "m1", "m1@email.com", time);
		Question q1 = new Question("title1","asking q1", time);

		Answer a1 = new Answer(q1, "answer1", dateCreatedOne);
		Answer a2 = new Answer(q1, "answer2", dateCreatedTwo);
		Answer a3 = new Answer(q1, "answer3", dateCreatedThree);
		
		m1.joinGroup(g, time);

		m1.addQuestion(g, q1, dateCreatedOne);	
		// Adding LocalDateTime in opposite order to ensure only dateCreated is 
		// considered in the method, not this parameter passed in.
		m1.addAnswer(g, q1, a1, dateCreatedThree);
		m1.addAnswer(g, q1, a2, dateCreatedTwo);
		m1.addAnswer(g, q1, a3, dateCreatedOne);
		
		
		ArrayList<Answer> actualList = (ArrayList<Answer>) g.getAnswers(2);
		
		ArrayList<Answer> expectedList = new ArrayList<Answer>();
		expectedList.add(a3);
		expectedList.add(a2);
		
		assertEquals(actualList, expectedList);
	}
	
	/**
	 * This method should test that a group returns all answers
	 * asked in that group, sorted on date created, most recent first.
	 * 
	 * The parameter given to getAnswers(int n) is greater than the total
	 * number of answers the group has. This should not crash.
	 */
	@Test
	void testGetAnswersOverloadedNGreaterThanTotalAnswers() {
		LocalDateTime time = LocalDateTime.parse("2018-07-01T08:00");
		// 2018/10/1 0:00:00
		LocalDateTime dateCreatedOne = LocalDateTime.of(2018,10,1,0,0);
		// 2018/10/2 0:00:00
		LocalDateTime dateCreatedTwo = LocalDateTime.of(2018,10,2,0,0);
		// 2018/10/3 0:00:00
		LocalDateTime dateCreatedThree = LocalDateTime.of(2018,10,3,0,0);

		Group g = new Group("This is a test Title", "This is a test Description", time);
		Member m1 = new Member("m1", "m1", "m1", "m1@email.com", time);
		Question q1 = new Question("title1","asking q1", time);

		Answer a1 = new Answer(q1, "answer1", dateCreatedOne);
		Answer a2 = new Answer(q1, "answer2", dateCreatedTwo);
		Answer a3 = new Answer(q1, "answer3", dateCreatedThree);
		
		m1.joinGroup(g, time);

		m1.addQuestion(g, q1, dateCreatedOne);	
		// Adding LocalDateTime in opposite order to ensure only dateCreated is 
		// considered in the method, not this parameter passed in.
		m1.addAnswer(g, q1, a1, dateCreatedThree);
		m1.addAnswer(g, q1, a2, dateCreatedTwo);
		m1.addAnswer(g, q1, a3, dateCreatedOne);
		
		
		ArrayList<Answer> actualList = (ArrayList<Answer>) g.getAnswers(4);
		
		ArrayList<Answer> expectedList = new ArrayList<Answer>();
		expectedList.add(a3);
		expectedList.add(a2);
		expectedList.add(a1);
		
		assertEquals(actualList, expectedList);
	}

	/**
	 * This method should test that a Group returns an array list of the first
	 * n answers of the input array list.
	 * 
	 */
    @Test
    void testGetListNumOfAnswers() {
        LocalDateTime ldt = LocalDateTime.now();
        
        Group g = new Group("Title", "description", ldt);

        Question q1 = new Question("title1", "asking q1", ldt);
		Answer a1 = new Answer(q1, "answer1", ldt);
		Answer a2 = new Answer(q1, "answer2", ldt);
		Answer a3 = new Answer(q1, "answer3", ldt);
		Answer a4 = new Answer(q1, "answer4", ldt);

        ArrayList<Answer> answers = new ArrayList<Answer>();
        ArrayList<Answer> expectedAnswers = new ArrayList<Answer>();
        answers.add(a4);
        answers.add(a2);
        answers.add(a1);
        answers.add(a3);
        
        expectedAnswers.add(a4);
        expectedAnswers.add(a2);

        ArrayList<Answer> actualAnswers = (ArrayList<Answer>)g.getListNumOfAnswers(expectedAnswers, 2);

        assertEquals(expectedAnswers, actualAnswers);
    }
    
    /**
	 * This method should test that a Group returns an array list of the first
	 * n questions of the input array list.
	 * 
	 */
    @Test
    void testGetListNumOfQuestions() {
        LocalDateTime ldt = LocalDateTime.now();
        
        Group g = new Group("Title", "description", ldt);

        Question q1 = new Question("title1", "asking q1", ldt);
        Question q2 = new Question("title2", "asking q2", ldt);
        Question q3 = new Question("title3", "asking q3", ldt);
        Question q4 = new Question("title4", "asking q4", ldt);
        
        ArrayList<Question> questions = new ArrayList<Question>();
        ArrayList<Question> expectedQuestions = new ArrayList<Question>();
        questions.add(q4);
        questions.add(q2);
        questions.add(q1);
        questions.add(q3);
        
        expectedQuestions.add(q4);
        expectedQuestions.add(q2);

        ArrayList<Question> actualQuestions = (ArrayList<Question>)g.getListNumOfQuestions(expectedQuestions, 2);

        assertEquals(expectedQuestions, actualQuestions);
    }
	
	/**
	 * This method should test that a group returns the correct toString.
	 * 
	 */
	@Test
	void testToString() {
		String title = "testTitle";
		String description = "testDescription";
		LocalDateTime dateCreated = LocalDateTime.now();

		String expectedToString = "Title: " + title + ", Description: " + description + ", Date Created: "
				+ dateCreated;

		Group g = new Group(title, description, dateCreated);
		String actualToString = g.toString();

		assertEquals(actualToString, expectedToString);
	}

	/**
	 * This method should test that the Group Title Comparator works
	 * properly.
	 * 
	 */
	@Test
	void testGroupTitleComparator() {
		LocalDateTime time = LocalDateTime.parse("2018-07-01T08:00");
		Group g1 = new Group("Apple", "This is a test Description", time);
		Group g2 = new Group("Barn", "This is a test Description", time);
		Group g3 = new Group("Cat", "This is a test Description", time);

		ArrayList<Group> actualList = new ArrayList<Group>();
		actualList.add(g3);
		actualList.add(g1);
		actualList.add(g2);

		ArrayList<Group> wrongList = new ArrayList<Group>();
		wrongList.add(g3);
		wrongList.add(g2);
		wrongList.add(g1);

		ArrayList<Group> expectedList = new ArrayList<Group>();
		expectedList.add(g1);
		expectedList.add(g2);
		expectedList.add(g3);

		Collections.sort(actualList, new GroupTitleComparator());

		// Test to make sure the list was sorted correctly and it didn't sort in the
		// wrong order
		assertEquals(actualList, expectedList);
		assertNotEquals(wrongList, expectedList);
	}

	/**
	 * This method should test that the Group Number of Members Comparator
	 * works properly.
	 * 
	 */
	@Test
	void testGroupNumMembersComparator() {
		LocalDateTime time = LocalDateTime.parse("2018-07-01T08:00");
		Member m1 = new Member("m1", "m1", "m1", "m1@email.com", time);
		Member m2 = new Member("m2", "m2", "m2", "m2@email.com", time);
		Member m3 = new Member("m3", "m3", "m3", "m3@email.com", time);
		Group g1 = new Group("g1", "This is a test Description", LocalDateTime.now());
		Group g2 = new Group("g2", "This is a test Description", LocalDateTime.now());

		// g1 has 1 member and g2 has 3 members
		m1.joinGroup(g1, LocalDateTime.now());
		m1.joinGroup(g2, LocalDateTime.now());
		m2.joinGroup(g2, LocalDateTime.now());
		m3.joinGroup(g2, LocalDateTime.now());

		ArrayList<Group> actualList = new ArrayList<Group>();
		actualList.add(g2);
		actualList.add(g1);

		ArrayList<Group> expectedList = new ArrayList<Group>();
		expectedList.add(g1);
		expectedList.add(g2);

		Collections.sort(actualList, new GroupNumMembersComparator());

		assertEquals(actualList, expectedList);
	}
	
	/**
	 * This method should test that the Group Overall Activeness Comparator works
	 * properly.
	 * 
	 */
	@Test
	void testGroupOverallActiveComparator() {
		LocalDateTime time = LocalDateTime.parse("2018-07-01T08:00");
		Group g1 = new Group("g1", "This is a test Description", time);
		Group g2 = new Group("g2", "This is a test Description", time);
		Group g3 = new Group("g3", "This is a test Description", time);

		Question q1 = new Question("question1", "asking q1", time);
		Question q2 = new Question("question2", "asking q2", time);
		Question q3 = new Question("question3", "asking q3", time);
		Question q4 = new Question("question4", "asking q4", time);
		Answer a1 = new Answer(q1, "answer1", time);
		Answer a2 = new Answer(q1, "answer2", time);
		Answer a3 = new Answer(q2, "answer3", time);
		Answer a4 = new Answer(q2, "answer4", time);
		
		ArrayList<Group> actualList = new ArrayList<Group>();
		actualList.add(g3);
		actualList.add(g1);
		actualList.add(g2);

		// g1 has 2 questions and 2 answers
		// g1 activeness = 4
		g1.addQuestion(q1);
		g1.addAnswer(a1);
		g1.addAnswer(a2);
		g1.addQuestion(q4);
		
		// g2 has 1 question and 2 answers
		// g2 activeness = 3
		g2.addQuestion(q2);
		g2.addAnswer(a3);
		g2.addAnswer(a4);
		
		// g3 has 1 question and 0 answers
		// g3 activeness = 1
		g3.addQuestion(q3);

		ArrayList<Group> expectedList = new ArrayList<Group>();
		expectedList.add(g3);
		expectedList.add(g2);
		expectedList.add(g1);
		
		ArrayList<Group> wrongList = new ArrayList<Group>();
		wrongList.add(g1);
		wrongList.add(g2);
		wrongList.add(g3);

		Collections.sort(actualList, new GroupOverallActiveComparator());

		// Test to make sure the list was sorted correctly and it didn't sort in the
		// wrong order
		assertEquals(actualList, expectedList);
		assertNotEquals(wrongList, expectedList);
	}
}

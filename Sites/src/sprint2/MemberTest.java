package sprint2;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

class MemberTest {
	// Base Variables
	LocalDateTime expectedDateCreated;
	String expectedFirstName = "John";
	String expectedLastName = "Doe";
	String expectedScreenName = "johndoe";
	String expectedEmail = "johndoe@gmail.com";

	// Flag Feature Variables
	String correctFlag = "correct";
	String inappropriateFlag = "inappropriate";
	String irrelevantFlag = "irrelevant";

	/**
	 * This method should test that a member is constructed properly with the values
	 * it is initialized with
	 * 
	 */
	@Test
	void testMember() {
		expectedDateCreated = LocalDateTime.now();
		Member m = new Member(expectedFirstName, expectedLastName, expectedScreenName, expectedEmail,
				expectedDateCreated);

		String actualFirstName = m.getFirstName();
		String actualLastName = m.getLastName();
		String actualUserName = m.getScreenName();
		String actualEmail = m.getEmailAddress();
		LocalDateTime actualDateTime = m.getDateCreated();

		assertEquals(expectedFirstName, actualFirstName);
		assertEquals(expectedLastName, actualLastName);
		assertEquals(expectedScreenName, actualUserName);
		assertEquals(expectedEmail, actualEmail);
		assertEquals(expectedDateCreated, actualDateTime);
	}

	/**
	 * This method should test that a member returns the proper email address for
	 * the Member.
	 * 
	 */
	@Test
	void testGetEmailAddress() {
		Member m = new Member(expectedFirstName, expectedLastName, expectedScreenName, expectedEmail,
				expectedDateCreated);
		String actualEmail = m.getEmailAddress();
		assertEquals(expectedEmail, actualEmail);
	}

	/**
	 * This method should test that a member returns the date a member was created.
	 * 
	 */
	@Test
	void testGetDateCreated() {
		expectedDateCreated = LocalDateTime.now();
		Member m = new Member(expectedFirstName, expectedLastName, expectedScreenName, expectedEmail,
				expectedDateCreated);
		LocalDateTime actualDateTime = m.getDateCreated();

		assertEquals(expectedDateCreated, actualDateTime);
	}

	/**
	 * This method should test that a member returns the first name of a member.
	 * 
	 */
	@Test
	void testGetFirstName() {
		Member m = new Member(expectedFirstName, expectedLastName, expectedScreenName, expectedEmail,
				expectedDateCreated);
		String actualFirstName = m.getFirstName();
		assertEquals(expectedFirstName, actualFirstName);
	}

	/**
	 * This method should test that a member returns the last name of a member.
	 * 
	 */
	@Test
	void testGetLastName() {
		Member m = new Member(expectedFirstName, expectedLastName, expectedScreenName, expectedEmail,
				expectedDateCreated);
		String actualLastName = m.getLastName();
		assertEquals(expectedLastName, actualLastName);
	}

	/**
	 * This method should test that a member returns the screen name of a member.
	 * 
	 */
	@Test
	void testGetScreenName() {
		Member m = new Member(expectedFirstName, expectedLastName, expectedScreenName, expectedEmail,
				expectedDateCreated);
		String actualScreenName = m.getScreenName();
		assertEquals(expectedScreenName, actualScreenName);
	}

	/**
	 * This method should test that a member returns the groups it has joined and
	 * that they are the same.
	 * 
	 */
	@Test
	void testGetGroups() {
		expectedDateCreated = LocalDateTime.now();
		Group g1 = new Group("B_title", "details", expectedDateCreated);
		Group g2 = new Group("C_title", "details", expectedDateCreated);
		Group g3 = new Group("A_title", "details", expectedDateCreated);
		Member m = new Member(expectedFirstName, expectedLastName, expectedScreenName, expectedEmail,
				expectedDateCreated);

		ArrayList<Group> expectedGroups = new ArrayList<Group>();
		expectedGroups.add(g3);
		expectedGroups.add(g1);
		expectedGroups.add(g2);

		m.joinGroup(g1, expectedDateCreated);
		m.joinGroup(g2, expectedDateCreated);
		m.joinGroup(g3, expectedDateCreated);

		ArrayList<Group> actualGroups = (ArrayList<Group>) m.getGroups();

		assertEquals(expectedGroups, actualGroups);
	}

	/**
	 * This method should test that a member returns the number of groups it has
	 * joined.
	 * 
	 */
	@Test
	void testGetNumGroups() {
		expectedDateCreated = LocalDateTime.now();
		Group g1 = new Group("title1", "details", expectedDateCreated);
		Group g2 = new Group("title2", "details", expectedDateCreated);
		Group g3 = new Group("title3", "details", expectedDateCreated);
		Member m = new Member(expectedFirstName, expectedLastName, expectedScreenName, expectedEmail,
				expectedDateCreated);

		ArrayList<Group> expectedGroups = new ArrayList<Group>();
		expectedGroups.add(g1);
		expectedGroups.add(g2);
		expectedGroups.add(g3);

		m.joinGroup(g1, expectedDateCreated);
		m.joinGroup(g2, expectedDateCreated);
		m.joinGroup(g3, expectedDateCreated);

		assertEquals(3, m.getNumGroups());
	}

	/**
	 * This method should test that a member returns the date is joined a group.
	 * 
	 */
	@Test
	void testGetDateJoined() {
		expectedDateCreated = LocalDateTime.now();
		LocalDateTime ldt = LocalDateTime.parse("2018-07-01T08:00");
		Group g1 = new Group("title1", "details", ldt);
		Member m = new Member(expectedFirstName, expectedLastName, expectedScreenName, expectedEmail, ldt);
		m.joinGroup(g1, expectedDateCreated);
		assertEquals(expectedDateCreated, m.getDateJoined(g1));
	}
	
	/**
	 * This method should test that a member returns the null if it
	 * hasn't joined the group passed to getDateJoined().
	 * 
	 */
	@Test
	void testGetDateJoinedMemberNotInGroup() {
		expectedDateCreated = LocalDateTime.now();
		LocalDateTime ldt = LocalDateTime.parse("2018-07-01T08:00");
		Group g1 = new Group("title1", "details", ldt);
		Member m = new Member(expectedFirstName, expectedLastName, expectedScreenName, expectedEmail, ldt);
		
		assertEquals(null, m.getDateJoined(g1));
	}

	/**
	 * This method should test that a member returns a group by title that it has
	 * joined.
	 * 
	 */
	@Test
	void testGetGroup() {
		expectedDateCreated = LocalDateTime.now();
		String title = "title1";
		Group g1 = new Group(title, "details", expectedDateCreated);
		Member m = new Member(expectedFirstName, expectedLastName, expectedScreenName, expectedEmail,
				expectedDateCreated);
		m.joinGroup(g1, expectedDateCreated);
		assertEquals(g1, m.getGroup(title));
	}
	
	/**
	 * This method should test that a member returns the null if it
	 * hasn't joined a group with the title passed to getGroup().
	 * 
	 */
	@Test
	void testGetGroupMemberNotInGroup() {
		expectedDateCreated = LocalDateTime.now();
		String title = "title1";
		Member m = new Member(expectedFirstName, expectedLastName, expectedScreenName, expectedEmail,
				expectedDateCreated);
		
		assertEquals(null, m.getGroup(title));
	}

	/**
	 * This method should test that a member joins a group properly.
	 * 
	 */
	@Test
	void testJoinGroup() {
		expectedDateCreated = LocalDateTime.now();
		String title = "title1";
		Group g1 = new Group(title, "details", expectedDateCreated);
		Member m = new Member(expectedFirstName, expectedLastName, expectedScreenName, expectedEmail,
				expectedDateCreated);
		m.joinGroup(g1, expectedDateCreated);
		assertEquals(g1, m.getGroup(title));
	}

	/**
	 * This method should test that a member returns questions by a group.
	 * 
	 */
	@Test
	void testGetQuestions() {
		expectedDateCreated = LocalDateTime.now();
		// 2018/10/1 0:00:00
		LocalDateTime dateCreatedOne = LocalDateTime.of(2018, 10, 1, 0, 0);
		// 2018/10/2 0:00:00
		LocalDateTime dateCreatedTwo = LocalDateTime.of(2018, 10, 2, 0, 0);
		// 2018/10/3 0:00:00
		LocalDateTime dateCreatedThree = LocalDateTime.of(2018, 10, 3, 0, 0);
		Group g1 = new Group("title1", "details", expectedDateCreated);
		Member m = new Member(expectedFirstName, expectedLastName, expectedScreenName, expectedEmail,
				expectedDateCreated);

		Question q1 = new Question("title1", "asking q1", dateCreatedTwo);
		Question q2 = new Question("title2", "asking q2", dateCreatedThree);
		Question q3 = new Question("title3", "asking q3", dateCreatedOne);

		m.joinGroup(g1, expectedDateCreated);

		ArrayList<Question> expectedQuestions = new ArrayList<Question>();

		expectedQuestions.add(q2);
		expectedQuestions.add(q1);
		expectedQuestions.add(q3);

		m.addQuestion(g1, q1, expectedDateCreated);
		m.addQuestion(g1, q2, expectedDateCreated);
		m.addQuestion(g1, q3, expectedDateCreated);

		ArrayList<Question> actualQuestions = (ArrayList<Question>) m.getQuestions(g1);

		assertEquals(expectedQuestions, actualQuestions);
	}

	/**
	 * This method should test that a member returns a list of answers by member in
	 * a group.
	 * 
	 */
	@Test
	void testGetAnswers() {
		expectedDateCreated = LocalDateTime.now();
		// 2018/10/1 0:00:00
		LocalDateTime dateCreatedOne = LocalDateTime.of(2018, 10, 1, 0, 0);
		// 2018/10/2 0:00:00
		LocalDateTime dateCreatedTwo = LocalDateTime.of(2018, 10, 2, 0, 0);
		// 2018/10/3 0:00:00
		LocalDateTime dateCreatedThree = LocalDateTime.of(2018, 10, 3, 0, 0);
		Group g1 = new Group("title1", "details", expectedDateCreated);
		Member m = new Member(expectedFirstName, expectedLastName, expectedScreenName, expectedEmail,
				expectedDateCreated);
		m.joinGroup(g1, expectedDateCreated);

		Question q1 = new Question("title1", "asking q1", expectedDateCreated);

		Answer a1 = new Answer(q1, "answer1", dateCreatedTwo);
		Answer a2 = new Answer(q1, "answer2", dateCreatedThree);
		Answer a3 = new Answer(q1, "answer3", dateCreatedOne);

		m.addQuestion(g1, q1, expectedDateCreated);
		m.addAnswer(g1, q1, a1, expectedDateCreated);
		m.addAnswer(g1, q1, a2, expectedDateCreated);
		m.addAnswer(g1, q1, a3, expectedDateCreated);

		ArrayList<Answer> expectedAnswers = new ArrayList<Answer>();

		expectedAnswers.add(a2);
		expectedAnswers.add(a1);
		expectedAnswers.add(a3);

		ArrayList<Answer> actualAnswers = (ArrayList<Answer>) m.getAnswers(g1);

		assertEquals(expectedAnswers, actualAnswers);
	}

	/**
	 * This method should test that a member adds a question to a group.
	 * 
	 */
	@Test
	void testAddQuestion() {
		expectedDateCreated = LocalDateTime.now();
		// 2018/10/1 0:00:00
		LocalDateTime dateCreatedOne = LocalDateTime.of(2018, 10, 1, 0, 0);
		// 2018/10/2 0:00:00
		LocalDateTime dateCreatedTwo = LocalDateTime.of(2018, 10, 2, 0, 0);
		// 2018/10/3 0:00:00
		LocalDateTime dateCreatedThree = LocalDateTime.of(2018, 10, 3, 0, 0);
		Group g1 = new Group("title1", "details", expectedDateCreated);
		Member m = new Member(expectedFirstName, expectedLastName, expectedScreenName, expectedEmail,
				expectedDateCreated);

		Question q1 = new Question("title1", "asking q1", dateCreatedTwo);
		Question q2 = new Question("title2", "asking q2", dateCreatedThree);
		Question q3 = new Question("title3", "asking q3", dateCreatedOne);

		m.joinGroup(g1, expectedDateCreated);

		ArrayList<Question> mExpectedQuestions = new ArrayList<Question>();
		ArrayList<Question> gExpectedQuestions = new ArrayList<Question>();

		mExpectedQuestions.add(q2);
		mExpectedQuestions.add(q1);
		mExpectedQuestions.add(q3);
		
		gExpectedQuestions.add(q2);
		gExpectedQuestions.add(q1);
		gExpectedQuestions.add(q3);

		m.addQuestion(g1, q1, expectedDateCreated);
		m.addQuestion(g1, q2, expectedDateCreated);
		m.addQuestion(g1, q3, expectedDateCreated);

		ArrayList<Question> mActualQuestions = (ArrayList<Question>) m.getQuestions(g1);
		ArrayList<Question> gActualQuestions = (ArrayList<Question>) g1.getQuestions();

		assertAll( () -> assertEquals(mExpectedQuestions, mActualQuestions),
				   () -> assertEquals(gExpectedQuestions, gActualQuestions));
	}

	/**
	 * This method should test that a member adds an answer to a question from a
	 * particular group.
	 * 
	 */
	@Test
	void testAddAnswer() {
		expectedDateCreated = LocalDateTime.now();
		// 2018/10/1 0:00:00
		LocalDateTime dateCreatedOne = LocalDateTime.of(2018, 10, 1, 0, 0);
		// 2018/10/2 0:00:00
		LocalDateTime dateCreatedTwo = LocalDateTime.of(2018, 10, 2, 0, 0);
		// 2018/10/3 0:00:00
		LocalDateTime dateCreatedThree = LocalDateTime.of(2018, 10, 3, 0, 0);
		Group g1 = new Group("title1", "details", expectedDateCreated);
		Member m = new Member(expectedFirstName, expectedLastName, expectedScreenName, expectedEmail,
				expectedDateCreated);
		m.joinGroup(g1, expectedDateCreated);

		Question q1 = new Question("title1", "asking q1", expectedDateCreated);

		Answer a1 = new Answer(q1, "answer1", dateCreatedThree);
		Answer a2 = new Answer(q1, "answer2", dateCreatedTwo);
		Answer a3 = new Answer(q1, "answer3", dateCreatedOne);

		m.addQuestion(g1, q1, expectedDateCreated);
		m.addAnswer(g1, q1, a1, expectedDateCreated);
		m.addAnswer(g1, q1, a2, expectedDateCreated);
		m.addAnswer(g1, q1, a3, expectedDateCreated);

		ArrayList<Answer> mExpectedAnswers = new ArrayList<Answer>();
		ArrayList<Answer> gExpectedAnswers = new ArrayList<Answer>();
		ArrayList<Answer> qExpectedAnswers = new ArrayList<Answer>();

		mExpectedAnswers.add(a1);
		mExpectedAnswers.add(a2);
		mExpectedAnswers.add(a3);

		gExpectedAnswers.add(a1);
		gExpectedAnswers.add(a2);
		gExpectedAnswers.add(a3);
		
		qExpectedAnswers.add(a1);
		qExpectedAnswers.add(a2);
		qExpectedAnswers.add(a3);
		
		ArrayList<Answer> mActualAnswers = (ArrayList<Answer>) m.getAnswers(g1);
		ArrayList<Answer> gActualAnswers = (ArrayList<Answer>) g1.getAnswers();
		ArrayList<Answer> qActualAnswers = (ArrayList<Answer>) q1.getAnswers();

		assertAll( () -> assertEquals(mExpectedAnswers, mActualAnswers),
				   () -> assertEquals(gExpectedAnswers, gActualAnswers),
				   () ->assertEquals(qExpectedAnswers, qActualAnswers));
	}

	/**
	 * This method should test that a member returns a list of the n most active
	 * groups, sorted on their title.
	 * 
	 */
	@Test
	void testGetGroupsOverloaded() {
		expectedDateCreated = LocalDateTime.now();
		// 2018/10/1 0:00:00
		LocalDateTime dateCreatedOne = LocalDateTime.of(2018, 10, 1, 0, 0);
		// 2018/10/2 0:00:00
		LocalDateTime dateCreatedTwo = LocalDateTime.of(2018, 10, 2, 0, 0);
		// 2018/10/3 0:00:00
		LocalDateTime dateCreatedThree = LocalDateTime.of(2018, 10, 3, 0, 0);
		Member m = new Member(expectedFirstName, expectedLastName, expectedScreenName, expectedEmail,
				expectedDateCreated);
		Group g1 = new Group("title1", "details1", expectedDateCreated);
		Group g2 = new Group("title2", "details2", expectedDateCreated);
		Group g3 = new Group("title3", "details3", expectedDateCreated);
		Question q1 = new Question("title1", "asking q1", dateCreatedOne);
		Question q2 = new Question("title2", "asking q2", dateCreatedTwo);
		Question q3 = new Question("title3", "asking q3", dateCreatedThree);
		Answer a1 = new Answer(q1, "answer1", dateCreatedOne);
		Answer a2 = new Answer(q1, "answer2", dateCreatedTwo);
		Answer a3 = new Answer(q1, "answer3", dateCreatedThree);
		Answer a4 = new Answer(q2, "answer4", dateCreatedOne);
		Answer a5 = new Answer(q2, "answer5", dateCreatedTwo);
		Answer a6 = new Answer(q2, "answer6", dateCreatedThree);
		Answer a7 = new Answer(q3, "answer7", dateCreatedOne);
		Answer a8 = new Answer(q3, "answer8", dateCreatedTwo);
		Answer a9 = new Answer(q3, "answer9", dateCreatedThree);

		m.joinGroup(g1, expectedDateCreated);
		m.joinGroup(g2, expectedDateCreated);
		m.joinGroup(g3, expectedDateCreated);

		// Group 1
		// activeness = 4
		m.addQuestion(g1, q1, dateCreatedOne);
		m.addQuestion(g1, q2, dateCreatedOne);

		m.addAnswer(g1, q1, a1, dateCreatedOne);
		m.addAnswer(g1, q1, a2, dateCreatedOne);

		// Group 2
		// activeness = 6
		m.addQuestion(g2, q3, dateCreatedTwo);
		m.addAnswer(g2, q3, a3, dateCreatedTwo);
		m.addAnswer(g2, q3, a4, dateCreatedTwo);
		m.addAnswer(g2, q3, a5, dateCreatedTwo);
		m.addAnswer(g2, q3, a6, dateCreatedTwo);
		m.addAnswer(g2, q3, a7, dateCreatedTwo);

		// Group 3
		// activeness = 3
		m.addQuestion(g3, q1, dateCreatedThree);
		m.addAnswer(g3, q1, a8, dateCreatedThree);
		m.addAnswer(g3, q1, a9, dateCreatedThree);

		ArrayList<Group> actualGroups = (ArrayList<Group>) m.getGroups(2);

		// Groups 1 and 2 are the most active, since "title1" comes before "title2"
		// the list should be g1 then g2
		ArrayList<Group> expectedGroups = new ArrayList<Group>();
		expectedGroups.add(g1);
		expectedGroups.add(g2);

		assertEquals(actualGroups, expectedGroups);
	}

	/**
	 * This method should test that a member returns a list of all
	 * groups, sorted on their title.
	 * 
	 * The parameter given to getGroups(int n) is greater than the total
	 * number of groups this member has joined. This should not crash.
	 */
	@Test
	void testGetGroupsOverloadedNGreaterThanTotalGroups() {
		expectedDateCreated = LocalDateTime.now();
		// 2018/10/1 0:00:00
		LocalDateTime dateCreatedOne = LocalDateTime.of(2018, 10, 1, 0, 0);
		// 2018/10/2 0:00:00
		LocalDateTime dateCreatedTwo = LocalDateTime.of(2018, 10, 2, 0, 0);
		// 2018/10/3 0:00:00
		LocalDateTime dateCreatedThree = LocalDateTime.of(2018, 10, 3, 0, 0);
		Member m = new Member(expectedFirstName, expectedLastName, expectedScreenName, expectedEmail,
				expectedDateCreated);
		Group g1 = new Group("title1", "details1", expectedDateCreated);
		Group g2 = new Group("title2", "details2", expectedDateCreated);
		Group g3 = new Group("title3", "details3", expectedDateCreated);
		Question q1 = new Question("title1", "asking q1", dateCreatedOne);
		Question q2 = new Question("title2", "asking q2", dateCreatedTwo);
		Question q3 = new Question("title3", "asking q3", dateCreatedThree);
		Answer a1 = new Answer(q1, "answer1", dateCreatedOne);
		Answer a2 = new Answer(q1, "answer2", dateCreatedTwo);
		Answer a3 = new Answer(q1, "answer3", dateCreatedThree);
		Answer a4 = new Answer(q2, "answer4", dateCreatedOne);
		Answer a5 = new Answer(q2, "answer5", dateCreatedTwo);
		Answer a6 = new Answer(q2, "answer6", dateCreatedThree);
		Answer a7 = new Answer(q3, "answer7", dateCreatedOne);
		Answer a8 = new Answer(q3, "answer8", dateCreatedTwo);
		Answer a9 = new Answer(q3, "answer9", dateCreatedThree);

		m.joinGroup(g1, expectedDateCreated);
		m.joinGroup(g2, expectedDateCreated);
		m.joinGroup(g3, expectedDateCreated);

		// Group 1
		// activeness = 4
		m.addQuestion(g1, q1, dateCreatedOne);
		m.addQuestion(g1, q2, dateCreatedOne);

		m.addAnswer(g1, q1, a1, dateCreatedOne);
		m.addAnswer(g1, q1, a2, dateCreatedOne);

		// Group 2
		// activeness = 6
		m.addQuestion(g2, q3, dateCreatedTwo);
		m.addAnswer(g2, q3, a3, dateCreatedTwo);
		m.addAnswer(g2, q3, a4, dateCreatedTwo);
		m.addAnswer(g2, q3, a5, dateCreatedTwo);
		m.addAnswer(g2, q3, a6, dateCreatedTwo);
		m.addAnswer(g2, q3, a7, dateCreatedTwo);

		// Group 3
		// activeness = 3
		m.addQuestion(g3, q1, dateCreatedThree);
		m.addAnswer(g3, q1, a8, dateCreatedThree);
		m.addAnswer(g3, q1, a9, dateCreatedThree);

		ArrayList<Group> actualGroups = (ArrayList<Group>) m.getGroups(4);

		ArrayList<Group> expectedGroups = new ArrayList<Group>();
		expectedGroups.add(g1);
		expectedGroups.add(g2);
		expectedGroups.add(g3);

		assertEquals(actualGroups, expectedGroups);
	}
	
	/**
	 * This method should test that a member returns a list of n 
	 * most recent questions, sorted on date created.
	 * 
	 */
	@Test
	void testGetQuestionsOverloaded() {
		expectedDateCreated = LocalDateTime.now();
		// 2018/10/1 0:00:00
		LocalDateTime dateCreatedOne = LocalDateTime.of(2018, 10, 1, 0, 0);
		// 2018/10/2 0:00:00
		LocalDateTime dateCreatedTwo = LocalDateTime.of(2018, 10, 2, 0, 0);
		// 2018/10/3 0:00:00
		LocalDateTime dateCreatedThree = LocalDateTime.of(2018, 10, 3, 0, 0);
		Group g1 = new Group("title1", "details", expectedDateCreated);
		Member m = new Member(expectedFirstName, expectedLastName, expectedScreenName, expectedEmail,
				expectedDateCreated);

		Question q1 = new Question("title1", "asking q1", dateCreatedOne);
		Question q2 = new Question("title2", "asking q2", dateCreatedTwo);
		Question q3 = new Question("title3", "asking q3", dateCreatedThree);

		m.joinGroup(g1, expectedDateCreated);

		ArrayList<Question> expectedQuestions = new ArrayList<Question>();

		// getQuestions(Group,Int) returns the most recent first
		expectedQuestions.add(q3);
		expectedQuestions.add(q2);

		m.addQuestion(g1, q1, expectedDateCreated);
		m.addQuestion(g1, q2, expectedDateCreated);
		m.addQuestion(g1, q3, expectedDateCreated);

		ArrayList<Question> actualQuestions = (ArrayList<Question>) m.getQuestions(g1, 2);

		assertEquals(expectedQuestions, actualQuestions);
	}

	/**
	 * This method should test that a member returns a list of all
	 * questions, sorted on date created, most recent first.
	 * 
	 * The parameter, n, given to getQuestions(Group g, int n) is greater than the 
	 * total number of questions in the group. This should not crash.
	 */
	@Test
	void testGetQuestionsOverloadedNGreaterThanTotalQuestions() {
		expectedDateCreated = LocalDateTime.now();
		// 2018/10/1 0:00:00
		LocalDateTime dateCreatedOne = LocalDateTime.of(2018, 10, 1, 0, 0);
		// 2018/10/2 0:00:00
		LocalDateTime dateCreatedTwo = LocalDateTime.of(2018, 10, 2, 0, 0);
		// 2018/10/3 0:00:00
		LocalDateTime dateCreatedThree = LocalDateTime.of(2018, 10, 3, 0, 0);
		Group g1 = new Group("title1", "details", expectedDateCreated);
		Member m = new Member(expectedFirstName, expectedLastName, expectedScreenName, expectedEmail,
				expectedDateCreated);

		Question q1 = new Question("title1", "asking q1", dateCreatedOne);
		Question q2 = new Question("title2", "asking q2", dateCreatedTwo);
		Question q3 = new Question("title3", "asking q3", dateCreatedThree);

		m.joinGroup(g1, expectedDateCreated);

		ArrayList<Question> expectedQuestions = new ArrayList<Question>();

		// getQuestions(Group,Int) returns the most recent first
		expectedQuestions.add(q3);
		expectedQuestions.add(q2);
		expectedQuestions.add(q1);

		m.addQuestion(g1, q1, expectedDateCreated);
		m.addQuestion(g1, q2, expectedDateCreated);
		m.addQuestion(g1, q3, expectedDateCreated);

		ArrayList<Question> actualQuestions = (ArrayList<Question>) m.getQuestions(g1, 4);

		assertEquals(expectedQuestions, actualQuestions);
	}
	
	/**
	 * This method should test that a member returns a list of n most recent
	 * answers sorted by date created, most recent first.
	 * 
	 */
	@Test
	void testGetAnswersOverloaded() {
		expectedDateCreated = LocalDateTime.now();
		// 2018/10/1 0:00:00
		LocalDateTime dateCreatedOne = LocalDateTime.of(2018, 10, 1, 0, 0);
		// 2018/10/2 0:00:00
		LocalDateTime dateCreatedTwo = LocalDateTime.of(2018, 10, 2, 0, 0);
		// 2018/10/3 0:00:00
		LocalDateTime dateCreatedThree = LocalDateTime.of(2018, 10, 3, 0, 0);
		Group g1 = new Group("title1", "details", expectedDateCreated);
		Member m = new Member(expectedFirstName, expectedLastName, expectedScreenName, expectedEmail,
				expectedDateCreated);

		Question q1 = new Question("title1", "asking q1", dateCreatedOne);

		Answer a1 = new Answer(q1, "answer1", dateCreatedOne);
		Answer a2 = new Answer(q1, "answer1", dateCreatedTwo);
		Answer a3 = new Answer(q1, "answer1", dateCreatedThree);

		m.joinGroup(g1, expectedDateCreated);

		ArrayList<Answer> expectedAnswers = new ArrayList<Answer>();

		// getAnswers(Group,Int) returns the most recent first
		expectedAnswers.add(a3);
		expectedAnswers.add(a2);

		m.addQuestion(g1, q1, expectedDateCreated);
		m.addAnswer(g1, q1, a1, dateCreatedOne);
		m.addAnswer(g1, q1, a2, dateCreatedOne);
		m.addAnswer(g1, q1, a3, dateCreatedOne);

		ArrayList<Answer> actualAnswers = (ArrayList<Answer>) m.getAnswers(g1, 2);

		assertEquals(expectedAnswers, actualAnswers);
	}
	
	/**
	 * This method should test that a member returns a list of all
	 * answers, sorted on date created, most recent first.
	 * 
	 * The parameter, n, given to getAnswers(Group g, int n) is greater than the 
	 * total number of answers in the group. This should not crash.
	 */
	@Test
	void testGetAnswersOverloadedNGreaterThanTotalAnswers() {
		expectedDateCreated = LocalDateTime.now();
		// 2018/10/1 0:00:00
		LocalDateTime dateCreatedOne = LocalDateTime.of(2018, 10, 1, 0, 0);
		// 2018/10/2 0:00:00
		LocalDateTime dateCreatedTwo = LocalDateTime.of(2018, 10, 2, 0, 0);
		// 2018/10/3 0:00:00
		LocalDateTime dateCreatedThree = LocalDateTime.of(2018, 10, 3, 0, 0);
		Group g1 = new Group("title1", "details", expectedDateCreated);
		Member m = new Member(expectedFirstName, expectedLastName, expectedScreenName, expectedEmail,
				expectedDateCreated);

		Question q1 = new Question("title1", "asking q1", dateCreatedOne);

		Answer a1 = new Answer(q1, "answer1", dateCreatedOne);
		Answer a2 = new Answer(q1, "answer1", dateCreatedTwo);
		Answer a3 = new Answer(q1, "answer1", dateCreatedThree);

		m.joinGroup(g1, expectedDateCreated);

		ArrayList<Answer> expectedAnswers = new ArrayList<Answer>();

		// getAnswers(Group,Int) returns the most recent first
		expectedAnswers.add(a3);
		expectedAnswers.add(a2);
		expectedAnswers.add(a1);

		m.addQuestion(g1, q1, expectedDateCreated);
		m.addAnswer(g1, q1, a1, dateCreatedOne);
		m.addAnswer(g1, q1, a2, dateCreatedOne);
		m.addAnswer(g1, q1, a3, dateCreatedOne);

		ArrayList<Answer> actualAnswers = (ArrayList<Answer>) m.getAnswers(g1, 4);

		assertEquals(expectedAnswers, actualAnswers);
	}

	/**
	 * Test getting a list of memberships
	 */
	@Test
	void testGetMemberships() {
		expectedDateCreated = LocalDateTime.now();
		Member m = new Member(expectedFirstName, expectedLastName, expectedScreenName, expectedEmail,
				expectedDateCreated);
		Group g1 = new Group("title1", "details1", expectedDateCreated);
		Group g2 = new Group("title2", "details2", expectedDateCreated);
		Group g3 = new Group("title3", "details3", expectedDateCreated);
		m.joinGroup(g1, expectedDateCreated);
		m.joinGroup(g2, expectedDateCreated);
		m.joinGroup(g3, expectedDateCreated);

		ArrayList<Membership> actualMemberships = (ArrayList<Membership>) m.getMemberships();
		ArrayList<Membership> expectedMemberships = new ArrayList<Membership>();
		expectedMemberships.add(m.getMembership(g1));
		expectedMemberships.add(m.getMembership(g2));
		expectedMemberships.add(m.getMembership(g3));

		assertEquals(actualMemberships, expectedMemberships);
	}

	/**
	 * Test getting a membership for this group
	 */
	@Test
	void testGetMembership() {
		expectedDateCreated = LocalDateTime.now();
		Member m = new Member(expectedFirstName, expectedLastName, expectedScreenName, expectedEmail,
				expectedDateCreated);
		Group g1 = new Group("title1", "details1", expectedDateCreated);
		m.joinGroup(g1, expectedDateCreated);
		Membership expectedMembership = new Membership(g1, m, expectedDateCreated);
		// use toString b/c we don't care if they're not the same references
		assertEquals(m.getMembership(g1).toString(), expectedMembership.toString());
	}

	/**************************************************************************************************
	 * VOTING
	 ***************************************************************************************************/

	/**
	 * Test a member upvoting a question and answer
	 */
	@Test
	void testUpvote() {
		expectedDateCreated = LocalDateTime.now();
		// 2018/10/1 0:00:00
		LocalDateTime dateCreatedOne = LocalDateTime.of(2018, 10, 1, 0, 0);
		Member m = new Member(expectedFirstName, expectedLastName, expectedScreenName, expectedEmail,
				expectedDateCreated);

		Question q1 = new Question("title1", "asking q1", dateCreatedOne);
		Answer a1 = new Answer(q1, "answer1", dateCreatedOne);

		assertAll("zero points", () -> assertEquals(q1.getPoints(), 0), () -> assertEquals(a1.getPoints(), 0));

		m.upVote(q1);
		m.upVote(a1);

		assertAll("one point", () -> assertEquals(q1.getPoints(), 1), () -> assertEquals(a1.getPoints(), 1));
	}

	/**
	 * Test a member downvoting a question and answer
	 */
	@Test
	void testDownvote() {
		expectedDateCreated = LocalDateTime.now();
		// 2018/10/1 0:00:00
		LocalDateTime dateCreatedOne = LocalDateTime.of(2018, 10, 1, 0, 0);
		Member m = new Member(expectedFirstName, expectedLastName, expectedScreenName, expectedEmail,
				expectedDateCreated);

		Question q1 = new Question("title1", "asking q1", dateCreatedOne);
		Answer a1 = new Answer(q1, "answer1", dateCreatedOne);

		assertAll("zero points", () -> assertEquals(q1.getPoints(), 0), () -> assertEquals(a1.getPoints(), 0));

		m.downVote(q1);
		m.downVote(a1);

		assertAll("one point", () -> assertEquals(q1.getPoints(), -1), () -> assertEquals(a1.getPoints(), -1));
	}

	/**
	 * Test getting a List of Answers sorted desc by points from a specific question
	 * Also tests the method getAnswersByPoints(int n, Question q)
	 */
	@Test
	void testGetAnswersByPoints() {
		expectedDateCreated = LocalDateTime.now();
		// 2018/10/1 0:00:00
		LocalDateTime dateCreatedOne = LocalDateTime.of(2018, 10, 1, 0, 0);
		// 2018/10/2 0:00:00
		LocalDateTime dateCreatedTwo = LocalDateTime.of(2018, 10, 2, 0, 0);
		// 2018/10/3 0:00:00
		LocalDateTime dateCreatedThree = LocalDateTime.of(2018, 10, 3, 0, 0);
		Member m = new Member(expectedFirstName, expectedLastName, expectedScreenName, expectedEmail,
				expectedDateCreated);
		Member m2 = new Member("fName", "lName", "screenName", "email@email.com",expectedDateCreated);
		Group g1 = new Group("title1", "details", expectedDateCreated);

		Question q1 = new Question("title1", "asking q1", dateCreatedOne);

		Answer a1 = new Answer(q1, "answer1", dateCreatedOne);
		Answer a2 = new Answer(q1, "answer1", dateCreatedTwo);
		Answer a3 = new Answer(q1, "answer1", dateCreatedThree);
		Answer a4 = new Answer(q1, "answer4", LocalDateTime.of(2018, 10, 4, 0, 0));
		// Join Group and add answers
		m.joinGroup(g1, expectedDateCreated);
		m.addQuestion(g1, q1, expectedDateCreated);
		m.addAnswer(g1, q1, a3, expectedDateCreated);
		m.addAnswer(g1, q1, a1, expectedDateCreated);
		m.addAnswer(g1, q1, a2, expectedDateCreated);
		
		// M2 join group and add answers
		// This answer should be ignored when m.getAnswersByPoints(q1) is called
		m2.joinGroup(g1, expectedDateCreated);
		m2.addAnswer(g1, q1, a4, expectedDateCreated);

		// 5 upvotes
		m.upVote(a1);
		m.upVote(a1);
		m.upVote(a1);
		m.upVote(a1);
		m.upVote(a1);

		// 2 upvotes - 1 downvote
		m.upVote(a2);
		m.upVote(a2);
		m.downVote(a2);

		// 3 downvotes
		m.downVote(a3);
		m.downVote(a3);
		m.downVote(a3);

		List<Answer> expectedAnswers = new ArrayList<Answer>();
		expectedAnswers.add(a1);
		expectedAnswers.add(a2);
		expectedAnswers.add(a3);
		List<Answer> expectedAnswersWithMostPoints = new ArrayList<Answer>();
		expectedAnswersWithMostPoints.add(a1);
		expectedAnswersWithMostPoints.add(a2);

		List<Answer> actualAnswers =  m.getAnswersByPoints(q1);
		List<Answer> actualAnswersWithMostPoints = m.getAnswersByPoints(2, q1);

		assertAll("a1->a2->a3", () -> assertEquals(actualAnswers, expectedAnswers),
				() -> assertEquals(actualAnswersWithMostPoints, expectedAnswersWithMostPoints));
	}

	/**
	 * Test getting a List of Questions sorted desc by points
	 */
	@Test
	void testGetQuestionsByPoints() {
		expectedDateCreated = LocalDateTime.now();
		// 2018/10/1 0:00:00
		LocalDateTime dateCreatedOne = LocalDateTime.of(2018, 10, 1, 0, 0);
		// 2018/10/2 0:00:00
		LocalDateTime dateCreatedTwo = LocalDateTime.of(2018, 10, 2, 0, 0);
		// 2018/10/3 0:00:00
		LocalDateTime dateCreatedThree = LocalDateTime.of(2018, 10, 3, 0, 0);
		Member m = new Member(expectedFirstName, expectedLastName, expectedScreenName, expectedEmail,
				expectedDateCreated);
		Group g1 = new Group("title1", "details", expectedDateCreated);

		Question q1 = new Question("title1", "asking q1", dateCreatedOne);
		Question q2 = new Question("title2", "asking q3", dateCreatedTwo);
		Question q3 = new Question("title3", "asking q2", dateCreatedThree);

		// Join Group and add questions
		m.joinGroup(g1, expectedDateCreated);
		m.addQuestion(g1, q3, expectedDateCreated);
		m.addQuestion(g1, q1, expectedDateCreated);
		m.addQuestion(g1, q2, expectedDateCreated);

		// 5 upvotes
		m.upVote(q1);
		m.upVote(q1);
		m.upVote(q1);
		m.upVote(q1);
		m.upVote(q1);

		// 2 upvotes + 1 downvote
		m.upVote(q2);
		m.upVote(q2);
		m.downVote(q2);

		// 3 downvotes
		m.downVote(q3);
		m.downVote(q3);
		m.downVote(q3);

		List<Question> expectedQuestions = new ArrayList<Question>();
		expectedQuestions.add(q1);
		expectedQuestions.add(q2);
		expectedQuestions.add(q3);
		List<Question> expectedQuestionsWithTwoQuestions = new ArrayList<Question>();
		expectedQuestionsWithTwoQuestions.add(q1);
		expectedQuestionsWithTwoQuestions.add(q2);

		List<Question> actualQuestions = m.getQuestionsByPoints();
		List<Question> actualQuestionsWithTwoQuestions = m.getQuestionsByPoints(2);

		assertAll("q1->q2->q3", () -> assertEquals(expectedQuestions, actualQuestions),
				() -> assertEquals(expectedQuestionsWithTwoQuestions, actualQuestionsWithTwoQuestions));
	}
	
	
	/**
	 * This method should test that a Group returns an array list of the first
	 * n answers of the input array list.
	 * 
	 */
    @Test
    void testGetListNumOfAnswers() {
        LocalDateTime ldt = LocalDateTime.now();
        
        Member m = new Member("Fname", "Lname", "ScreenName","email@email.com", ldt);

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

        ArrayList<Answer> actualAnswers = (ArrayList<Answer>)m.getListNumOfAnswers(expectedAnswers, 2);

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
        
        Member m = new Member("Fname", "Lname", "ScreenName","email@email.com", ldt);

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

        ArrayList<Question> actualQuestions = (ArrayList<Question>)m.getListNumOfQuestions(expectedQuestions, 2);

        assertEquals(expectedQuestions, actualQuestions);
    }

	/**************************************************************************************************
	 * FLAGS
	 ***************************************************************************************************/

	/**
	 * Tests marking an Answer as correct
	 */
	@Test
	void testMarkAsCorrect() {
		expectedDateCreated = LocalDateTime.now();
		Member m1 = new Member(expectedFirstName, expectedLastName, expectedScreenName, expectedEmail,
				expectedDateCreated);
		Member m2 = new Member(expectedFirstName + "2", expectedLastName + "2", expectedScreenName + "2",
				expectedEmail + "2", expectedDateCreated);

		Group g1 = new Group("title1", "details", expectedDateCreated);

		Question q1 = new Question("title1", "asking q1", expectedDateCreated);
		Question q2 = new Question("title2", "asking q2", expectedDateCreated);
		Answer a1 = new Answer(q2, "answer1", expectedDateCreated);
		Answer a2 = new Answer(q1, "answer2", expectedDateCreated);
		Answer a3 = new Answer(q1, "answer3", expectedDateCreated);

		m1.joinGroup(g1, expectedDateCreated);
		m2.joinGroup(g1, expectedDateCreated);
		m1.addQuestion(g1, q1, expectedDateCreated);
		m2.addQuestion(g1, q2, expectedDateCreated);

		// add answers m1 adds answer to q2 and m2 adds answer to q1
		m1.addAnswer(g1, q2, a1, expectedDateCreated);
		m2.addAnswer(g1, q1, a2, expectedDateCreated);
		
		// m2 adds another answer to q1
		m2.addAnswer(g1, q1, a3, expectedDateCreated);

		/**
		 * m1 is the owner of q1 so he can mark a2 as correct m2 is the owner of q2 so
		 * he can mark a1 as correct
		 */
		m1.markAsCorrect(a1, true);
		m2.markAsCorrect(a2, true);
		assertAll("non owner of question", () -> assertFalse(a2.isCorrect()), () -> assertFalse(a1.isCorrect()));

		m1.markAsCorrect(a2, true);
		m2.markAsCorrect(a1, true);
		// a3 question is q1 which already has a correct answer, a2, so this should not 
		// set a3 "correct" flag to true
		m1.markAsCorrect(a3, true);
		assertAll("owner of question", () -> assertTrue(a1.isCorrect()), () -> assertTrue(a2.isCorrect()), 
				() -> assertFalse(a3.isCorrect()));
	}

	/**
	 * Tests marking an Answer as inappropriate
	 */
	@Test
	void testMarkAsInappropriate() {
		expectedDateCreated = LocalDateTime.now();
		Member m = new Member(expectedFirstName, expectedLastName, expectedScreenName, expectedEmail,
				expectedDateCreated);

		Group g1 = new Group("title1", "details", expectedDateCreated);

		Question q1 = new Question("title1", "asking q1", expectedDateCreated);
		Answer a1 = new Answer(q1, "answer1", expectedDateCreated);

		m.joinGroup(g1, expectedDateCreated);
		m.addQuestion(g1, q1, expectedDateCreated);
		m.addAnswer(g1, q1, a1, expectedDateCreated);

		m.markAsInappropriate(a1, true);
		m.markAsInappropriate(q1, true);
		assertAll("innappropriate set to true", () -> assertTrue(q1.isInappropriate()),
				() -> assertTrue(a1.isInappropriate()));
		m.markAsInappropriate(a1, false);
		m.markAsInappropriate(q1, false);
		assertAll("innappropriate set to false from true", () -> assertFalse(q1.isInappropriate()),
				() -> assertFalse(a1.isInappropriate()));
	}

	/**
	 * Tests marking an Answer as correct
	 */
	@Test
	void testMarkAsIrrelevant() {
		expectedDateCreated = LocalDateTime.now();
		Member m = new Member(expectedFirstName, expectedLastName, expectedScreenName, expectedEmail,
				expectedDateCreated);

		Group g1 = new Group("title1", "details", expectedDateCreated);

		Question q1 = new Question("title1", "asking q1", expectedDateCreated);
		Answer a1 = new Answer(q1, "answer1", expectedDateCreated);

		m.joinGroup(g1, expectedDateCreated);
		m.addQuestion(g1, q1, expectedDateCreated);
		m.addAnswer(g1, q1, a1, expectedDateCreated);

		m.markAsIrrelevant(a1, true);
		m.markAsIrrelevant(q1, true);
		assertAll("irrelevant set to true", () -> assertTrue(q1.isIrrelevant()), () -> assertTrue(a1.isIrrelevant()));
		m.markAsIrrelevant(a1, false);
		m.markAsIrrelevant(q1, false);
		assertAll("irrelevant set to false from true", () -> assertFalse(q1.isIrrelevant()),
				() -> assertFalse(a1.isIrrelevant()));
	}

	/**
	 * This method should test that a member returns the correct toString.
	 * 
	 */

	@Test
	void testToString() {
		expectedDateCreated = LocalDateTime.now();
		Member m = new Member(expectedFirstName, expectedLastName, expectedScreenName, expectedEmail,
				expectedDateCreated);

		String expected = ("Screen Name: " + expectedScreenName + ", First Name: " + expectedFirstName + ", Last Name: "
				+ expectedLastName + ", Email: " + expectedEmail + ", Date Created: " + expectedDateCreated);

		assertEquals(expected, m.toString());
	}

	/**
	 * This method should test that a member name comparator works properly.
	 * 
	 */
	@Test
	void testMemberNameComparator() {
		expectedDateCreated = LocalDateTime.now();
		// FirstName comparison case
		Member ashleyApple = new Member("Ashley", "Apple", expectedScreenName, expectedEmail, expectedDateCreated);
		Member buckyApple = new Member("Bucky", "Apple", expectedScreenName, expectedEmail, expectedDateCreated);
		// LastName comparison case
		Member buckyBarns = new Member("Bucky", "Barns", expectedScreenName, expectedEmail, expectedDateCreated);
		Member buckyCondridge = new Member("Bucky", "Condridge", expectedScreenName, expectedEmail,
				expectedDateCreated);
		Member cleoCat = new Member("Cleo", "Cat", expectedScreenName, expectedEmail, expectedDateCreated);
		Member ashleyDuragno = new Member("Ashley", "Duragno", expectedScreenName, expectedEmail, expectedDateCreated);

		ArrayList<Member> actualList = new ArrayList<Member>();
		actualList.add(cleoCat);
		actualList.add(ashleyDuragno);
		actualList.add(ashleyApple);
		actualList.add(buckyBarns);
		actualList.add(buckyApple);
		actualList.add(buckyCondridge);

		ArrayList<Member> expectedList = new ArrayList<Member>();
		expectedList.add(ashleyApple);
		expectedList.add(buckyApple);
		expectedList.add(buckyBarns);
		expectedList.add(cleoCat);
		expectedList.add(buckyCondridge);
		expectedList.add(ashleyDuragno);

		Collections.sort(actualList, new MemberNameComparator());

		assertEquals(expectedList, actualList);
	}

	/**
	 * This method should test that a member Overall Activeness Comparator works
	 * properly.
	 * 
	 */
	@Test
	void testMemberOverallActiveComparator() {
		expectedDateCreated = LocalDateTime.now();
		// 2018/10/1 0:00:00
		LocalDateTime dateCreatedOne = LocalDateTime.of(2018, 10, 1, 0, 0);
		// 2018/10/2 0:00:00
		LocalDateTime dateCreatedTwo = LocalDateTime.of(2018, 10, 2, 0, 0);
		// 2018/10/3 0:00:00
		LocalDateTime dateCreatedThree = LocalDateTime.of(2018, 10, 3, 0, 0);
		Member m1 = new Member(expectedFirstName + "1", expectedLastName + "1", expectedScreenName + "1",
				expectedEmail + "1", expectedDateCreated);
		Member m2 = new Member(expectedFirstName + "2", expectedLastName + "2", expectedScreenName + "2",
				expectedEmail + "2", expectedDateCreated);
		Member m3 = new Member(expectedFirstName + "3", expectedLastName + "3", expectedScreenName + "3",
				expectedEmail + "3", expectedDateCreated);
		Group g1 = new Group("title1", "details1", expectedDateCreated);
		Group g2 = new Group("title2", "details2", expectedDateCreated);
		Group g3 = new Group("title3", "details3", expectedDateCreated);
		Question q1 = new Question("title1", "asking q1", dateCreatedOne);
		Question q2 = new Question("title2", "asking q2", dateCreatedTwo);
		Question q3 = new Question("title3", "asking q3", dateCreatedThree);
		Answer a1 = new Answer(q1, "answer1", dateCreatedOne);
		Answer a2 = new Answer(q1, "answer2", dateCreatedTwo);
		Answer a3 = new Answer(q1, "answer3", dateCreatedThree);
		Answer a4 = new Answer(q2, "answer4", dateCreatedOne);
		Answer a5 = new Answer(q2, "answer5", dateCreatedTwo);
		Answer a6 = new Answer(q2, "answer6", dateCreatedThree);
		Answer a7 = new Answer(q2, "answer7", dateCreatedOne);
		Answer a8 = new Answer(q2, "answer8", dateCreatedTwo);
		Answer a9 = new Answer(q3, "answer9", dateCreatedThree);

		m1.joinGroup(g1, expectedDateCreated);
		m2.joinGroup(g2, expectedDateCreated);
		m3.joinGroup(g3, expectedDateCreated);

		// Member 1
		// activeness = 4
		m1.addQuestion(g1, q1, dateCreatedOne);
		m1.addAnswer(g1, q1, a1, dateCreatedOne);
		m1.addAnswer(g1, q1, a2, dateCreatedOne);
		m1.addAnswer(g1, q1, a3, dateCreatedOne);

		// Member 2
		// activeness = 6
		m2.addQuestion(g2, q2, dateCreatedTwo);
		m2.addAnswer(g2, q2, a4, dateCreatedTwo);
		m2.addAnswer(g2, q2, a5, dateCreatedTwo);
		m2.addAnswer(g2, q2, a6, dateCreatedTwo);
		m2.addAnswer(g2, q2, a7, dateCreatedTwo);
		m2.addAnswer(g2, q2, a8, dateCreatedTwo);

		// Member 3
		// activeness = 2
		m3.addQuestion(g3, q3, dateCreatedThree);
		m3.addAnswer(g3, q3, a9, dateCreatedThree);

		ArrayList<Member> actualMembers = new ArrayList<Member>();
		actualMembers.add(m1);
		actualMembers.add(m2);
		actualMembers.add(m3);

		/*
		 * desc order of activeness is m3->m1->m2
		 */
		ArrayList<Member> expectedMembers = new ArrayList<Member>();
		expectedMembers.add(m3);
		expectedMembers.add(m1);
		expectedMembers.add(m2);

		Collections.sort(actualMembers, new MemberOverallActiveComparator());

		assertEquals(actualMembers, expectedMembers);
	}

}

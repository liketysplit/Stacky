package sprint2;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;

class MembershipTest {

	/**
	 * This method should test that a membership is constructed properly with the values
	 * it is initialized with
	 * 
	 */
	@Test
	void testMembership() {
		LocalDateTime ldt = LocalDateTime.now();
		
		Group g = new Group("Group Title", "Group Description", ldt);
		Member m = new Member("FirstName", "LastName", "ScreenName", "FirstNameLastName@email.com", ldt);
		Membership membership = new Membership(g, m, ldt);
		
		assertAll(() -> assertEquals(membership.getGroup(), g), 
				  () -> assertEquals(membership.getMember(), m),
				  () -> assertEquals(membership.getDateJoined(), ldt));
	}

	/**
	 * This method should test that a membership returns the proper group for
	 * the Membership.
	 * 
	 */
	@Test
	void testGetGroup() {
		LocalDateTime ldt = LocalDateTime.now();
		
		Group g = new Group("Group Title", "Group Description", ldt);
		Member m = new Member("FirstName", "LastName", "ScreenName", "FirstNameLastName@email.com", ldt);
		Membership membership = new Membership(g, m, ldt);
		
		Group membershipGroup = membership.getGroup();
		assertEquals(membershipGroup, g);
	}

	/**
	 * This method should test that a membership adds a question to its internal arrayList of questions.
	 * 
	 */
	@Test
	void testAddQuestion() {
		LocalDateTime ldt = LocalDateTime.now();
		
		Group g = new Group("Group Title", "Group Description", ldt);
		Member m = new Member("FirstName", "LastName", "ScreenName", "FirstNameLastName@email.com", ldt);
		Membership membership = new Membership(g, m, ldt);
		
		Question q1 = new Question("title1","asking q1", ldt);
		Question q2 = new Question("title2","asking q2", ldt);
		Question q3 = new Question("title3","asking q3", ldt);

		membership.addQuestion(q1);
		membership.addQuestion(q2);
		membership.addQuestion(q3);
		
		ArrayList<Question> expectedQuestions = new ArrayList<Question>();
		expectedQuestions.add(q1);
		expectedQuestions.add(q2);
		expectedQuestions.add(q3);
		
		ArrayList<Question> actualQuestions = membership.getQuestions();
		
		// Test the questions array in Membership
		assertEquals(actualQuestions, expectedQuestions);
	}

	/**
	 * This method should test that a membership adds an answer to its internal arrayList of answers.
	 * 
	 */
	@Test
	void testAddAnswer() {
		LocalDateTime ldt = LocalDateTime.now();
		
		Group g = new Group("Group Title", "Group Description", ldt);
		Member m = new Member("FirstName", "LastName", "ScreenName", "FirstNameLastName@email.com", ldt);
		Membership membership = new Membership(g, m, ldt);
		
		Question q1 = new Question("title1","asking q1", ldt);
		Answer a1 = new Answer(q1, "answer1", ldt);
		Answer a2 = new Answer(q1, "answer2", ldt);
		Answer a3 = new Answer(q1, "answer3", ldt);
		
		membership.addAnswer(a1);
		membership.addAnswer(a2);
		membership.addAnswer(a3);
		
		ArrayList<Answer> expectedAnswers = new ArrayList<Answer>();
		expectedAnswers.add(a1);
		expectedAnswers.add(a2);
		expectedAnswers.add(a3);
		
		ArrayList<Answer> actualAnswers = membership.getAnswers();
		
		// Test the answers array in Membership
		assertEquals(actualAnswers, expectedAnswers);
	}
	
	/**
	 * This method should test that a membership returns the number of questions its member
	 * has asked in its group.
	 * 
	 */
	@Test
	void testGetNumQuestions() {
		LocalDateTime ldt = LocalDateTime.now();
		
		Group g = new Group("Group Title", "Group Description", ldt);
		Member m = new Member("FirstName", "LastName", "ScreenName", "FirstNameLastName@email.com", ldt);
		Membership membership = new Membership(g, m, ldt);
		
		Question q1 = new Question("title1","asking q1", ldt);
		Question q2 = new Question("title2","asking q2", ldt);
		Question q3 = new Question("title3","asking q3", ldt);

		membership.addQuestion(q1);
		membership.addQuestion(q2);
		membership.addQuestion(q3);
		
		int expectedNumQuestions = 3;
		
		int actualNumQuestions = membership.getNumQuestions();
		
		assertEquals(actualNumQuestions, expectedNumQuestions);
	}
	
	/**
	 * This method should test that a membership returns the number of answers its member
	 * has posted in its group.
	 * 
	 */
	@Test
	void testGetNumAnswers() {
		LocalDateTime ldt = LocalDateTime.now();
		
		Group g = new Group("Group Title", "Group Description", ldt);
		Member m = new Member("FirstName", "LastName", "ScreenName", "FirstNameLastName@email.com", ldt);
		Membership membership = new Membership(g, m, ldt);
		
		Question q1 = new Question("title1","asking q1", ldt);
		Answer a1 = new Answer(q1, "answer1", ldt);
		Answer a2 = new Answer(q1, "answer2", ldt);
		Answer a3 = new Answer(q1, "answer3", ldt);
		
		membership.addAnswer(a1);
		membership.addAnswer(a2);
		membership.addAnswer(a3);
		
		int expectedNumAnswers = 3;
		
		int actualNumAnswers = membership.getNumAnswers();
		
		assertEquals(actualNumAnswers, expectedNumAnswers);
	}

	/**
	 * This method should test that a membership returns the date that its 
	 * member joined its group.
	 * 
	 */
	@Test
	void testGetDateJoined() {
		LocalDateTime ldt = LocalDateTime.parse("2018-07-01T08:00");
		LocalDateTime expectedDate = LocalDateTime.now();
		
		Group g = new Group("Group Title", "Group Description", ldt);
		Member m = new Member("FirstName", "LastName", "ScreenName", "FirstNameLastName@email.com", ldt);
		Membership membership = new Membership(g, m, expectedDate);
		
		assertEquals(membership.getDateJoined(), expectedDate);
	}

	/**
	 * This method should test that a membership returns the proper member for
	 * the Membership.
	 * 
	 */
	@Test
	void testGetMember() {
		LocalDateTime ldt = LocalDateTime.now();
		
		Group g = new Group("Group Title", "Group Description", ldt);
		Member m = new Member("FirstName", "LastName", "ScreenName", "FirstNameLastName@email.com", ldt);
		Membership membership = new Membership(g, m, ldt);
		
		assertEquals(membership.getMember(), m);
	}

	/**
	 * This method should test that a membership returns questions asked by its member 
	 * in its group.
	 * 
	 */
	@Test
	void testGetQuestions() {
		LocalDateTime ldt = LocalDateTime.now();
		
		Group g = new Group("Group Title", "Group Description", ldt);
		Member m = new Member("FirstName", "LastName", "ScreenName", "FirstNameLastName@email.com", ldt);
		Membership membership = new Membership(g, m, ldt);
		
		Question q1 = new Question("title1","asking q1", ldt);
		Question q2 = new Question("title2","asking q2", ldt);
		Question q3 = new Question("title3","asking q3", ldt);
		Question q4 = new Question("title4","asking q4", ldt);

		membership.addQuestion(q1);
		membership.addQuestion(q2);
		membership.addQuestion(q3);
		
		ArrayList<Question> expectedQuestions = new ArrayList<Question>();
		expectedQuestions.add(q1);
		expectedQuestions.add(q2);
		expectedQuestions.add(q3);
		
		ArrayList<Question> actualQuestions = membership.getQuestions();
		
		// As this is question is added after the getQuestions() method call
		// it should not appear in either of the array lists in the assertion
		membership.addQuestion(q4);
		
		// Test the questions array in Membership
		assertEquals(actualQuestions, expectedQuestions);
	}

	/**
	 * This method should test that a membership returns answers posted by its member 
	 * in its group.
	 * 
	 */
	@Test
	void testGetAnswers() {
		LocalDateTime ldt = LocalDateTime.now();
		
		Group g = new Group("Group Title", "Group Description", ldt);
		Member m = new Member("FirstName", "LastName", "ScreenName", "FirstNameLastName@email.com", ldt);
		Membership membership = new Membership(g, m, ldt);
		
		Question q1 = new Question("title1","asking q1", ldt);
		Answer a1 = new Answer(q1, "answer1", ldt);
		Answer a2 = new Answer(q1, "answer2", ldt);
		Answer a3 = new Answer(q1, "answer3", ldt);
		Answer a4 = new Answer(q1, "answer4", ldt);
		
		membership.addAnswer(a1);
		membership.addAnswer(a2);
		membership.addAnswer(a3);
		
		ArrayList<Answer> expectedAnswers = new ArrayList<Answer>();
		expectedAnswers.add(a1);
		expectedAnswers.add(a2);
		expectedAnswers.add(a3);
		
		ArrayList<Answer> actualAnswers = membership.getAnswers();
		
		// As this is answer is added after the getAnswers() method call
		// it should not appear in either of the array lists in the assertion
		membership.addAnswer(a4);
		
		// Test the answers array in Membership
		assertEquals(actualAnswers, expectedAnswers);
	}

	/**
	 * This method should test that a membership returns the correct toString.
	 * 
	 */
	@Test
	void testToString() {
		String screenName = "ScreenName";
		String groupTitle = "GroupTitle";
		int points = 0;
		LocalDateTime ldt = LocalDateTime.parse("2018-07-01T08:00");
		LocalDateTime expectedDate = LocalDateTime.now();
		
		String expectedToString = "Member: " + screenName + ", Group: "+ groupTitle +
        		", Points: " + points + ", Date Joined: " + expectedDate;
		
		Group g = new Group(groupTitle, "Group Description", ldt);
		Member m = new Member("FirstName", "LastName", screenName, "FirstNameLastName@email.com", ldt);
		Membership membership = new Membership(g, m, expectedDate);
		
		assertEquals(expectedToString, membership.toString());
	}
	
	/**
	 * This method should test that the Membership Activeness Comparator works
	 * properly.
	 * 
	 */
	@Test
	void testMembershipActiveComparator() {
		LocalDateTime time = LocalDateTime.parse("2018-07-01T08:00");
		Group g1 = new Group("g1", "This is a test Description", time);
		Group g2 = new Group("g2", "This is a test Description", time);
		Group g3 = new Group("g3", "This is a test Description", time);

		Member m1 = new Member("m1", "m1", "m1", "m1@email.com", time);
		Member m2 = new Member("m2", "m2", "m2", "m2@email.com", time);
		Member m3 = new Member("m3", "m3", "m3", "m3@email.com", time);
		
		Membership mbs1 = new Membership(g1,m1,time);
		Membership mbs2 = new Membership(g2,m2,time);
		Membership mbs3 = new Membership(g3,m3,time);

		Question q1 = new Question("question1", "asking q1", time);
		Question q2 = new Question("question2", "asking q2", time);
		Question q3 = new Question("question3", "asking q3", time);
		Question q4 = new Question("question4", "asking q4", time);
		Answer a1 = new Answer(q1, "answer1", time);
		Answer a2 = new Answer(q1, "answer2", time);
		Answer a3 = new Answer(q2, "answer3", time);
		Answer a4 = new Answer(q2, "answer4", time);
		
		ArrayList<Membership> actualList = new ArrayList<Membership>();
		actualList.add(mbs3);
		actualList.add(mbs1);
		actualList.add(mbs2);

		mbs1.addQuestion(q1);
		mbs1.addAnswer(a1);
		mbs1.addAnswer(a2);
		mbs1.addQuestion(q4);
		
		mbs2.addQuestion(q2);
		mbs2.addAnswer(a3);
		mbs2.addAnswer(a4);
		
		mbs3.addQuestion(q3);

		ArrayList<Membership> expectedList = new ArrayList<Membership>();
		expectedList.add(mbs3);
		expectedList.add(mbs2);
		expectedList.add(mbs1);
		
		ArrayList<Membership> wrongList = new ArrayList<Membership>();
		wrongList.add(mbs1);
		wrongList.add(mbs2);
		wrongList.add(mbs3);

		Collections.sort(actualList, new MembershipActiveComparator());

		// Test to make sure the list was sorted correctly and it didn't sort in the
		// wrong order
		assertEquals(actualList, expectedList);
		assertNotEquals(wrongList, expectedList);
	}

}

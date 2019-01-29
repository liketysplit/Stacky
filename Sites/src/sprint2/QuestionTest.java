package sprint2;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;

class QuestionTest {
	LocalDateTime expectedDateCreated;
	String expectedFirstName = "John";
	String expectedLastName = "Doe";
	String expectedScreenName = "johndoe";
	String expectedEmail = "johndoe@gmail.com";
	String expectedTitle = "title1";
	String expectedText = "asking q1";
	// Flags
	String inappropriateFlag = "inappropriate";
	String irrelevantFlag = "irrelevant";

	/**
	 * Test for creating an Question.
	 * 
	 */
	@Test
	void testQuestion() {
		expectedDateCreated = LocalDateTime.now();
		Question q1 = new Question(expectedTitle, expectedText, expectedDateCreated);
		assertEquals(expectedTitle, q1.getTitle());
		assertEquals(q1.getText(), expectedText);
		assertEquals(expectedDateCreated, q1.getDate());
	}

	/**
	 * Test for returning a title of a Question.
	 * 
	 */
	@Test
	void testGetTitle() {
		expectedDateCreated = LocalDateTime.now();
		Question q1 = new Question(expectedTitle, "asking q1", expectedDateCreated);
		assertEquals(expectedTitle, q1.getTitle());
	}

	/**
	 * Test for setting a title of a Question.
	 * 
	 */
	@Test
	void testSetTitle() {
		expectedDateCreated = LocalDateTime.now();
		String newExpectedTitle = "title1(edited)";
		Question q1 = new Question("title1", "asking q1", expectedDateCreated);
		q1.setTitle(newExpectedTitle);
		assertEquals(newExpectedTitle, q1.getTitle());
	}
	
	/**
	 * This method should test that a question returns true
	 * when hasCorrectAnswer() is called and at least one of the answers 
	 * posted for this question has been marked correct.
	 * 
	 */
	@Test
	void testHasCorrectAnswer() {
		expectedDateCreated = LocalDateTime.now();

		Question q1 = new Question("title1", "asking q1", expectedDateCreated);
		Answer a1 = new Answer(q1, "answer1", expectedDateCreated);
		Answer a2 = new Answer(q1, "answer2", expectedDateCreated);
		
		q1.addAnswer(a1);
		q1.addAnswer(a2);
		
		a2.setFlag("correct", true);
		
		assertEquals(true, q1.hasCorrectAnswer());
	}
	
	/**
	 * This method should test that a question returns false
	 * when hasCorrectAnswer() is called but none of the answers 
	 * posted for this question have been marked correct.
	 * 
	 */
	@Test
	void testHasCorrectAnswerWhenNoCorrectAnswer() {
		expectedDateCreated = LocalDateTime.now();

		Question q1 = new Question("title1", "asking q1", expectedDateCreated);
		Answer a1 = new Answer(q1, "answer1", expectedDateCreated);
		Answer a2 = new Answer(q1, "answer2", expectedDateCreated);
		
		q1.addAnswer(a1);
		q1.addAnswer(a2);
		
		assertEquals(false, q1.hasCorrectAnswer());
	}

	/**
	 * Test for adding an Answer to a Question.
	 * 
	 */
	@Test
	void testAddAnswer() {
		expectedDateCreated = LocalDateTime.now();

		Question q1 = new Question("title1", "asking q1", expectedDateCreated);

		// 2018/10/1 0:00:00
		LocalDateTime dateCreatedOne = LocalDateTime.of(2018,10,1,0,0);
		// 2018/10/2 0:00:00
		LocalDateTime dateCreatedTwo = LocalDateTime.of(2018,10,2,0,0);
		// 2018/10/3 0:00:00
		LocalDateTime dateCreatedThree = LocalDateTime.of(2018,10,3,0,0);

		Answer a1 = new Answer(q1, "answer1", dateCreatedThree);
		Answer a2 = new Answer(q1, "answer2", dateCreatedOne);
		Answer a3 = new Answer(q1, "answer3", dateCreatedTwo);

		q1.addAnswer(a1);
		q1.addAnswer(a2);
		q1.addAnswer(a3);

		ArrayList<Answer> expectedAnswers = new ArrayList<Answer>();

		expectedAnswers.add(a1);
		expectedAnswers.add(a3);
		expectedAnswers.add(a2);

		ArrayList<Answer> actualAnswers = (ArrayList<Answer>) q1.getAnswers();

		assertEquals(expectedAnswers, actualAnswers);
	}

	/**
	 * This method should test that a question returns a list of the n
	 * most recent answers posted for that question, sorted on
	 * date created, most recent first.
	 * 
	 */
	@Test
	void testGetAnswers() {
		expectedDateCreated = LocalDateTime.now();

		Question q1 = new Question("title1", "asking q1", expectedDateCreated);
		
		// 2018/10/1 0:00:00
		LocalDateTime dateCreatedOne = LocalDateTime.of(2018,10,1,0,0);
		// 2018/10/2 0:00:00
		LocalDateTime dateCreatedTwo = LocalDateTime.of(2018,10,2,0,0);
		// 2018/10/3 0:00:00
		LocalDateTime dateCreatedThree = LocalDateTime.of(2018,10,3,0,0);

		Answer a1 = new Answer(q1, "answer1", dateCreatedThree);
		Answer a2 = new Answer(q1, "answer2", dateCreatedOne);
		Answer a3 = new Answer(q1, "answer3", dateCreatedTwo);
		Answer a4 = new Answer(q1, "answer4", LocalDateTime.now());

		q1.addAnswer(a1);
		q1.addAnswer(a2);
		q1.addAnswer(a3);

		ArrayList<Answer> expectedAnswers = new ArrayList<Answer>();

		expectedAnswers.add(a1);
		expectedAnswers.add(a3);
		expectedAnswers.add(a2);

		ArrayList<Answer> actualAnswers = (ArrayList<Answer>) q1.getAnswers();
		
		q1.addAnswer(a4);

		assertEquals(expectedAnswers, actualAnswers);
	}
	
	/**
	 * This method should test that a question returns a list all
	 * answers posted for this question, sorted on
	 * points, highest first.
	 * 
	 */
	@Test
	void testGetAnswersByPoints() {
		LocalDateTime time = LocalDateTime.parse("2018-07-01T08:00");	
		
		Question q1 = new Question("title1", "asking q1", time);
		Answer a1 = new Answer(q1, "answer1", time);
		Answer a2 = new Answer(q1, "answer2", time);
		Answer a3 = new Answer(q1, "answer3", time);
		Answer a4 = new Answer(q1, "answer4", time);
		
		q1.addAnswer(a1);
		q1.addAnswer(a2);
		q1.addAnswer(a3);
		q1.addAnswer(a4);
		
		// Answer Two has 3 points
		a2.upvote();
		a2.upvote();
		a2.upvote();
		
		// Answer Three has 2 points
		a3.upvote();
		a3.upvote();
		
		// Answer One has 1 point
		a1.upvote();

		ArrayList<Answer> expectedAnswers = new ArrayList<Answer>();

		expectedAnswers.add(a2);
		expectedAnswers.add(a3);
		expectedAnswers.add(a1);
		expectedAnswers.add(a4);

		ArrayList<Answer> actualAnswers = (ArrayList<Answer>) q1.getAnswersByPoints();

		assertEquals(expectedAnswers, actualAnswers);
	}
	
	/**
	 * This method should test that a question returns a list of the n
	 * answers posted with the most points, sorted on
	 * points, highest first.
	 * 
	 */
	@Test
	void testGetAnswersByPointsOverloaded() {
		LocalDateTime time = LocalDateTime.parse("2018-07-01T08:00");	
		
		Question q1 = new Question("title1", "asking q1", time);
		Answer a1 = new Answer(q1, "answer1", time);
		Answer a2 = new Answer(q1, "answer2", time);
		Answer a3 = new Answer(q1, "answer3", time);
		Answer a4 = new Answer(q1, "answer4", time);
		
		q1.addAnswer(a1);
		q1.addAnswer(a2);
		q1.addAnswer(a3);
		q1.addAnswer(a4);
		
		// Answer Two has 3 points
		a2.upvote();
		a2.upvote();
		a2.upvote();
		
		// Answer Three has 2 points
		a3.upvote();
		a3.upvote();
		
		// Answer One has 1 point
		a1.upvote();

		ArrayList<Answer> expectedAnswers = new ArrayList<Answer>();

		expectedAnswers.add(a2);
		expectedAnswers.add(a3);

		ArrayList<Answer> actualAnswers = (ArrayList<Answer>) q1.getAnswersByPoints(2);

		assertEquals(expectedAnswers, actualAnswers);
	}
	
	/**
	 * This method should test that a question returns a list of the n
	 * answers posted with the most points, sorted on
	 * points, highest first.
	 * 
	 * The parameter given to getAnswersByPoints(int n) is greater than the total
	 * number of answers for this question. This should not crash. 
	 */
	@Test
	void testGetAnswersByPointsOverloadedNGreatarThanTotalAnswers() {
		LocalDateTime time = LocalDateTime.parse("2018-07-01T08:00");	
		
		Question q1 = new Question("title1", "asking q1", time);
		Answer a1 = new Answer(q1, "answer1", time);
		Answer a2 = new Answer(q1, "answer2", time);
		Answer a3 = new Answer(q1, "answer3", time);
		Answer a4 = new Answer(q1, "answer4", time);
		
		q1.addAnswer(a1);
		q1.addAnswer(a2);
		q1.addAnswer(a3);
		q1.addAnswer(a4);
		
		// Answer Two has 3 points
		a2.upvote();
		a2.upvote();
		a2.upvote();
		
		// Answer Three has 2 points
		a3.upvote();
		a3.upvote();
		
		// Answer One has 1 point
		a1.upvote();

		ArrayList<Answer> expectedAnswers = new ArrayList<Answer>();

		expectedAnswers.add(a2);
		expectedAnswers.add(a3);
		expectedAnswers.add(a1);
		expectedAnswers.add(a4);

		ArrayList<Answer> actualAnswers = (ArrayList<Answer>) q1.getAnswersByPoints(6);

		assertEquals(expectedAnswers, actualAnswers);
	}
	
	/**
	 * This method should test that a question returns a list all
	 * answers posted for this question, sorted on
	 * points, with the correct answer first.
	 * 
	 */
	@Test
	void testGetAnswersByPointsAndCorrect() {
		LocalDateTime time = LocalDateTime.parse("2018-07-01T08:00");	
		
		Question q1 = new Question("title1", "asking q1", time);
		Answer a1 = new Answer(q1, "answer1", time);
		Answer a2 = new Answer(q1, "answer2", time);
		Answer a3 = new Answer(q1, "answer3", time);
		Answer a4 = new Answer(q1, "answer4", time);
		
		q1.addAnswer(a1);
		q1.addAnswer(a2);
		q1.addAnswer(a3);
		q1.addAnswer(a4);
		
		// Answer Two has 3 points
		a2.upvote();
		a2.upvote();
		a2.upvote();
		
		// Answer Three has 2 points
		a3.upvote();
		a3.upvote();
		
		// Answer One has 1 point and is marked as correct
		a1.upvote();	
		a1.setFlag("correct", true);

		ArrayList<Answer> expectedAnswers = new ArrayList<Answer>();

		expectedAnswers.add(a1);
		expectedAnswers.add(a2);
		expectedAnswers.add(a3);
		expectedAnswers.add(a4);

		ArrayList<Answer> actualAnswers = (ArrayList<Answer>) q1.getAnswersByPointsAndCorrect();

		assertEquals(expectedAnswers, actualAnswers);
	}
	
	/**
	 * This method should test that a question returns a list all
	 * answers posted for this question, sorted on
	 * points, with the correct answer first.
	 * 
	 */
	@Test
	void testGetAnswersByPointsAndCorrectOverloaded() {
		LocalDateTime time = LocalDateTime.parse("2018-07-01T08:00");	
		
		Question q1 = new Question("title1", "asking q1", time);
		Answer a1 = new Answer(q1, "answer1", time);
		Answer a2 = new Answer(q1, "answer2", time);
		Answer a3 = new Answer(q1, "answer3", time);
		Answer a4 = new Answer(q1, "answer4", time);
		
		q1.addAnswer(a1);
		q1.addAnswer(a2);
		q1.addAnswer(a3);
		q1.addAnswer(a4);
		
		// Answer Two has 3 points
		a2.upvote();
		a2.upvote();
		a2.upvote();
		
		// Answer Three has 2 points
		a3.upvote();
		a3.upvote();
		
		// Answer One has 1 point and is marked as correct
		a1.upvote();	
		a1.setFlag("correct", true);

		ArrayList<Answer> expectedAnswers = new ArrayList<Answer>();

		expectedAnswers.add(a1);
		expectedAnswers.add(a2);

		ArrayList<Answer> actualAnswers = (ArrayList<Answer>) q1.getAnswersByPointsAndCorrect(2);

		assertEquals(expectedAnswers, actualAnswers);
	}
	
	/**
	 * This method should test that a question returns a list all
	 * answers posted for this question, sorted on
	 * points, with the correct answer first.
	 * 
	 * The parameter given to getAnswersByPointsAndCorrect(int n) is greater than the total
	 * number of answers for this question. This should not crash. 
	 */
	@Test
	void testGetAnswersByPointsAndCorrectOverloadedNGreaterThanTotalAnswers() {
		LocalDateTime time = LocalDateTime.parse("2018-07-01T08:00");	
		
		Question q1 = new Question("title1", "asking q1", time);
		Answer a1 = new Answer(q1, "answer1", time);
		Answer a2 = new Answer(q1, "answer2", time);
		Answer a3 = new Answer(q1, "answer3", time);
		Answer a4 = new Answer(q1, "answer4", time);
		
		q1.addAnswer(a1);
		q1.addAnswer(a2);
		q1.addAnswer(a3);
		q1.addAnswer(a4);
		
		// Answer Two has 3 points
		a2.upvote();
		a2.upvote();
		a2.upvote();
		
		// Answer Three has 2 points
		a3.upvote();
		a3.upvote();
		
		// Answer One has 1 point and is marked as correct
		a1.upvote();	
		a1.setFlag("correct", true);

		ArrayList<Answer> expectedAnswers = new ArrayList<Answer>();

		expectedAnswers.add(a1);
		expectedAnswers.add(a2);
		expectedAnswers.add(a3);
		expectedAnswers.add(a4);

		ArrayList<Answer> actualAnswers = (ArrayList<Answer>) q1.getAnswersByPointsAndCorrect(6);

		assertEquals(expectedAnswers, actualAnswers);
	}

	/**
	 * Test for getting data of a Question in string format.
	 * 
	 */
	@Test
	void testToString() {
		expectedDateCreated = LocalDateTime.now();
		Question q1 = new Question("title1", "asking q1", expectedDateCreated);
		String expected = ("Title: " + q1.getTitle() + "\nText: " + q1.getText() + "\nDate Created: " + q1.getDate()
				+ "\nPoints: 0\n" + "irrelevant: false\n" + "correct: false\n" + "inappropriate: false");
		assertEquals(expected, q1.toString());
	}

	/**
	 * Test is not writable due to the nature of Posts.
	 * 
	 */
	@Test
	void testPost() {
		// I did not see how to test this. testQuestion I believe is accomplishing this
		// for us.
	}

	/**
	 * Test for returning text of a Question.
	 * 
	 */
	@Test
	void testGetText() {
		expectedDateCreated = LocalDateTime.now();
		Question q1 = new Question("title1", expectedText, expectedDateCreated);
		assertEquals(q1.getText(), expectedText);
	}

	/**
	 * Test for returning date that a Question was created.
	 * 
	 */
	@Test
	void testGetDate() {
		expectedDateCreated = LocalDateTime.now();
		Question q1 = new Question("title1", "asking q1", expectedDateCreated);
		assertEquals(expectedDateCreated, q1.getDate());
	}

	/**
	 * Test for setting text of a Question.
	 * 
	 */
	@Test
	void testSetText() {
		expectedDateCreated = LocalDateTime.now();
		String newExpectedText = "asking q1(edited)";
		Question q1 = new Question("title1", "asking q1", expectedDateCreated);
		q1.setText(newExpectedText);
		assertEquals(q1.getText(), newExpectedText);
	}

	/**
	 * Test for returning author of a Question.
	 * 
	 */
	@Test
	void testGetAuthor() {
		expectedDateCreated = LocalDateTime.now();
		Member m1 = new Member(expectedFirstName, expectedLastName, expectedScreenName, expectedEmail,
				expectedDateCreated);
		Question q1 = new Question("title1", "asking q1", expectedDateCreated);
		Group g1 = new Group("title1", "details", expectedDateCreated);
		m1.joinGroup(g1, expectedDateCreated);
		m1.addQuestion(g1, q1, expectedDateCreated);
		assertEquals(m1, q1.getAuthor());
	}
	
	/**
	 * Test for returning author of a Question.
	 * Should return null when question has not been added by a member.
	 * 
	 */
	@Test
	void testGetAuthorNotAddedYet() {
		expectedDateCreated = LocalDateTime.now();
		Member m1 = new Member(expectedFirstName, expectedLastName, expectedScreenName, expectedEmail,
				expectedDateCreated);
		Question q1 = new Question("title1", "asking q1", expectedDateCreated);
		Group g1 = new Group("title1", "details", expectedDateCreated);
		m1.joinGroup(g1, expectedDateCreated);
		assertEquals(null, q1.getAuthor());
	}

	/**
	 * Test for returning group of a Question.
	 * 
	 */
	@Test
	void testGetGroup() {
		expectedDateCreated = LocalDateTime.now();
		Member m1 = new Member(expectedFirstName, expectedLastName, expectedScreenName, expectedEmail,
				expectedDateCreated);
		Question q1 = new Question("title1", "asking q1", expectedDateCreated);
		Group g1 = new Group("title1", "details", expectedDateCreated);
		m1.joinGroup(g1, expectedDateCreated);
		m1.addQuestion(g1, q1, expectedDateCreated);
		assertEquals(g1, q1.getGroup());
	}
	
	/**
	 * Test for returning group of a Question.
	 * Should return null when question has not been added by a member.
	 * 
	 */
	@Test
	void testGetGroupNotAddedYet() {
		expectedDateCreated = LocalDateTime.now();
		Member m1 = new Member(expectedFirstName, expectedLastName, expectedScreenName, expectedEmail,
				expectedDateCreated);
		Question q1 = new Question("title1", "asking q1", expectedDateCreated);
		Group g1 = new Group("title1", "details", expectedDateCreated);
		m1.joinGroup(g1, expectedDateCreated);
		
		assertEquals(null, q1.getGroup());
	}

	/**
	 * Test for returning membership of a Question.
	 * 
	 */
	@Test
	void testGetMembership() {
		expectedDateCreated = LocalDateTime.now();
		Member m1 = new Member(expectedFirstName, expectedLastName, expectedScreenName, expectedEmail,
				expectedDateCreated);
		Question q1 = new Question("title1", "asking q1", expectedDateCreated);
		Group g1 = new Group("title1", "details", expectedDateCreated);
		m1.joinGroup(g1, expectedDateCreated);
		m1.addQuestion(g1, q1, expectedDateCreated);
		assertEquals(m1.getMembership(g1), q1.getMembership());
	}

	/**
	 * Test for setting the membership of a Question.
	 * 
	 */
	@Test
	void testSetMembership() {
		expectedDateCreated = LocalDateTime.now();
		Member m1 = new Member(expectedFirstName, expectedLastName, expectedScreenName, expectedEmail,
				expectedDateCreated);
		Question q1 = new Question("title1", "asking q1", expectedDateCreated);
		Group g1 = new Group("title1", "details", expectedDateCreated);
		m1.joinGroup(g1, expectedDateCreated);
		Membership mShip1 = m1.getMembership(g1);
		q1.setMembership(mShip1);
		assertEquals(mShip1, q1.getMembership());
	}

	/**
	 * Test for getting points of a Question.
	 * 
	 */
	@Test
	void testGetPoints() {
		Question q1 = new Question("title1", "asking q1", expectedDateCreated);

		// Try correct initialization
		assertEquals(q1.getPoints(), 0);

		// Try negative
		q1.downvote();
		assertEquals(q1.getPoints(), -1);

		// Try positive
		q1.upvote();
		q1.upvote();
		assertEquals(q1.getPoints(), 1);
	}

	/**
	 * Tests downvoting a Question.
	 * 
	 */
	@Test
	void testDownvote() {
		Question q1 = new Question("title1", "asking q1", expectedDateCreated);

		q1.downvote();
		assertEquals(q1.getPoints(), -1);
	}

	/**
	 * Tests upvoting a Question.
	 * 
	 */
	@Test
	void testUpvote() {
		Question q1 = new Question("title1", "asking q1", expectedDateCreated);

		q1.upvote();
		assertEquals(q1.getPoints(), 1);
	}

	/**
	 * Test that the private setFlag method works correctly.
	 * 
	 */
	void testSetFlag() {
		Question q1 = new Question("title1", "asking q1", expectedDateCreated);

		// test false
		q1.setFlag(inappropriateFlag, false);
		q1.setFlag(irrelevantFlag, false);
		assertFalse(q1.isInappropriate());
		assertFalse(q1.isIrrelevant());

		// test true
		q1.setFlag(inappropriateFlag, true);
		q1.setFlag(irrelevantFlag, true);
		assertTrue(q1.isInappropriate());
		assertTrue(q1.isIrrelevant());
	}

	/**
	 * Test for checking if this question is marked as correct.
	 * 
	 */
	@Test
	void testIsInappropriate() {
		Question q1 = new Question("title1", "asking q1", expectedDateCreated);

		assertFalse(q1.isInappropriate());
		q1.setFlag(inappropriateFlag, true);
		assertTrue(q1.isInappropriate());
	}

	/**
	 * Test for checking if this question is marked as correct.
	 * 
	 */
	@Test
	void testIsIrrelevant() {
		Question q1 = new Question("title1", "asking q1", expectedDateCreated);

		assertFalse(q1.isIrrelevant());
		q1.setFlag(irrelevantFlag, true);
		assertTrue(q1.isIrrelevant());
	}

	@Test
	void testQuestionDateComparator() {
		// 2018/10/1 0:00:00
		LocalDateTime dateCreatedOne = LocalDateTime.of(2018, 10, 1, 0, 0);
		// 2018/10/2 0:00:00
		LocalDateTime dateCreatedTwo = LocalDateTime.of(2018, 10, 2, 0, 0);
		// 2018/10/3 0:00:00
		LocalDateTime dateCreatedThree = LocalDateTime.of(2018, 10, 3, 0, 0);

		Question q1 = new Question("title1", "asking q1", dateCreatedOne);
		Question q2 = new Question("title2", "asking q2", dateCreatedTwo);
		Question q3 = new Question("title3", "asking q3", dateCreatedThree);

		ArrayList<Question> actualList = new ArrayList<Question>();
		actualList.add(q3);
		actualList.add(q2);
		actualList.add(q1);

		ArrayList<Question> expectedList = new ArrayList<Question>();
		expectedList.add(q1);
		expectedList.add(q2);
		expectedList.add(q3);

		Collections.sort(actualList, new PostDateComparator());

		assertEquals(actualList, expectedList);
	}

	/**
	 * Test for using the PostPointsComparator().
	 * 
	 */
	@Test
	void testQuestionPointsComparator() {
		// 2018/10/1 0:00:00
		LocalDateTime dateCreatedOne = LocalDateTime.of(2018, 10, 1, 0, 0);
		// 2018/10/2 0:00:00
		LocalDateTime dateCreatedTwo = LocalDateTime.of(2018, 10, 2, 0, 0);
		// 2018/10/3 0:00:00
		LocalDateTime dateCreatedThree = LocalDateTime.of(2018, 10, 3, 0, 0);

		Question q1 = new Question("title1", "asking q1", dateCreatedOne);
		Question q2 = new Question("title2", "asking q2", dateCreatedTwo);
		Question q3 = new Question("title3", "asking q3", dateCreatedThree);

		ArrayList<Question> actualList = new ArrayList<Question>();
		actualList.add(q3);
		actualList.add(q2);
		actualList.add(q1);

		// 5 upvotes
		q3.upvote();
		q3.upvote();
		q3.upvote();
		q3.upvote();
		q3.upvote();
		q3.upvote();

		// 2 upvotes + 1 downvote
		q2.upvote();
		q2.upvote();
		q2.downvote();

		// 3 downvotes
		q1.downvote();
		q1.downvote();
		q1.downvote();

		ArrayList<Question> expectedList = new ArrayList<Question>();
		expectedList.add(q1);
		expectedList.add(q2);
		expectedList.add(q3);

		Collections.sort(actualList, new PostPointsComparator());

		assertEquals(actualList, expectedList);
	}
}

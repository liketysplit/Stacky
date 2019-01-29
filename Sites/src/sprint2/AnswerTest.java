package sprint2;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;


import org.junit.jupiter.api.Test;

class AnswerTest {
	LocalDateTime expectedDateCreated;
	String expectedFirstName = "John";
	String expectedLastName = "Doe";
	String expectedUserName = "johndoe";
	String expectedEmail = "johndoe@gmail.com";
	// Flags
	String correctFlag = "correct";
	String inappropriateFlag = "inappropriate";
	String irrelevantFlag = "irrelevant";

	/**
	 * Test for creating an Answer.
	 * 
	 */
	@Test
	void testAnswer() {
		expectedDateCreated = LocalDateTime.now();
		Question q1 = new Question("title1", "asking q1", LocalDateTime.parse("2018-07-01T08:00"));
		Answer a1 = new Answer(q1, "answer1", expectedDateCreated);

		assertEquals("answer1", a1.getText());
		assertEquals(q1, a1.getQuestion());
		assertEquals(expectedDateCreated, a1.getDate());
	}

	/**
	 * Test for returning a question of an Answer.
	 * 
	 */
	@Test
	void testGetQuestion() {
		expectedDateCreated = LocalDateTime.now();
		Question q1 = new Question("title1", "asking q1", expectedDateCreated);
		Answer a1 = new Answer(q1, "answer1", expectedDateCreated);
		assertEquals(q1, a1.getQuestion());
	}

	/**
	 * Test for creating an Answer and comparing its output values to a string of
	 * expected results.
	 * 
	 */

	@Test
	void testToString() {
		Question question = new Question("title1", "asking q1", LocalDateTime.parse("2018-07-01T08:00"));
		String expectedText = "answer1";
		Answer a1 = new Answer(question, expectedText, expectedDateCreated);
		String expected = ("Question Text: " + question.getText() + "\nText: " + expectedText + "\nDate Created: "
				+ expectedDateCreated + "\nPoints: 0\n" + "irrelevant: false\n" + "correct: false\n"
				+ "inappropriate: false");
		assertEquals(expected, a1.toString());
	}

	/**
	 * Test is not writable due to the nature of Posts.
	 * 
	 */
	@Test
	void testPost() {
		// I did not see how to test this. testAnswer I believe is accomplishing this
		// for us.
	}

	/**
	 * Test for returning text of an Answer.
	 * 
	 */
	@Test
	void testGetText() {
		expectedDateCreated = LocalDateTime.now();
		String expectedText = "answer1";
		Question q1 = new Question("title1", "asking q1", expectedDateCreated);
		Answer a1 = new Answer(q1, expectedText, expectedDateCreated);
		assertEquals(expectedText, a1.getText());

	}

	/**
	 * Test for returning date that an Answer was submitted.
	 * 
	 */
	@Test
	void testGetDate() {
		expectedDateCreated = LocalDateTime.now();
		Question q1 = new Question("title1", "asking q1", LocalDateTime.parse("2018-07-01T08:00"));
		Answer a1 = new Answer(q1, "answer1", expectedDateCreated);
		assertEquals(expectedDateCreated, a1.getDate());
	}

	/**
	 * Test for setting text of an Answer.
	 * 
	 */
	@Test
	void testSetText() {
		expectedDateCreated = LocalDateTime.now();
		String expectedText = "answer1(edited)";
		Question q1 = new Question("title1", "asking q1", expectedDateCreated);
		Answer a1 = new Answer(q1, "answer1", expectedDateCreated);
		a1.setText(expectedText);
		assertEquals(expectedText, a1.getText());
	}

	/**
	 * Test for returning the Author of an Answer.
	 * 
	 */
	@Test
	void testGetAuthor() {
		expectedDateCreated = LocalDateTime.now();
		Member m1 = new Member(expectedFirstName, expectedLastName, expectedUserName, expectedEmail,
				expectedDateCreated);
		Question q1 = new Question("title1", "asking q1", expectedDateCreated);
		Answer a1 = new Answer(q1, "answer1", expectedDateCreated);
		Group g1 = new Group("title1", "details", expectedDateCreated);
		m1.joinGroup(g1, expectedDateCreated);
		m1.addQuestion(g1, q1, expectedDateCreated);
		m1.addAnswer(g1, q1, a1, LocalDateTime.now());
		assertEquals(m1, a1.getAuthor());
	}

	/**
	 * Test for returning the group of an Answer.
	 * 
	 */
	@Test
	void testGetGroup() {
		expectedDateCreated = LocalDateTime.now();
		Member m1 = new Member(expectedFirstName, expectedLastName, expectedUserName, expectedEmail,
				expectedDateCreated);
		Question q1 = new Question("title1", "asking q1", expectedDateCreated);
		Answer a1 = new Answer(q1, "answer1", expectedDateCreated);
		Group g1 = new Group("title1", "details", expectedDateCreated);
		m1.joinGroup(g1, expectedDateCreated);
		m1.addQuestion(g1, q1, expectedDateCreated);
		m1.addAnswer(g1, q1, a1, LocalDateTime.now());
		assertEquals(a1.getGroup(), g1);
	}

	/**
	 * Test for returning the membership of an Answer.
	 * 
	 */
	@Test
	void testGetMembership() {
		expectedDateCreated = LocalDateTime.now();
		Member m1 = new Member(expectedFirstName, expectedLastName, expectedUserName, expectedEmail,
				expectedDateCreated);
		Question q1 = new Question("title1", "asking q1", expectedDateCreated);
		Answer a1 = new Answer(q1, "answer1", expectedDateCreated);
		Group g1 = new Group("title1", "details", expectedDateCreated);
		m1.joinGroup(g1, expectedDateCreated);
		m1.addQuestion(g1, q1, expectedDateCreated);
		m1.addAnswer(g1, q1, a1, LocalDateTime.now());
		assertEquals(m1.getMembership(g1), a1.getMembership());
	}

	/**
	 * Test for setting the membership of an Answer.
	 * 
	 */
	@Test
	void testSetMembership() {
		expectedDateCreated = LocalDateTime.now();
		Member m1 = new Member(expectedFirstName, expectedLastName, expectedUserName, expectedEmail,
				expectedDateCreated);
		Question q1 = new Question("title1", "asking q1", expectedDateCreated);
		Answer a1 = new Answer(q1, "answer1", expectedDateCreated);
		Group g1 = new Group("title1", "details", expectedDateCreated);
		m1.joinGroup(g1, expectedDateCreated);
		Membership mShip1 = m1.getMembership(g1);
		a1.setMembership(mShip1);
		assertEquals(mShip1, a1.getMembership());

	}

	/**
	 * Test for getting points of an answer.
	 * 
	 */
	@Test
	void testGetPoints() {
		Question q1 = new Question("title1", "asking q1", expectedDateCreated);
		Answer a1 = new Answer(q1, "answer1", expectedDateCreated);

		// Try correct initialization
		assertEquals(a1.getPoints(), 0);

		// Try negative
		a1.downvote();
		assertEquals(a1.getPoints(), -1);

		// Try positive
		a1.upvote();
		a1.upvote();
		assertEquals(a1.getPoints(), 1);
	}

	/**
	 * Tests downvoting an Answer.
	 * 
	 */
	@Test
	void testDownvote() {
		Question q1 = new Question("title1", "asking q1", expectedDateCreated);
		Answer a1 = new Answer(q1, "answer1", expectedDateCreated);

		a1.downvote();
		assertEquals(a1.getPoints(), -1);
	}

	/**
	 * Tests upvoting an Answer.
	 * 
	 */
	@Test
	void testUpvote() {
		Question q1 = new Question("title1", "asking q1", expectedDateCreated);
		Answer a1 = new Answer(q1, "answer1", expectedDateCreated);

		a1.upvote();
		assertEquals(a1.getPoints(), 1);
	}

	/**
	 * Test that the private setFlag method works correctly.
	 * 
	 */
	void testSetFlag() {
		Question q1 = new Question("title1", "asking q1", expectedDateCreated);
		Answer a1 = new Answer(q1, "answer1", expectedDateCreated);

		// test false
		a1.setFlag(correctFlag, false);
		a1.setFlag(inappropriateFlag, false);
		a1.setFlag(irrelevantFlag, false);
		assertFalse(a1.isCorrect());
		assertFalse(a1.isInappropriate());
		assertFalse(a1.isIrrelevant());

		// test true
		a1.setFlag(correctFlag, true);
		a1.setFlag(inappropriateFlag, true);
		a1.setFlag(irrelevantFlag, true);
		assertTrue(a1.isCorrect());
		assertTrue(a1.isInappropriate());
		assertTrue(a1.isIrrelevant());
	}

	/**
	 * Test for checking if this answer is marked as correct.
	 * 
	 */
	@Test
	void testIsCorrect() {
		Question q1 = new Question("title1", "asking q1", expectedDateCreated);
		Answer a1 = new Answer(q1, "answer1", expectedDateCreated);

		assertFalse(a1.isCorrect());
		a1.setFlag(correctFlag, true);
		assertTrue(a1.isCorrect());
	}

	/**
	 * Test for checking if this answer is marked as correct
	 */
	@Test
	void testIsInappropriate() {
		Question q1 = new Question("title1", "asking q1", expectedDateCreated);
		Answer a1 = new Answer(q1, "answer1", expectedDateCreated);

		assertFalse(a1.isInappropriate());
		a1.setFlag(inappropriateFlag, true);
		assertTrue(a1.isInappropriate());
	}

	/**
	 * Test for checking if this answer is marked as correct.
	 * 
	 */
	@Test
	void testIsIrrelevant() {
		Question q1 = new Question("title1", "asking q1", expectedDateCreated);
		Answer a1 = new Answer(q1, "answer1", expectedDateCreated);

		assertFalse(a1.isIrrelevant());
		a1.setFlag(irrelevantFlag, true);
		assertTrue(a1.isIrrelevant());
	}

	/**
	 * Test for using the PostDateComparator().
	 * 
	 */
	@Test
	void testAnswerDateComparator() {
		// 2018/10/1 0:00:00
		LocalDateTime dateCreatedOne = LocalDateTime.of(2018, 10, 1, 0, 0);
		// 2018/10/2 0:00:00
		LocalDateTime dateCreatedTwo = LocalDateTime.of(2018, 10, 2, 0, 0);
		// 2018/10/3 0:00:00
		LocalDateTime dateCreatedThree = LocalDateTime.of(2018, 10, 3, 0, 0);

		Question q1 = new Question("title1", "asking q1", dateCreatedOne);

		Answer a1 = new Answer(q1, "answer1", dateCreatedOne);
		Answer a2 = new Answer(q1, "answer1", dateCreatedTwo);
		Answer a3 = new Answer(q1, "answer1", dateCreatedThree);

		ArrayList<Answer> actualList = new ArrayList<Answer>();
		actualList.add(a3);
		actualList.add(a2);
		actualList.add(a1);

		ArrayList<Answer> expectedList = new ArrayList<Answer>();
		expectedList.add(a1);
		expectedList.add(a2);
		expectedList.add(a3);

		Collections.sort(actualList, new PostDateComparator());

		assertEquals(actualList, expectedList);
	}

	/**
	 * Test for using the PostPointsComparator().
	 * 
	 */
	@Test
	void testAnswerPointsComparator() {
		// 2018/10/1 0:00:00
		LocalDateTime dateCreatedOne = LocalDateTime.of(2018, 10, 1, 0, 0);
		// 2018/10/2 0:00:00
		LocalDateTime dateCreatedTwo = LocalDateTime.of(2018, 10, 2, 0, 0);
		// 2018/10/3 0:00:00
		LocalDateTime dateCreatedThree = LocalDateTime.of(2018, 10, 3, 0, 0);

		Question q1 = new Question("title1", "asking q1", dateCreatedOne);

		Answer a1 = new Answer(q1, "answer1", dateCreatedOne);
		Answer a2 = new Answer(q1, "answer1", dateCreatedTwo);
		Answer a3 = new Answer(q1, "answer1", dateCreatedThree);

		ArrayList<Answer> actualList = new ArrayList<Answer>();
		actualList.add(a3);
		actualList.add(a2);
		actualList.add(a1);

		// 5 upvotes
		a3.upvote();
		a3.upvote();
		a3.upvote();
		a3.upvote();
		a3.upvote();
		a3.upvote();

		// 2 upvotes + 1 downvote
		a2.upvote();
		a2.upvote();
		a2.downvote();

		// 3 downvotes
		a1.downvote();
		a1.downvote();
		a1.downvote();

		ArrayList<Answer> expectedList = new ArrayList<Answer>();
		expectedList.add(a1);
		expectedList.add(a2);
		expectedList.add(a3);

		Collections.sort(actualList, new PostPointsComparator());

		assertEquals(actualList, expectedList);
	}
}

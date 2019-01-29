package sprint2;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

/**
 * Old Integration testing. We have moved forward with a new model
 *
 * @deprecated use {@link #IntSiteManagerMember()} instead.
 */
@Deprecated
class IntMemberTest {
	/**
	 * Start of Test Data Creation
	 */
	// Test Member Variables
	String memberFirstName = "FirstName";
	String memberLastName = "LastName";
	String memberScreenName = "ScreenName";
	String memberEmailAddress = "FirstNameLastName@email.com";
	LocalDateTime memberDateCreated = LocalDateTime.now();
	// Create Test Member
	Member testMember = new Member(memberFirstName, memberLastName, memberScreenName, memberEmailAddress,
			memberDateCreated);

	/**
	 * End of Test Data Creation
	 */

	// Test Basic Member Getters
	@Test
	void testBasicMemberGetters() {
		String testMemberFirstName = testMember.getFirstName();
		assertEquals(memberFirstName, testMemberFirstName);

		String testMemberLastName = testMember.getLastName();
		assertEquals(memberLastName, testMemberLastName);

		String testMemberScreenName = testMember.getScreenName();
		assertEquals(memberScreenName, testMemberScreenName);

		String testMemberEmailAddress = testMember.getEmailAddress();
		assertEquals(memberEmailAddress, testMemberEmailAddress);

		LocalDateTime testMemberDateCreated = testMember.getDateCreated();
		assertEquals(memberDateCreated, testMemberDateCreated);
	}

	@Test
	void testMaster() {
		/**
		 * Member Joins a Group ____________________ - calls joinGroup - joinGroup
		 * creates new Membership and adds it to group.memberships array and
		 * this.memberships array
		 */
		// Add 1-3 fake members to groupOne
		int noExecutions = 10;

		for (int i = 0; i < noExecutions; i++) {
			// Generate five Members
			// Add them to a single Group
			for (int j = 0; j < 10; j++) {
				// Array that holds all groups and Members
				ArrayList<Member> tempMembers = new ArrayList<Member>();
				ArrayList<Group> tempGroups = new ArrayList<Group>();

				// Add fakeMembers to tempMembers
				for (int k = 0; k < 10; k++) {
					tempMembers.add(generateFakeMember(j, k));
				}

				// For each Group test joinGroup...
				for (int l = 0; l < 10; l++) {
					// Add Group to Group Array
					tempGroups.add(generateFakeGroup());
					// Declare current Group
					Group cGroup = tempGroups.get(l);
					// Declare random number to add to Group between 0-10
					int noMembers = (int) (Math.random() * 11);

					// Declare actual arrays of groups and Memberships
					ArrayList<Member> actualMembers = new ArrayList<Member>();
					ArrayList<Membership> actualMemberships = new ArrayList<Membership>();

					for (int z = 0; z < noMembers; z++) {
						// Current Member
						Member cMem = tempMembers.get(z);

						// Add to real object and actual arrays
						cMem.joinGroup(cGroup, LocalDateTime.now());
						actualMembers.add(cMem);
						actualMemberships.add(cMem.getMembership(cGroup));

						// check if Member has Membership by group
						assertTrue(actualMemberships.contains(cMem.getMembership(cGroup)));
					}

					// Test to see if the Number of Members is right for the Group
					assertEquals(cGroup.getNumMembers(), noMembers);

					// In Group, test to see if the list of members is the same for each group,
					// therefore memberships is equal
					assertEquals(actualMembers, cGroup.getMembers());

					/**
					 * Test Question and Answer
					 * 
					 * addAnswer effects - setMembership in Member - Membership addAnswer is called
					 */

					for (int a = 0; a < 10; a++) {
						// Current Member
						Member cMem = tempMembers.get(a);

						ArrayList<Question> actualQuestions = new ArrayList<Question>();
						ArrayList<Answer> actualAnswers = new ArrayList<Answer>();

						for (int z = 0; z < 10; z++) {
							// Generate fake question and add it
							Question fakeQuestion = generateFakeQuestion(cMem.getEmailAddress(), z);
							actualQuestions.add(fakeQuestion);
							// Add Current Question to Member with questionTime
							LocalDateTime questionTime = actualQuestions.get(z).getDate();
							cMem.addQuestion(cGroup, fakeQuestion, questionTime);

							// test that actualQuestion has the fakeQuestion
							if (cMem.getQuestions(cGroup) != null)
								assertTrue(cMem.getQuestions(cGroup).contains(fakeQuestion));

							// TODO: Test Group Methods for get questions for this cMem

							// TODO: Test Membership Methods for questions for this cMem & Group

							// Generate Answers
							for (int x = 0; x < 10; x++) {
								// Generate fake answer and add it
								Answer fakeAnswer = generateFakeAnswer(fakeQuestion, cMem.getEmailAddress(), x);
								actualAnswers.add(fakeAnswer);
								// Add Current Answer to Member's Current Question
								int randomIndex = (int) (Math.random() * 10);
								Member randomMember = tempMembers.get(randomIndex);
								LocalDateTime answerTime = LocalDateTime.now();
								randomMember.addAnswer(cGroup, fakeQuestion, fakeAnswer, answerTime);
								// cMem.addAnswer(cGroup, fakeQuestion, fakeAnswer, answerTime );

								// Test that Member.getAnswer
								if (randomMember.getAnswers(cGroup) != null) {
									assertTrue(randomMember.getAnswers(cGroup).contains(fakeAnswer));
								}

								// TODO: Test Group Methods for get answers for this cMem

								// TODO: Test Membership Methods for answers for this cMem & Group

							}

						}
					}
				}

			}
		}
	}

	private Group generateFakeGroup() {
		String groupOneTitle = "Group One";
		String groupOneDescription = "Group Description: Class aptent taciti sociosqu ad litora torquent per conubia nostra, "
				+ "per inceptos himenaeos. Praesent sit amet sem a justo mattis fermentum at a elit.";
		LocalDateTime groupOneDateCreated = LocalDateTime.now();
		// Create Test Group One
		return new Group(groupOneTitle, groupOneDescription, groupOneDateCreated);
	}

	private Member generateFakeMember(int j, int k) {
		// Test Member Variables
		String memberFirstName = "FirstName";
		String memberLastName = "LastName";
		String memberScreenName = "ScreenName";
		String memberEmailAddress = "FirstNameLast" + j + k + "Name@email.com";
		LocalDateTime memberDateCreated = LocalDateTime.now();
		// Create Test Member
		return new Member(memberFirstName, memberLastName, memberScreenName, memberEmailAddress, memberDateCreated);
	}

	private Question generateFakeQuestion(String emailAddress, int index) {
		return new Question("Title: " + emailAddress + " " + index, "Description", LocalDateTime.now());
	}

	private Answer generateFakeAnswer(Question q, String screenName, int index) {
		return new Answer(q, "Text: Answer, " + screenName + " " + index, LocalDateTime.now());
	}

}

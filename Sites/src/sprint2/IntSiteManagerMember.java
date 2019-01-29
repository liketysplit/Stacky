package sprint2;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class IntSiteManagerMember {
	SiteManager sm;
	String tylerEmail = "trangelier@valdosta.edu";
	String jordanEmail = "rjboles@valdosta.edu";
	String malloryEmail = "mghelms@valdosta.edu";
	String mickeyEmail = "memontgomery@valdosta.edu";

	/**
	 * Tyler is the owner of the Group SQL (created it first?) - Jordan is a member
	 * Jordan is the owner of the Group NodeJS (created it first?) - Tyler is a
	 * member Mallory is the owner of the Group Java (created it first?) - Mickey is
	 * a member - Jordan is a member
	 */
	@BeforeEach
	@DisplayName("generate SiteManger")
	void generateSiteManager() {
		sm = new SiteManager();
	}

	/**
	 * Tests adding a member successfully and returning true and tests adding a
	 * duplicate member and returning false
	 */
	@Test
	@DisplayName("add duplicate Members")
	void addMembers() {
		LocalDateTime sharedTime = LocalDateTime.now();
		sm.addMember("Tyler", "Angelier", "TylerAngelier", tylerEmail, sharedTime);
		assertFalse(sm.addMember("Tyler", "Angelier", "TylerAngelier", tylerEmail, sharedTime));
		LocalDateTime differentTime = LocalDateTime.now();
		assertTrue(sm.addMember("Jordan", "Boles", "JordanBoles", jordanEmail, differentTime));
		sm.addMember("Mallory", "Helms", "MallortyHelms", "mghelms@valdosta.edu", sharedTime);
		sm.addMember("Mickey", "Montgomery", "MickeyMontgomery", mickeyEmail, sharedTime);
	}

	/**
	 * 
	 */
	@Nested
	@DisplayName("Groups and Members")
	class getGroups {
		LocalDateTime groupCreation;
		LocalDateTime memberCreation;
		Group nodeJS;
		Group java;
		Group sql;
		String nodeJSTitle = "NodeJS";
		String javaTitle = "Java";
		String sqlTitle = "SQL";

		@BeforeEach
		void addMembersToSiteManagerAndGroup() {
			groupCreation = LocalDateTime.now();
			memberCreation = LocalDateTime.now();
			nodeJS = new Group(nodeJSTitle, "This is a group about the language NodeJS", groupCreation);
			java = new Group(javaTitle, "This is a group about the language Java", groupCreation);
			sql = new Group(sqlTitle, "This is a group about the database language SQL", groupCreation);
			sm.addMember("Mallory", "Helms", "MallortyHelms", "mghelms@valdosta.edu", memberCreation);
			sm.addMember("Mickey", "Montgomery", "MickeyMontgomery", mickeyEmail, memberCreation);
			sm.addMember("Tyler", "Angelier", "TylerAngelier", tylerEmail, memberCreation);
			sm.addMember("Jordan", "Boles", "JordanBoles", jordanEmail, memberCreation);
			sm.addGroup(nodeJSTitle, "This is a group about the language NodeJS", groupCreation);
			sm.addGroup(javaTitle, "This is a group about the language Java", groupCreation);
			sm.addGroup(sqlTitle, "This is a group about the database language SQL", groupCreation);
			sm.getMember(jordanEmail).joinGroup(sm.getGroup(nodeJSTitle), groupCreation);
			sm.getMember(malloryEmail).joinGroup(sm.getGroup(javaTitle), groupCreation);
			sm.getMember(tylerEmail).joinGroup(sm.getGroup(sqlTitle), groupCreation);
			sm.getMember(jordanEmail).joinGroup(sm.getGroup(sqlTitle), groupCreation);
			sm.getMember(tylerEmail).joinGroup(sm.getGroup(nodeJSTitle), groupCreation);
			sm.getMember(jordanEmail).joinGroup(sm.getGroup(javaTitle), groupCreation);
			sm.getMember(mickeyEmail).joinGroup(sm.getGroup(javaTitle), groupCreation);

		}

		@DisplayName("getNumGroups")
		@Test
		void testGetNumGroups() {
			assertEquals(sm.getMember(tylerEmail).getNumGroups(), 2);
			assertEquals(sm.getMember(malloryEmail).getNumGroups(), 1);
			assertEquals(sm.getMember(jordanEmail).getNumGroups(), 3);
			assertEquals(sm.getMember(mickeyEmail).getNumGroups(), 1);
		}

		@DisplayName("getNumMembers")
		@Test
		void testGetNumMembers() {
			System.out.println(sm.getGroup(nodeJSTitle).getMembers());

			assertEquals(sm.getMember(tylerEmail).getGroup(nodeJSTitle).getMembers().size(), 2);
			assertEquals(sm.getMember(malloryEmail).getGroup(javaTitle).getMembers().size(), 3);
			assertEquals(sm.getMember(tylerEmail).getGroup(sqlTitle).getMembers().size(), 2);

			assertEquals(sm.getGroup(nodeJSTitle).getMembers().size(), 2);
			assertEquals(sm.getGroup(javaTitle).getMembers().size(), 3);
			assertEquals(sm.getGroup(sqlTitle).getMembers().size(), 2);
		}

		@DisplayName("getMembers")
		@Test
		void testGetMembers() {
			assertEquals(sm.getMember(tylerEmail).getEmailAddress(), tylerEmail);
			assertEquals(sm.getMember(malloryEmail).getEmailAddress(), malloryEmail);
			assertEquals(sm.getMember(jordanEmail).getEmailAddress(), jordanEmail);
			assertEquals(sm.getMember(mickeyEmail).getEmailAddress(), mickeyEmail);
		}

		@DisplayName("getDateJoined")
		@Test
		void testGetDateJoined() {
			assertEquals(sm.getMember(tylerEmail).getDateJoined(nodeJS), groupCreation);
		}
	}

	/**
	 * 
	 */
	@Nested
	@DisplayName("Questions and Answers")
	class QuestionsAndAnswers {
		LocalDateTime groupCreation = LocalDateTime.now();
		LocalDateTime memberCreation = LocalDateTime.now();

		Group nodeJS;
		Group java;
		Group sql;

		String nodeJSTitle = "NodeJS";
		String javaTitle = "Java";
		String sqlTitle = "SQL";

		Question java1;
		Question node1;
		Question node2;
		Question sql1;
		Question sql2;

		/**
		 * Jordan asks two questions about SQL in the SQL Group Mickey asks one question
		 * about Java in the Java Group Tyler asks two questions about NodeJS in the
		 * NodeJS Group
		 */
		@BeforeEach
		void askFiveQuestionsAndTenAnswers() {
			groupCreation = LocalDateTime.now();
			memberCreation = LocalDateTime.now();
			nodeJS = new Group(nodeJSTitle, "This is a group about the language NodeJS", groupCreation);
			java = new Group(javaTitle, "This is a group about the language Java", groupCreation);
			sql = new Group(sqlTitle, "This is a group about the database language SQL", groupCreation);
			sm.addMember("Mallory", "Helms", "MallortyHelms", "mghelms@valdosta.edu", memberCreation);
			sm.addMember("Mickey", "Montgomery", "MickeyMontgomery", mickeyEmail, memberCreation);
			sm.addMember("Tyler", "Angelier", "TylerAngelier", tylerEmail, memberCreation);
			sm.addMember("Jordan", "Boles", "JordanBoles", jordanEmail, memberCreation);
			sm.addGroup(nodeJSTitle, "This is a group about the language NodeJS", groupCreation);
			sm.addGroup(javaTitle, "This is a group about the language Java", groupCreation);
			sm.addGroup(sqlTitle, "This is a group about the database language SQL", groupCreation);
			sm.getMember(jordanEmail).joinGroup(sm.getGroup(nodeJSTitle), groupCreation);
			sm.getMember(malloryEmail).joinGroup(sm.getGroup(javaTitle), groupCreation);
			sm.getMember(tylerEmail).joinGroup(sm.getGroup(sqlTitle), groupCreation);
			sm.getMember(jordanEmail).joinGroup(sm.getGroup(sqlTitle), groupCreation);
			sm.getMember(tylerEmail).joinGroup(sm.getGroup(nodeJSTitle), groupCreation);
			sm.getMember(jordanEmail).joinGroup(sm.getGroup(javaTitle), groupCreation);
			sm.getMember(mickeyEmail).joinGroup(sm.getGroup(javaTitle), groupCreation);

			LocalDateTime questionCreation = LocalDateTime.now();
			java1 = new Question("Beginner Java Question!", "How do I log things in Java?", questionCreation);
			node1 = new Question("Beginner NodeJS Question!", "How do I log things in NodeJS?", questionCreation);
			node2 = new Question("Intermediate Question!", "How do I fix callback hell in NodeJS?", questionCreation);
			sql1 = new Question("Beginner SQL Question!", "How do I select everything in SQL?", questionCreation);
			sql2 = new Question("Intermediate Question!", "How do I use CASE statemetns in SQL", questionCreation);
			sm.getMember(jordanEmail).addQuestion(sm.getGroup(sqlTitle), sql1, questionCreation);
			sm.getMember(jordanEmail).addQuestion(sm.getGroup(sqlTitle), sql2, questionCreation);
			sm.getMember(mickeyEmail).addQuestion(sm.getGroup(javaTitle), java1, questionCreation);
			sm.getMember(tylerEmail).addQuestion(sm.getGroup(nodeJSTitle), node1, questionCreation);
			sm.getMember(tylerEmail).addQuestion(sm.getGroup(nodeJSTitle), node2, questionCreation);

			LocalDateTime answerCreation = LocalDateTime.now();
			Answer javaAnswer1 = new Answer(java1, "System.out.println(\"Message Here\")", answerCreation);
			Answer javaAnswer2 = new Answer(java1, "System.out.printf(\"%s\",\"Message Here\")", answerCreation);
			Answer nodeAnswer1 = new Answer(node1, "console.log()", answerCreation);
			Answer nodeAnswer2 = new Answer(node1, "console.warn()", answerCreation);
			Answer nodeAnswer3 = new Answer(node2, "www.callbackhell.com", answerCreation);
			Answer nodeAnswer4 = new Answer(node2, "Use Promises!", answerCreation);
			Answer sqlAnswer1 = new Answer(sql1, "SELECT * FROM TABLE", answerCreation);
			Answer sqlAnswer2 = new Answer(sql1, "https://www.techonthenet.com/sql/select.php", answerCreation);
			Answer sqlAnswer3 = new Answer(sql2, "CASE WHEN CONDITION THEN END CASE", answerCreation);
			Answer sqlAnswer4 = new Answer(sql2, "https://www.techonthenet.com/oracle/functions/case.php",
					answerCreation);

			// Java Answers
			sm.getMember(jordanEmail).addAnswer(sm.getGroup(javaTitle), javaAnswer1.getQuestion(), javaAnswer1,
					answerCreation);
			sm.getMember(mickeyEmail).addAnswer(sm.getGroup(javaTitle), javaAnswer2.getQuestion(), javaAnswer2,
					answerCreation);
			// NodeJS Answers
			sm.getMember(jordanEmail).addAnswer(sm.getGroup(nodeJSTitle), nodeAnswer1.getQuestion(), nodeAnswer1,
					answerCreation);
			sm.getMember(jordanEmail).addAnswer(sm.getGroup(nodeJSTitle), nodeAnswer2.getQuestion(), nodeAnswer2,
					answerCreation);
			sm.getMember(tylerEmail).addAnswer(sm.getGroup(nodeJSTitle), nodeAnswer2.getQuestion(), nodeAnswer2,
					answerCreation);
			sm.getMember(tylerEmail).addAnswer(sm.getGroup(nodeJSTitle), nodeAnswer2.getQuestion(), nodeAnswer2,
					answerCreation);
			// SQL Answers
			sm.getMember(jordanEmail).addAnswer(sm.getGroup(sqlTitle), sqlAnswer1.getQuestion(), sqlAnswer1,
					answerCreation);
			sm.getMember(jordanEmail).addAnswer(sm.getGroup(sqlTitle), sqlAnswer2.getQuestion(), sqlAnswer2,
					answerCreation);
			sm.getMember(jordanEmail).addAnswer(sm.getGroup(sqlTitle), sqlAnswer3.getQuestion(), sqlAnswer3,
					answerCreation);
			sm.getMember(tylerEmail).addAnswer(sm.getGroup(sqlTitle), sqlAnswer4.getQuestion(), sqlAnswer4,
					answerCreation);
		}

		/**
		 * Activity breakdown Tyler - 5 Jordan - 8 Mickey - 2 Mallory - 0
		 */
		@Test
		void testGetActiveMembers() {
			sm.getActiveMembers(1);
			ArrayList<Member> expectedMembers = new ArrayList<Member>();
			expectedMembers.add(sm.getMember(jordanEmail));
			assertEquals(sm.getActiveMembers(1), expectedMembers);
		}

	}

	@Test
	@DisplayName("PersistenceManager and SiteManager")
	void testPersistanceManager() {
		File file = new File("file1.ser");
		PersistenceManager pm = new PersistenceManager();

		pm.save(sm, file);
		SiteManager smLoaded = pm.read(file);

		ArrayList<Member> members1 = new ArrayList<Member>();
		ArrayList<Member> members2 = new ArrayList<Member>();

		ArrayList<Group> groups1 = new ArrayList<Group>();
		ArrayList<Group> groups2 = new ArrayList<Group>();

		for (Member m : sm.getMembers()) {
			members1.add(m);
		}
		for (Member m : smLoaded.getMembers()) {
			members2.add(m);
		}

		for (Group g : sm.getGroups()) {
			groups1.add(g);
		}
		for (Group g : smLoaded.getGroups()) {
			groups2.add(g);
		}

		if (members1.size() == members2.size()) {
			for (int i = 0; i < members1.size(); i++) {
				assertEquals(members1.get(i).toString(), members2.get(i).toString());

				ArrayList<Group> groups3 = new ArrayList<Group>();
				ArrayList<Group> groups4 = new ArrayList<Group>();

				for (Group g : members1.get(i).getGroups()) {
					groups3.add(g);
				}
				for (Group g : members2.get(i).getGroups()) {
					groups4.add(g);
				}

				ArrayList<Question> questions1 = new ArrayList<Question>();
				ArrayList<Question> questions2 = new ArrayList<Question>();

				for (Question q : members1.get(i).getQuestions(groups3.get(i))) {
					questions1.add(q);
				}
				for (Question q : members2.get(i).getQuestions(groups4.get(i))) {
					questions2.add(q);
				}
				if (questions1.size() == questions2.size()) {
					for (int j = 0; j < questions1.size(); j++) {
						assertEquals(questions1.get(j).toString(), questions2.get(j).toString());

						ArrayList<Answer> answers1 = new ArrayList<Answer>();
						ArrayList<Answer> answers2 = new ArrayList<Answer>();

						for (Answer a : questions1.get(j).getAnswers()) {
							answers1.add(a);
						}
						for (Answer a : questions2.get(j).getAnswers()) {
							answers2.add(a);
						}
						if (answers1.size() == answers2.size()) {
							for (int k = 0; k < answers1.size(); k++) {
								assertEquals(answers1.get(k).toString(), answers2.get(k).toString());
							}
						}
					}
				}
			}
		} else {
			fail("Members are not equal");
		}

		if (groups1.size() == groups2.size()) {
			for (int i = 0; i < groups1.size(); i++) {
				assertEquals(groups1.get(i).toString(), groups2.get(i).toString());
			}
		} else {
			fail("Groups are not equal");
		}
	}

}

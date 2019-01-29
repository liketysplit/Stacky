package sprint2;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class PersistenceManagerTest {
	/**
	 * This method should test that a persistence manager can save a site without
	 * error.
	 * 
	 */

	@Test
	void testSave() {
		File file = new File("file.ser");
		SiteManager sm = new SiteManager();
		PersistenceManager pm = new PersistenceManager();
		assertEquals(true, pm.save(sm, file));
	}

	/**
	 * This method should test that a persistence manager can read a site without
	 * error and the data saved can be retrieved. Member data was used here as test
	 * given that we do not want to compare references of the object.
	 * 
	 */
	@Test
	void testRead() {
		File file = new File("file1.ser");
		SiteManager sm = new SiteManager();
		PersistenceManager pm = new PersistenceManager();

		sm.addMember("John", "Doe", "m1", "m1@valdosta.edu", LocalDateTime.parse("2018-07-01T08:00"));
		sm.addMember("John", "Doe", "m2", "m2@valdosta.edu", LocalDateTime.parse("2018-07-01T08:00"));
		sm.addMember("John", "Doe", "m3", "m3@valdosta.edu", LocalDateTime.parse("2018-07-01T08:00"));
		sm.addMember("John", "Doe", "m4", "m4@valdosta.edu", LocalDateTime.parse("2018-07-01T08:00"));
		sm.addMember("John", "Doe", "m5", "m5@valdosta.edu", LocalDateTime.parse("2018-07-01T08:00"));
		sm.addMember("John", "Doe", "m6", "m6@valdosta.edu", LocalDateTime.parse("2018-07-01T08:00"));
		sm.addMember("John", "Doe", "m7", "m7@valdosta.edu", LocalDateTime.parse("2018-07-01T08:00"));
		sm.addMember("John", "Doe", "m8", "m8@valdosta.edu", LocalDateTime.parse("2018-07-01T08:00"));
		sm.addMember("John", "Doe", "m9", "m9@valdosta.edu", LocalDateTime.parse("2018-07-01T08:00"));
		sm.addMember("John", "Doe", "m0", "m0@valdosta.edu", LocalDateTime.parse("2018-07-01T08:00"));
		
		sm.addGroup("Group 1", "Ut risus mauris, commodo id justo non, egestas porttitor quam. Maecenas placerat vestibulum libero. Ut risus mauris, commodo id justo non, egestas porttitor quam. Maecenas placerat vestibulum libero. ", LocalDateTime.now());
		sm.addGroup("Group 2", "Group 2 Details", LocalDateTime.now());
		sm.addGroup("Group 3", "Group 3 Details", LocalDateTime.now());
		sm.addGroup("Group 4", "Group 4 Details", LocalDateTime.now());
		sm.addGroup("Group 5", "Group 5 Details", LocalDateTime.now());
		
		
		sm.getMember("m1@valdosta.edu").joinGroup(sm.getGroup("Group 1"), LocalDateTime.now());
		sm.getMember("m9@valdosta.edu").joinGroup(sm.getGroup("Group 1"), LocalDateTime.now());
		sm.getMember("m4@valdosta.edu").joinGroup(sm.getGroup("Group 1"), LocalDateTime.now());
		sm.getMember("m6@valdosta.edu").joinGroup(sm.getGroup("Group 1"), LocalDateTime.now());
		sm.getMember("m0@valdosta.edu").joinGroup(sm.getGroup("Group 1"), LocalDateTime.now());
		
		sm.getMember("m1@valdosta.edu").joinGroup(sm.getGroup("Group 2"), LocalDateTime.now());
		sm.getMember("m1@valdosta.edu").joinGroup(sm.getGroup("Group 3"), LocalDateTime.now());
		sm.getMember("m1@valdosta.edu").joinGroup(sm.getGroup("Group 4"), LocalDateTime.now());
		sm.getMember("m1@valdosta.edu").joinGroup(sm.getGroup("Group 5"), LocalDateTime.now());
		
		Question q1 = new Question("Blah Title","Blah Description",  LocalDateTime.parse("2018-07-01T08:00"));
		Answer a1 = new Answer(q1,"Blah Answer1 Text!",  LocalDateTime.parse("2018-07-01T08:00"));
		Answer a2 = new Answer(q1,"Blah Answer2 Text!",  LocalDateTime.parse("2018-07-01T08:00"));
		Answer a3 = new Answer(q1,"Blah Answer3 Text!",  LocalDateTime.parse("2018-07-01T08:00"));
		Answer a4 = new Answer(q1,"Blah Answer4 Text!",  LocalDateTime.parse("2018-07-01T08:00"));
		
		sm.getMember("m1@valdosta.edu").addQuestion(sm.getGroup("Group 1"), q1,  LocalDateTime.parse("2018-07-01T08:00"));
		sm.getMember("m9@valdosta.edu").addAnswer(sm.getGroup("Group 1"), q1, a1,  LocalDateTime.parse("2018-07-01T08:00"));
		sm.getMember("m4@valdosta.edu").addAnswer(sm.getGroup("Group 1"), q1, a2,  LocalDateTime.parse("2018-07-01T08:00"));
		sm.getMember("m0@valdosta.edu").addAnswer(sm.getGroup("Group 1"), q1, a3,  LocalDateTime.parse("2018-07-01T08:00"));
		sm.getMember("m6@valdosta.edu").addAnswer(sm.getGroup("Group 1"), q1, a4,  LocalDateTime.parse("2018-07-01T08:00"));
		System.out.println(sm.getMembers());

		pm.save(sm, file);
		SiteManager smLoaded = pm.read(file);

		ArrayList<Member> members1 = new ArrayList<Member>();
		ArrayList<Member> members2 = new ArrayList<Member>();

		for (Member m : sm.getMembers()) {
			members1.add(m);
		}
		for (Member m : smLoaded.getMembers()) {
			members2.add(m);
		}
		if (members1.size() == members2.size()) {
			for (int i = 0; i < members1.size(); i++) {
				assertEquals(members1.get(i).toString(), members2.get(i).toString());
			}
		} else {
			fail("Members are not equal");
		}

	}

}

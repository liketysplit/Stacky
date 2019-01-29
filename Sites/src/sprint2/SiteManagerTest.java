package sprint2;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.*;
import org.junit.jupiter.api.Test;

public class SiteManagerTest {

    LocalDateTime ldt = LocalDateTime.now();
    String expectedFirstName = "John";
    String expectedLastName = "Doe";
    String expectedScreenName = "johndoe";
    String expectedEmail = "johndoe@gmail.com";
    String expectedGroupTitle = "Software";
    String expectedGroupDescription = "This is a description";

    /**
	 * This method should test that a SiteManager adds a group {addGroup(String, String, etc.)} to its internal
	 * list of groups and returns true.
	 * 
	 */
    @Test
    void testAddGroupOverloaded() {
        LocalDateTime ldt = LocalDateTime.now();

        SiteManager s = new SiteManager();

        assertEquals(true, s.addGroup("Hardware", "This is a description", ldt));
    }    
        
    /**
	 * This method should test that a SiteManager does not add a group {addGroup(String, String, etc.)} to its internal
	 * list of groups when it already exists and returns false.
	 * 
	 */
    @Test
    void testAddGroupOverloadedAlreadyExists() {
        LocalDateTime ldt = LocalDateTime.now();
        String title = "Hardware";
        String description = "This is a description";

        SiteManager s = new SiteManager();
        s.addGroup(title, description, ldt);

        // A group with the title "Hardware" has already been added to 
        // s which means this should return false.
        Boolean actualValue = s.addGroup(title, description, ldt);
        
        Boolean expectedValue = false;
        
        assertEquals(expectedValue, actualValue);
    }
    
    /**
	 * This method should test that a SiteManager adds a group {addGroup(Group)} to its internal
	 * list of groups and returns true.
	 * 
	 */
    @Test
    void testAddGroup() {
        LocalDateTime ldt = LocalDateTime.now();

        SiteManager s = new SiteManager();
        Group g = new Group("Title", "description", ldt);

        assertEquals(true, s.addGroup(g));
    }
    
    /**
	 * This method should test that a SiteManager does not add a group {addGroup(Group)} to its internal
	 * list of groups when it already exists and returns false.
	 * 
	 */
    @Test
    void testAddGroupAlreadyExists() {
        LocalDateTime ldt = LocalDateTime.now();
        String title = "Title";

        SiteManager s = new SiteManager();
        Group g = new Group(title, "description", ldt);
        s.addGroup(g);
        Group g2 = new Group(title, "new description", LocalDateTime.now());

        assertEquals(false, s.addGroup(g2));
    }

    /**
	 * This method should test that a SiteManager adds a member {addMember(String, String, etc.)} to its internal
	 * list of members and returns true.
	 * 
	 */
    @Test
    void testAddMemberOverloaded() {
        LocalDateTime ldt = LocalDateTime.now();

        SiteManager s = new SiteManager();

        assertEquals(true, s.addMember("John", "Doe", "johndoe", "johndoe@gmail.com", ldt));
    }
    
    /**
	 * This method should test that a SiteManager does not add a member {addMember(String, String, etc.)} to its internal
	 * list of members when it already exits and returns false.
	 * 
	 */
    @Test
    void testAddMemberOverloadedAlreadyExists() {
        LocalDateTime ldt = LocalDateTime.now();
        String firstName = "John";
        String lastName = "Doe";
        String screenName = "johndoe";
        String email = "johndoe@email.com";

        SiteManager s = new SiteManager();
        s.addMember(firstName, lastName, screenName, email, ldt);

        // A member with the email "johndoe@email.com" has already been added to 
        // s which means this should return false.
        Boolean actualValue = s.addMember(firstName, lastName, screenName, email, ldt);
        
        Boolean expectedValue = false;
        
        assertEquals(expectedValue, actualValue);
    }
    
    /**
	 * This method should test that a SiteManager adds a member {addMember(Member)} to its internal
	 * list of members and returns true.
	 * 
	 */
    @Test
    void testAddMember() {
        LocalDateTime ldt = LocalDateTime.now();

        SiteManager s = new SiteManager();
        Member m = new Member("John", "Doe", "johndoe", "johndoe@gmail.com", ldt);

        assertEquals(true, s.addMember(m));
    }    
    
    /**
	 * This method should test that a SiteManager does not add a member {addMember(Member)} to its internal
	 * list of members when it already exits and returns false.
	 * 
	 */
    @Test
    void testAddMemberAlreadyExists() {
        LocalDateTime ldt = LocalDateTime.now();
        String email = "johndoe@gmail.com";

        SiteManager s = new SiteManager();
        Member m = new Member("John", "Doe", "johndoe", email, ldt);
        s.addMember(m);
        Member m2 = new Member("Fname", "Lname", "ScreenName", email, LocalDateTime.now());

        assertEquals(false, s.addMember(m2));
    } 
    

    /**
	 * This method should test that a SiteManager returns a specific group
	 * given the title of that group.
	 * 
	 */
    @Test
    void testGetGroup() {
        LocalDateTime ldt = LocalDateTime.now();

        Group g1 = new Group("Software", "This is a description", ldt);

        SiteManager s = new SiteManager();
        s.addGroup("Software", "This is a description", ldt);
        s.addGroup("Hardware", "This is a description", ldt);

        assertEquals(g1.toString(), s.getGroup("Software").toString());

    }

    /**
   	 * This method should test that a SiteManager returns a null when there
   	 * is no group in the list of groups with the given title.
   	 * 
   	 */
       @Test
       void testGetGroupDoesNotExist() {
           LocalDateTime ldt = LocalDateTime.now();

           SiteManager s = new SiteManager();
           s.addGroup("Software", "This is a description", ldt);
           s.addGroup("Hardware", "This is a description", ldt);
           
           Group expectedGroup = null;
           Group actualGroup = s.getGroup("Testing");

           assertEquals(expectedGroup, actualGroup);

       }
    /**
	 * This method should test that a SiteManager returns a list of all groups
	 * in its internal list of groups, sorted on title.
	 * 
	 */
    @Test
    void testGetGroups() {
        LocalDateTime ldt = LocalDateTime.now();

        Group g1 = new Group("Software", "This is a description", ldt);
        Group g2 = new Group("Hardware", "This is a description", ldt);
        Group g3 = new Group("Testing", "This is a description", ldt);
        Group g4 = new Group("Game Development", "This is a description", ldt);

        ArrayList<Group> expectedGroups = new ArrayList<Group>();
        expectedGroups.add(g4);
        expectedGroups.add(g2);
        expectedGroups.add(g1);
        expectedGroups.add(g3);

        SiteManager s = new SiteManager();
        s.addGroup("Software", "This is a description", ldt);
        s.addGroup("Hardware", "This is a description", ldt);
        s.addGroup("Testing", "This is a description", ldt);
        s.addGroup("Game Development", "This is a description", ldt);
        ArrayList<Group> actualGroups = (ArrayList<Group>) s.getGroups();

        assertEquals(expectedGroups.toString(), actualGroups.toString());
    }

    /**
	 * This method should test that a SiteManager returns a list of all groups
	 * in its internal list of groups where any part of the title or description 
	 * matches a particular string, sorted on title.
	 * 
	 */
    @SuppressWarnings("unused")
    @Test
    void testGetGroupsByText() {
        LocalDateTime ldt = LocalDateTime.now();

        Group g1 = new Group("Software", "This is a description", ldt);
        Group g2 = new Group("Hardware", "This is a description", ldt);
        Group g3 = new Group("Hardware Testing", "This is a description", ldt);
        Group g4 = new Group("Game Development", "We also look at game Hardware", ldt);

        ArrayList<Group> expectedGroups = new ArrayList<Group>();
        expectedGroups.add(g4);
        expectedGroups.add(g2);
        expectedGroups.add(g3);

        SiteManager s = new SiteManager();
        s.addGroup("Software", "This is a description", ldt);
        s.addGroup("Hardware", "This is a description", ldt);
        s.addGroup("Hardware Testing", "This is a description", ldt);
        s.addGroup("Game Development", "We also look at game Hardware", ldt);
        ArrayList<Group> actualGroups = (ArrayList<Group>) s.getGroups("Hardware");

        assertEquals(expectedGroups.toString(), actualGroups.toString());
    }
    
    /**
	 * This method should test that a SiteManager returns an array list of the first
	 * n groups of the input array list.
	 * 
	 */
    @Test
    void testGetListNumOfGroups() {
        LocalDateTime ldt = LocalDateTime.now();

        Group g1 = new Group("Software", "This is a description", ldt);
        Group g2 = new Group("Hardware", "This is a description", ldt);
        Group g3 = new Group("Testing", "This is a description", ldt);
        Group g4 = new Group("Game Development", "This is a description", ldt);

        ArrayList<Group> groups = new ArrayList<Group>();
        ArrayList<Group> expectedGroups = new ArrayList<Group>();
        groups.add(g4);
        groups.add(g2);
        groups.add(g1);
        groups.add(g3);
        
        expectedGroups.add(g4);
        expectedGroups.add(g2);

        SiteManager s = new SiteManager();
        ArrayList<Group> actualGroups = (ArrayList<Group>)s.getListNumOfGroups(expectedGroups, 2);

        assertEquals(expectedGroups, actualGroups);
    }

    /**
	 * This method should test that a SiteManager returns a specific member
	 * given the email address of that member.
	 * 
	 */
    @SuppressWarnings("unused")
    @Test
    void testGetMember() {
        LocalDateTime ldt = LocalDateTime.now();

        Member mem1 = new Member("John", "Doe", "johndoe", "johndoe@gmail.com", ldt);
        Member mem2 = new Member("Jane", "Doe", "janedoe", "janedoe@gmail.com", ldt);
        Member mem3 = new Member("Jason", "Doe", "jasondoe", "jasondoe@gmail.com", ldt);

        SiteManager s = new SiteManager();
        s.addMember("John", "Doe", "johndoe", "johndoe@gmail.com", ldt);
        s.addMember("Jane", "Doe", "janedoe", "janedoe@gmail.com", ldt);
        s.addMember("Jason", "Doe", "jasondoe", "jasondoe@gmail.com", ldt);

        assertEquals(mem2.toString(), s.getMember("janedoe@gmail.com").toString());
    }
    
    /**
	 * This method should test that a SiteManager returns a null when there
   	 * is no member in the list of members with the given email address.
	 * 
	 */
    @SuppressWarnings("unused")
    @Test
    void testGetMemberDoesNotExist() {
        LocalDateTime ldt = LocalDateTime.now();

        SiteManager s = new SiteManager();
        s.addMember("John", "Doe", "johndoe", "johndoe@gmail.com", ldt);
        s.addMember("Jane", "Doe", "janedoe", "janedoe@gmail.com", ldt);
        
        Member expectedMember = null;
        Member actualMember = s.getMember("jasondoe@gmail.com");

        assertEquals(expectedMember, actualMember);
    }

    /**
	 * This method should test that a SiteManager returns a list of all members
	 * in its internal list of members, sorted on last name then first name.
	 * 
	 */
    @Test
    void testGetMembers() {
        LocalDateTime ldt = LocalDateTime.now();

        Member mem1 = new Member("John", "Doe", "johndoe", "johndoe@gmail.com", ldt);
        Member mem2 = new Member("Jane", "Doe", "janedoe", "janedoe@gmail.com", ldt);
        Member mem3 = new Member("Jason", "Zane", "jasonzane", "jasonzane@gmail.com", ldt);

        ArrayList<Member> expectedMembers = new ArrayList<Member>();
        expectedMembers.add(mem2);
        expectedMembers.add(mem1);
        expectedMembers.add(mem3);

        SiteManager s = new SiteManager();
        s.addMember("John", "Doe", "johndoe", "johndoe@gmail.com", ldt);
        s.addMember("Jane", "Doe", "janedoe", "janedoe@gmail.com", ldt);
        s.addMember("Jason", "Zane", "jasonzane", "jasonzane@gmail.com", ldt);
        ArrayList<Member> actualMembers = (ArrayList<Member>) s.getMembers();

        assertEquals(expectedMembers.toString(), actualMembers.toString());
    }

    /**
	 * This method should test that a SiteManager returns a list of all members
	 * in its internal list of members where any part of the first name, last name, 
	 * screen name, or email address matches a particular string, sorted on last name
	 * then first name.
	 * 
	 */
    @SuppressWarnings("unused")
    @Test
    void testGetMembersByText() {
        LocalDateTime ldt = LocalDateTime.now();

        Member mem1 = new Member("FirstName", "LastName_Doe", "mem1", "mem1@gmail.com", ldt);
        Member mem2 = new Member("FirstName_Doe", "LastName_B", "mem2", "mem2@gmail.com", ldt);
        Member mem3 = new Member("FirstName_A", "LastName_B", "mem3Doe", "mem3@gmail.com", ldt);
        Member mem4 = new Member("FirstName", "LastName_A", "mem4", "Doe@gmail.com", ldt);
        Member mem5 = new Member("FirstName", "LastName", "mem5", "mem5@gmail.com", ldt);

        ArrayList<Member> expectedMembers = new ArrayList<Member>();
        expectedMembers.add(mem4);
        expectedMembers.add(mem3);
        expectedMembers.add(mem2);
        expectedMembers.add(mem1);
        

        SiteManager s = new SiteManager();
        s.addMember("FirstName", "LastName_Doe", "mem1", "mem1@gmail.com", ldt);
        s.addMember("FirstName_Doe", "LastName_B", "mem2", "mem2@gmail.com", ldt);
        s.addMember("FirstName_A", "LastName_B", "mem3Doe", "mem3@gmail.com", ldt);
        s.addMember("FirstName", "LastName_A", "mem4", "Doe@gmail.com", ldt);
        s.addMember("FirstName", "LastName", "mem5", "mem5@gmail.com", ldt);
        ArrayList<Member> actualMembers = (ArrayList<Member>) s.getMembers("Doe");

        assertEquals(expectedMembers.toString(), actualMembers.toString());
    }

    /**
	 * This method should test that a SiteManager returns a list of the n groups
	 * with the highest number of members, sorted on number of members, highest
	 * first.
	 * 
	 */
    @SuppressWarnings("unused")
    @Test
    void testGetPopularGroups() {
        LocalDateTime ldt = LocalDateTime.now();
        SiteManager s = new SiteManager();
        Group g1 = new Group("Software", "This is a description", ldt);
        Group g2 = new Group("Hardware", "This is a description", ldt);
        Group g3 = new Group("Testing", "This is a description", ldt);
        Group g4 = new Group("Game Development", "This is a description", ldt);

        s.addGroup("Software", "This is a description", ldt);
        s.addGroup("Hardware", "This is a description", ldt);
        s.addGroup("Testing", "This is a description", ldt);
        s.addGroup("Game Development", "This is a description", ldt);

        Member mem1 = new Member("John", "Doe", "johndoe", "johndoe@gmail.com", ldt);
        Member mem2 = new Member("Jane", "Doe", "janedoe", "janedoe@gmail.com", ldt);
        Member mem3 = new Member("Jason", "Doe", "jasondoe", "jasondoe@gmail.com", ldt);
        Member mem4 = new Member("Jenny", "Doe", "jennydoe", "jennydoe@gmail.com", ldt);

        // Group g1
        // NumMembers = 4
        mem1.joinGroup(s.getGroup("Software"), ldt);
        mem2.joinGroup(s.getGroup("Software"), ldt);
        mem3.joinGroup(s.getGroup("Software"), ldt);
        mem4.joinGroup(s.getGroup("Software"), ldt);

        // Group g2
        // NumMembers = 3
        mem1.joinGroup(s.getGroup("Hardware"), ldt);
        mem2.joinGroup(s.getGroup("Hardware"), ldt);
        mem3.joinGroup(s.getGroup("Hardware"), ldt);

        // Group g3
        // NumMembers = 2
        mem1.joinGroup(s.getGroup("Testing"), ldt);
        mem2.joinGroup(s.getGroup("Testing"), ldt);

        // Group g4
        // NumMembers = 1
        mem1.joinGroup(s.getGroup("Game Development"), ldt);

        ArrayList<Group> expectedGroups = new ArrayList<Group>();
        expectedGroups.add(g1);
        expectedGroups.add(g2);

        ArrayList<Group> actualGroups = (ArrayList<Group>) s.getPopularGroups(2);

        assertEquals(expectedGroups.toString(), actualGroups.toString());
    }
    
    /**
	 * This method should test that a SiteManager returns a list of all groups,
	 * sorted on number of members, highest first.
	 * 
	 * The parameter given to getPopularGroups(int n) is greater than the total
	 * number of groups in the list. This should not crash.
	 * 
	 */
    @SuppressWarnings("unused")
    @Test
    void testGetPopularGroupsNGreaterThanTotalGroups() {
        LocalDateTime ldt = LocalDateTime.now();
        SiteManager s = new SiteManager();
        Group g1 = new Group("Software", "This is a description", ldt);
        Group g2 = new Group("Hardware", "This is a description", ldt);
        Group g3 = new Group("Testing", "This is a description", ldt);
        Group g4 = new Group("Game Development", "This is a description", ldt);

        s.addGroup("Software", "This is a description", ldt);
        s.addGroup("Hardware", "This is a description", ldt);
        s.addGroup("Testing", "This is a description", ldt);
        s.addGroup("Game Development", "This is a description", ldt);

        Member mem1 = new Member("John", "Doe", "johndoe", "johndoe@gmail.com", ldt);
        Member mem2 = new Member("Jane", "Doe", "janedoe", "janedoe@gmail.com", ldt);
        Member mem3 = new Member("Jason", "Doe", "jasondoe", "jasondoe@gmail.com", ldt);
        Member mem4 = new Member("Jenny", "Doe", "jennydoe", "jennydoe@gmail.com", ldt);

        // Group g1
        // NumMembers = 4
        mem1.joinGroup(s.getGroup("Software"), ldt);
        mem2.joinGroup(s.getGroup("Software"), ldt);
        mem3.joinGroup(s.getGroup("Software"), ldt);
        mem4.joinGroup(s.getGroup("Software"), ldt);

        // Group g2
        // NumMembers = 3
        mem1.joinGroup(s.getGroup("Hardware"), ldt);
        mem2.joinGroup(s.getGroup("Hardware"), ldt);
        mem3.joinGroup(s.getGroup("Hardware"), ldt);

        // Group g3
        // NumMembers = 2
        mem1.joinGroup(s.getGroup("Testing"), ldt);
        mem2.joinGroup(s.getGroup("Testing"), ldt);

        // Group g4
        // NumMembers = 1
        mem1.joinGroup(s.getGroup("Game Development"), ldt);

        ArrayList<Group> expectedGroups = new ArrayList<Group>();
        expectedGroups.add(g1);
        expectedGroups.add(g2);
        expectedGroups.add(g3);
        expectedGroups.add(g4);

        ArrayList<Group> actualGroups = (ArrayList<Group>) s.getPopularGroups(5);

        assertEquals(expectedGroups.toString(), actualGroups.toString());
    }

    /**
	 * This method should test that a SiteManager returns a list of the n groups
	 * who have the most activeness (questions asked + answers posted), 
	 * sorted on activeness, highest first.
	 * 
	 */
    @SuppressWarnings("unused")
    @Test
    void testGetActiveGroups() {
        LocalDateTime ldt = LocalDateTime.now();

        SiteManager s = new SiteManager();

        Group g1 = new Group("Software", "This is a description", ldt);
        Group g2 = new Group("Hardware", "This is a description", ldt);
        Group g3 = new Group("Testing", "This is a description", ldt);

        s.addGroup("Software", "This is a description", ldt);
        s.addGroup("Hardware", "This is a description", ldt);
        s.addGroup("Testing", "This is a description", ldt);

        Question q1 = new Question("title1", "asking q1", ldt);
        Question q2 = new Question("title2", "asking q2", ldt);
        Question q3 = new Question("title3", "asking q3", ldt);
        Question q4 = new Question("title4", "asking q1", ldt);
        Question q5 = new Question("title5", "asking q2", ldt);
        Question q6 = new Question("title6", "asking q3", ldt);
        Question q7 = new Question("title7", "asking q1", ldt);
        Question q8 = new Question("title8", "asking q2", ldt);
        Question q9 = new Question("title9", "asking q3", ldt);

        // Group g1
        // Activeness = 4
        s.getGroup("Software").addQuestion(q1);
        s.getGroup("Software").addQuestion(q2);
        s.getGroup("Software").addQuestion(q3);
        s.getGroup("Software").addQuestion(q4);
        
        // Group g2
        // Activeness = 3
        s.getGroup("Hardware").addQuestion(q5);
        s.getGroup("Hardware").addQuestion(q6);
        s.getGroup("Hardware").addQuestion(q7);
        
        // Group g3
        // Activeness = 1
        s.getGroup("Testing").addQuestion(q8);

        ArrayList<Group> actualGroups = (ArrayList<Group>) s.getActiveGroups(2);

        ArrayList<Group> expectedGroups = new ArrayList<Group>();
        expectedGroups.add(g1);
        expectedGroups.add(g2);

        assertEquals(expectedGroups.toString(), actualGroups.toString());

    }
    
    /**
	 * This method should test that a SiteManager returns a list of all groups, 
	 * sorted on activeness, highest first.
	 * 
	 * The parameter given to getActiveGroups(int n) is greater than the total
	 * number of groups in the list. This should not crash.
	 * 
	 */
    @SuppressWarnings("unused")
    @Test
    void testGetActiveGroupsNGreaterThanTotalGroups() {
        LocalDateTime ldt = LocalDateTime.now();

        SiteManager s = new SiteManager();

        Group g1 = new Group("Software", "This is a description", ldt);
        Group g2 = new Group("Hardware", "This is a description", ldt);
        Group g3 = new Group("Testing", "This is a description", ldt);

        s.addGroup("Software", "This is a description", ldt);
        s.addGroup("Hardware", "This is a description", ldt);
        s.addGroup("Testing", "This is a description", ldt);

        Question q1 = new Question("title1", "asking q1", ldt);
        Question q2 = new Question("title2", "asking q2", ldt);
        Question q3 = new Question("title3", "asking q3", ldt);
        Question q4 = new Question("title4", "asking q1", ldt);
        Question q5 = new Question("title5", "asking q2", ldt);
        Question q6 = new Question("title6", "asking q3", ldt);
        Question q7 = new Question("title7", "asking q1", ldt);
        Question q8 = new Question("title8", "asking q2", ldt);
        Question q9 = new Question("title9", "asking q3", ldt);

        // Group g1
        // Activeness = 4
        s.getGroup("Software").addQuestion(q1);
        s.getGroup("Software").addQuestion(q2);
        s.getGroup("Software").addQuestion(q3);
        s.getGroup("Software").addQuestion(q4);
        
        // Group g2
        // Activeness = 3
        s.getGroup("Hardware").addQuestion(q5);
        s.getGroup("Hardware").addQuestion(q6);
        s.getGroup("Hardware").addQuestion(q7);
        
        // Group g3
        // Activeness = 1
        s.getGroup("Testing").addQuestion(q8);

        ArrayList<Group> actualGroups = (ArrayList<Group>) s.getActiveGroups(4);

        ArrayList<Group> expectedGroups = new ArrayList<Group>();
        expectedGroups.add(g1);
        expectedGroups.add(g2);
        expectedGroups.add(g3);

        assertEquals(expectedGroups.toString(), actualGroups.toString());

    }

    /**
	 * This method should test that a SiteManager returns a list of the n members
	 * who have the most activeness (questions asked + answers posted), 
	 * sorted on activeness, highest first.
	 * 
	 */
    @SuppressWarnings("unused")
    @Test
    void testGetActiveMember() {
        LocalDateTime ldt = LocalDateTime.now();

        SiteManager s = new SiteManager();

        s.addGroup("Software", "This is a description", ldt);
        s.addGroup("Hardware", "This is a description", ldt);

        Member mem1 = new Member("John", "Doe", "johndoe", "johndoe@gmail.com", ldt);
        Member mem2 = new Member("Jane", "Doe", "janedoe", "janedoe@gmail.com", ldt);
        Member mem3 = new Member("Jason", "Doe", "jasondoe", "jasondoe@gmail.com", ldt);
        Member mem4 = new Member("Jenny", "Doe", "jennydoe", "jennydoe@gmail.com", ldt);

        Question q1 = new Question("title1", "asking q1", ldt);
        Question q2 = new Question("title2", "asking q2", ldt);
        Question q3 = new Question("title3", "asking q3", ldt);
        Question q4 = new Question("title4", "asking q1", ldt);

        Answer a1 = new Answer(q1,"answer1", ldt);
        Answer a2 = new Answer(q1,"answer2", ldt);

        // Add 4 members to SiteManager
        s.addMember("John", "Doe", "johndoe", "johndoe@gmail.com", ldt);
        s.addMember("Jane", "Doe", "janedoe", "janedoe@gmail.com", ldt);
        s.addMember("Jason", "Doe", "jasondoe", "jasondoe@gmail.com", ldt);
        s.addMember("Jenny", "Doe", "jennydoe", "jennydoe@gmail.com", ldt);

        // Members join various groups
        s.getMember("johndoe@gmail.com").joinGroup(s.getGroup("Software"), ldt);
        s.getMember("johndoe@gmail.com").joinGroup(s.getGroup("Hardware"), ldt);
        
        s.getMember("janedoe@gmail.com").joinGroup(s.getGroup("Software"), ldt);
        
        s.getMember("jasondoe@gmail.com").joinGroup(s.getGroup("Software"), ldt);
        
        s.getMember("jennydoe@gmail.com").joinGroup(s.getGroup("Software"), ldt);

        
        // Members post questions and answers
        
        // John Doe asks 3 questions
        // Activeness = 3
        s.getMember("johndoe@gmail.com").addQuestion(s.getGroup("Software"), q1, ldt);
        s.getMember("johndoe@gmail.com").addQuestion(s.getGroup("Hardware"), q2, ldt);
        s.getMember("johndoe@gmail.com").addQuestion(s.getGroup("Hardware"), q3, ldt);
        
        // Jane Doe asks 1 question and posts 1 answer
        // Activeness = 2
        s.getMember("janedoe@gmail.com").addQuestion(s.getGroup("Software"), q4, ldt);
        s.getMember("janedoe@gmail.com").addAnswer(s.getGroup("Software"), a1.getQuestion(), a1, ldt);
        
        // Jason Doe asks posts 1 answer
        // Activeness = 1
        s.getMember("jasondoe@gmail.com").addAnswer(s.getGroup("Software"), a2.getQuestion(), a2, ldt);

        // Jenny Doe asks 0 questions and posts 0 answers
        // Activeness = 0
        
        ArrayList<Member> actualMembers = (ArrayList<Member>) s.getActiveMembers(2);

        ArrayList<Member> expectedMembers = new ArrayList<Member>();
        expectedMembers.add(mem1);
        expectedMembers.add(mem2);

        assertEquals(expectedMembers.toString(), actualMembers.toString());
    }
    
    /**
	 * This method should test that a SiteManager returns a list of all members, 
	 * sorted on activeness, highest first.
	 * 
	 * The parameter given to getActiveMembers(int n) is greater than the total
	 * number of members in the list. This should not crash.
	 * 
	 */
    @SuppressWarnings("unused")
    @Test
    void testGetActiveMemberNGreaterThanTotalMembers() {
        LocalDateTime ldt = LocalDateTime.now();

        SiteManager s = new SiteManager();

        s.addGroup("Software", "This is a description", ldt);
        s.addGroup("Hardware", "This is a description", ldt);

        Member mem1 = new Member("John", "Doe", "johndoe", "johndoe@gmail.com", ldt);
        Member mem2 = new Member("Jane", "Doe", "janedoe", "janedoe@gmail.com", ldt);
        Member mem3 = new Member("Jason", "Doe", "jasondoe", "jasondoe@gmail.com", ldt);
        Member mem4 = new Member("Jenny", "Doe", "jennydoe", "jennydoe@gmail.com", ldt);

        Question q1 = new Question("title1", "asking q1", ldt);
        Question q2 = new Question("title2", "asking q2", ldt);
        Question q3 = new Question("title3", "asking q3", ldt);
        Question q4 = new Question("title4", "asking q1", ldt);

        Answer a1 = new Answer(q1,"answer1", ldt);
        Answer a2 = new Answer(q1,"answer2", ldt);

        // Add 4 members to SiteManager
        s.addMember("John", "Doe", "johndoe", "johndoe@gmail.com", ldt);
        s.addMember("Jane", "Doe", "janedoe", "janedoe@gmail.com", ldt);
        s.addMember("Jason", "Doe", "jasondoe", "jasondoe@gmail.com", ldt);
        s.addMember("Jenny", "Doe", "jennydoe", "jennydoe@gmail.com", ldt);

        // Members join various groups
        s.getMember("johndoe@gmail.com").joinGroup(s.getGroup("Software"), ldt);
        s.getMember("johndoe@gmail.com").joinGroup(s.getGroup("Hardware"), ldt);
        
        s.getMember("janedoe@gmail.com").joinGroup(s.getGroup("Software"), ldt);
        
        s.getMember("jasondoe@gmail.com").joinGroup(s.getGroup("Software"), ldt);
        
        s.getMember("jennydoe@gmail.com").joinGroup(s.getGroup("Software"), ldt);

        
        // Members post questions and answers
        
        // John Doe asks 3 questions
        // Activeness = 3
        s.getMember("johndoe@gmail.com").addQuestion(s.getGroup("Software"), q1, ldt);
        s.getMember("johndoe@gmail.com").addQuestion(s.getGroup("Hardware"), q2, ldt);
        s.getMember("johndoe@gmail.com").addQuestion(s.getGroup("Hardware"), q3, ldt);
        
        // Jane Doe asks 1 question and posts 1 answer
        // Activeness = 2
        s.getMember("janedoe@gmail.com").addQuestion(s.getGroup("Software"), q4, ldt);
        s.getMember("janedoe@gmail.com").addAnswer(s.getGroup("Software"), a1.getQuestion(), a1, ldt);
        
        // Jason Doe asks posts 1 answer
        // Activeness = 1
        s.getMember("jasondoe@gmail.com").addAnswer(s.getGroup("Software"), a2.getQuestion(), a2, ldt);

        // Jenny Doe asks 0 questions and posts 0 answers
        // Activeness = 0
        
        ArrayList<Member> actualMembers = (ArrayList<Member>) s.getActiveMembers(5);

        ArrayList<Member> expectedMembers = new ArrayList<Member>();
        expectedMembers.add(mem1);
        expectedMembers.add(mem2);
        expectedMembers.add(mem3);
        expectedMembers.add(mem4);

        assertEquals(expectedMembers.toString(), actualMembers.toString());
    }
    
    
    /**
	 * This method should test that a site manager returns a list of all questions 
	 * asked, sorted on the number of points, highest first.
	 * 
	 */
    @Test
    void testGetQuestionsByPoints() {
    	LocalDateTime time = LocalDateTime.parse("2018-07-01T08:00");
		Group g = new Group("This is a test Title", "This is a test Description", time);
		Group g2 = new Group("This is a test Title2", "This is a test Description2", time);
		SiteManager s = new SiteManager();
		s.addGroup(g);
		s.addGroup(g2);
		
		Question q1 = new Question("title1", "asking q1", time);
		Question q2 = new Question("title2", "asking q2", time);
		Question q3 = new Question("title3", "asking q3", time);
		Question q4 = new Question("title4", "asking q4", time);

		g.addQuestion(q1);
		g.addQuestion(q2);
		
		g2.addQuestion(q3);
		g2.addQuestion(q4);
		
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
		expectedQuestions.add(q4);

		List<Question> actualQuestions = (ArrayList<Question>) s.getQuestionsByPoints();

		assertEquals(actualQuestions, expectedQuestions);
    }
    
    /**
	 * This method should test that a site manager returns a list of n questions 
	 * asked across all groups with the most points, sorted on the 
	 * number of points, highest first.
	 * 
	 */
    @Test
    void testGetQuestionsByPointsOverloaded() {
    	LocalDateTime time = LocalDateTime.parse("2018-07-01T08:00");
		Group g = new Group("This is a test Title", "This is a test Description", time);
		Group g2 = new Group("This is a test Title2", "This is a test Description2", time);
		SiteManager s = new SiteManager();
		s.addGroup(g);
		s.addGroup(g2);
		
		Question q1 = new Question("title1", "asking q1", time);
		Question q2 = new Question("title2", "asking q2", time);
		Question q3 = new Question("title3", "asking q3", time);
		Question q4 = new Question("title4", "asking q4", time);

		g.addQuestion(q1);
		g.addQuestion(q2);
		
		g2.addQuestion(q3);
		g2.addQuestion(q4);
		
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

		List<Question> actualQuestions = (ArrayList<Question>) s.getQuestionsByPoints(3);

		assertEquals(actualQuestions, expectedQuestions);
    }
    
    /**
	 * This method should test that a site manager returns a list of n questions 
	 * asked across all groups with the most points, sorted on the 
	 * number of points, highest first.
	 * 
	 * The parameter given to getQuestionsByPoints(int n) is greater than the total
	 * number of questions. This should not crash.
	 * 
	 */
    @Test
    void testGetQuestionsByPointsOverloadedNGreaterThanTotalQuestions() {
    	LocalDateTime time = LocalDateTime.parse("2018-07-01T08:00");
		Group g = new Group("This is a test Title", "This is a test Description", time);
		Group g2 = new Group("This is a test Title2", "This is a test Description2", time);
		SiteManager s = new SiteManager();
		s.addGroup(g);
		s.addGroup(g2);
		
		Question q1 = new Question("title1", "asking q1", time);
		Question q2 = new Question("title2", "asking q2", time);
		Question q3 = new Question("title3", "asking q3", time);
		Question q4 = new Question("title4", "asking q4", time);

		g.addQuestion(q1);
		g.addQuestion(q2);
		
		g2.addQuestion(q3);
		g2.addQuestion(q4);
		
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
		expectedQuestions.add(q4);

		List<Question> actualQuestions = (ArrayList<Question>) s.getQuestionsByPoints(6);

		assertEquals(actualQuestions, expectedQuestions);
    }
}
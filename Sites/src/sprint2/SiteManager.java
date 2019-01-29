package sprint2;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.Serializable;

public class SiteManager implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<Member> members = new ArrayList<Member>();
    private List<Group> groups = new ArrayList<Group>();

    public SiteManager() {

    }

    /**
     * Adds a new Member provided they don't already exist returning whether
     * successful or not.
     * 
     * @param String        firstName
     * @param String        lastName
     * @param String        screenName
     * @param String        emailAddress
     * @param LocalDateTime dateCreated
     * @return boolean b
     */
    public boolean addMember(String firstName, String lastName, String screenName, String emailAddress,
            LocalDateTime dateCreated) {
        Member mem = new Member(firstName, lastName, screenName, emailAddress, dateCreated);

        if(this.getMember(emailAddress) == null) {
	        members.add(mem);
	        return true;
        }
        else
        	return false;
    }
    
    public boolean addMember(Member mem) {
      if(this.getMember(mem.getEmailAddress()) == null) {
	        members.add(mem);
	        return true;
        }
        else
        	return false;
    }

    /**
     * Returns the Member corresponding to this emailAddress if they exist.
     * 
     * @return Member
     */

    public Member getMember(String emailAddress) {
        for (Member m : members) {
            if (m.getEmailAddress().equals(emailAddress))
                return m;
        }
        return null;
    }

    /**
     * Returns a list of all Members, sorted by last name, then first name.
     * 
     * @return List<Member>
     */
    public List<Member> getMembers() {
        Collections.sort(members, new MemberNameComparator());
        return members;
    }

    /**
     * Returns a list of all Members where text (partially) matches any of
     * firstName, lastName, screenName, emailAddress across all Members, , sorted by
     * last name, then first name.
     * 
     * @param String text
     * @return List<Member>
     */
    public List<Member> getMembers(String text) {
        ArrayList<Member> finalMembers = new ArrayList<Member>();
        for (Member m : members) {
            if (m.getFirstName().contains(text) || m.getLastName().contains(text) || m.getEmailAddress().contains(text)
                    || m.getScreenName().contains(text))
                finalMembers.add(m);
        }
        Collections.sort(finalMembers, new MemberNameComparator());
        return finalMembers;
    }

    /**
     * boolean - Adds a new Group provided it doesn't already exist returning
     * whether successful or not.
     * 
     * @param String        title
     * @param String        description
     * @param LocalDateTime dateCreated
     * 
     * @return boolean b
     */
    public boolean addGroup(String title, String description, LocalDateTime dateCreated) {
        Group g = new Group(title, description, dateCreated);
 	
        if(this.getGroup(title) == null) {
	        groups.add(g);
	        return true;
        }
        else 
        	return false;
    }
    
    public boolean addGroup(Group g) {
        if(this.getGroup(g.getTitle()) == null) {
	        groups.add(g);
	        return true;
        }
        else 
        	return false;
    }

    /**
     * Returns the Group corresponding to this title if it exists.
     * 
     */
    public Group getGroup(String title) {
        for (Group g : groups) {
            if (g.getTitle().equals(title))
                return g;
        }
        return null;
    }

    /**
     * Returns a list of all Groups, sorted by title.
     * 
     * @return List<Group>
     */
    public List<Group> getGroups() {
        Collections.sort(groups, new GroupTitleComparator());
        return groups;
    }

    /**
     * Returns a list of all Groups where text (partially) matches any of title or
     * description across all Groups, sorted by title.
     * 
     * @param String text
     * @return List<Group>
     */
    public List<Group> getGroups(String text) {
        ArrayList<Group> finalGroups = new ArrayList<Group>();
        for (Group g : groups) {
            if (g.getTitle().contains(text) || g.getDescription().contains(text))
                finalGroups.add(g);
        }
        Collections.sort(finalGroups, new GroupTitleComparator());
        return finalGroups;
    }

    /**
     * Returns a list of n Groups that have the most members, sorted on the number
     * of members.
     * 
     * @param int n
     * @return List<Group>
     */
    public List<Group> getPopularGroups(int n) {
        int nChecked = n >= groups.size() ? groups.size() : n;
        Collections.sort(groups, new GroupNumMembersComparator());
        Collections.reverse(groups);
        return getListNumOfGroups(groups, nChecked);
    }

    /**
     * Returns a list of n Groups that have the most questions and answers combined
     * (activeness), sorted descending on activeness.
     * 
     * @param int n
     * @return List<Group>
     */
    public List<Group> getActiveGroups(int n) {
        int nChecked = n >= groups.size() ? groups.size() : n;
        Collections.sort(groups, new GroupOverallActiveComparator());
        Collections.reverse(groups);
        return getListNumOfGroups(groups, nChecked);
    }

    /**
     * Returns a list of n Members that have the most questions and answers combined
     * (activeness) across all groups they are a member of, sorted descending on
     * activeness.
     * 
     * @param int n
     * @return List<Member>
     */
    public List<Member> getActiveMembers(int n) {
        ArrayList<Member> finalMembers = new ArrayList<Member>();
        Collections.sort(members, new MemberOverallActiveComparator());
        Collections.reverse(members);
        int nChecked = n >= members.size() ? members.size() : n;
        for (int i = 0; i < nChecked; i++) {
            finalMembers.add(members.get(i));
        }
        return finalMembers;
    }
    
    /**
     * Returns an array list of groups with only the first n elements of
     * the input array list.
     * 
     * @return List<Group>
     */
    protected List<Group> getListNumOfGroups(List<Group> g, int n) {
    	List<Group> finalGroups = new ArrayList<Group>();
    	for (int i = 0; i < n; i++) {
            finalGroups.add(g.get(i));
        }
        return finalGroups;
    }
    
    /**
    * Returns a list of all questions, sorted by points.
    * 
    * @return List<Group>
    */
   public List<Question> getQuestionsByPoints() {
	   List<Question> finalQuestions = new ArrayList<Question>();
	   for(Group g: groups)
		   for(Question q: g.getQuestions())
		   finalQuestions.add(q);
       Collections.sort(finalQuestions, new PostPointsComparator());
       Collections.reverse(finalQuestions);
       return finalQuestions;
   }
   
   /**
    * Returns a list of all questions, sorted by points.
    * 
    * @return List<Group>
    */
   public List<Question> getQuestionsByPoints(int n) {
	   List<Question> allQuestions = getQuestionsByPoints();
	   List<Question> finalQuestions = new ArrayList<Question>();
	   int nChecked = n >= allQuestions.size() ? allQuestions.size() : n;
	   for(int i = 0; i < nChecked; i++)
		   finalQuestions.add(allQuestions.get(i));
       return finalQuestions;
   }
    
}

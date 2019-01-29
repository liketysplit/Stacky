package sprint2;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.HashMap;
import java.io.Serializable;

public class Member implements Serializable {
    private static final long serialVersionUID = 1L;
    private LocalDateTime dateCreated;
    private String firstName;
    private String lastName;
    private String screenName;
    private String emailAddress;
    private HashMap<String, Membership> memberships = new HashMap<String, Membership>();

    /**
     * Creates a member with the default values of firstName, lastName, screenName,
     * and emailAddress
     * 
     * @param firstName
     * @param lastName
     * @param screenName
     * @param emailAddress
     * @param dateCreated
     */
    public Member(String firstName, String lastName, String screenName, String emailAddress,
            LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
        this.firstName = firstName;
        this.lastName = lastName;
        this.screenName = screenName;
        this.emailAddress = emailAddress;
    }

    /**
     * Returns the emailAddress of the Member
     * 
     * @return
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Returns the dateCreated of the Member
     * 
     * @return
     */
    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    /**
     * Returns the firstName of the Member
     * 
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * 
     * Returns the lastName of the Member
     * 
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns the screenName of the Member
     * 
     * @return
     */
    public String getScreenName() {
        return screenName;
    }

    // Group Getters

    /**
     * Creates a temporary List of Groups Iterates through Memberships and adds each
     * Group the the groups List
     *
     * @return List<Group>
     */
    public List<Group> getGroups() {
        List<Group> groups = new ArrayList<Group>();
        Set<String> keys = memberships.keySet();
        for (String key: keys) {
        	groups.add(memberships.get(key).getGroup());
        }
        groups.sort(new GroupTitleComparator());
        return groups;

    }

    /**
     * Returns the size of the memberships ArrayList
     *
     * @return
     */
    public int getNumGroups() {
        return memberships.size();
    }

    /**
     * Compares Group g with all groups in memberships Group.title is comparator
     *
     * @param {Group} g
     * @return LocalDateTime || null
     */
    public LocalDateTime getDateJoined(Group g) {
    	Membership temp = memberships.get(g.getTitle());
    	if(temp == null)
    		return null;
        return temp.getDateJoined();
    }

    /**
     * Compares groupId with title of of all groups
     *
     * @param groupId title of Group
     * @return m.getGroup
     */
    public Group getGroup(String groupId) {
    	Membership temp = memberships.get(groupId);
    	if(temp == null)
    		return null;
        return temp.getGroup();
    }

    /**
     * Adds this member to group specified
     *
     * @param group
     * @param dateJoined
     */
    public void joinGroup(Group group, LocalDateTime dateJoined) {
        Membership member = new Membership(group, this, dateJoined);
        group.addToMemberships(member);
        memberships.put(group.getTitle(), member);
    }

    /**
     * 
     * Returns all memberships this member has in their memberships map
     * 
     * @param group
     * @return
     */
    protected List<Membership> getMemberships() {
        return new ArrayList<Membership>(memberships.values());
    }

    /**
     * 
     * Returns all questions asked by this member in the group passed in as
     * parameters
     * 
     * @param group
     * @return
     */
    public List<Question> getQuestions(Group group) {
    	Membership temp = memberships.get(group.getTitle());
    	if(temp == null)
    		return null;
        List<Question> myQuestions = temp.getQuestions();
        myQuestions.sort(new PostDateComparator());
        Collections.reverse(myQuestions);
        return myQuestions;
    }

    /**
     * Returns all answers answered by this member in the group passed in as
     * parameters
     * 
     * @param group
     * @return
     */
    public List<Answer> getAnswers(Group group) {
    	Membership temp = memberships.get(group.getTitle());
    	if(temp == null)
    		return null;
        List<Answer> myAnswers = temp.getAnswers();
        myAnswers.sort(new PostDateComparator());
        Collections.reverse(myAnswers);
        return myAnswers;
    }

    /**
     * Adds the question to the group by this member and records the date the
     * question was asked.
     * 
     * @param group
     * @param question
     * @param date
     */
    public void addQuestion(Group group, Question question, LocalDateTime date) {
    	Membership temp = memberships.get(group.getTitle());
    	if(temp == null)
    		return;
    	question.setMembership(temp);
        temp.addQuestion(question);
        group.addQuestion(question);
    }

    /**
     * Adds this member's answer to this question which is in this group and records
     * the date answered.
     * 
     * @param group
     * @param question
     * @param answer
     * @param date
     */
    public void addAnswer(Group group, Question question, Answer answer, LocalDateTime date) {
    	Group realGroup = answer.getQuestion().getGroup();
    	if(realGroup != null)
    		if(!realGroup.getTitle().equals(group.getTitle()))
    			return;
    	Membership temp = memberships.get(group.getTitle());
    	if(temp == null)
    		return;
    	answer.setMembership(temp);
        temp.addAnswer(answer);
        question.addAnswer(answer);
        group.addAnswer(answer);
    }

    /**
     * Helper method used for testing
     * 
     * @param group
     * @return
     */
    protected Membership getMembership(Group group) {
    	Membership temp = memberships.get(group.getTitle());
    	if(temp == null)
    		return null;
        return temp;
    }

    /**
     * Returns a list of the n Groups that the member is most active in, sorted on
     * title. Measure most active by the total questions posted and answers
     * provided, in total. If there are not n groups, then return the groups that
     * exist.
     * 
     * @param n number of items to return
     * @return List<Group>
     */
    public List<Group> getGroups(int n) {
        List<Group> groups = new ArrayList<Group>();
        ArrayList<Membership> memberships = (ArrayList<Membership>) this.getMemberships();
        int nChecked = n >= memberships.size() ? memberships.size() : n;
        // Sort "Group Activeness" by sorting the membership tied to each group
        Collections.sort(memberships, new MembershipActiveComparator());
        Collections.reverse(memberships);

        // Cut array to length specified BEFORE sorting on title
        ArrayList<Membership> tempMemberships = new ArrayList<Membership>();
        for (int i = 0; i < nChecked; i++) {
            tempMemberships.add(memberships.get(i));
        }

        // add to Group ArrayList so you can sort on title and return
        for (Membership m : tempMemberships) {
            groups.add(m.getGroup());
        }

        Collections.sort(groups, new GroupTitleComparator());

        return groups;
    }

    /**
     * Returns the n most recent questions asked by this member in this group sorted
     * on the order they were asked, most recent first.
     * 
     * @param n number of items to return
     * @return shallowCopy List<Group>
     */
    public List<Question> getQuestions(Group group, int n) {
        List<Question> questions = this.getQuestions(group);
    	if(questions == null)
    		return null;
        int nChecked = n >= questions.size() ? questions.size() : n;

        // We want the most recent question first so reverse the array with
        // Collections.reverse
        // PostDateComparator returns questions in asc we want desc
        Collections.sort(questions, new PostDateComparator());
        Collections.reverse(questions);

        return getListNumOfQuestions(questions, nChecked);
    }

    /**
     * Returns the n most recent questions asked by this member in this group sorted
     * on the order they were asked, most recent first.
     * 
     * @param n number of items to return
     * @return shallowCopy List<Group>
     */
    public List<Answer> getAnswers(Group group, int n) {
        List<Answer> answers = this.getAnswers(group);
    	if(answers == null)
    		return null;
        int nChecked = n >= answers.size() ? answers.size() : n;

        // We want the most recent answers first so reverse the array with
        // Collections.reverse
        // PostDateComparator returns posts in asc we want desc
        Collections.sort(answers, new PostDateComparator());
        Collections.reverse(answers);

        return getListNumOfAnswers(answers, nChecked);
    }

    /**
     * Up-votes the post passed in
     * 
     * @param Post p
     */
    public void upVote(Post p) {
        p.upvote();
    }

    /**
     * Down-votes the post passed in
     * 
     * @param Post p
     */
    public void downVote(Post p) {
        p.downvote();
    }

    /**
     * Returns a list of n length of questions for this member sorted by points,
     * highest to lowest.
     * 
     * @param int n
     * @return List<Question>
     */
    public List<Question> getQuestionsByPoints(int n) {
        List<Question> questions = getQuestionsByPoints();
        int nChecked = n >= questions.size() ? questions.size() : n;

        return getListNumOfQuestions(questions, nChecked);
    }
    
    /**
     * Returns a list of all of questions for this member sorted by points,
     * highest to lowest.
     * 
     * @param int n
     * @return List<Question>
     */
    public List<Question> getQuestionsByPoints() {
        List<Membership> memberships = (ArrayList<Membership>) this.getMemberships();
        List<Question> questions = new ArrayList<Question>();

        for (Membership m : memberships) 
        	for(Question q: m.getQuestions())
        		questions.add(q);
        // We want the most recent questions first so reverse the array with
        // Collections.reverse
        // PostPointsComparator returns questions in asc we want desc
        Collections.sort(questions, new PostPointsComparator());
        Collections.reverse(questions);

        return questions;
    }

    /**
     * Returns a list of n length of answers by question for this member sorted by
     * points, highest to lowest.
     * 
     * @param          int n
     * @param Question q
     * @return List<Question>
     */
    public List<Answer> getAnswersByPoints(int n, Question q) {

        List<Answer> answers = getAnswersByPoints(q);
        int nChecked = n >= answers.size() ? answers.size() : n;

        return getListNumOfAnswers(answers, nChecked);
    }

    /**
     * Returns a list of all answers by question for this member sorted by points,
     * highest to lowest.
     * 
     * @param Question q
     * @return List<Question>
     */
    public List<Answer> getAnswersByPoints(Question q) {
    	List<Answer> finalAnswers = new ArrayList<Answer>();
        List<Answer> answers = q.getAnswers();
        
        for(Answer a: answers) {
        	if(a.getAuthor().equals(this))
        		finalAnswers.add(a);
        }
        // We want the most recent questions first so reverse the array with
        // Collections.reverse
        // PostPointsComparator returns questions in asc we want desc
        Collections.sort(finalAnswers, new PostPointsComparator());
        Collections.reverse(finalAnswers);

        return finalAnswers;
    }
    
    /**
     * Returns an array list of answers with only the first n elements of
     * the input array list.
     * 
     * @return List<Answer>
     */
    protected List<Answer> getListNumOfAnswers(List<Answer> a, int n) {
    	List<Answer> finalAnswers = new ArrayList<Answer>();
    	for (int i = 0; i < n; i++) {
            finalAnswers.add(a.get(i));
        }
        return finalAnswers;
    }
    
    /**
     * Returns an array list of questions with only the first n elements of
     * the input array list.
     * 
     * @return List<Question>
     */
    protected List<Question> getListNumOfQuestions(List<Question> q, int n) {
    	List<Question> finalQuestions = new ArrayList<Question>();
    	for (int i = 0; i < n; i++) {
            finalQuestions.add(q.get(i));
        }
        return finalQuestions;
    }

    /**
     * Sets the correct flag for the answer specified if the member requesting this
     * method is the owner of the question tied to the answer.
     * 
     * @param a Answer
     * @param b boolean
     */

    protected void markAsCorrect(Answer a, Boolean b) {
        if (a.getQuestion().getAuthor().equals(this)) {
           if(b && a.getQuestion().hasCorrectAnswer()) 
        	   return;
           else		   
        	   this.setFlag(a, "correct", b);
        }
    }

    /**
     * Sets the inappropriate flag for the post specified.
     * 
     * @param a Answer
     * @param b boolean
     */
    protected void markAsInappropriate(Post p, Boolean b) {
        this.setFlag(p, "inappropriate", b);
    }

    /**
     * Sets the irrelevant flag for the post specified.
     * 
     * @param a Answer
     * @param b boolean
     */
    protected void markAsIrrelevant(Post p, Boolean b) {
        this.setFlag(p, "irrelevant", b);
    }

    /**
     * Helper method for the above flag methods
     * 
     * @param Post    p
     * @param String  s
     * @param Boolean b
     */
    private void setFlag(Post p, String s, Boolean b) {
        p.setFlag(s, b);
    }

    /**
     * Provides useful information about this member, neatly formatted.
     */
    public String toString() {
        return ("Screen Name: " + screenName + ", First Name: " + firstName + ", Last Name: " + lastName + ", Email: "
                + emailAddress + ", Date Created: " + dateCreated);
    }
}

class MemberOverallActiveComparator implements Comparator<Member> {
    public int compare(Member m1, Member m2) {
        int m1Activeness = 0;
        int m2Activeness = 0;
        for (Membership mb : m1.getMemberships()) {
            m1Activeness += mb.getNumAnswers() + mb.getNumQuestions();
        }
        for (Membership mb : m2.getMemberships()) {
            m2Activeness += mb.getNumAnswers() + mb.getNumQuestions();
        }
        return m1Activeness - m2Activeness;
    }
}

class MemberNameComparator implements Comparator<Member> {
    public int compare(Member m1, Member m2) {
        // uses compareToIgnoreCase
        if ((m1.getLastName().compareToIgnoreCase(m2.getLastName())) == 0)
            return m1.getFirstName().compareTo(m2.getFirstName());
        return m1.getLastName().compareToIgnoreCase(m2.getLastName());
    }
}
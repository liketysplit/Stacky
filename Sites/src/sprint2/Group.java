package sprint2;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.HashMap;
import java.util.Set;
import java.util.Comparator;
import java.io.Serializable;

public class Group implements Serializable {

    private static final long serialVersionUID = 1L;
    private LocalDateTime dateCreated;
    private String title;
    private String description;
    private HashMap<String, Membership> memberships = new HashMap<String, Membership>();
    private ArrayList<Question> questions = new ArrayList<Question>();
    private ArrayList<Answer> answers = new ArrayList<Answer>();

    /**
     * Creates a group with the default values of title, description, and
     * dateCreated
     * 
     * @param title
     * @param description
     * @param dateCreated
     */
    public Group(String title, String description, LocalDateTime dateCreated) {
        this.title = title;
        this.description = description;
        this.dateCreated = dateCreated;
    }

    /**
     * Returns the date in which this Group was created
     * 
     * @return
     */
    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    /**
     * Returns the title of this Group
     * 
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the description of this Group
     * 
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the number of Members that have joined this Group
     * 
     * @return
     */
    public int getNumMembers() {
        return memberships.size();
    }

    /**
     * Returns the number of Answers that have been posted for this Group
     * 
     * @return
     */
    protected int getNumAnswers() {
        return answers.size();
    }

    /**
     * Returns the number of Questions that have been posted for this Group
     * 
     * @return
     */
    protected int getNumQuestions() {
        return questions.size();
    }

    /**
     * Returns a list of Members that have joined this Group
     * 
     * @return
     */
    public List<Member> getMembers() {
        ArrayList<Member> members = new ArrayList<Member>();
        Set<String> keys = memberships.keySet();
        for (String key: keys) {
        	members.add(memberships.get(key).getMember());
        }
        members.sort(new MemberNameComparator());
        return members;
    }

    /**
     * Returns a specific Member of this Group
     * 
     * @param emailAddress
     * @return
     */
    public Member getMember(String emailAddress) {
    	Membership temp = memberships.get(emailAddress);
    	if(temp == null)
    		return null;
        return temp.getMember();
    }

    /**
     * Adds a membership to the memberships array so that a group knows about its
     * members
     * 
     * @param membership
     */
    protected void addToMemberships(Membership membership) {
        memberships.put(membership.getMember().getEmailAddress(), membership);
    }

    /**
     * Returns a List of questions that have been added to this group
     * 
     * @return
     */
    public List<Question> getQuestions() {
    	List<Question> finalQuestions = new ArrayList<Question>(questions);
        finalQuestions.sort(new PostDateComparator());
        Collections.reverse(finalQuestions);
        return finalQuestions;
    }

    /**
     * Returns a List of answers that have been added to this group
     * 
     * @return
     */
    public List<Answer> getAnswers() {
    	List<Answer> finalAnswers = new ArrayList<Answer>(answers);
        finalAnswers.sort(new PostDateComparator());
        Collections.reverse(finalAnswers);
        return finalAnswers;
    }

    /**
     * Adds a Question to the List of questions in this Group
     * 
     * @param question
     */
    protected void addQuestion(Question question) {
        questions.add(question);
    }

    /**
     * Adds an Answer to the List of answers in this Group
     * 
     * @param answer
     */
    protected void addAnswer(Answer answer) {
        answers.add(answer);
    }

    /**
     * Returns a list of the n most active Members of this group, sorted by acivity
     * (questions plus answers), most active first.
     * 
     * @param n number of items to return
     * @return List<Member>
     */
    public List<Member> getActiveMembers(int n) {
        List<Member> members = this.getMembers();
        List<Membership> memberships = new ArrayList<Membership>();
        int nChecked = n >= members.size() ? members.size() : n;

        // Get Memberships for sort
        for (Member m : members) {
            memberships.add(m.getMembership(this));
        }

        // we want the most active memberships first
        Collections.sort(memberships, new MembershipActiveComparator());
        Collections.reverse(memberships);

        // Shorten list to n provided n does not exceed size of the array
        List<Member> finalMembers = new ArrayList<Member>();
        for (int i = 0; i < nChecked; i++) {
            finalMembers.add(memberships.get(i).getMember());
        }

        return finalMembers;
    }

    /**
     * Returns the n most recent questions asked in this group sorted on the order
     * they were asked, most recent first.
     * 
     * @param n number of items to return
     * @return List<Question>
     */
    public List<Question> getQuestions(int n) {
        List<Question> questions = this.getQuestions();
        int nChecked = n >= questions.size() ? questions.size() : n;
        // we want the most active members first
        Collections.sort(questions, new PostDateComparator());
        Collections.reverse(questions);

        return getListNumOfQuestions(questions, nChecked);
    }

    /**
     * Returns the n most recent answers in this group sorted on the order they were
     * provided, most recent first.
     * 
     * @param n number of items to return
     * @return List<Answer>
     */
    public List<Answer> getAnswers(int n) {
        List<Answer> answers = this.getAnswers();
        int nChecked = n >= answers.size() ? answers.size() : n;
        // we want the most active members first
        Collections.sort(answers, new PostDateComparator());
        Collections.reverse(answers);

        return getListNumOfAnswers(answers, nChecked);
    }

    /**
     * Returns a list of n length of questions for this member sorted by points,
     * highest to lowest.
     * 
     * @param int n
     * @return List<Question>
     */
    public List<Question> getQuestionsByPoints(int n) {
        List<Question> questions = (ArrayList<Question>) this.getQuestions();
        int nChecked = n >= questions.size() ? questions.size() : n;

        // We want the most recent questions first so reverse the array with
        // Collections.reverse
        // PostPointsComparator returns questions in asc we want desc
        Collections.sort(questions, new PostPointsComparator());
        Collections.reverse(questions);

        return getListNumOfQuestions(questions, nChecked);
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
        // Check to see if the question being asked for is a question in this group
        // otherwise return null
        if (!questions.contains(q))
            return null;

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
    	// Check to see if the question being asked for is a question in this group
        // otherwise return null
    	if (!questions.contains(q))
            return null;
    	
        List<Answer> answers = q.getAnswers();

        // We want the most recent questions first so reverse the array with
        // Collections.reverse
        // PostPointsComparator returns questions in asc we want desc
        Collections.sort(answers, new PostPointsComparator());
        Collections.reverse(answers);

        return answers;
    }
    
    /**
     * Returns an array list of answers with only the first n elements of
     * the input array list.
     * 
     * @return List<Group>
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
     * @return List<Group>
     */
    protected List<Question> getListNumOfQuestions(List<Question> q, int n) {
    	List<Question> finalQuestions = new ArrayList<Question>();
    	for (int i = 0; i < n; i++) {
            finalQuestions.add(q.get(i));
        }
        return finalQuestions;
    }

    /**
     * Provides useful information about this group, neatly formatted.
     * 
     */
    public String toString() {
        return ("Title: " + title + ", Description: " + description + ", Date Created: " + dateCreated);
    }
}

class GroupOverallActiveComparator implements Comparator<Group> {
    public int compare(Group g1, Group g2) {
        int g1Activeness = g1.getNumAnswers() + g1.getNumQuestions();
        int g2Activeness = g2.getNumAnswers() + g2.getNumQuestions();
        return g1Activeness - g2Activeness;
    }
}

class GroupTitleComparator implements Comparator<Group> {
    public int compare(Group g1, Group g2) {
        return g1.getTitle().compareTo(g2.getTitle());
    }
}

class GroupNumMembersComparator implements Comparator<Group> {
    // Sorts by ascending order
    public int compare(Group g1, Group g2) {
        return g1.getNumMembers() - g2.getNumMembers();
    }
}
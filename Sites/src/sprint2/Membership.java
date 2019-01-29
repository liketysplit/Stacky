package sprint2;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.io.Serializable;

public class Membership implements Serializable {

    private static final long serialVersionUID = 1L;
    private LocalDateTime dateJoined;
    private int points;
    private Group group;
    private Member member;
    private ArrayList<Question> questions = new ArrayList<Question>();
    private ArrayList<Answer> answers = new ArrayList<Answer>();

    /**
     * Creates a Membership with the default values of group, member, and dateJoined
     * 
     * @param group
     * @param member
     * @param dateJoined
     */
    protected Membership(Group group, Member member, LocalDateTime dateJoined) {
        this.group = group;
        this.member = member;
        this.dateJoined = dateJoined;
        // Set to 0 initially
        this.points = 0;
    }

    /**
     * Returns the group from Membership
     * 
     * @return
     */
    protected Group getGroup() {
        return group;
    }

    /**
     * Adds a question to the questions array that pertains to this member to this
     * group
     * 
     * @param question
     */
    protected void addQuestion(Question question) {
        questions.add(question);
    }

    /**
     * Adds a question to the questions array that pertains to this member to this
     * group Calls the group and adds the answer there as well
     * 
     * @param answer
     */
    protected void addAnswer(Answer answer) {
        answers.add(answer);
    }

    /**
     * Returns the dateJoined from Membership
     * 
     * @return
     */
    protected LocalDateTime getDateJoined() {
        return dateJoined;
    }

    /**
     * Returns the member from Membership
     * 
     * @return
     */
    protected Member getMember() {
        return member;
    }

    /**
     * Returns the list of questions from Membership
     * 
     * @return
     */
    protected ArrayList<Question> getQuestions() {
        ArrayList<Question> myQuestions = new ArrayList<Question>(questions);
    	return myQuestions;
    }

    /**
     * Returns the list of answers from Membership
     * 
     * @return
     */
    protected ArrayList<Answer> getAnswers() {
    	ArrayList<Answer> myAnswers = new ArrayList<Answer>(answers);
        return myAnswers;
    }

    /**
     * Returns the number of Questions this member posted in this group
     * 
     * @return
     */
    protected int getNumQuestions() {
        return questions.size();
    }

    /**
     * Returns the number of Answers this member posed in this group
     * 
     * @return
     */
    protected int getNumAnswers() {
        return answers.size();
    }

    /**
     * Provides useful information about this membership, neatly formatted.
     * 
     */
    public String toString() {
        return ("Member: " + member.getScreenName() + ", Group: " + group.getTitle() + ", Points: " + points
                + ", Date Joined: " + dateJoined);
    }
}

class MembershipActiveComparator implements Comparator<Membership> {
    public int compare(Membership m1, Membership m2) {
        return (m1.getNumAnswers() + m1.getNumQuestions()) - (m2.getNumAnswers() + m2.getNumQuestions());
    }
}

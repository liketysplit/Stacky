package sprint2;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.io.Serializable;

public abstract class Post implements Serializable {

    private static final long serialVersionUID = 1L;
    protected String text;
    protected LocalDateTime date;
    protected Membership membership;
    protected int points;
    protected HashMap<String, Boolean> flags = new HashMap<String, Boolean>();

    /**
     * Constructor of the class that implements Post, takes in a text and date
     * 
     * @param text
     * @param date
     */
    public Post(String text, LocalDateTime date) {
        this.text = text;
        this.date = date;
        this.points = 0;
        this.flags.put("correct",false);
        this.flags.put("inappropriate",false);
        this.flags.put("irrelevant",false);
    }

    /**
     * Returns the text(content) of the class that implements Post
     * 
     * @return
     */
    public String getText() {
        return text;
    }

    /**
     * Returns the date created of the class that implements Post
     * 
     * @return
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Sets the text of the class that implements Post
     * 
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Returns the member(Author) of the class that implements Post
     *
     * @return
     */
    public Member getAuthor() {
    	if(membership == null)
    		return null;
        return membership.getMember();
    }

    /**
     * Returns the group of the class that implements Post
     *
     * @return
     */
    public Group getGroup() {
    	if(membership == null)
    		return null;
        return membership.getGroup();
    }

    /**
     * Returns the membership of the class that implements Post
     *
     * @return
     */
    protected Membership getMembership() {
        return membership;
    }

    /**
     * Sets the membership of the class that implements Post
     *
     * @param membership
     */
    protected void setMembership(Membership membership) {
        this.membership = membership;
    }

    /**
     * Increments points by 1 for the Post
     *
     */
    public void upvote() {
        points++;
    }

    /**
     * Decrements points by 1 for the Post
     * 
     */
    public void downvote() {
        points--;
    }

    /**
     * Returns the number of points for this Post
     *
     * @return int
     */
    public int getPoints() {
        return this.points;
    }

    public boolean isCorrect() {
        return flags.get("correct");
    }

    /**
     * Returns whether to not this Post is marked as correct
     *
     * @return boolean
     */
    public boolean isInappropriate() {
        return flags.get("inappropriate");
    }

    /**
     * Returns whether to not this Post is marked as correct
     *
     * @return boolean
     */
    public boolean isIrrelevant() {
        return flags.get("irrelevant");
    }

    /**
     * Returns whether to not this Post is marked as correct
     *
     */
    protected void setFlag(String s, Boolean b) {
        flags.put(s, b);
    }

}

class PostDateComparator implements Comparator<Post> {
    public int compare(Post p1, Post p2) {
        return p1.getDate().compareTo(p2.getDate());
    }
}

class PostPointsComparator implements Comparator<Post> {
    public int compare(Post p1, Post p2) {
        return p1.getPoints() - p2.getPoints();
    }
}
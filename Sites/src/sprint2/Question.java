package sprint2;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.io.Serializable;

public class Question extends Post implements Serializable {

    private static final long serialVersionUID = 1L;
    private String title;
    private ArrayList<Answer> answers = new ArrayList<Answer>();

    /**
     * Constructor for the Question Class that takes in a value for title,
     * text(question), and a date(created)
     *
     * @param title
     * @param text
     * @param date
     */
    public Question(String title, String text, LocalDateTime date) {
        super(text, date);
        this.title = title;
    }

    /**
     * Returns the title of the Question Class
     * 
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the Question Class
     * 
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * Checks to see if the question has a correct answer
     * 
     * @param answer
     */
    public boolean hasCorrectAnswer() {
    	boolean flag = false;
    	for(Answer a: answers) {
    		if(a.isCorrect()) {
    			flag = true;
    			break;
    		}
    	}
    	return flag;
    }

    /**
     * Adds and Answer to the Question Class
     * 
     * @param answer
     */
    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    /**
     * Returns all of the answers provided to the Question Class for this question
     * 
     * @return
     */
    public List<Answer> getAnswers() {
    	List<Answer> finalAnswers = new ArrayList<Answer>(answers);
    	Collections.sort(finalAnswers, new PostDateComparator());
    	Collections.reverse(finalAnswers);
        return finalAnswers;
    }
    
    /**
     * Returns all of the answers provided for this question
     * sorted on points
     * 
     * @return
     */
    public List<Answer> getAnswersByPoints() {
    	List<Answer> finalAnswers = new ArrayList<Answer>(answers);
    	Collections.sort(finalAnswers, new PostPointsComparator());
    	Collections.reverse(finalAnswers);
        return finalAnswers;
    }
    
    /**
     * Returns the n answers with the most points for this question
     * sorted on points
     * 
     * @return
     */
    public List<Answer> getAnswersByPoints(int n) {
    	List<Answer> finalAnswers = new ArrayList<Answer>();
    	List<Answer> tempAnswers = getAnswersByPoints();
    	
    	int nChecked = n >= tempAnswers.size() ? tempAnswers.size() : n;
    	
    	for(int i = 0; i < nChecked; i++) {
    		finalAnswers.add(tempAnswers.get(i));
    	}
        return finalAnswers;
    }
    
    /**
     * Returns all of the answers provided for this question
     * sorted on points with the correct answer listed first
     * 
     * @return
     */
    public List<Answer> getAnswersByPointsAndCorrect() {
    	List<Answer> finalAnswers = new ArrayList<Answer>();
    	Collections.sort(answers, new PostPointsComparator());
    	Collections.reverse(answers);
    	
    	for(Answer a: answers) {
    		if(a.isCorrect()) {
    			finalAnswers.add(0, a);
    		}
    		else
    			finalAnswers.add(a);
    	}
        return finalAnswers;
    }
    
    /**
     * Returns list of n answers provided for this question
     * sorted on points with the correct answer listed first
     * 
     * @return
     */
    public List<Answer> getAnswersByPointsAndCorrect(int n) {
    	List<Answer> finalAnswers = new ArrayList<Answer>();
    	List<Answer> tempAnswers = getAnswersByPointsAndCorrect();
    	int nChecked = n >= tempAnswers.size() ? tempAnswers.size() : n;
    	for(int i = 0; i < nChecked; i++) {
    		finalAnswers.add(tempAnswers.get(i));
    	}
        return finalAnswers;
    }

    /**
     * Provides useful information about this question, neatly formatted.
     * 
     */
    public String toString() {
        Iterator<Entry<String, Boolean>> it = flags.entrySet().iterator();
        String toFlags = "";
        while (it.hasNext()) {
            Map.Entry<String, Boolean> pair = (Map.Entry<String, Boolean>) it.next();
            toFlags += "\n" + pair.getKey() + ": " + pair.getValue();
        }

        return ("Title: " + title + "\nText: " + this.getText() + "\nDate Created: " + this.getDate() + "\nPoints: "
                + this.getPoints() + toFlags);
    }

}
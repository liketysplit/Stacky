package sprint2;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.io.Serializable;

public class Answer extends Post implements Serializable {

    private static final long serialVersionUID = 1L;
    private Question question;

    /**
     * Constructor for the Answer Class that takes in a question, text(answerTest),
     * and a date(created)
     *
     * @param q
     * @param text
     * @param date
     */
    public Answer(Question q, String text, LocalDateTime date) {
        super(text, date);
        this.question = q;
    }

    /**
     * Return the question that this class is answering
     *
     * @return
     */
    public Question getQuestion() {
        return question;
    }

    /**
     * Provides useful information about this answer, neatly formatted.
     * 
     */
    public String toString() {
        Iterator<Entry<String, Boolean>> it = flags.entrySet().iterator();
        String toFlags = "";
        while (it.hasNext()) {
            Map.Entry<String, Boolean> pair = (Map.Entry<String, Boolean>) it.next();
            toFlags += "\n" + pair.getKey() + ": " + pair.getValue();
        }
        return ("Question Text: " + question.getText() + "\nText: " + this.getText() + "\nDate Created: "
                + this.getDate() + "\nPoints: " + this.getPoints() + toFlags);
    }
}
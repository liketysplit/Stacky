package sprint2;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DataCreator {
	
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new File("./Users.csv"));
		// skip 1st line
		sc.nextLine();
		
		SiteManager sm = new SiteManager();
		while (sc.hasNext()) {
			String line = sc.nextLine();
			List<String> items = Arrays.asList(line.split("\\s*,\\s*"));

			sm.addMember(new Member(items.get(1),items.get(2),items.get(3),items.get(4), LocalDateTime.now()));
		}
		
		
		String jsDesc = "JavaScript (often shortened to JS) is a lightweight, interpreted, object-oriented language with first-class functions, and is best known as the scripting language for Web pages, but it's used in many non-browser environments as well. It is a prototype-based, multi-paradigm scripting language that is dynamic, and supports object-oriented, imperative, and functional programming styles.";
		String pyDesc = "Python is an interpreted high-level programming language for general-purpose programming. Created by Guido van Rossum and first released in 1991, Python has a design philosophy that emphasizes code readability, notably using significant whitespace. It provides constructs that enable clear programming on both small and large scales.";
		String cDesc = "C is a general-purpose, imperative computer programming language, supporting structured programming, lexical variable scope and recursion, while a static type system prevents many unintended operations. By design, C provides constructs that map efficiently to typical machine instructions, and therefore it has found lasting use in applications that had formerly been coded in assembly language, including operating systems.";
		String cSharpDesc = "C# is a general-purpose, multi-paradigm programming language encompassing strong typing, imperative, declarative, functional, generic, object-oriented (class-based), and component-oriented programming disciplines. It was developed around 2000 by Microsoft within its .NET initiative and later approved as a standard by ECMA and ISO. C# is one of the programming languages designed for the Common Language Infrastructure";
		String phpDesc = "PHP (recursive acronym for PHP: Hypertext Preprocessor) is a widely-used open source general-purpose scripting language that is especially suited for web development and can be embedded into HTML.";
		
		Group cSharp = new Group("C#", cSharpDesc, LocalDateTime.now());
		Group php = new Group("PHP", phpDesc, LocalDateTime.now());
		Group javaScript = new Group("JavaScript", jsDesc, LocalDateTime.now());
		Group c = new Group("C", cDesc, LocalDateTime.now());
		Group python = new Group("Python", pyDesc, LocalDateTime.now());
		
		sm.addGroup(cSharp);
		sm.addGroup(php);
		sm.addGroup(javaScript);
		sm.addGroup(c);
		sm.addGroup(python);
		
		ArrayList<Member> members = (ArrayList<Member>) sm.getMembers();
		
		String text1 = "Quisque quis nibh diam. Pellentesque libero nibh, auctor quis lorem sed, sodales aliquet libero. Aenean porttitor sodales eleifend.";
		String text2 = "In quis eros urna. Nullam rhoncus rhoncus metus at interdum. Nullam sed vehicula lorem. ";
		String text3 = "Aenean porttitor sodales eleifend. Cras et neque nec justo malesuada sodales id nec sapien.";
		String text4 = "Quisque sodales eros sit amet urna vestibulum varius id non turpis. Aenean consectetur dapibus ipsum, nec ultrices velit placerat ac.";
		String text5 = "Mauris eget auctor sem, non fringilla turpis. Integer eu mauris tortor. Phasellus pellentesque elit sagittis, bibendum lectus vitae, sagittis lorem. Ut at condimentum magna.";
		
		String title1 = "Ut risus mauris";
		String title2 = "Phasellus gravida odio eget velit maximus";
		String title3 = "Gravida odio";
		String title4 = "Pellentesque libero nibh";
		String title5 = "Sodales aliquet libero";
		
		for (int i = 0; i < members.size(); i+=5) {
			if (i <= 199) {
				sm.getMember(members.get(i).getEmailAddress()).joinGroup(sm.getGroup("C#"),LocalDateTime.now());
				sm.getMember(members.get(i+1).getEmailAddress()).joinGroup(sm.getGroup("C#"),LocalDateTime.now());
				sm.getMember(members.get(i+2).getEmailAddress()).joinGroup(sm.getGroup("C#"),LocalDateTime.now());
				sm.getMember(members.get(i+3).getEmailAddress()).joinGroup(sm.getGroup("C#"),LocalDateTime.now());
				sm.getMember(members.get(i+4).getEmailAddress()).joinGroup(sm.getGroup("C#"),LocalDateTime.now());

				Question q = new Question(title5+i, text1, LocalDateTime.now());
				Answer a1 = new Answer(q, title1, LocalDateTime.now());
				Answer a2 = new Answer(q, title2, LocalDateTime.now());
				Answer a3 = new Answer(q, title3, LocalDateTime.now());
				Answer a4 = new Answer(q, title4, LocalDateTime.now());

				sm.getMember(members.get(i).getEmailAddress()).addQuestion(sm.getGroup("C#"), q, LocalDateTime.now());
				sm.getMember(members.get(i+1).getEmailAddress()).addAnswer(sm.getGroup("C#"), q, a1, LocalDateTime.now());
				sm.getMember(members.get(i+2).getEmailAddress()).addAnswer(sm.getGroup("C#"), q, a2, LocalDateTime.now());
				sm.getMember(members.get(i+3).getEmailAddress()).addAnswer(sm.getGroup("C#"), q, a3, LocalDateTime.now());
				sm.getMember(members.get(i+4).getEmailAddress()).addAnswer(sm.getGroup("C#"), q, a4, LocalDateTime.now());
			}else if (i <= 399) {
				sm.getMember(members.get(i).getEmailAddress()).joinGroup(sm.getGroup("PHP"),LocalDateTime.now());
				sm.getMember(members.get(i+1).getEmailAddress()).joinGroup(sm.getGroup("PHP"),LocalDateTime.now());
				sm.getMember(members.get(i+2).getEmailAddress()).joinGroup(sm.getGroup("PHP"),LocalDateTime.now());
				sm.getMember(members.get(i+3).getEmailAddress()).joinGroup(sm.getGroup("PHP"),LocalDateTime.now());
				sm.getMember(members.get(i+4).getEmailAddress()).joinGroup(sm.getGroup("PHP"),LocalDateTime.now());

				Question q = new Question(title5+i, text1, LocalDateTime.now());
				Answer a1 = new Answer(q, title1, LocalDateTime.now());
				Answer a2 = new Answer(q, title2, LocalDateTime.now());
				Answer a3 = new Answer(q, title3, LocalDateTime.now());
				Answer a4 = new Answer(q, title4, LocalDateTime.now());

				sm.getMember(members.get(i).getEmailAddress()).addQuestion(sm.getGroup("PHP"), q, LocalDateTime.now());
				sm.getMember(members.get(i+1).getEmailAddress()).addAnswer(sm.getGroup("PHP"), q, a1, LocalDateTime.now());
				sm.getMember(members.get(i+2).getEmailAddress()).addAnswer(sm.getGroup("PHP"), q, a2, LocalDateTime.now());
				sm.getMember(members.get(i+3).getEmailAddress()).addAnswer(sm.getGroup("PHP"), q, a3, LocalDateTime.now());
				sm.getMember(members.get(i+4).getEmailAddress()).addAnswer(sm.getGroup("PHP"), q, a4, LocalDateTime.now());			
			}else if (i <= 599) {
			
				sm.getMember(members.get(i).getEmailAddress()).joinGroup(sm.getGroup("JavaScript"),LocalDateTime.now());
				sm.getMember(members.get(i+1).getEmailAddress()).joinGroup(sm.getGroup("JavaScript"),LocalDateTime.now());
				sm.getMember(members.get(i+2).getEmailAddress()).joinGroup(sm.getGroup("JavaScript"),LocalDateTime.now());
				sm.getMember(members.get(i+3).getEmailAddress()).joinGroup(sm.getGroup("JavaScript"),LocalDateTime.now());
				sm.getMember(members.get(i+4).getEmailAddress()).joinGroup(sm.getGroup("JavaScript"),LocalDateTime.now());

				Question q = new Question(title5+i, text1, LocalDateTime.now());
				Answer a1 = new Answer(q, title1, LocalDateTime.now());
				Answer a2 = new Answer(q, title2, LocalDateTime.now());
				Answer a3 = new Answer(q, title3, LocalDateTime.now());
				Answer a4 = new Answer(q, title4, LocalDateTime.now());

				sm.getMember(members.get(i).getEmailAddress()).addQuestion(sm.getGroup("JavaScript"), q, LocalDateTime.now());
				sm.getMember(members.get(i+1).getEmailAddress()).addAnswer(sm.getGroup("JavaScript"), q, a1, LocalDateTime.now());
				sm.getMember(members.get(i+2).getEmailAddress()).addAnswer(sm.getGroup("JavaScript"), q, a2, LocalDateTime.now());
				sm.getMember(members.get(i+3).getEmailAddress()).addAnswer(sm.getGroup("JavaScript"), q, a3, LocalDateTime.now());
				sm.getMember(members.get(i+4).getEmailAddress()).addAnswer(sm.getGroup("JavaScript"), q, a4, LocalDateTime.now());			
				

				
			}else if (i <= 799) {
				sm.getMember(members.get(i).getEmailAddress()).joinGroup(sm.getGroup("C"),LocalDateTime.now());
				sm.getMember(members.get(i+1).getEmailAddress()).joinGroup(sm.getGroup("C"),LocalDateTime.now());
				sm.getMember(members.get(i+2).getEmailAddress()).joinGroup(sm.getGroup("C"),LocalDateTime.now());
				sm.getMember(members.get(i+3).getEmailAddress()).joinGroup(sm.getGroup("C"),LocalDateTime.now());
				sm.getMember(members.get(i+4).getEmailAddress()).joinGroup(sm.getGroup("C"),LocalDateTime.now());

				Question q = new Question(title5+i, text1, LocalDateTime.now());
				Answer a1 = new Answer(q, title1, LocalDateTime.now());
				Answer a2 = new Answer(q, title2, LocalDateTime.now());
				Answer a3 = new Answer(q, title3, LocalDateTime.now());
				Answer a4 = new Answer(q, title4, LocalDateTime.now());

				sm.getMember(members.get(i).getEmailAddress()).addQuestion(sm.getGroup("C"), q, LocalDateTime.now());
				sm.getMember(members.get(i+1).getEmailAddress()).addAnswer(sm.getGroup("C"), q, a1, LocalDateTime.now());
				sm.getMember(members.get(i+2).getEmailAddress()).addAnswer(sm.getGroup("C"), q, a2, LocalDateTime.now());
				sm.getMember(members.get(i+3).getEmailAddress()).addAnswer(sm.getGroup("C"), q, a3, LocalDateTime.now());
				sm.getMember(members.get(i+4).getEmailAddress()).addAnswer(sm.getGroup("C"), q, a4, LocalDateTime.now());			
			} else {
				sm.getMember(members.get(i).getEmailAddress()).joinGroup(sm.getGroup("Python"),LocalDateTime.now());
				sm.getMember(members.get(i+1).getEmailAddress()).joinGroup(sm.getGroup("Python"),LocalDateTime.now());
				sm.getMember(members.get(i+2).getEmailAddress()).joinGroup(sm.getGroup("Python"),LocalDateTime.now());
				sm.getMember(members.get(i+3).getEmailAddress()).joinGroup(sm.getGroup("Python"),LocalDateTime.now());
				sm.getMember(members.get(i+4).getEmailAddress()).joinGroup(sm.getGroup("Python"),LocalDateTime.now());

				Question q = new Question(title5+i, text1, LocalDateTime.now());
				Answer a1 = new Answer(q, title1, LocalDateTime.now());
				Answer a2 = new Answer(q, title2, LocalDateTime.now());
				Answer a3 = new Answer(q, title3, LocalDateTime.now());
				Answer a4 = new Answer(q, title4, LocalDateTime.now());

				sm.getMember(members.get(i).getEmailAddress()).addQuestion(sm.getGroup("Python"), q, LocalDateTime.now());
				sm.getMember(members.get(i+1).getEmailAddress()).addAnswer(sm.getGroup("Python"), q, a1, LocalDateTime.now());
				sm.getMember(members.get(i+2).getEmailAddress()).addAnswer(sm.getGroup("Python"), q, a2, LocalDateTime.now());
				sm.getMember(members.get(i+3).getEmailAddress()).addAnswer(sm.getGroup("Python"), q, a3, LocalDateTime.now());
				sm.getMember(members.get(i+4).getEmailAddress()).addAnswer(sm.getGroup("Python"), q, a4, LocalDateTime.now());			
			}
		}
		
		Member rick =  new Member("Rick","Boles","rjboles","rjboles@valdosta.edu",LocalDateTime.now());
		Member tyler =  new Member("Tyler","Angelier","trangelier","trangelier@valdosta.edu",LocalDateTime.now());
		Member mallory =  new Member("Mallory","Helms","mghelms","mghelms@valdosta.edu",LocalDateTime.now());
		Member michael =  new Member("Michael","Montgomery","memontgomery","memontgomery@valdosta.edu",LocalDateTime.now());
		
		sm.addMember(rick);
		sm.addMember(tyler);
		sm.addMember(mallory);
		sm.addMember(michael);
		
		Group nodeJS = new Group("NodeJS", "Test Group for presentation", LocalDateTime.now());
		
		sm.addGroup(nodeJS);
		
		sm.getMember(rick.getEmailAddress()).joinGroup(sm.getGroup(nodeJS.getTitle()), LocalDateTime.now());
		sm.getMember(tyler.getEmailAddress()).joinGroup(sm.getGroup(nodeJS.getTitle()), LocalDateTime.now());
		sm.getMember(mallory.getEmailAddress()).joinGroup(sm.getGroup(nodeJS.getTitle()), LocalDateTime.now());
		sm.getMember(michael.getEmailAddress()).joinGroup(sm.getGroup(nodeJS.getTitle()), LocalDateTime.now());
		
		File file = new File("demoData.ser");
		PersistenceManager pm = new PersistenceManager();
		pm.save(sm, file);
		
	}
	

}

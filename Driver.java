import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Driver{
	
	EmailPlow plow = new EmailPlow();
	Map<String, String> emailMap = new HashMap<>();
	Map<String, List<String>> categoryMap = new HashMap<>();
	
	public void readFile(String filePath) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
        reader.readLine();
        String line;
        
        // Loop through file & organize contents into Map (email address as key, subject as value)
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            String emailAddress = parts[0];
            String subject = parts[1];
            emailMap.put(emailAddress, subject);
        }
        
        reader.close();
        //test
        
	}
	
	/* 
	 * Organizes emails in map of emails into default categories.
	 */
	public void categorizeEmails() {
		// Loop through map of email addresses-subjects, sort into default categories
		for (String emailAddress : emailMap.keySet()) {
			String category = plow.categorizeEmail(emailMap.get(emailAddress));
            if(category == "Other"){
                category = plow.categorizeEmail(emailAddress);
            }
            String emailStr = emailAddress.toString() + ": " + emailMap.get(emailAddress).toString();
            List<String> list = categoryMap.get(category);
            list.add(emailStr);
            categoryMap.put(category, list);
            
		}
		
	}
	
	public void deleteByKeyword(ArrayList<String> keywords) {
		for (String keyword : keywords) {
			for (String emailAddress : emailMap.keySet()) {
				if (emailAddress.contains(keyword) || emailMap.get(emailAddress).contains(keyword)) {
					emailMap.remove(emailAddress);
				}
			}
		}
		
	}
	
	public void printCategorized() {
		for (String category : categoryMap.keySet()) {
            System.out.println(category + ":");
            for (String emailSubject : categoryMap.get(category)) {
                System.out.println(emailSubject);
            }
            System.out.println();
        }
		
	}
	
	public static void main(String args[]) throws IOException{
		
		Driver foo = new Driver();
		
		System.out.println("Welcome to the Email Sorter!");
		
		while(true) { // while the user input is not "EXIT" continue to ask for a actor
			
            System.out.println("Enter 1 to DELETE emails");
            System.out.println("      2 to CATEGORIZE all emails");
            System.out.println("      3 to EXIT the program: ");
            
            Scanner sc = new Scanner(System.in);
            
            String input = sc.nextLine();
            
            //if use input is "EXIT" end loop
			if (input.equals("3")) {
				System.out.println("Thanks for using the Email Sorter!");
				System.exit(0);
			}
			//if user input is not "EXIT" continue with the program
			else {
	
				if (input.equals("1")) {
		            System.out.println("Enter 1 to delete by KEYWORD");
		            System.out.println("      2 to delete by EMAIL ADDRESS");
		            input = sc.nextLine();
		            
		            if (input.equals("1")) {
		            	System.out.println("Enter keywords: ");
		            	input = sc.nextLine();
		            	
		            	// calls function with filename and input --> for each input compares it with the elems in the file and deletes them
		            	//deleteByKeyword(input);
		            	//outputs previous count, number of emails deleted, current count and save new inbox in "newInbox.txt"
		            	
		            }else if (input.equals("2")) {
		            	System.out.println("Enter email address(s): ");
		            	input = sc.nextLine();
		            	
		            	// calls function with filename and input --> for each input compares it with the elems in the file and deletes them
		            	
		            	//outputs previous count, number of emails deleted, current count and save new inbox in "newInbox.txt"
		            	
		            	
		            }else {
		            	System.out.println("Invalid input. Try again.");
		            }
					
				}
				else if(input.equals("2")) {
					System.out.println("Enter 1 to customize sorting");
		            System.out.println("      2 to sort with default settings");
		            input = sc.nextLine();
		            
		            if (input.equals("1")) {
		            	
		            	System.out.println("Enter categories to sort by: "); // maybe we should give a list??
		            	input = sc.nextLine();
		            	
		            	// calls function that searches and sorts all emails into categories thats user inputed
		            	// print out categorized emails
		            	
		            }else if (input.equals("2")) {
		            	
		            	// calls function that searches and sorts all emails into default categories 
		            	// print out categorized emails
		            	
		            }else {
		            	System.out.println("Invalid input. Try again.");
		            }
					
				}
				else {
					System.out.println("Invalid input. Try again.");
				}
			}
		}

	}


}

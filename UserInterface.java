//Team 14
package com.usf.hackathon;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class UserInterface{
	
	public void categorize(String filename) throws IOException {
		EmailCategorizer categorizer = new EmailCategorizer();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        reader.readLine();
        String line;
        Map<String, List<String>> Map = new HashMap<>();

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            String email = parts[0];
            String subject = parts[1];
            String category = categorizer.categorizeEmail(subject);
            if(category == "Other"){
                category = categorizer.categorizeEmail(email);
            }
            // Add the email and subject to the list by category
            List<String> emailList = Map.getOrDefault(category, new ArrayList<>());
            emailList.add(email + ": " + subject);
            Map.put(category, emailList);
        }
        reader.close();
        
        // Printing
        for (String category : Map.keySet()) {
            System.out.println(category + ":");
            for (String emailSubject : Map.get(category)) {
                System.out.println(emailSubject);
            }
            System.out.println();
        }
	}
	public void delete(String filename, String search_value, String type) throws IOException {
		EmailCategorizer categorizer = new EmailCategorizer();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        reader.readLine();
        String line;
        Map<String, List<String>> Map = new HashMap<>();

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            String email = parts[0];
            String subject = parts[1];
            String category = categorizer.categorizeEmail(subject);
            if(category == "Other"){
                category = categorizer.categorizeEmail(email);
            }
            // Add the email and subject to the list by category
            List<String> emailList = Map.getOrDefault(category, new ArrayList<>());
            emailList.add(email + ": " + subject);
            Map.put(category, emailList);
        }
        reader.close();
        
        if (type.equals("type1")) {
        	Map = deleteEmailAndKeywords(Map,search_value,null,type);
        }else {
        	Map = deleteEmailAndKeywords(Map,null,search_value,type);
        }
        
        // Printing
        for (String category : Map.keySet()) {
            //System.out.println(category + ":");
            for (String emailSubject : Map.get(category))
            {
                System.out.println(emailSubject);
            }
            System.out.println();
        }
	}
	
	public static Map<String, List<String>> deleteEmailAndKeywords(Map<String, List<String>> map, String emailAddress, String keyword,  String type) {
        Map<String, List<String>> updatedMap = new HashMap<>();
        List<String> emailSubjectList = new ArrayList<>();
        for (String category : map.keySet()) {
            for (String emailSubject : map.get(category)) {
                String email = emailSubject.split(":")[0].trim();
                // Delete by email
                if(emailAddress != null && email.equals(emailAddress) && type.equals("type1")){
                    continue;
                }
                if(keyword != null && emailSubject.toLowerCase().contains(keyword.toLowerCase()) && type.equals("type2")){
                      continue;
                }
                emailSubjectList.add(emailSubject);
            }
            updatedMap.put(category, emailSubjectList);
        }
        return updatedMap;
    }
	
	
	
	public static void main(String args[]) throws IOException{
		
		//File f = new File(args[0]);
		
		UserInterface foo = new UserInterface();
			
		String filename = "Inbox.csv";
		
		String type1 = "email";
		String type2 = "keywords";
		
		System.out.println("Welcome to the Email Plow!");
		
		while(true) { // while the user input is not "EXIT" continue to ask for a actor
			
            System.out.println("Enter 1 to DELETE emails");
            System.out.println("      2 to CATEGORIZE all emails");
            System.out.println("      3 to EXIT the program: ");
            
            Scanner sc = new Scanner(System.in);
            
            String input = sc.nextLine();
            
            //if use input is "EXIT" end loop
			if (input.equals("3")) {
				System.out.println("Thanks for using the Email Plow!");
				System.exit(0);
			}
			//if user input is not "EXIT" continue with the program
			else {
	
				if (input.equals("1")) {
		            System.out.println("Enter 1 to delete by EMAIL ADDRESS");
		            System.out.println("      2 to delete by KEYWORD");
		            System.out.println("      3 to delete by CATEGORY");
		            input = sc.nextLine();
		            
		            if (input.equals("1")) {
		            	System.out.println("Enter email address: ");
		            	input = sc.nextLine();
		            	
		            	// calls function with filename and input --> for each input compares it with the elems in the file and deletes them
		            	foo.delete(filename, input, type1);
		            	//outputs previous count, number of emails deleted, current count and save new inbox in "newInbox.txt"
		            	
		            }else if (input.equals("2")) {
		            	System.out.println("Enter keyword: ");
		            	input = sc.nextLine();
		            	
		            	// calls function with filename and input --> for each input compares it with the elems in the file and deletes them
		            	foo.delete(filename, input, type2);

		            	
		            	
		            }else {
		            	System.out.println("Invalid input. Try again.");
		            }
					
				}
				else if(input.equals("2")) {

		            	System.out.println("Sorting into categories... ");

		            	// calls function that searches and sorts all emails into categories thats user inputed & input
		            	foo.categorize(filename);
					
				}
				else {
					System.out.println("Invalid input. Try again.");
				}
			}
		}

	}


}

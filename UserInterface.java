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
	
	public void categorizeByInput(String filename, String input) throws IOException {
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
	
	
	
	public static void main(String args[]) throws IOException{
		
		//File f = new File(args[0]);
		
		UserInterface foo = new UserInterface();
			
		String filename = "inbox.csv";

		
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
		            System.out.println("Enter 1 to delete by KEYWORD");
		            System.out.println("      2 to delete by EMAIL ADDRESS");
		            System.out.println("      3 to delete by CATEGORY");
		            input = sc.nextLine();
		            
		            if (input.equals("1")) {
		            	System.out.println("Enter keywords: ");
		            	input = sc.nextLine();
		            	
		            	// calls function with filename and input --> for each input compares it with the elems in the file and deletes them
		            	
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
		            	
		            	System.out.println("Sorting categories: ");
		            	System.out.println("(1) Shopping\n(2) Subscriptions\n(3) Health\n(4) Work\n(5) School\n(6) Entertainment\n(7) Social\n(8) Urgent\n(9) Finances\n(10) Promotions\n");
		            	System.out.println("Enter categories to sort by: "); // maybe we should give a list??
		            	input = sc.nextLine();
		            	
		            	// calls function that searches and sorts all emails into categories thats user inputed
		            	foo.categorize(filename);
		            	// print out categorized emails
		            	
		            }else if (input.equals("2")) {
		            	
		            	// calls function that searches and sorts all emails into default categories
		            	foo.categorizeByInput(filename, input);
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

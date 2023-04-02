import java.util.HashMap;
import java.util.Map;

public class EmailPlow {

    private static final String Urgent_Category = "Urgent";
    private static final String Shopping_Category = "Shopping";
    private static final String Subscriptions_Category = "Subscriptions";
    private static final String Health_Category = "Health";
    private static final String Work_Category = "Work";
    private static final String School_Category = "School";
    private static final String Entertainment_Category = "Entertainment";
    private static final String Social_Category = "Social";
    //private static final String Urgent_Category = "Urgent";

    private static final String[] Urgent_Values = {"reminder", "reminders", "urgent", "important", "action required", "deadline", "upcoming"};
    private static final String[] Shopping_Values = {"amazon", "sales", "wholefoods"};
    private static final String[] Subscriptions_Values = {"Expired", "monthly billing", "billing", "weekly billing", "amount due", "balance"};
    private static final String[] Health_Values = {"covid 19", "covid", "clean food", "clean foods", "protein powder", "health, wellness", "stress, workout", "pilates", "gym", "doctor", "coronavirus", "flu", "virus", "Vegetarian", "vegan", "plant-based"};
    private static final String[] Work_Values = {"Apply", "Timesheet", "Intern", "application", "job posting"};
    private static final String[] School_Values = {"canvas", "assignments", "class", "labs", "review", "piazza", "learning", "skill"};
    private static final String[] Entertainment_Values = {"concert", "social", "social events", "socials", "concerts", "music festival"};
    private static final String[] Social_Values = {"instagram", "twitter", "snapchat", "youtube", "creators"};
    //private static final String[] Urgent_Values = {"reminder", "reminders", "urgent", "important", "action required", "deadline", "upcoming"};


    private Map<String, String[]> categories;

    public EmailPlow() {
        categories = new HashMap<>();
        categories.put(Urgent_Category, Urgent_Values);
        categories.put(Shopping_Category, Shopping_Values);
        categories.put(Subscriptions_Category, Subscriptions_Values);
        categories.put(Health_Category, Health_Values);
        categories.put(Work_Category, Work_Values);
        categories.put(School_Category, School_Values);
        categories.put(Entertainment_Category, Entertainment_Values);
        categories.put(Social_Category, Social_Values);
        //categories.put(Urgent_Category, Urgent_Values);

    }


    public String categorizeEmail(String subject) {
        for (Map.Entry<String, String[]> entry : categories.entrySet()) {
            String category = entry.getKey();
            String[] values = entry.getValue();
            for (String value : values) {
                if (subject.toLowerCase().contains(value.toLowerCase())) {
                    return category;
                }
            }
        }
        return "Other";
    }


}

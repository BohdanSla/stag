package pro1;

import java.util.Comparator;
import java.util.stream.Collectors;
import com.google.gson.Gson;
import pro1.apiDataModel.DeadlinesList;

public class Main7 {

    public static void main(String[] args) {
        System.out.println(specializationDeadlines(2025));
    }

    public static String specializationDeadlines(int year) {
        String json = Api.getSpecializations(year);
        
        DeadlinesList deadlines = new Gson().fromJson(json, DeadlinesList.class);


        return deadlines.items.stream()
            .map(d -> d.deadline.value) 
            .distinct() 
            .sorted(Comparator.comparing((String date) -> {
                String[] parts = date.split("\\."); 
                return Integer.parseInt(parts[2]) * 10000 + 
                       Integer.parseInt(parts[1]) * 100 +  
                       Integer.parseInt(parts[0]);       
            }))
            .filter(date -> !date.equals("30.4.2025"))
            .collect(Collectors.joining(",")); 
    }
}
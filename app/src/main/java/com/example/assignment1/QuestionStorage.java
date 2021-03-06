package com.example.assignment1;

import java.util.Arrays;
import java.util.Map;

/*
    This class is used to store the game A's questions.
 */

public class QuestionStorage {
    Question Q1 = new Question("What is moon?", Arrays.asList("A Satellite", "A Star", "A Planet", "A Meteor"), 0);
    Question Q2 = new Question("What is tulip?", Arrays.asList("A type of food", "An animal", "A flower", "A Star"), 2);
    Question Q3 = new Question("45+65=?", Arrays.asList("115", "100", "120", "110"), 3);
    Question Q4 = new Question("Where do swans live?", Arrays.asList("In dessert", "In mountains", "In lakes", "In forest"), 2);
    Question Q5 = new Question("Where is China?", Arrays.asList("Europe", "Asia", "Africa", "North America"), 1);
    Question Q6 = new Question("45*5=?", Arrays.asList("225", "215", "235", "245"), 0);
    Question Q7 = new Question("When does February have only 27 days?",Arrays.asList("Every other years", "Every 3 years", "Every 4 years", "Never"), 3);
    Question Q8 = new Question("Samantha throws a soccer ball ten feet, and it turns around and goes right back at her. In which direction did she throw it?",Arrays.asList("Up", "Down", "Left", "Right"), 0);
    Question Q9 = new Question("What insects can chew through wood and destroy your home?", Arrays.asList("Ants", "Lice", "Termites", "Bed bugs"), 2);
    Question Q10 = new Question("Prunes were once what kind of fruit?", Arrays.asList("Pears", "Plums", "Apricots", "Figs"), 1);

    // Connect these questions as a map
    Map<Integer, Question> qList = Map.of(0, Q1, 1, Q2, 2, Q3, 3, Q4, 4, Q5,5,Q6,6,Q7,7,Q8,8,Q9,9,Q10);

    // Get the map of questions
    public Map<Integer, Question> getQuestionList(){
        return qList;
    }

}

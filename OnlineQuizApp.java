import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Question {
    private String questionText;
    private List<String> options;
    private int correctAnswerIndex;
    
    public Question(String questionText, List<String> options, int correctAnswerIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }
    
    public String getQuestionText() {
        return questionText;
    }
    
    public List<String> getOptions() {
        return options;
    }
    
    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
    
    public boolean isCorrectAnswer(int userChoice) {
        return userChoice == correctAnswerIndex;
    }
}

class Quiz {
    private List<Question> questions;
    private int score;
    private Scanner scanner;
    
    public Quiz() {
        questions = new ArrayList<>();
        score = 0;
        scanner = new Scanner(System.in);
        initializeQuestions();
    }
    
    private void initializeQuestions() {

        List<String> options1 = new ArrayList<>();
        options1.add("JVM");
        options1.add("JRE");
        options1.add("JDK");
        options1.add("JAR");
        questions.add(new Question("What does the Java compiler compile Java code into?", options1, 0));
    
        List<String> options2 = new ArrayList<>();
        options2.add("int");
        options2.add("float");
        options2.add("double");
        options2.add("String");
        questions.add(new Question("Which of the following is not a primitive data type in Java?", options2, 3));
        
        List<String> options3 = new ArrayList<>();
        options3.add("Inheritance");
        options3.add("Encapsulation");
        options3.add("Polymorphism");
        options3.add("Compilation");
        questions.add(new Question("Which OOP concept allows one class to acquire the properties of another class?", options3, 0));
        
        List<String> options4 = new ArrayList<>();
        options4.add("for");
        options4.add("while");
        options4.add("do-while");
        options4.add("repeat");
        questions.add(new Question("Which of the following is not a loop structure in Java?", options4, 3));
        
        List<String> options5 = new ArrayList<>();
        options5.add("==");
        options5.add("equals()");
        options5.add("!=");
        options5.add("compare()");
        questions.add(new Question("Which method is used to compare two strings in Java?", options5, 1));

        List<String> options6 = new ArrayList<>();
        options6.add("Microsoft");
        options6.add("Apple");
        options6.add("Sun Microsystems");
        options6.add("IBM");
        questions.add(new Question("Which company developed Java?", options6, 1));

    }
    private int askQuestion(Question question, int questionNumber) {
        System.out.println("\nQuestion " + questionNumber + ": " + question.getQuestionText());
        
        List<String> options = question.getOptions();
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
        
        int userChoice = -1;
        while (userChoice < 1 || userChoice > options.size()) {
            System.out.print("Enter your choice (1-" + options.size() + "): ");
            try {
                userChoice = Integer.parseInt(scanner.nextLine());
                if (userChoice < 1 || userChoice > options.size()) {
                    System.out.println("Please enter a valid option between 1 and " + options.size());
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
        
        return userChoice - 1;
    }
    
    public void start() {
        System.out.println("========== WELCOME TO THE JAVA QUIZ ==========");
        System.out.println("You will be presented with " + questions.size() + " multiple-choice questions.");
        System.out.println("Enter the number of your chosen option for each question.");
        System.out.println("==============================================");
        
        for (int i = 0; i < questions.size(); i++) {
            Question currentQuestion = questions.get(i);
            int userAnswerIndex = askQuestion(currentQuestion, i + 1);
            
            if (currentQuestion.isCorrectAnswer(userAnswerIndex)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect! The correct answer is: " + 
                    (currentQuestion.getCorrectAnswerIndex() + 1) + ". " + 
                    currentQuestion.getOptions().get(currentQuestion.getCorrectAnswerIndex()));
            }
        }
        
        displayResults();
    }
    
    private void displayResults() {
        System.out.println("\n========== QUIZ COMPLETE ==========");
        System.out.println("Your final score: " + score + "/" + questions.size());
        
        double percentage = (double) score / questions.size() * 100;
        System.out.printf("Percentage: %.2f%%\n", percentage);
        
        if (percentage >= 80) {
            System.out.println("Excellent! You have a strong understanding of Java.");
        } else if (percentage >= 60) {
            System.out.println("Good job! You have a decent understanding of Java.");
        } else if (percentage >= 40) {
            System.out.println("Not bad! Keep learning to improve your Java knowledge.");
        } else {
            System.out.println("You might want to review Java fundamentals.");
        }
        
        System.out.println("===================================");
    }
}

public class OnlineQuizApp {
    public static void main(String[] args) {
        Quiz quiz = new Quiz();
        quiz.start();
    }
}
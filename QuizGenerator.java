import java.util.ArrayList;
import java.util.Scanner;

class Question {
    private String questionText;
    private ArrayList<String> options;
    private int correctAns;

    public Question(String questionText, ArrayList<String> options, int correctAns) {
        this.questionText = questionText;
        this.options = options;
        this.correctAns = correctAns;
    }

    public String getQuestionText() {
        return questionText;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public int getCorrectAns() {
        return correctAns;
    }

    public boolean isCorrect(int userAnswer) {
        return userAnswer == correctAns;
    }
}

class Quiz {
    private String quizName;
    private ArrayList<Question> questions;

    public Quiz(String quizName) {
        this.quizName = quizName;
        this.questions = new ArrayList<>();
    }

    public String getQuizName() {
        return quizName;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void takeQuiz() {
        Scanner scanner = new Scanner(System.in);
        int score = 0;

        System.out.println("Starting the quiz: " + quizName);
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println((i + 1) + ". " + q.getQuestionText());

            ArrayList<String> options = q.getOptions();
            for (int j = 0; j < options.size(); j++) {
                System.out.println("  " + (j + 1) + ": " + options.get(j));
            }

            System.out.print("Your answer: ");
            int userAnswer = scanner.nextInt();
            if (q.isCorrect(userAnswer - 1)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect. The correct answer is: " + (q.getCorrectAns() + 1));
            }
        }

        System.out.println("Quiz completed. Your score: " + score + "/" + questions.size());
    }
}

// Main class to run the quiz application
public class QuizGenerator {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Quiz> quizzes = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nQuiz Generator");
            System.out.println("Type 1 for Create a new quiz");
            System.out.println("Type 2 to Take a quiz");
            System.out.println("Type 3 to Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createQuiz();
                    break;
                case 2:
                    takeQuiz();
                    break;
                case 3:
                    System.out.println("Exiting the quiz generator. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createQuiz() {
        System.out.print("What type of quiz you create!, Enter the name. : ");
        scanner.nextLine();
        String quizName = scanner.nextLine();
        Quiz quiz = new Quiz(quizName);

        while (true) {
            System.out.print("Enter the question text (or type 'done' to finish): ");
            String questionText = scanner.nextLine();
            if (questionText.equalsIgnoreCase("done")) break;

            ArrayList<String> options = new ArrayList<>();
            for (int i = 0; i < 4; i++) {  // Assuming 4 options per question
                System.out.print("Enter option " + (i + 1) + ": ");
                options.add(scanner.nextLine());
            }

            System.out.print("Enter the correct option number (1-4): ");
            int correctOption = scanner.nextInt() - 1;
            scanner.nextLine();  // Consume newline

            Question question = new Question(questionText, options, correctOption);
            quiz.addQuestion(question);
        }

        quizzes.add(quiz);
        System.out.println("Quiz '" + quizName + "' created successfully!");
    }

    private static void takeQuiz() {
        if (quizzes.isEmpty()) {
            System.out.println("No quizzes available. Please create one first.");
            return;
        }

        System.out.println("Available quizzes:");
        for (int i = 0; i < quizzes.size(); i++) {
            System.out.println((i + 1) + ". " + quizzes.get(i).getQuizName());
        }

        System.out.print("Choose a quiz to take: ");
        int quizChoice = scanner.nextInt() - 1;

        if (quizChoice >= 0 && quizChoice < quizzes.size()) {
            quizzes.get(quizChoice).takeQuiz();
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }
}


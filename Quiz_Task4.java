import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
class QuizQuestion {
private String question;
private String[] options;
private int correctAnswerIndex;
public QuizQuestion(String question, String[] options, int correctAnswerIndex) {
this.question = question;
this.options = options;
this.correctAnswerIndex = correctAnswerIndex;
}
public String getQuestion() {
return question;
}
public String[] getOptions() {
return options;
}
public int getCorrectAnswerIndex() {
return correctAnswerIndex;
}
}
public class QuizApplication {
private static ArrayList<QuizQuestion> questions = new ArrayList<>();
private static int score = 0;
private static int timeLimitPerQuestion = 10;
private static boolean isTimeUp = false;
private static Scanner scanner = new Scanner(System.in);
public static void main(String[] args) {
questions.add(new QuizQuestion("What is the capital of France?", new String[]{"Paris", "London", "Berlin",
"Rome"}, 0));
questions.add(new QuizQuestion("Who wrote 'Romeo and Juliet'?", new String[]{"William Shakespeare",
"Charles Dickens", "Mark Twain", "Jane Austen"}, 0));
questions.add(new QuizQuestion("What is the largest planet in our solar system?", new String[]{"Earth", "Mars",
"Jupiter", "Saturn"}, 2));
questions.add(new QuizQuestion("Which element has the chemical symbol 'O'?", new String[]{"Oxygen",
"Gold", "Silver", "Iron"}, 0));
startQuiz();
displayResults();
}
private static void startQuiz() {
for (int i = 0; i < questions.size(); i++) {
QuizQuestion currentQuestion = questions.get(i);
displayQuestion(currentQuestion, i + 1);
Timer timer = new Timer();
isTimeUp = false;
TimerTask task = new TimerTask() {
public void run() {
isTimeUp = true;
System.out.println("\nTime's up!");
timer.cancel();
}
};
timer.schedule(task, timeLimitPerQuestion * 1000);
int userAnswer = getUserAnswer();
timer.cancel();
if (userAnswer == currentQuestion.getCorrectAnswerIndex() + 1 && !isTimeUp) {
System.out.println("Correct!");
score++;
} else if (!isTimeUp) {
System.out.println("Incorrect! The correct answer was: " + (currentQuestion.getCorrectAnswerIndex() + 1));
}
}
}
private static void displayQuestion(QuizQuestion question, int questionNumber) {
System.out.println("\nQuestion " + questionNumber + ": " + question.getQuestion());
String[] options = question.getOptions();
for (int i = 0; i < options.length; i++) {
System.out.println((i + 1) + ". " + options[i]);
}
System.out.println("You have " + timeLimitPerQuestion + " seconds to answer.");
}
private static int getUserAnswer() {
int answer = -1;
if (!isTimeUp) {
System.out.print("Your answer (1-4): ");
answer = scanner.nextInt();
}
return answer;
}
private static void displayResults() {
System.out.println("\nQuiz Over!");
System.out.println("Your final score: " + score + " out of " + questions.size());
System.out.println("Summary:");
for (int i = 0; i < questions.size(); i++) {
QuizQuestion question = questions.get(i);
System.out.println("Question " + (i + 1) + ": " + question.getQuestion());
System.out.println("Correct Answer: " + (question.getCorrectAnswerIndex() + 1) + " (" +
question.getOptions()[question.getCorrectAnswerIndex()] + ")");
}
}
}
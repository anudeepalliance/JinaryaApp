//package com.example.android.jinarya.Questions.RelationshipQuestionPapers;
//
//import com.example.android.jinarya.Objects.Option;
//import com.example.android.jinarya.Objects.QuestionsAndOptions;
//import com.example.android.jinarya.Objects.ReportObject;
//import com.example.android.jinarya.R;
//import java.util.ArrayList;
//
///**
// * Created by Aadhyamo on 31/12/17.
// */
//
//public class RelationshipQuestionPaper4  {
//
//    //no constructor so a default constructor will be called by android studio
//
//    //female Questions
//    public static QuestionsAndOptions RQP4FQ1 = new QuestionsAndOptions(
//            "Pick a color that matches you",
//            new Option("blue", "A"),
//            new Option("red", "B"),
//            new Option("green", "C"),
//            new Option("white", "D"),
//            R.drawable.ic_celebration);
//
//
//
//    public static QuestionsAndOptions RQP4FQ2 = new QuestionsAndOptions(
//            "Pick a vacation spot that matches you",
//            new Option("Greece", "A"),
//            new Option("Italy", "B"),
//            new Option("Switzerland", "C"),
//            new Option("Dont care", "D"),
//            R.drawable.ic_baby_boy);
//
//    public static QuestionsAndOptions RQP4FQ3 = new QuestionsAndOptions(
//            "Pick a movie spot that matches you",
//            new Option("dil", "A"),
//            new Option("karma", "B"),
//            new Option("swat", "C"),
//            new Option("kim", "D"),
//            R.drawable.ic_justice);
//
//
//
//    public static ArrayList<QuestionsAndOptions> RQP4GirlQuestions() {
//
//        ArrayList<QuestionsAndOptions> QuestionPaper4FemaleQuestions= new ArrayList<>();
//        QuestionPaper4FemaleQuestions.add(RQP4FQ1);
//        QuestionPaper4FemaleQuestions.add(RQP4FQ2);
//        QuestionPaper4FemaleQuestions.add(RQP4FQ3);
//
//        return QuestionPaper4FemaleQuestions;
//    }
//
//
//    // male Questions
//    public static QuestionsAndOptions RQP4MQ1 = new QuestionsAndOptions(
//            "Pick a color that matches you",
//            new Option("blue", "A"),
//            new Option("red", "B"),
//            new Option("green", "C"),
//            new Option("white", "D"),
//            R.drawable.ic_happy);
//
//    public static QuestionsAndOptions RQP4MQ2 = new QuestionsAndOptions(
//            "Pick a vacation spot that matches you",
//            new Option("Greece", "A"),
//            new Option("Italy", "B"),
//            new Option("Switzerland", "C"),
//            new Option("Dont care", "D"),
//            R.drawable.ic_house);
//
//    public static QuestionsAndOptions RQP4MQ3 = new QuestionsAndOptions(
//            "Pick a movie spot that matches you",
//            new Option("dil", "A"),
//            new Option("karma", "B"),
//            new Option("swat", "C"),
//            new Option("kim", "D"),
//            R.drawable.ic_love);
//
//    public static ArrayList<QuestionsAndOptions> RQP4BoyQuestions() {
//
//        ArrayList<QuestionsAndOptions> QuestionPaper4MaleQuestions = new ArrayList<>();
//        QuestionPaper4MaleQuestions.add(RQP4MQ1);
//        QuestionPaper4MaleQuestions.add(RQP4MQ2);
//        QuestionPaper4MaleQuestions.add(RQP4MQ3);
//
//        return QuestionPaper4MaleQuestions;
//    }
//
//
//
//    public static ReportObject calculateRelationshipQuestionsPaper4Results
//            (String boyName,
//             String girlName,
//             ArrayList<String> mBoyAnswers,
//             ArrayList<String> mGirlAnswers) {
//
//        String resultSummary = "";
//        int compatibilityScore = 0;
//        String compatibilityScoreFeedback;
//        String adviceSummary = "";
//
//        //evaluation of question 1
//        if (mBoyAnswers.get(0).equals("A")) {
//
//            switch (mGirlAnswers.get(0)) {
//
//                case "A":
//                    compatibilityScore = compatibilityScore + 10;
//                    break;
//                case "B":
//                    compatibilityScore = compatibilityScore + 7;
//                    break;
//                case "C":
//                    compatibilityScore = compatibilityScore + 5;
//                    break;
//                case "D":
//                    compatibilityScore = compatibilityScore + 3;
//                    break;
//            }
//        }
//
//        if ( mBoyAnswers.get(0).equals("B")) {
//
//            switch (mGirlAnswers.get(0)) {
//
//                case "B":
//                    compatibilityScore = compatibilityScore + 10;
//                    break;
//                case "C":
//                    compatibilityScore = compatibilityScore + 7;
//                    break;
//                case "D":
//                    compatibilityScore = compatibilityScore + 5;
//                    break;
//                case "A":
//                    compatibilityScore = compatibilityScore + 3;
//                    break;
//            }
//        }
//
//        if ( mBoyAnswers.get(0).equals("C")) {
//
//            switch (mGirlAnswers.get(0)) {
//
//                case "C":
//                    compatibilityScore = compatibilityScore + 10;
//                    break;
//                case "D":
//                    compatibilityScore = compatibilityScore + 7;
//                    break;
//                case "A":
//                    compatibilityScore = compatibilityScore + 5;
//                    break;
//                case "B":
//                    compatibilityScore = compatibilityScore + 3;
//                    break;
//            }
//
//        }
//
//        if ( mBoyAnswers.get(0).equals("D")) {
//
//            switch (mGirlAnswers.get(0)) {
//
//                case "D":
//                    compatibilityScore = compatibilityScore + 10;
//                    break;
//                case "A":
//                    compatibilityScore = compatibilityScore + 7;
//                    break;
//                case "B":
//                    compatibilityScore = compatibilityScore + 5;
//                    break;
//                case "C":
//                    compatibilityScore = compatibilityScore + 3;
//                    break;
//            }
//        }
//        resultSummary = resultSummary + boyName + "Question 1 evaluated\n";
//        adviceSummary = adviceSummary + girlName + "advice for question 1 generated\n";
//
//        //evaluation of question 2
//        if (mBoyAnswers.get(1).equals("A")) {
//
//            switch (mGirlAnswers.get(1)) {
//
//                case "A":
//                    compatibilityScore = compatibilityScore + 10;
//                    break;
//                case "B":
//                    compatibilityScore = compatibilityScore + 7;
//                    break;
//                case "C":
//                    compatibilityScore = compatibilityScore + 5;
//                    break;
//                case "D":
//                    compatibilityScore = compatibilityScore + 3;
//                    break;
//            }
//        }
//
//        if ( mBoyAnswers.get(1).equals("B")) {
//
//            switch (mGirlAnswers.get(1)) {
//
//                case "B":
//                    compatibilityScore = compatibilityScore + 10;
//                    break;
//                case "C":
//                    compatibilityScore = compatibilityScore + 7;
//                    break;
//                case "D":
//                    compatibilityScore = compatibilityScore + 5;
//                    break;
//                case "A":
//                    compatibilityScore = compatibilityScore + 3;
//                    break;
//            }
//        }
//
//        if ( mBoyAnswers.get(1).equals("C")) {
//
//            switch (mGirlAnswers.get(1)) {
//
//                case "C":
//                    compatibilityScore = compatibilityScore + 10;
//                    break;
//                case "D":
//                    compatibilityScore = compatibilityScore + 7;
//                    break;
//                case "A":
//                    compatibilityScore = compatibilityScore + 5;
//                    break;
//                case "B":
//                    compatibilityScore = compatibilityScore + 3;
//                    break;
//            }
//        }
//
//        if ( mBoyAnswers.get(1).equals("D")) {
//
//            switch (mGirlAnswers.get(1)) {
//
//                case "D":
//                    compatibilityScore = compatibilityScore + 10;
//                    break;
//                case "A":
//                    compatibilityScore = compatibilityScore + 7;
//                    break;
//                case "B":
//                    compatibilityScore = compatibilityScore + 5;
//                    break;
//                case "C":
//                    compatibilityScore = compatibilityScore + 3;
//                    break;
//            }
//        }
//
//        resultSummary = resultSummary + boyName + "Question 2 evaluated\n";
//        adviceSummary = adviceSummary + girlName + "advice for question 1 generated\n";
//
//        //evaluation of question 3
//        if (mBoyAnswers.get(2).equals("A")) {
//
//            switch (mGirlAnswers.get(2)) {
//
//                case "A":
//                    compatibilityScore = compatibilityScore + 10;
//                    break;
//                case "B":
//                    compatibilityScore = compatibilityScore + 7;
//                    break;
//                case "C":
//                    compatibilityScore = compatibilityScore + 5;
//                    break;
//                case "D":
//                    compatibilityScore = compatibilityScore + 3;
//                    break;
//            }
//        }
//
//        if ( mBoyAnswers.get(2).equals("B")) {
//
//            switch (mGirlAnswers.get(2)) {
//
//                case "B":
//                    compatibilityScore = compatibilityScore + 10;
//                    break;
//                case "C":
//                    compatibilityScore = compatibilityScore + 7;
//                    break;
//                case "D":
//                    compatibilityScore = compatibilityScore + 5;
//                    break;
//                case "A":
//                    compatibilityScore = compatibilityScore + 3;
//                    break;
//            }
//        }
//
//        if ( mBoyAnswers.get(2).equals("C")) {
//
//            switch (mGirlAnswers.get(2)) {
//
//                case "C":
//                    compatibilityScore = compatibilityScore + 10;
//                    break;
//                case "D":
//                    compatibilityScore = compatibilityScore + 7;
//                    break;
//                case "A":
//                    compatibilityScore = compatibilityScore + 5;
//                    break;
//                case "B":
//                    compatibilityScore = compatibilityScore + 3;
//                    break;
//            }
//        }
//
//        if ( mBoyAnswers.get(2).equals("D")) {
//
//            switch (mGirlAnswers.get(2)) {
//
//                case "D":
//                    compatibilityScore = compatibilityScore + 10;
//                    break;
//                case "A":
//                    compatibilityScore = compatibilityScore + 7;
//                    break;
//                case "B":
//                    compatibilityScore = compatibilityScore + 5;
//                    break;
//                case "C":
//                    compatibilityScore = compatibilityScore + 3;
//                    break;
//            }
//        }
//
//        //set the compatibility Score Feedback
//        if ( compatibilityScore < 30 ) {
//            compatibilityScoreFeedback = "You need to pay close attention to our advice";
//        } else if ( compatibilityScore > 29 && compatibilityScore < 50) {
//            compatibilityScoreFeedback = "You seem to doing fine but you should do better to reach a blissful state";
//        } else if ( compatibilityScore > 49 && compatibilityScore < 90) {
//            compatibilityScoreFeedback = "Great! you both have something close to an match and mindset, follow our advice to score 90+ next time";
//        } else {
//            compatibilityScoreFeedback = "Amazing! you set an example to all the couples out there, keep it up but do take a note of our advice below";
//        }
//
//        resultSummary = resultSummary + boyName + "Question 3 evaluated\n";
//        adviceSummary = adviceSummary + girlName + "advice for question 3 generated\n resultPaper4\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
//
//        ReportObject relationshipQuestionPaper4ReportObject = new ReportObject(boyName, girlName,
//                resultSummary, compatibilityScore,compatibilityScoreFeedback, adviceSummary);
//
//        return relationshipQuestionPaper4ReportObject;
//    }
//
//}

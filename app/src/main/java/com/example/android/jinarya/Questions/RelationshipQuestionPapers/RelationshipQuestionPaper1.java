package com.example.android.jinarya.Questions.RelationshipQuestionPapers;

import com.example.android.jinarya.Objects.Option;
import com.example.android.jinarya.Objects.QuestionsAndOptions;
import com.example.android.jinarya.Objects.ReportObject;
import com.example.android.jinarya.R;
import java.util.ArrayList;

/**
 * Created by Aadhyamo on 31/12/17.
 */

public class RelationshipQuestionPaper1  {

    //no constructor so a default constructor will be called by android studio

    //female Questions
    public static QuestionsAndOptions RQP1FQ1 = new QuestionsAndOptions(
            "What is important for you in a Relationship ? (F)",
            new Option("I simply want to be around my partner", "A"),
            new Option("Companionship", "B"),
            new Option("Money and wealth", "C"),
            new Option("My partners’ Family background", "D")
            );



    public static QuestionsAndOptions RQP1FQ2 = new QuestionsAndOptions(
            "Which of this you think is the most important to you in a Relationship ?",
            new Option("Trust", "A"),
            new Option("Communication", "B"),
            new Option("Sharing & Caring", "C"),
            new Option("Clear boundaries are necessary in any Relationship", "D")
            );

    public static QuestionsAndOptions RQP1FQ3 = new QuestionsAndOptions(
            "Which member of your Family are you closest with ?",
            new Option("Your Dad", "A"),
            new Option("Your Mom", "B"),
            new Option("Your Sibling", "C"),
            new Option("Other members of your Family", "D")
            );

    public static QuestionsAndOptions RQP1FQ4 = new QuestionsAndOptions(
            "In a relationship, What would you do to make it Healthier and Happier ?",
            new Option("The responsibility is equal, I will reciprocate based on what my partner does", "A"),
            new Option("I will care for myself first", "B"),
            new Option("I will share my partner’s burdens and worries", "C"),
            new Option("It’s my partner’s primary responsibility to ensure I am happy, so  I expect my partner to share all my responsibilities and care for me", "D")
            );

    public static QuestionsAndOptions RQP1FQ5 = new QuestionsAndOptions(
            "If you have had a bad day, you wish",
            new Option("To be alone", "A"),
            new Option("Would want my partner to be around me and cheer me up", "B"),
            new Option("Would prefer to be with my friends", "C"),
            new Option("Would pray or meditate", "D")
            );

    public static QuestionsAndOptions RQP1FQ6 = new QuestionsAndOptions(
            "If you were certain that you were right and your partner is wrong, would you take a stand even if that would create a scene in public or would you let it go?",
            new Option("I would let it go Gracefully", "A"),
            new Option("I would take a stand Firmly", "B"),
            new Option("I would may be withdraw but Sulk", "C"),
            new Option("I would try to convince my partner without creating a scene.", "D")
            );

    public static QuestionsAndOptions RQP1FQ7 = new QuestionsAndOptions(
            "Do you plan out your Day ?",
            new Option("No, I go with the Flow", "A"),
            new Option("Yes I plan my day Well", "B"),
            new Option("Plan very Little", "C"),
            new Option("Others tell me What to do", "D")
            );

    public static QuestionsAndOptions RQP1FQ8 = new QuestionsAndOptions(
            "How should Finances be managed by a Couple?",
            new Option("Discuss and Decide accordingly", "A"),
            new Option("It’s each partner’s choice as to what do with their Money", "B"),
            new Option("Pool the resources, Plan spending and Saving", "C"),
            new Option("Let my partner take the Financial decisions, I don't mind just Complying", "D")
            );

    public static QuestionsAndOptions RQP1FQ9 = new QuestionsAndOptions(
            "As a couple, do you think it’s necessary to share a common Future Vision?",
            new Option("Not necessary, we can have our own different Visions", "A"),
            new Option("Let my partner do the thinking, I will Follow", "B"),
            new Option("Yes, but yet to figure it Out", "C"),
            new Option("Certainly yes, we talk about it Frequently", "D")
            );

    public static QuestionsAndOptions RQP1FQ10 = new QuestionsAndOptions(
            "What is your typical reaction when someone criticizes you ? (F)",
            new Option("I get upset and withdraw", "A"),
            new Option("I give back immediately", "B"),
            new Option("I hold back my anger and wait for the right time to give back", "C"),
            new Option("I think if there was a truth in what they said", "D")
            );



    public static ArrayList<QuestionsAndOptions> RQP1GirlQuestions() {

        ArrayList<QuestionsAndOptions> QuestionPaper1FemaleQuestions= new ArrayList<>();
        QuestionPaper1FemaleQuestions.add(RQP1FQ1);
        QuestionPaper1FemaleQuestions.add(RQP1FQ2);
        QuestionPaper1FemaleQuestions.add(RQP1FQ3);
        QuestionPaper1FemaleQuestions.add(RQP1FQ4);
        QuestionPaper1FemaleQuestions.add(RQP1FQ5);
        QuestionPaper1FemaleQuestions.add(RQP1FQ6);
        QuestionPaper1FemaleQuestions.add(RQP1FQ7);
        QuestionPaper1FemaleQuestions.add(RQP1FQ8);
        QuestionPaper1FemaleQuestions.add(RQP1FQ9);
        QuestionPaper1FemaleQuestions.add(RQP1FQ10);

        return QuestionPaper1FemaleQuestions;
    }


    // male Questions
    public static QuestionsAndOptions RQP1MQ1 = new QuestionsAndOptions(
            "What is important for you in a Relationship ? (F)",
            new Option("I simply want to be around my partner", "A"),
            new Option("Companionship", "B"),
            new Option("Money and wealth", "C"),
            new Option("My partners’ Family background", "D")
    );



    public static QuestionsAndOptions RQP1MQ2 = new QuestionsAndOptions(
            "Which of this you think is the most important to you in a Relationship ?",
            new Option("Trust", "A"),
            new Option("Communication", "B"),
            new Option("Sharing & Caring", "C"),
            new Option("Clear boundaries are necessary in any Relationship", "D")
    );

    public static QuestionsAndOptions RQP1MQ3 = new QuestionsAndOptions(
            "Which member of your Family are you closest with ?",
            new Option("Your Dad", "A"),
            new Option("Your Mom", "B"),
            new Option("Your Sibling", "C"),
            new Option("Other members of your Family", "D")
    );

    public static QuestionsAndOptions RQP1MQ4 = new QuestionsAndOptions(
            "In a relationship, What would you do to make it Healthier and Happier ?",
            new Option("The responsibility is equal, I will reciprocate based on what my partner does", "A"),
            new Option("I will care for myself first", "B"),
            new Option("I will share my partner’s burdens and worries", "C"),
            new Option("It’s my partner’s primary responsibility to ensure I am happy, so  I expect my partner to share all my responsibilities and care for me", "D")
    );

    public static QuestionsAndOptions RQP1MQ5 = new QuestionsAndOptions(
            "If you have had a bad day, you wish",
            new Option("To be alone", "A"),
            new Option("Would want my partner to be around me and cheer me up", "B"),
            new Option("Would prefer to be with my friends", "C"),
            new Option("Would pray or meditate", "D")
    );

    public static QuestionsAndOptions RQP1MQ6 = new QuestionsAndOptions(
            "If you were certain that you were right and your partner is wrong, would you take a stand even if that would create a scene in public or would you let it go?",
            new Option("I would let it go Gracefully", "A"),
            new Option("I would take a stand Firmly", "B"),
            new Option("I would may be withdraw but Sulk", "C"),
            new Option("I would try to convince my partner without creating a scene.", "D")
    );

    public static QuestionsAndOptions RQP1MQ7 = new QuestionsAndOptions(
            "Do you plan out your Day ?",
            new Option("No, I go with the Flow", "A"),
            new Option("Yes I plan my day Well", "B"),
            new Option("Plan very Little", "C"),
            new Option("Others tell me What to do", "D")
    );

    public static QuestionsAndOptions RQP1MQ8 = new QuestionsAndOptions(
            "How should Finances be managed by a Couple?",
            new Option("Discuss and Decide accordingly", "A"),
            new Option("It’s each partner’s choice as to what do with their Money", "B"),
            new Option("Pool the resources, Plan spending and Saving", "C"),
            new Option("Let my partner take the Financial decisions, I don't mind just Complying", "D")
    );

    public static QuestionsAndOptions RQP1MQ9 = new QuestionsAndOptions(
            "As a couple, do you think it’s necessary to share a common Future Vision?",
            new Option("Not necessary, we can have our own different Visions", "A"),
            new Option("Let my partner do the thinking, I will Follow", "B"),
            new Option("Yes, but yet to figure it Out", "C"),
            new Option("Certainly yes, we talk about it Frequently", "D")
    );

    public static QuestionsAndOptions RQP1MQ10 = new QuestionsAndOptions(
            "What is your typical reaction when someone criticizes you ? (F)",
            new Option("I get upset and withdraw", "A"),
            new Option("I give back immediately", "B"),
            new Option("I hold back my anger and wait for the right time to give back", "C"),
            new Option("I think if there was a truth in what they said", "D")
    );

    public static ArrayList<QuestionsAndOptions> RQP1BoyQuestions() {

        ArrayList<QuestionsAndOptions> QuestionPaper1MaleQuestions = new ArrayList<>();
        QuestionPaper1MaleQuestions.add(RQP1MQ1);
        QuestionPaper1MaleQuestions.add(RQP1MQ2);
        QuestionPaper1MaleQuestions.add(RQP1MQ3);
        QuestionPaper1MaleQuestions.add(RQP1MQ4);
        QuestionPaper1MaleQuestions.add(RQP1MQ5);
        QuestionPaper1MaleQuestions.add(RQP1MQ6);
        QuestionPaper1MaleQuestions.add(RQP1MQ7);
        QuestionPaper1MaleQuestions.add(RQP1MQ8);
        QuestionPaper1MaleQuestions.add(RQP1MQ9);
        QuestionPaper1MaleQuestions.add(RQP1MQ10);

        return QuestionPaper1MaleQuestions;
    }



    public static ReportObject calculateRelationshipQuestionsPaper1Results
            (String girlName,
             String boyName,
             ArrayList<String> mGirlAnswers,
             ArrayList<String> mBoyAnswers) {

        int compatibilityScore = 0;
        String compatibilityScoreFeedback;
        String insightsForGirl = "";
        String insightsForBoy = "";
        String adviceForGirl = "";
        String adviceForBoy = "";

        //evaluation of question 1
        if (mGirlAnswers.get(0).equals("A")) {

            switch (mBoyAnswers.get(0)) {

                case "A":
                    compatibilityScore = compatibilityScore + 10;

                    insightsForGirl = "You are going to be the main focus of " + boyName + "’s life. You are likely to have a Healthy Relationship with him.";
                    insightsForBoy = "You are going to be " +  girlName + "’s main Focus. You are likely to have a Healthy Relationship with her. \n";

                    break;
                case "B":
                    compatibilityScore = compatibilityScore + 10;

                    insightsForGirl = boyName + " loves companionship… a matured partner. He is likely to go out of his way to keep you happy.";
                    insightsForBoy = "You are going to be " +  girlName + "’s main Focus. You are likely to have a Healthy Relationship with her.";
                    break;
                case "C":
                    compatibilityScore = compatibilityScore + 8;

                    insightsForGirl = "It appears that Money and Wealth would be " + boyName + "’s main priority and that is likely to dominate his main criteria in the relationship.";
                    insightsForBoy = "You are going to be " +  girlName + "’s main Focus. You are likely to have a Healthy Relationship with her.";

                    break;
                case "D":
                    compatibilityScore = compatibilityScore + 8;

                    insightsForGirl = boyName + "’s Partner’s Family name and Reputation are quite Important to him";
                    insightsForBoy = "You are going to be " +  girlName + "’s main Focus. You are likely to have a Healthy Relationship with her.";
                    break;
            }
        }

        if ( mGirlAnswers.get(0).equals("B")) {

            switch (mBoyAnswers.get(0)) {

                case "A":
                    compatibilityScore = compatibilityScore + 10;

                    insightsForGirl = "You are going to be the focus of " + boyName + "’s life. This is great for the relationship";
                    insightsForBoy = girlName + " loves companionship... a matured partner... she is likely to go out of her way to keep you happy";
                    break;
                case "B":
                    compatibilityScore = compatibilityScore + 10;

                    insightsForGirl = boyName + " loves companionship… a matured partner. He is likely to go out of his way to keep you happy. \n";
                    insightsForBoy = girlName + " loves companionship... a matured partner... she is likely to go out of her way to keep you happy";
                    break;
                case "C":
                    compatibilityScore = compatibilityScore + 8;

                    insightsForGirl = "Money and Wealth are quite Important to " + boyName;
                    insightsForBoy = girlName + " loves companionship... a matured partner... she is likely to go out of her way to keep you happy";
                    break;
                case "D":
                    compatibilityScore = compatibilityScore + 8;

                    insightsForGirl = "Family standing and Societal reputation of " + boyName + "’s Partner play an important role in his relationships. \n";
                    insightsForBoy = girlName + " loves companionship... a matured partner... she is likely to go out of her way to keep you happy";
                    break;
            }
        }

        if ( mGirlAnswers.get(0).equals("C")) {

            switch (mBoyAnswers.get(0)) {

                case "A":
                    compatibilityScore = compatibilityScore + 8;

                    insightsForGirl = "You are going to be the focus of " + boyName + "’s life. This is great for the relationship";
                    insightsForBoy = "Money and Wealth are quite Important to " + girlName;
                    break;
                case "B":
                    compatibilityScore = compatibilityScore + 8;

                    insightsForGirl = boyName + "loves companionship… a matured partner. He is likely to go out of his way to keep you happy.";
                    insightsForBoy = "It appears that Money and Wealth are quite Important to " + girlName;
                    break;
                case "C":
                    compatibilityScore = compatibilityScore + 8;

                    insightsForGirl = "It appears that Money and Wealth are quite important to " + boyName;
                    insightsForBoy = "It appears that Money and Wealth are quite Important to " + girlName;
                    break;
                case "D":
                    compatibilityScore = compatibilityScore + 8;

                    insightsForGirl = "Family standing and Societal reputation of " + boyName + "’s Partner is quite important to him.";
                    insightsForBoy = "It appears that Money and Wealth are quite Important to " + girlName;
                    break;
            }

        }

        if ( mGirlAnswers.get(0).equals("D")) {

            switch (mBoyAnswers.get(0)) {

                case "A":
                    compatibilityScore = compatibilityScore + 8;

                    insightsForGirl = "You are going to be the focus of " + boyName + "’s life. This is great for the relationship";
                    insightsForBoy = "Family standing and Societal reputation of " + girlName + "’s Partner is quite important to her";
                    break;
                case "B":
                    compatibilityScore = compatibilityScore + 8;

                    insightsForGirl = boyName + " loves companionship… a matured partner. He is likely to go out of his way to keep you happy.";
                    insightsForBoy = "Family standing and Societal reputation of " + girlName + "’s Partner is quite important to her";
                    break;
                case "C":
                    compatibilityScore = compatibilityScore + 3;

                    insightsForGirl = "Money and Wealth likely to dominate " + boyName + "'s relationship preferences";
                    insightsForBoy = "Family standing and Societal reputation of " + girlName + "’s Partner is quite important to her";
                    break;
                case "D":
                    compatibilityScore = compatibilityScore + 3;

                    insightsForGirl = "Family standing and Societal reputation of " + boyName + "’s Partner is quite important to him";
                    insightsForBoy = "Family standing and Societal reputation of " + girlName + "’s Partner is quite important to her";
                    break;
            }

            insightsForGirl = insightsForGirl + "\n";
            insightsForBoy = insightsForBoy + "\n";
        }

        //evaluation of question 2
        if (mGirlAnswers.get(1).equals("A")) {

            switch (mBoyAnswers.get(1)) {

                case "A":
                    compatibilityScore = compatibilityScore + 10;
                    adviceForGirl = " Trust in a relationship is very importance to " + boyName + ". So, never lie to him and never hide secrets from him.";
                    adviceForBoy = "Trust in a relationship is of paramount importance to " + girlName + " in a relationship. So, never lie to her and never hide secrets from her. ";
                    break;
                case "B":
                    compatibilityScore = compatibilityScore + 10;
                    adviceForGirl = "Communicate freely with " + boyName + " and share how you feel often. That will make him happy, He likes to Communicate with his Partner. ";
                    adviceForBoy = " Trust in a relationship is of paramount importance to " + girlName + " in a relationship. So, never lie to her and never hide secrets from her.";
                    break;
                case "C":
                    compatibilityScore = compatibilityScore + 10;
                    adviceForGirl = boyName + " believes that in a relationship, each partner has to care for other partner and share everything with each other. So, don't hesitate to express concern and show Love as much as you can. ";
                    adviceForBoy = " Trust in a relationship is of paramount importance to " + girlName + " in a relationship. So, never lie to her and never hide secrets from her.";
                    break;
                case "D":
                    compatibilityScore = compatibilityScore + 8;
                    adviceForGirl = boyName + " appears to believe in a Line of Control between each other and will likely be disturbed when you cross his comfort zone. So try not to invade his privacy.";
                    adviceForBoy = "Trust in a relationship is of paramount importance to " + girlName + " in a relationship. So, never lie to her and never hide secrets from her. ";
                    break;
            }
        }

        if ( mGirlAnswers.get(1).equals("B")) {

            switch (mBoyAnswers.get(1)) {

                case "A":
                    compatibilityScore = compatibilityScore + 10;
                    adviceForGirl = "Trust in a relationship is very important to " + boyName + " in a relationship. So, never lie to him and never hide secrets from him. ";
                    adviceForBoy = " Communicate freely with " + girlName + " and share how you feel often. That will make her happy as she likes to Communicate with her Partner.";
                    break;
                case "B":
                    compatibilityScore = compatibilityScore + 10;
                    adviceForGirl = "Communicate freely with " + boyName + " and share how you feel often. That will make him happy, he likes to Communicate with his Partner. ";
                    adviceForBoy = insightsForGirl + " Communicate freely with " + girlName + " and share how you feel often. That will make her happy as she likes to Communicate with her Partner.";
                    break;
                case "C":
                    compatibilityScore = compatibilityScore + 10;
                    adviceForGirl = boyName + " believes that in a relationship, each partner has to care for the other partner and share everything with each other. So, don't hold back from expressing Love and concern for him.";
                    adviceForBoy = " Communicate freely with " + girlName + " and share how you feel often. That will make her happy as she likes to Communicate with her Partner.";
                    break;
                case "D":
                    compatibilityScore = compatibilityScore + 8;
                    adviceForGirl = boyName + " appears to believe in Line of Privacy between each other and likely to be disturbed when you cross his comfort zone. So try not to invade his privacy. ";
                    adviceForBoy = "Communicate freely with " + girlName + " and share how you feel often. That will make her happy, she likes to communicate with her Partner. ";
                    break;
            }
        }

        if ( mGirlAnswers.get(1).equals("C")) {

            switch (mBoyAnswers.get(1)) {

                case "A":
                    compatibilityScore = compatibilityScore + 10;
                    adviceForGirl = "Trust in a relationship is very important to " + boyName + " in a relationship. So, never lie to him and never hide secrets from him. ";
                    adviceForBoy = girlName + " believes that in a relationship, each partner has to care for other partner and share everything with each other. So don't hesitate to, express concern and love always. ";
                    break;
                case "B":
                    compatibilityScore = compatibilityScore + 10;
                    adviceForGirl = " Communicate freely with " + boyName + " and share how you feel often. That will make him happy as he likes to Communicate with his Partner.";
                    adviceForBoy = "" + girlName + " believes that in a relationship, each partner has to care for other partner and share everything with each other. So don't hesitate to, express concern and love always. ";
                    break;
                case "C":
                    compatibilityScore = compatibilityScore + 10;
                    adviceForGirl = "Express Love and concern to " + boyName + ", He will love it";
                    adviceForBoy = "" + girlName + " believes that in a relationship, each partner has to care for other partner and share everything with each other. So don't hesitate to, express concern and love always. ";
                    break;
                case "D":
                    compatibilityScore = compatibilityScore + 8;
                    adviceForGirl = boyName + " believes in a Line of Control between each other and likely to be disturbed when you cross his comfort zone. So try not to invade his privacy. ";
                    adviceForBoy = "" + girlName + " believes that in a relationship, each partner has to care for other partner and share everything with each other. So don't hesitate to, express concern and love always. ";
                    break;
            }
        }

        if ( mGirlAnswers.get(1).equals("D")) {

            switch (mBoyAnswers.get(1)) {

                case "A":
                    compatibilityScore = compatibilityScore + 8;
                    adviceForGirl = "Trust in a relationship is very important to " + boyName + " in a relationship. So, never lie to him and never keep secrets from him. ";
                    adviceForBoy = "Express Love and concern to " + girlName + ", She will love it";
                    break;
                case "B":
                    compatibilityScore = compatibilityScore + 8;
                    adviceForGirl ="Communicate freely with " + boyName + " and share how you feel often. That will make him happy, He likes to communicate with her Partner.";
                    adviceForBoy = "Express Love and concern to " + girlName + ", She will love it";
                    break;
                case "C":
                    compatibilityScore = compatibilityScore + 8;
                    adviceForGirl = "Express Love and concern to " + boyName + ", He will love it";
                    adviceForBoy = "Express Love and concern to " + girlName + ", She will love it";
                    break;
                case "D":
                    compatibilityScore = compatibilityScore + 5;
                    adviceForGirl = boyName + "believes in Line of Privacy between each other and will likely feel uncomfortable when you cross his comfort zone. So try not to invade his privacy.";
                    adviceForBoy = "Express Love and concern to " + girlName + ", She will love it";
                    break;
            }
            adviceForGirl = adviceForGirl + "\n";
            adviceForBoy = adviceForBoy + "\n";
        }

        //evaluation of question 3
        if (mGirlAnswers.get(2).equals("A")) {

            switch (mBoyAnswers.get(2)) {

                case "A":
                    compatibilityScore = compatibilityScore + 10;
                    break;
                case "B":
                    compatibilityScore = compatibilityScore + 7;
                    break;
                case "C":
                    compatibilityScore = compatibilityScore + 5;
                    break;
                case "D":
                    compatibilityScore = compatibilityScore + 3;
                    break;
            }
        }

        if ( mGirlAnswers.get(2).equals("B")) {

            switch (mBoyAnswers.get(2)) {

                case "A":
                    compatibilityScore = compatibilityScore + 10;
                    break;
                case "B":
                    compatibilityScore = compatibilityScore + 7;
                    break;
                case "C":
                    compatibilityScore = compatibilityScore + 5;
                    break;
                case "D":
                    compatibilityScore = compatibilityScore + 3;
                    break;
            }
        }

        if ( mGirlAnswers.get(2).equals("C")) {

            switch (mBoyAnswers.get(2)) {

                case "A":
                    compatibilityScore = compatibilityScore + 10;
                    break;
                case "B":
                    compatibilityScore = compatibilityScore + 7;
                    break;
                case "C":
                    compatibilityScore = compatibilityScore + 5;
                    break;
                case "D":
                    compatibilityScore = compatibilityScore + 3;
                    break;
            }
        }

        if ( mGirlAnswers.get(2).equals("D")) {

            switch (mBoyAnswers.get(2)) {

                case "A":
                    compatibilityScore = compatibilityScore + 10;
                    break;
                case "B":
                    compatibilityScore = compatibilityScore + 7;
                    break;
                case "C":
                    compatibilityScore = compatibilityScore + 5;
                    break;
                case "D":
                    compatibilityScore = compatibilityScore + 3;
                    break;
            }
        }

        //evaluation of question 4
        if (mGirlAnswers.get(3).equals("A")) {

            switch (mBoyAnswers.get(3)) {

                case "A":
                    compatibilityScore = compatibilityScore + 5;

                    insightsForGirl = insightsForGirl + " He expects equal participation and caring from his partner in keeping the relationship Healthy and Strong. ";
                    insightsForBoy = insightsForBoy + " She expects equal participation and caring from her partner in keeping the Relationship Healthy and Strong.";
                    break;
                case "B":
                    compatibilityScore = compatibilityScore + 3;

                    insightsForGirl = insightsForGirl + "Appears, self is his first priority in a relationship.";
                    insightsForBoy = insightsForBoy + " She expects equal participation and caring from you in keeping the Relationship Healthy and Strong.";
                    break;
                case "C":
                    compatibilityScore = compatibilityScore + 8;

                    insightsForGirl = insightsForGirl + " he is likely to share your responsibilities and worries and help you relax.";
                    insightsForBoy = insightsForBoy + " she expects equal participation and caring from her partner in keeping the Relationship Healthy and Strong.";
                    break;
                case "D":
                    compatibilityScore = compatibilityScore + 3;

                    insightsForGirl = insightsForGirl + " he is likely to expect that it’s your priority and responsibility to keep him happy and care for him.";
                    insightsForBoy = insightsForBoy + "she expects equal participation and caring from you in keeping the Relationship Healthy and Strong.";
                    break;
            }
        }

        if ( mGirlAnswers.get(3).equals("B")) {

            switch (mBoyAnswers.get(3)) {

                case "A":
                    compatibilityScore = compatibilityScore + 5;

                    insightsForGirl = insightsForGirl +  " expects equal participation and caring from you in keeping the Relationship Healthy and Strong.";
                    insightsForBoy = insightsForBoy + "Appears, self is her first priority in her relationship.";
                    break;
                case "B":
                    compatibilityScore = compatibilityScore + 3;

                    insightsForGirl = insightsForGirl + "Appears, self is his first priority in his relationship.";
                    insightsForBoy = insightsForBoy + "Appears, self is her first priority in her relationship.";
                    break;
                case "C":
                    compatibilityScore = compatibilityScore + 8;

                    insightsForGirl = insightsForGirl + boyName + " is likely to share your responsibilities and worries and help you relax.";
                    insightsForBoy = insightsForBoy + "Appears, self is her first priority in her relationship.";
                    break;
                case "D":
                    compatibilityScore = compatibilityScore + 3;

                    insightsForGirl = insightsForGirl + boyName + " is likely to expect that it’s your priority and responsibility to keep him happy and care for him.";
                    insightsForBoy = insightsForBoy + "Appears, self is her first priority in her relationship.";
                    break;
            }

        }

        if ( mGirlAnswers.get(3).equals("C")) {

            switch (mBoyAnswers.get(3)) {

                case "A":
                    compatibilityScore = compatibilityScore + 8;

                    insightsForGirl = insightsForGirl + " He expects equal participation and caring from you in keeping the healthy relationship Healthy and Strong.";
                    insightsForBoy = insightsForBoy + " She is likely to share your responsibilities and worries and help you relax.";
                    break;
                case "B":
                    compatibilityScore = compatibilityScore + 5;

                    insightsForGirl = insightsForGirl + "Appears, self is his first priority in his relationship.";
                    insightsForBoy = insightsForBoy + " She is likely to share your responsibilities and worries and help you relax.";
                    break;
                case "C":
                    compatibilityScore = compatibilityScore + 10;

                    insightsForGirl = insightsForGirl + " He is likely to share your responsibilities and worries and help you relax.";
                    insightsForBoy = insightsForBoy + " She is likely to share your responsibilities and worries and help you relax.";
                    break;
                case "D":
                    compatibilityScore = compatibilityScore + 8;

                    insightsForGirl = insightsForGirl + " He is likely to expect that it’s your priority and responsibility to keep him happy and care for him.";
                    insightsForBoy = insightsForBoy + " She is likely to share your responsibilities and worries and help you relax.";
                    break;
            }
        }

        if ( mGirlAnswers.get(3).equals("D")) {

            switch (mBoyAnswers.get(3)) {

                case "A":
                    compatibilityScore = compatibilityScore + 5;

                    insightsForGirl = insightsForGirl + " He expects equal participation and caring from his partner in keeping the relationship Healthy and Strong.";
                    insightsForBoy = insightsForBoy + " She is likely to expect it’s her  partner’s responsibility to keep her happy and to care for her.";
                    break;
                case "B":
                    compatibilityScore = compatibilityScore + 3;

                    insightsForGirl = insightsForGirl + "Appears, self is his first priority in his relationship.";
                    insightsForBoy = insightsForBoy + " She is likely to expect it’s her  partner’s responsibility to keep her happy and to care for her.";
                    break;
                case "C":
                    compatibilityScore = compatibilityScore + 8;

                    insightsForGirl = insightsForGirl + " He is likely to share your responsibilities and worries and help you relax.";
                    insightsForBoy = insightsForBoy + " She is likely to expect it’s her  partner’s responsibility to keep her happy and to care for her.";
                    break;
                case "D":
                    compatibilityScore = compatibilityScore + 3;

                    insightsForGirl = insightsForGirl + " She is likely to expect it’s his partner’s responsibility to keep him happy and to care for him.";
                    insightsForBoy = insightsForBoy + " She is likely to expect it’s her  partner’s responsibility to keep her happy and to care for her.";
                    break;
            }
        }

        //evaluation of question 5
        if (mGirlAnswers.get(4).equals("A")) {

            switch (mBoyAnswers.get(4)) {

                case "A":
                    compatibilityScore = compatibilityScore + 5;

                    insightsForGirl = insightsForGirl + " He wishes to be left alone if he had a bad day.";
                    insightsForBoy = insightsForBoy + " She wishes to be left alone if she had a bad day.";
                    break;
                case "B":
                    compatibilityScore = compatibilityScore + 8;

                    insightsForGirl = insightsForGirl + " He wishes you to be around and cheer him up if he has had a bad day.";
                    insightsForBoy = insightsForBoy + " She wishes to be left alone if she had a bad day.";
                    break;
                case "C":
                    compatibilityScore = compatibilityScore + 5;

                    insightsForGirl = insightsForGirl + " He wishes to be with his friends if he had a bad day and relax himself.";
                    insightsForBoy = insightsForBoy + " She wishes to be left alone if she had a bad day.";
                    break;
                case "D":
                    compatibilityScore = compatibilityScore + 5;

                    insightsForGirl = insightsForGirl + " He wishes to pray and meditate if he had bad day.";
                    insightsForBoy = insightsForBoy + " She wishes to be left alone if she had a bad day.";
                    break;
            }
        }

        if ( mGirlAnswers.get(4).equals("B")) {

            switch (mBoyAnswers.get(4)) {

                case "A":
                    compatibilityScore = compatibilityScore + 8;

                    insightsForGirl = insightsForGirl + " He wishes to be left alone if he had a bad day.";
                    insightsForBoy = insightsForBoy + " She wishes you to be around and cheer her up if she had a bad day.";
                    break;
                case "B":
                    compatibilityScore = compatibilityScore + 10;

                    insightsForGirl = insightsForGirl + " He wishes you to be around and cheer him up if he had a bad day";
                    insightsForBoy = insightsForBoy + " She wishes you to be around and cheer her up if she had a bad day.";
                    break;
                case "C":
                    compatibilityScore = compatibilityScore + 8;

                    insightsForGirl = insightsForGirl + " He wishes to be with his friends if he had a bad day and relax himself.";
                    insightsForBoy = insightsForBoy + " She wishes you to be around and cheer her up if she had a bad day.";
                    break;
                case "D":
                    compatibilityScore = compatibilityScore + 8;

                    insightsForGirl = insightsForGirl + " He wishes to pray and meditate if he had bad day.";
                    insightsForBoy = insightsForBoy + " She wishes you to be around and cheer her up if she had a bad day.";
                    break;
            }
        }

        if ( mGirlAnswers.get(4).equals("C")) {

            switch (mBoyAnswers.get(4)) {

                case "A":
                    compatibilityScore = compatibilityScore + 5;

                    insightsForGirl = insightsForGirl + " He wishes to be left alone if he had a bad day.";
                    insightsForBoy = insightsForBoy + "She wishes to be with friends if she had a bad day and relax herself.";
                    break;
                case "B":
                    compatibilityScore = compatibilityScore + 8;

                    insightsForGirl = insightsForGirl + " He wishes you to be around and cheer him up if";
                    insightsForBoy = insightsForBoy + "She wishes to be with friends if she had a bad day and relax herself.";
                    break;
                case "C":
                    compatibilityScore = compatibilityScore + 3;

                    insightsForGirl = insightsForGirl + " He wishes to be with friends if he had a bad day and relax himself.";
                    insightsForBoy = insightsForBoy + "She wishes to be with friends if she had a bad day and relax herself.";
                    break;
                case "D":
                    compatibilityScore = compatibilityScore + 5;

                    insightsForGirl = insightsForGirl + " He wishes to pray and meditate if he had bad day.";
                    insightsForBoy = insightsForBoy + "She wishes to be with friends if she had a bad day and relax herself.";
                    break;
            }
        }

        if ( mGirlAnswers.get(4).equals("D")) {

            switch (mBoyAnswers.get(4)) {

                case "A":
                    compatibilityScore = compatibilityScore + 5;

                    insightsForGirl = insightsForGirl + "He wishes to be left alone if he had a bad day.";
                    insightsForBoy = insightsForBoy + "She wishes to pray and meditate if she had bad day.";
                    break;
                case "B":
                    compatibilityScore = compatibilityScore + 8;

                    insightsForGirl = insightsForGirl + "He wishes you to be around and cheer him up.";
                    insightsForBoy = insightsForBoy + "She wishes to pray and meditate if she had bad day.";
                    break;
                case "C":
                    compatibilityScore = compatibilityScore + 5;

                    insightsForGirl = insightsForGirl + "He wishes to be with friends if he had a bad day and relax himself.";
                    insightsForBoy = insightsForBoy + "She wishes to pray and meditate if she had bad day.";
                    break;
                case "D":
                    compatibilityScore = compatibilityScore + 8;

                    insightsForGirl = insightsForGirl + "He wishes to pray and meditate if he had bad day.";
                    insightsForBoy = insightsForBoy + "She wishes to pray and meditate if she had bad day.";
                    break;
            }
            insightsForGirl = insightsForGirl + "\n";
            insightsForBoy = insightsForBoy + "\n";
        }

        //evaluation of question 6
        if (mGirlAnswers.get(5).equals("A")) {

            switch (mBoyAnswers.get(5)) {

                case "A":
                    compatibilityScore = compatibilityScore + 10;
                    break;
                case "B":
                    compatibilityScore = compatibilityScore + 8;
                    break;
                case "C":
                    compatibilityScore = compatibilityScore + 5;
                    break;
                case "D":
                    compatibilityScore = compatibilityScore + 10;
                    break;
            }
        }

        if ( mGirlAnswers.get(5).equals("B")) {

            switch (mBoyAnswers.get(5)) {

                case "A":
                    compatibilityScore = compatibilityScore + 8;
                    break;
                case "B":
                    compatibilityScore = compatibilityScore + 3;
                    break;
                case "C":
                    compatibilityScore = compatibilityScore + 3;
                    break;
                case "D":
                    compatibilityScore = compatibilityScore + 5;
                    break;
            }
        }

        if ( mGirlAnswers.get(5).equals("C")) {

            switch (mBoyAnswers.get(5)) {

                case "A":
                    compatibilityScore = compatibilityScore + 8;
                    break;
                case "B":
                    compatibilityScore = compatibilityScore + 3;
                    break;
                case "C":
                    compatibilityScore = compatibilityScore + 3;
                    break;
                case "D":
                    compatibilityScore = compatibilityScore + 8;
                    break;
            }
        }

        if ( mGirlAnswers.get(5).equals("D")) {

            switch (mBoyAnswers.get(5)) {

                case "A":
                    compatibilityScore = compatibilityScore + 10;
                    break;
                case "B":
                    compatibilityScore = compatibilityScore + 8;
                    break;
                case "C":
                    compatibilityScore = compatibilityScore + 8;
                    break;
                case "D":
                    compatibilityScore = compatibilityScore + 10;
                    break;
            }
        }

        //evaluation of question 7
        if (mGirlAnswers.get(6).equals("A")) {

            switch (mBoyAnswers.get(6)) {

                case "A":
                    compatibilityScore = compatibilityScore + 5;
                    break;
                case "B":
                    compatibilityScore = compatibilityScore + 5;
                    break;
                case "C":
                    compatibilityScore = compatibilityScore + 8;
                    break;
                case "D":
                    compatibilityScore = compatibilityScore + 3;
                    break;
            }
        }

        if ( mGirlAnswers.get(6).equals("B")) {

            switch (mBoyAnswers.get(6)) {

                case "A":
                    compatibilityScore = compatibilityScore + 8;
                    break;
                case "B":
                    compatibilityScore = compatibilityScore + 10;
                    break;
                case "C":
                    compatibilityScore = compatibilityScore + 5;
                    break;
                case "D":
                    compatibilityScore = compatibilityScore + 5;
                    break;
            }
        }

        if ( mGirlAnswers.get(6).equals("C")) {

            switch (mBoyAnswers.get(6)) {

                case "A":
                    compatibilityScore = compatibilityScore + 5;
                    break;
                case "B":
                    compatibilityScore = compatibilityScore + 8;
                    break;
                case "C":
                    compatibilityScore = compatibilityScore + 8;
                    break;
                case "D":
                    compatibilityScore = compatibilityScore + 5;
                    break;
            }
        }

        if ( mGirlAnswers.get(6).equals("D")) {

            switch (mBoyAnswers.get(6)) {

                case "A":
                    compatibilityScore = compatibilityScore + 3;
                    break;
                case "B":
                    compatibilityScore = compatibilityScore + 8;
                    break;
                case "C":
                    compatibilityScore = compatibilityScore + 5;
                    break;
                case "D":
                    compatibilityScore = compatibilityScore + 3;
                    break;
            }
        }

        //evaluation of question 8
        if (mGirlAnswers.get(7).equals("A")) {

            switch (mBoyAnswers.get(7)) {

                case "A":
                    compatibilityScore = compatibilityScore + 10;
                    adviceForGirl = adviceForGirl + " Discuss and participate well in the conversations when it comes to planning Family Finances, He would prefer if his partner to participate in these conversations ";
                    adviceForBoy = adviceForBoy + " Discuss and participate well in the conversations when it comes to planning Family Finances, She would prefer if her partner to participate in these conversations ";
                    break;
                case "B":
                    compatibilityScore = compatibilityScore + 5;
                    adviceForGirl = adviceForGirl +  " He appears to be believe in individual’s responsibility to manage the personal money. A more ideal approach would be to pool and plan the resources jointly. You may need to discuss regarding this so you can plan together. ";
                    adviceForBoy = adviceForBoy + " Discuss and participate well in the conversations when it comes to planning Family Finances, She would prefer if her partner to participate in these conversations ";
                    break;
                case "C":
                    compatibilityScore = compatibilityScore + 10;
                    adviceForGirl = adviceForGirl + " He believes in combining the Financial resources of both to achieve the financial goals according to a plan. An ideal approach in managing joint resources. An excellent choice.";
                    adviceForBoy = adviceForBoy + " Discuss and participate well in the conversations when it comes to planning Family Finances, She would prefer if her partner to participate in these conversations ";
                    break;
                case "D":
                    compatibilityScore = compatibilityScore + 5;
                    adviceForGirl = adviceForGirl + " He appears to be believing in individual’s responsibility in managing the personal money. You may need to discuss regarding this so you can plan together. ";
                    adviceForBoy = adviceForBoy + " Discuss and participate well in the conversations when it comes to planning Family Finances, She would prefer if her partner to participate in these conversations ";
                    break;
            }
        }

        if ( mGirlAnswers.get(7).equals("B")) {

            switch (mBoyAnswers.get(7)) {

                case "A":
                    compatibilityScore = compatibilityScore + 8;
                    adviceForGirl = adviceForGirl + " He believes in joint responsibility and management of Family Finances. So participate well in these discussions and give your inputs. That is likely to increase the bonding between the two of you in the relationship.";
                    adviceForBoy = adviceForBoy + " She appears to be believing in individual’s responsibility in managing the personal money.You may need to discuss regarding this so you can plan together.";
                    break;
                case "B":
                    compatibilityScore = compatibilityScore + 3;
                    adviceForGirl = adviceForGirl + " He appears to be believing in individual’s responsibility in managing the personal money.  You may need to discuss regarding this so you can plan together. ";
                    adviceForBoy = adviceForBoy + " She appears to be believing in individual’s responsibility in managing the personal money. You may need to discuss regarding this so you can plan together. ";
                    break;
                case "C":
                    compatibilityScore = compatibilityScore + 8;
                    adviceForGirl = adviceForGirl + " He Believes in combining the Financial resources of both to achieve the Financial goals according to a plan. A healthy approach in managing joint resources. An excellent choice. ";
                    adviceForBoy = adviceForBoy + " She appears to be believing in individual’s responsibility in managing the personal money.  You may need to discuss regarding this so you can plan together. ";
                    break;
                case "D":
                    compatibilityScore = compatibilityScore + 3;
                    adviceForGirl = adviceForGirl + " He appears to be believing in individual’s responsibility in managing the personal money.  You may need to discuss regarding this so you can plan together. ";
                    adviceForBoy = adviceForBoy + " She appears to be believing in individual’s responsibility in managing the personal money.  You may need to discuss regarding this so you can plan together. ";
                    break;
            }
        }

        if ( mGirlAnswers.get(7).equals("C")) {

            switch (mBoyAnswers.get(7)) {

                case "A":
                    compatibilityScore = compatibilityScore + 10;
                    adviceForGirl = adviceForGirl + " He believes in joint responsibility and management of Family Finances. So participate well in these discussions and give your inputs. That is likely to increase the bonding between the two of you in the relationship.\n ";
                    adviceForBoy = adviceForBoy + " She Believes in combining the Financial resources of both to achieve the financial goals according to a plan. A healthy approach in managing joint resources. An excellent choice. ";
                    break;
                case "B":
                    compatibilityScore = compatibilityScore + 8;
                    adviceForGirl = adviceForGirl + " He appears to be believing in individual’s responsibility in managing the personal money. You may need to discuss regarding this so you can plan together. ";
                    adviceForBoy = adviceForBoy + " She Believes in combining the Financial resources of both to achieve the financial goals according to a plan. A healthy approach in managing joint resources. An excellent choice. ";
                    break;
                case "C":
                    compatibilityScore = compatibilityScore + 10;
                    adviceForGirl = adviceForGirl + " He Believes in combining the resources of both to achieve the financial goals according to a plan. A healthy approach in managing joint resources. An excellent choice. ";
                    adviceForBoy = adviceForBoy + " She Believes in combining the Financial resources of both to achieve the financial goals according to a plan. A healthy approach in managing joint resources. An excellent choice. ";
                    break;
                case "D":
                    compatibilityScore = compatibilityScore + 8;
                    adviceForGirl = adviceForGirl + " He is likely to throw the burden of Financial Management on you. A more Responsible approach would be to pool and manage the resources jointly. You may need to discuss regarding this so you can plan together. ";
                    adviceForBoy = adviceForBoy + " She Believes in combining the Financial resources of both to achieve the financial goals according to a plan. A healthy approach in managing joint resources. An excellent choice. ";
                    break;
            }
        }

        if ( mGirlAnswers.get(7).equals("D")) {

            switch (mBoyAnswers.get(7)) {

                case "A":
                    compatibilityScore = compatibilityScore + 5;
                    adviceForGirl = adviceForGirl + " He believes in joint responsibility and management of Family Finances. So participate well in these discussions and give your inputs. That is likely to increase the bonding between the two of you in the relationship. ";
                    adviceForBoy = adviceForBoy + " She is likely to throw the burden of financial management on you. You may need to discuss regarding this so you can plan together. ";
                    break;
                case "B":
                    compatibilityScore = compatibilityScore + 3;
                    adviceForGirl = adviceForGirl + " He appears to be believing in individual’s responsibility in managing the personal money.You may need to discuss regarding this so you can plan together. ";
                    adviceForBoy = adviceForBoy + " She is likely to throw the burden of financial management on you.You may need to discuss regarding this so you can plan together. ";
                    break;
                case "C":
                    compatibilityScore = compatibilityScore + 5;
                    adviceForGirl = adviceForGirl + " He Believes in combining the resources of both to achieve the financial goals according to a plan. A healthy approach in managing joint resources. An excellent choice. ";
                    adviceForBoy = adviceForBoy + " She is likely to throw the burden of financial management on you.You may need to discuss regarding this so you can plan together. ";
                    break;
                case "D":
                    compatibilityScore = compatibilityScore + 10;
                    adviceForGirl = adviceForGirl + " He is likely to throw the burden of Financial Management on you. A more Responsible approach would be to pool and manage the resources jointly. You may need to discuss regarding this so you can plan together. ";
                    adviceForBoy = adviceForBoy + " She is likely to throw the burden of financial management on you. You may need to discuss regarding this so you can plan together. ";
                    break;
            }
            adviceForGirl = adviceForGirl + "\n";
            adviceForBoy = adviceForBoy + "\n";
        }

        //evaluation of question 9
        if (mGirlAnswers.get(8).equals("A")) {

            switch (mBoyAnswers.get(8)) {

                case "A":
                    compatibilityScore = compatibilityScore + 3;
                    adviceForGirl = adviceForGirl + " He seems to believe that each partner is free to have their own plans & not necessary to have joint visions, even in his relationships.You may need to discuss regarding this so you can plan together. ";
                    adviceForBoy = adviceForBoy + " She seems to believe that each partner is free to have their own plans & not necessary to have joint visions, even in her relationship.  You may need to discuss regarding this so you can plan together. ";
                    break;
                case "B":
                    compatibilityScore = compatibilityScore + 5;
                    adviceForGirl = adviceForGirl + " He is likely to leave all the planning and hard work to you. You may need to discuss regarding this so you can plan together for a future that you both want. ";
                    adviceForBoy = adviceForBoy + " She seems to believe that each partner is free to have their own plans & not necessary to have joint visions, even in her relationship.  You may need to discuss regarding this so you can plan together. ";
                    break;
                case "C":
                    compatibilityScore = compatibilityScore + 8;
                    adviceForGirl = adviceForGirl + " He believes in joint planning and a vision, but yet to figure out how to go about it. Discuss with him and make a joint vision. ";
                    adviceForBoy = adviceForBoy + " She seems to believe that each partner is free to have their own plans & not necessary to have joint visions, even in her relationship.  You may need to discuss regarding this so you can plan together. ";
                    break;
                case "D":
                    compatibilityScore = compatibilityScore + 8;
                    adviceForGirl = adviceForGirl + " He believes in the joint planning and vision for the family. That is quite healthy and good for your relationship ";
                    adviceForBoy = adviceForBoy + " She seems to believe that each partner is free to have their own plans & not necessary to have joint visions, even in her relationship.  You may need to discuss regarding this so you can plan together. ";
                    break;
            }
        }

        if ( mGirlAnswers.get(8).equals("B")) {

            switch (mBoyAnswers.get(8)) {

                case "A":
                    compatibilityScore = compatibilityScore + 5;
                    adviceForGirl = adviceForGirl + " He seems to believe that each partner is free to have their own plans & not necessary to have joint visions, even in his relationships.You may need to discuss regarding this so you can plan together. ";
                    adviceForBoy = adviceForBoy + " She is likely to leave all the planning and hard work to you. You may need to discuss regarding this so you can plan together & visualise a joint future. ";
                    break;
                case "B":
                    compatibilityScore = compatibilityScore + 8;
                    adviceForGirl = adviceForGirl + " He is likely to leave all the planning and hard work to you. You may need to discuss regarding this so you can plan together for a future that you both want. ";
                    adviceForBoy = adviceForBoy + " She is likely to leave all the planning and hard work to you. You may need to discuss regarding this so you can plan together & visualise a joint future. ";
                    break;
                case "C":
                    compatibilityScore = compatibilityScore + 5;
                    adviceForGirl = adviceForGirl + " He believes in joint planning and a vision, but yet to figure out how to go about it. Discuss with him and make a joint vision. ";
                    adviceForBoy = adviceForBoy + " She is likely to leave all the planning and hard work to you. You may need to discuss regarding this so you can plan together & visualise a joint future. ";
                    break;
                case "D":
                    compatibilityScore = compatibilityScore + 8;
                    adviceForGirl = adviceForGirl + " He believes in the joint planning and vision for the family. That is quite healthy and good for your relationship ";
                    adviceForBoy = adviceForBoy + " She is likely to leave all the planning and hard work to you. You may need to discuss regarding this so you can plan together & visualise a joint future. ";
                    break;
            }
        }

        if ( mGirlAnswers.get(8).equals("C")) {

            switch (mBoyAnswers.get(8)) {

                case "A":
                    compatibilityScore = compatibilityScore + 8;
                    adviceForGirl = adviceForGirl + " He seems to believe that each partner is free to have their own plans & not necessary to have joint visions, even in his relationships.You may need to discuss regarding this so you can plan together. ";
                    adviceForBoy = adviceForBoy + " She believes in joint planning and a vision, but yet to figure out how to go about it. Discuss with her and design a vision that you both are happy with. ";
                    break;
                case "B":
                    compatibilityScore = compatibilityScore + 5;
                    adviceForGirl = adviceForGirl + " He is likely to leave all the planning and hard work to you. You may need to discuss regarding this so you can plan together for a future that you both want. ";
                    adviceForBoy = adviceForBoy + " She believes in joint planning and a vision, but yet to figure out how to go about it. Discuss with her and design a vision that you both are happy with. ";
                    break;
                case "C":
                    compatibilityScore = compatibilityScore + 8;
                    adviceForGirl = adviceForGirl + " He believes in joint planning and a vision, but yet to figure out how to go about it. Discuss with him and make a joint vision. ";
                    adviceForBoy = adviceForBoy + " She believes in joint planning and a vision, but yet to figure out how to go about it. Discuss with her and design a vision that you both are happy with. ";
                    break;
                case "D":
                    compatibilityScore = compatibilityScore + 10;
                    adviceForGirl = adviceForGirl + " He believes in the joint planning and vision for the family. That is quite healthy and good for your relationship ";
                    adviceForBoy = adviceForBoy + " She believes in joint planning and a vision, but yet to figure out how to go about it. Discuss with her and design a vision that you both are happy with. ";
                    break;
            }
        }

        if ( mGirlAnswers.get(8).equals("D")) {

            switch (mBoyAnswers.get(8)) {

                case "A":
                    compatibilityScore = compatibilityScore + 5;
                    adviceForGirl = adviceForGirl + " He seems to believe that each partner is free to have their own plans & not necessary to have joint visions, even in his relationships.You may need to discuss regarding this so you can plan together. ";
                    adviceForBoy = adviceForBoy + " She believes in the joint planning and vision for the family. This is very healthy for a Relationship. ";
                    break;
                case "B":
                    compatibilityScore = compatibilityScore + 5;
                    adviceForGirl = adviceForGirl + " He is likely to leave all the planning and hard work to you. You may need to discuss regarding this so you can plan together for a future that you both want. ";
                    adviceForBoy = adviceForBoy + " She believes in the joint planning and vision for the family. This is very healthy for a Relationship. ";
                    break;
                case "C":
                    compatibilityScore = compatibilityScore + 8;
                    adviceForGirl = adviceForGirl + " He believes in joint planning and a vision, but yet to figure out how to go about it. Discuss with him and make a joint vision. ";
                    adviceForBoy = adviceForBoy + " She believes in the joint planning and vision for the family. This is very healthy for a Relationship. ";
                    break;
                case "D":
                    compatibilityScore = compatibilityScore + 10;
                    adviceForGirl = adviceForGirl + " He believes in the joint planning and vision for the family. That is quite healthy and good for your relationship ";
                    adviceForBoy = adviceForBoy + " She believes in the joint planning and vision for the family. This is very healthy for a Relationship. ";
                    break;
            }
            adviceForGirl = adviceForGirl + "\n";
            adviceForBoy = adviceForBoy + "\n";
        }

        //evaluation of question 10
        if (mGirlAnswers.get(9).equals("A")) {

            switch (mBoyAnswers.get(9)) {

                case "A":
                    compatibilityScore = compatibilityScore + 3;
                    break;
                case "B":
                    compatibilityScore = compatibilityScore + 3;
                    break;
                case "C":
                    compatibilityScore = compatibilityScore + 3;
                    break;
                case "D":
                    compatibilityScore = compatibilityScore + 5;
                    break;
            }
        }

        if ( mGirlAnswers.get(9).equals("B")) {

            switch (mBoyAnswers.get(9)) {

                case "A":
                    compatibilityScore = compatibilityScore + 3;
                    break;
                case "B":
                    compatibilityScore = compatibilityScore + 3;
                    break;
                case "C":
                    compatibilityScore = compatibilityScore + 3;
                    break;
                case "D":
                    compatibilityScore = compatibilityScore + 8;
                    break;
            }
        }

        if ( mGirlAnswers.get(9).equals("C")) {

            switch (mBoyAnswers.get(9)) {

                case "A":
                    compatibilityScore = compatibilityScore + 5;
                    break;
                case "B":
                    compatibilityScore = compatibilityScore + 3;
                    break;
                case "C":
                    compatibilityScore = compatibilityScore + 3;
                    break;
                case "D":
                    compatibilityScore = compatibilityScore + 8;
                    break;
            }
        }

        if ( mGirlAnswers.get(9).equals("D")) {

            switch (mBoyAnswers.get(9)) {

                case "A":
                    compatibilityScore = compatibilityScore + 5;
                    break;
                case "B":
                    compatibilityScore = compatibilityScore + 5;
                    break;
                case "C":
                    compatibilityScore = compatibilityScore + 5;
                    break;
                case "D":
                    compatibilityScore = compatibilityScore + 10;
                    break;
            }
        }

        //set the compatibility Score Feedback
        if ( compatibilityScore <= 30 ) {
            compatibilityScoreFeedback = "You need to pay close attention to our adviceForGirl";
        } else if ( compatibilityScore > 30 && compatibilityScore <= 50) {
            compatibilityScoreFeedback = "You seem to doing fine but you should do better to reach a blissful state";
        } else if ( compatibilityScore > 50 && compatibilityScore <= 89) {
            compatibilityScoreFeedback = "Great! you both have something close to an Ideal match, take our Advice to make your Relationship even Happier";
        } else {
            compatibilityScoreFeedback = "Amazing! you set an example to all the couples out there, keep it up but do take a note of our Advice below";
        }


        return new ReportObject(girlName, boyName,
                compatibilityScore, compatibilityScoreFeedback, insightsForGirl, insightsForBoy,
                adviceForGirl, adviceForBoy);
    }

}

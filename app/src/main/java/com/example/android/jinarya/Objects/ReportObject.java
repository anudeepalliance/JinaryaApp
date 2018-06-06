package com.example.android.jinarya.Objects;

/**
 * Created by Aadhyamo on 31/12/17.
 */

public class ReportObject {

    private String partner1Name;
    private String partner2Name;
    private int compatibilityScore;
    private String compatibilityScoreFeedback;
    private String insightsForPartner1;
    private String insightsForPartner2;
    private String adviceForPartner1;
    private String adviceForPartner2;

    public ReportObject(String partner1Name, String partner2Name,
                        int compatibilityScore, String compatibilityScoreFeedback,
                        String insightsForPartner1, String insightsForPartner2,
                        String adviceForPartner1, String adviceForPartner2) {

                this.partner1Name = partner1Name;
                this.partner2Name = partner2Name;
                this.compatibilityScore = compatibilityScore;
                this.compatibilityScoreFeedback = compatibilityScoreFeedback;
                this.insightsForPartner1 = insightsForPartner1;
                this.insightsForPartner2 = insightsForPartner2;
                this.adviceForPartner1 = adviceForPartner1;
                this.adviceForPartner2 = adviceForPartner2;

    }

    public String getPartner1Name() {
        return partner1Name;
    }

    public String getPartner2Name() {
        return partner2Name;
    }

    public int getCompatibilityScore() {
        return compatibilityScore;
    }

    public String getCompatibilityScoreFeedback() {
        return compatibilityScoreFeedback;
    }

    public String getInsightsForPartner1() {
        return insightsForPartner1;
    }

    public String getInsightsForPartner2() {
        return insightsForPartner2;
    }

    public String getAdviceForPartner1() {
        return adviceForPartner1;
    }

    public String getAdviceForPartner2() {
        return adviceForPartner2;
    }
}

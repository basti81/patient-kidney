package com.mySystem.patientekidney.librery;

import com.mySystem.patientekidney.models.entities.Anthropometry;
import com.mySystem.patientekidney.models.entities.Exam;
import com.mySystem.patientekidney.models.entities.ExamDiagnosis;
import com.mySystem.patientekidney.models.entities.Record;

import java.time.Instant;
import java.util.List;
import java.util.Set;

public class DiagnosisExam {

    private Instant DiagnosisExamDate;
    public DiagnosisExam(Instant diagnosisExamDate){
        this.DiagnosisExamDate = diagnosisExamDate;
    }
    public enum CategoryOfFg {
        CHRONIC_KIDNEY_DISEASE,
        G1_NORMAL,
        G2_SLIGHTLY_DECREASED,
        G3a_LEADS_A_MODERATELY_DECREASED,
        G3b_MODERATE_TO_SEVERELY_DIMINISHED,
        G4_SEVERELY_DECREASED,
        G5_KIDNEY_FAILURE,
    }
    public ExamDiagnosis examDiagnosis(){

    }

    public String getDescription(Set<StateExam> stateExamSet){
        return "";
    }
    public 

    /**
     * fg  - cock
     */
    public double fgByCaG(Record record, Exam exam, Anthropometry anthropometry) {
        double fg = ((140 - record.getAgeInteger()) * anthropometry.getWeight()) / 7.2 * exam.getCreatine();
        if (record.getGenre() == Genre.FEMALE) {
            return fg * 0.85;
        }
        return fg;
    }

    /**
     * fg - MDRD
     */

    public double fgByMdrd4(Record record, Exam exam) {
        double fg = 186 * Math.pow(exam.getCreatine(), -1.154) * Math.pow(record.getAgeInteger(), -0.203);
        if (record.getGenre() == Genre.FEMALE) fg *= 0.742;
        if (record.getRace() == Race.AFRO_AMERICAN) fg *= 1.21;
        if (record.getRace() == Race.JAPANESE) fg *= 0.763;
        if (record.getRace() == Race.CHINESE) fg *= 1.233;
        return fg;
    }

    /**
     * fg - Ckd-Epi
     */
    public double fgByCkdEpi(Record record, Exam exam) {
        double fg = 0;
        if (record.getRace() == Race.CAUCASIAN) {
            if (record.getGenre() == Genre.FEMALE) {
                if (exam.getCreatine() <= 0.7)
                    fg = 144 * Math.pow(exam.getCreatine() / 0.7, -0.329) * Math.pow(0.993, record.getAgeInteger());
                else fg = 144 * Math.pow(exam.getCreatine() / 0.7, -1.209) * Math.pow(0.993, record.getAgeInteger());
            }
            if (record.getGenre() == Genre.MALE) {
                if (exam.getCreatine() <= 0.9)
                    fg = 144 * Math.pow(exam.getCreatine() / 0.9, -0.411) * Math.pow(0.993, record.getAgeInteger());
                else fg = 144 * Math.pow(exam.getCreatine() / 0.9, -1.209) * Math.pow(0.993, record.getAgeInteger());
            }
        }
        if (record.getRace() == Race.AFRO_AMERICAN) {
            if (record.getGenre() == Genre.FEMALE) {
                if (exam.getCreatine() <= 0.7)
                    fg = 166 * Math.pow(exam.getCreatine() / 0.7, -0.329) * Math.pow(0.993, record.getAgeInteger());
                else fg = 166 * Math.pow(exam.getCreatine() / 0.7, -1.209) * Math.pow(0.993, record.getAgeInteger());
            }
            if (record.getGenre() == Genre.MALE) {
                if (exam.getCreatine() <= 0.9)
                    fg = 163 * Math.pow(exam.getCreatine() / 0.9, -0.411) * Math.pow(0.993, record.getAgeInteger());
                else fg = 163 * Math.pow(exam.getCreatine() / 0.9, -1.209) * Math.pow(0.993, record.getAgeInteger());
            }
        }
        return fg;
    }


}

package com.mySystem.patientekidney.librery;

import com.mySystem.patientekidney.models.entities.*;
import com.mySystem.patientekidney.models.entities.Record;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class ToolsDiagnosis {

    private Instant DiagnosisExamDate;

    public ToolsDiagnosis() {

    }

    /**
     * Create - Diagnosis
     * @param patient
     * @param exam
     * @param anthropometry
     * @return
     */
    public Diagnosis createDiagnosis(Patient patient, Exam exam, Anthropometry anthropometry) {
        Diagnosis diagnosis = new Diagnosis();
        System.out.println("Before: "+diagnosis.toString());
        diagnosis.setFg(fgByCkdEpi(patient.getRecord(), exam));
        diagnosis.setStateExamSet(getStateExams(patient.getRecord(), exam));
        diagnosis.setDescription(getDescription(patient,exam,null,exam.getDiagnosis().getStateExamSet()));
        System.out.println("After: "+diagnosis.toString());
        return diagnosis;
    }

    /**
     * Get - State Patient
     * @param exam
     * @return
     */
    public StatePatient changeStatePatient(Exam exam){
        Set<StateExam> states = exam.getDiagnosis().getStateExamSet();
        for (StateExam state: states) {
            if(state.equals(StateExam.G5_KIDNEY_FAILURE) || state.equals(StateExam.G4_SEVERELY_DECREASED)) return StatePatient.DANGER;
            if(state.equals(StateExam.G3b_MODERATE_TO_SEVERELY_DIMINISHED)) return StatePatient.WARNING;
        }
        return StatePatient.FINE;
    }

    /**
     * Get - StateExams
     *
     * @param record
     * @param exam
     * @return Set
     */
    public Set<StateExam> getStateExams(Record record, Exam exam) {
        Set<StateExam> myStates = new HashSet<>();
        double fgCk = fgByCkdEpi(record, exam);
        myStates.add(getCategoryOfFg(fgCk));
        myStates.add(getCategoryOfChlorine(exam.getChlorine()));
        myStates.add(getCategoryOfAlbumin(exam.getAlbumin()));
        myStates.add(getCategoryOfPotassium(exam.getPotassium()));
        myStates.add(getCategoryOfSodium(exam.getSodium()));
        return myStates;
    }

    /**
     * Get - Description
     *
     * @param patient
     * @param exam
     * @param anthropometry
     * @param stateExamSet
     * @return msg
     */
    public String getDescription(Patient patient, Exam exam, Anthropometry anthropometry, Set<StateExam> stateExamSet) {
        double fgCag = fgByCaG(patient.getRecord(), exam, anthropometry);
        double fgMdrd = fgByMdrd4(patient.getRecord(), exam);
        double fgCk = fgByCkdEpi(patient.getRecord(), exam);
        String msg =
                " Patient: " + patient.getName() + " " + patient.getLastName() + "\n"
                + "Fg - Cock croft: " + fgCag + " \n"
                + "Fg - Mdrd: " + fgMdrd + "\n"
                + "Fg - Ckd epi: " + fgCk + "\n"
                + "Results needing attention: \n"
                + "{ " + stateExamSet.toString() + " }";
        return msg;
    }

    /**
     * Category of fg
     *
     * @param fg
     * @return State Exam
     */
    public StateExam getCategoryOfFg(double fg) {
        if (fg >= 90) return StateExam.G1_NORMAL;
        else if (fg >= 60 && fg <= 89) return StateExam.G2_SLIGHTLY_DECREASED;
        else if (fg >= 45 && fg <= 59) return StateExam.G3a_LEADS_A_MODERATELY_DECREASED;
        else if (fg >= 30 && fg <= 44) return StateExam.G3b_MODERATE_TO_SEVERELY_DIMINISHED;
        else if (fg >= 15 && fg <= 29) return StateExam.G4_SEVERELY_DECREASED;
        else return StateExam.G5_KIDNEY_FAILURE;
    }

    /**
     * Category of Potassium
     *
     * @param potassium
     * @return State Potassium
     */
    public StateExam getCategoryOfPotassium(double potassium) {
        if (potassium >= 3.5 && potassium <= 5) return StateExam.NORMAL;
        else if (potassium < 3.5) return StateExam.HYPOKALEMIA;
        else return StateExam.HYPERKALEMIA;
    }

    /**
     * Category of Albumin
     *
     * @param albumin
     * @return State Albumin
     */
    public StateExam getCategoryOfAlbumin(double albumin) {
        if (albumin >= 3.4 && albumin <= 5.4) return StateExam.NORMAL;
        else if (albumin < 3.5) return StateExam.HYPOALBUMINEMIA;
        else return StateExam.HYPERALBUMINEMIA;
    }

    /**
     * Category of Chlorine
     *
     * @param chlorine
     * @return State Chlorine
     */
    public StateExam getCategoryOfChlorine(double chlorine) {
        if (chlorine >= 96 && chlorine <= 106) return StateExam.NORMAL;
        else if (chlorine < 3.5) return StateExam.HYPOCHLOREMIA;
        else return StateExam.HYPERCHLOREMIA;
    }

    /**
     * Category of Sodium
     *
     * @param sodium
     * @return State Sodium
     */
    public StateExam getCategoryOfSodium(double sodium) {
        if (sodium >= 135 && sodium <= 145) return StateExam.NORMAL;
        else if (sodium < 135) return StateExam.HYPONATREMIA;
        else return StateExam.HYPERNATRIMIA;
    }

    /**
     * fg - cock
     *
     * @param record
     * @param exam
     * @param anthropometry
     * @return double fg
     */
    public double fgByCaG(Record record, Exam exam, Anthropometry anthropometry) {
        double fg = ((140 - record.getAgeInteger()) * anthropometry.getWeight()) / 7.2 * exam.getCreatine();
        if (record.getGenre() == Genre.FEMALE) {
            return fg * 0.85;
        }
        return fg;
    }

    /**
     * fg - Mdrd4
     *
     * @param record
     * @param exam
     * @return
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
     * fg - CkdEpi
     *
     * @param record
     * @param exam
     * @return
     */
    public double fgByCkdEpi(Record record, Exam exam) {
        System.out.println("Entre a fg by ckdepi- ");
        System.out.println(record.toString());
        System.out.println(exam.toString());


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

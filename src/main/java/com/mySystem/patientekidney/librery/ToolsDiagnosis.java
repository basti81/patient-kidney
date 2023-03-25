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
     *
     * @param patient
     * @param exam
     * @param anthropometry
     * @return
     */
    public Diagnosis createDiagnosis(Patient patient, Exam exam, Anthropometry anthropometry) {
        Double fgCag = 0.0;
        if (anthropometry != null){
            fgCag = (double)Math.round(fgByCaG(patient.getRecord(), exam, anthropometry) * 100d) / 100d ;
        }
        Double fgMdrd = (double)Math.round(fgByMdrd4(patient.getRecord(), exam) * 100d) / 100d;
        Double fgCk = (double)Math.round(fgByCkdEpi(patient.getRecord(), exam) * 100d) / 100d;

        System.out.println("Before: " + exam.getDiagnosis().toString());
        Set<StateExam> createdStatesExams = getStateExams(patient.getRecord(), exam);
        exam.getDiagnosis().setFgCockCroft(fgCag);
        exam.getDiagnosis().setFgMdrd(fgMdrd);
        exam.getDiagnosis().setFgCkdEpi(fgCk);
        exam.getDiagnosis().setStateExamSet(createdStatesExams);
        exam.getDiagnosis().setDescription(getDescription(patient, exam, anthropometry, createdStatesExams));
        patient.setStatePatient(changeStatePatient(exam));
        exam.getRecord().setPatient(patient);
        System.out.println("After: " + exam.getDiagnosis().toString());
        return exam.getDiagnosis();
    }

    /**
     * Get - State Patient
     *
     * @param exam
     * @return
     */
    public StatePatient changeStatePatient(Exam exam) {
        Set<StateExam> states = exam.getDiagnosis().getStateExamSet();
        for (StateExam state : states) {
            if (state.equals(StateExam.G5_KIDNEY_FAILURE) || state.equals(StateExam.G4_SEVERELY_DECREASED))
                return StatePatient.DANGER;
            if (state.equals(StateExam.G3b_MODERATE_TO_SEVERELY_DIMINISHED)) return StatePatient.WARNING;
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
    public String getDescription(Patient patient, Exam exam, Anthropometry anthropometry,
                                 Set<StateExam> stateExamSet) {
        return  " Patient: " + patient.getName() + " " + patient.getLastName() + "\n"
                        + "Category of FG: " + getCategoryOfFg(exam.getDiagnosis().getFgCkdEpi()) + " \n"
                        + "Fg - Cock croft: " + exam.getDiagnosis().getFgCockCroft() + " \n"
                        + "Fg - Mdrd: " + exam.getDiagnosis().getFgMdrd() + "\n"
                        + "Fg - Ckd epi: " + exam.getDiagnosis().getFgCkdEpi() + "\n"
                        + "Results needing attention: \n"
                        + "{ " + stateExamSet.stream().toList() + " }";

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
    public Double fgByCaG(Record record, Exam exam, Anthropometry anthropometry) {
        Double fg = ((140 - record.getAgeInteger()) * anthropometry.getWeight()) / 7.2 * exam.getCreatine();
        if (record.getStateGenre() == StateGenre.FEMALE) {
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
    public Double fgByMdrd4(Record record, Exam exam) {
        Double fg = 186 * Math.pow(exam.getCreatine(), -1.154) * Math.pow(record.getAgeInteger(), -0.203);
        if (record.getStateGenre() == StateGenre.FEMALE) fg *= 0.742;
        if (record.getStateRace() == StateRace.AFRO_AMERICAN) fg *= 1.21;
        if (record.getStateRace() == StateRace.JAPANESE) fg *= 0.763;
        if (record.getStateRace() == StateRace.CHINESE) fg *= 1.233;
        return fg;
    }

    /**
     * fg - CkdEpi
     *
     * @param record
     * @param exam
     * @return
     */
    public Double fgByCkdEpi(Record record, Exam exam) {
        Double fg = (double)0;
        if (record.getStateRace() == StateRace.CAUCASIAN) {
            if (record.getStateGenre() == StateGenre.FEMALE) {
                if (exam.getCreatine() <= 0.7)
                    fg = 144 * Math.pow(exam.getCreatine() / 0.7, -0.329) * Math.pow(0.993, record.getAgeInteger());
                else fg = 144 * Math.pow(exam.getCreatine() / 0.7, -1.209) * Math.pow(0.993, record.getAgeInteger());
            }
            if (record.getStateGenre() == StateGenre.MALE) {
                if (exam.getCreatine() <= 0.9)
                    fg = 144 * Math.pow(exam.getCreatine() / 0.9, -0.411) * Math.pow(0.993, record.getAgeInteger());
                else fg = 144 * Math.pow(exam.getCreatine() / 0.9, -1.209) * Math.pow(0.993, record.getAgeInteger());
            }
        }
        if (record.getStateRace() == StateRace.AFRO_AMERICAN) {
            if (record.getStateGenre() == StateGenre.FEMALE) {
                if (exam.getCreatine() <= 0.7)
                    fg = 166 * Math.pow(exam.getCreatine() / 0.7, -0.329) * Math.pow(0.993, record.getAgeInteger());
                else fg = 166 * Math.pow(exam.getCreatine() / 0.7, -1.209) * Math.pow(0.993, record.getAgeInteger());
            }
            if (record.getStateGenre() == StateGenre.MALE) {
                if (exam.getCreatine() <= 0.9)
                    fg = 163 * Math.pow(exam.getCreatine() / 0.9, -0.411) * Math.pow(0.993, record.getAgeInteger());
                else fg = 163 * Math.pow(exam.getCreatine() / 0.9, -1.209) * Math.pow(0.993, record.getAgeInteger());
            }
        }
        return fg;
    }
}

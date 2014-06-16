/**
 * 
 */
package com.simple.registration.controller;

import java.util.ArrayList;

import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * @author papu
 *
 */
@Named("mpb_registrationBasic")
@ManagedBean
@RequestScoped
public class RegistrationBasicInfoMpb {
	private String firstName;
	private String lastName;
	private String dateOfBirth;
	private String birthState;
	private String birthCity;
	private String birthCountry;
	private String religion;
	private boolean physicallyDisabled;
	private String gender;
	private String motherTongue;
	private String currentSchoolName;
	private int currentStandard;
	private String currentSchoolboard;
	private String currentSchoolState;
	private String currentSchoolCity;
	private String currentSchoolCountry;
	private ArrayList<String> listOfCountry;
	private ArrayList<String> listOfState;
	private ArrayList<String> listOfCity;
	private ArrayList<String> listOfBoard;
	private ArrayList<String> listOfLanguages;
	private ArrayList<Integer> listOfStandard;
	private ArrayList<String> listOfReligion;
	
	public RegistrationBasicInfoMpb() {
		
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the dateOfBirth
	 */
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	/**
	 * @return the birthState
	 */
	public String getBirthState() {
		return birthState;
	}
	/**
	 * @param birthState the birthState to set
	 */
	public void setBirthState(String birthState) {
		this.birthState = birthState;
	}
	/**
	 * @return the birthCity
	 */
	public String getBirthCity() {
		return birthCity;
	}
	/**
	 * @param birthCity the birthCity to set
	 */
	public void setBirthCity(String birthCity) {
		this.birthCity = birthCity;
	}
	/**
	 * @return the birthCountry
	 */
	public String getBirthCountry() {
		return birthCountry;
	}
	/**
	 * @param birthCountry the birthCountry to set
	 */
	public void setBirthCountry(String birthCountry) {
		this.birthCountry = birthCountry;
	}
	/**
	 * @return the religion
	 */
	public String getReligion() {
		return religion;
	}
	/**
	 * @param religion the religion to set
	 */
	public void setReligion(String religion) {
		this.religion = religion;
	}
	/**
	 * @return the physicallyDisabled
	 */
	public boolean isPhysicallyDisabled() {
		return physicallyDisabled;
	}
	/**
	 * @param physicallyDisabled the physicallyDisabled to set
	 */
	public void setPhysicallyDisabled(boolean physicallyDisabled) {
		this.physicallyDisabled = physicallyDisabled;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the motherTongue
	 */
	public String getMotherTongue() {
		return motherTongue;
	}
	/**
	 * @param motherTongue the motherTongue to set
	 */
	public void setMotherTongue(String motherTongue) {
		this.motherTongue = motherTongue;
	}
	/**
	 * @return the currentSchoolName
	 */
	public String getCurrentSchoolName() {
		return currentSchoolName;
	}
	/**
	 * @param currentSchoolName the currentSchoolName to set
	 */
	public void setCurrentSchoolName(String currentSchoolName) {
		this.currentSchoolName = currentSchoolName;
	}
	/**
	 * @return the currentStandard
	 */
	public int getCurrentStandard() {
		return currentStandard;
	}
	/**
	 * @param currentStandard the currentStandard to set
	 */
	public void setCurrentStandard(int currentStandard) {
		this.currentStandard = currentStandard;
	}
	/**
	 * @return the currentSchoolboard
	 */
	public String getCurrentSchoolboard() {
		return currentSchoolboard;
	}
	/**
	 * @param currentSchoolboard the currentSchoolboard to set
	 */
	public void setCurrentSchoolboard(String currentSchoolboard) {
		this.currentSchoolboard = currentSchoolboard;
	}
	/**
	 * @return the currentSchoolState
	 */
	public String getCurrentSchoolState() {
		return currentSchoolState;
	}
	/**
	 * @param currentSchoolState the currentSchoolState to set
	 */
	public void setCurrentSchoolState(String currentSchoolState) {
		this.currentSchoolState = currentSchoolState;
	}
	/**
	 * @return the currentSchoolCountry
	 */
	public String getCurrentSchoolCountry() {
		return currentSchoolCountry;
	}
	/**
	 * @param currentSchoolCountry the currentSchoolCountry to set
	 */
	public void setCurrentSchoolCountry(String currentSchoolCountry) {
		this.currentSchoolCountry = currentSchoolCountry;
	}
	/**
	 * @return the listOfCountry
	 */
	public ArrayList<String> getListOfCountry() {
		listOfCountry = new ArrayList<String>();
		listOfCountry.add("INDIA");
		listOfCountry.add("PAKISTAN");
		listOfCountry.add("CHINA");
		return listOfCountry;
	}
	/**
	 * @param listOfCountry the listOfCountry to set
	 */
	public void setListOfCountry(ArrayList<String> listOfCountry) {
		this.listOfCountry = listOfCountry;
	}
	/**
	 * @return the listOfState
	 */
	public ArrayList<String> getListOfState() {
		this.listOfState = new ArrayList<String>();
		listOfState.add("ODISHA");
		listOfState.add("ANDHRA");
		return listOfState;
	}
	/**
	 * @param listOfState the listOfState to set
	 */
	public void setListOfState(ArrayList<String> listOfState) {
		this.listOfState = listOfState;
	}
	/**
	 * @return the listOfCity
	 */
	public ArrayList<String> getListOfCity() {
		listOfCity = new ArrayList<String>();
		listOfCity.add("BHUBANESWAR");
		listOfCity.add("CUTTACK");
		return listOfCity;
	}
	/**
	 * @param listOfCity the listOfCity to set
	 */
	public void setListOfCity(ArrayList<String> listOfCity) {
		this.listOfCity = listOfCity;
	}
	/**
	 * @return the listOfBoard
	 */
	public ArrayList<String> getListOfBoard() {
		this.listOfBoard = new ArrayList<String>();
		listOfBoard.add("CBSE");
		listOfBoard.add("ICSE");
		return listOfBoard;
	}
	/**
	 * @param listOfBoard the listOfBoard to set
	 */
	public void setListOfBoard(ArrayList<String> listOfBoard) {
		this.listOfBoard = listOfBoard;
	}
	/**
	 * @return the listOfLanguages
	 */
	public ArrayList<String> getListOfLanguages() {
		listOfLanguages = new ArrayList<String>();
		listOfLanguages.add("ODIA");
		listOfLanguages.add("TELUGU");
		return listOfLanguages;
	}
	/**
	 * @param listOfLanguages the listOfLanguages to set
	 */
	public void setListOfLanguages(ArrayList<String> listOfLanguages) {
		this.listOfLanguages = listOfLanguages;
	}
	/**
	 * @return the listOfStandard
	 */
	public ArrayList<Integer> getListOfStandard() {
		this.listOfStandard = new ArrayList<Integer>();
		listOfStandard.add(10);
		listOfStandard.add(11);
		return listOfStandard;
	}
	/**
	 * @param listOfStandard the listOfStandard to set
	 */
	public void setListOfStandard(ArrayList<Integer> listOfStandard) {
		this.listOfStandard = listOfStandard;
	}
	/**
	 * @return the listOfReligion
	 */
	public ArrayList<String> getListOfReligion() {
		this.listOfReligion = new ArrayList<String>();
		listOfReligion.add("HINDU");
		listOfReligion.add("MUSLIM");
		return listOfReligion;
	}
	/**
	 * @param listOfReligion the listOfReligion to set
	 */
	public void setListOfReligion(ArrayList<String> listOfReligion) {
		this.listOfReligion = listOfReligion;
	}
	/**
	 * @return the currentSchoolCity
	 */
	public String getCurrentSchoolCity() {
		return currentSchoolCity;
	}
	/**
	 * @param currentSchoolCity the currentSchoolCity to set
	 */
	public void setCurrentSchoolCity(String currentSchoolCity) {
		this.currentSchoolCity = currentSchoolCity;
	}
	
}

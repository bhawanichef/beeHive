package com.simple.registration.controller;

import java.util.ArrayList;

import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("mpb_registrationOtherContacts")
@RequestScoped
@ManagedBean
public class RegistrationOtherContactsInfoMpb {
	private String emergencyContactOneName;
	private String emergencyContactOneRelationship;
	private String emergencyContactOnePhoneNumer;
	private String emergencyContactOnePhoneType;
	private String emergencyContactTwoName;
	private String emergencyContactTwoRelationship;
	private String emergencyContactTwoPhoneNumer;
	private String emergencyContactTwoPhoneType;
	private String healthInsuranceProvider;
	private String healthInsurancePolicyNumber;
	private ArrayList<String> listOfRelationship;
	private ArrayList<String> listOfPhoneType;

	public RegistrationOtherContactsInfoMpb() {
	}

	/**
	 * @return the emergencyContactOneName
	 */
	public String getEmergencyContactOneName() {
		return emergencyContactOneName;
	}

	/**
	 * @param emergencyContactOneName
	 *            the emergencyContactOneName to set
	 */
	public void setEmergencyContactOneName(String emergencyContactOneName) {
		this.emergencyContactOneName = emergencyContactOneName;
	}

	/**
	 * @return the emergencyContactOneRelationship
	 */
	public String getEmergencyContactOneRelationship() {
		return emergencyContactOneRelationship;
	}

	/**
	 * @param emergencyContactOneRelationship
	 *            the emergencyContactOneRelationship to set
	 */
	public void setEmergencyContactOneRelationship(
			String emergencyContactOneRelationship) {
		this.emergencyContactOneRelationship = emergencyContactOneRelationship;
	}

	/**
	 * @return the emergencyContactOnePhoneNumer
	 */
	public String getEmergencyContactOnePhoneNumer() {
		return emergencyContactOnePhoneNumer;
	}

	/**
	 * @param emergencyContactOnePhoneNumer
	 *            the emergencyContactOnePhoneNumer to set
	 */
	public void setEmergencyContactOnePhoneNumer(
			String emergencyContactOnePhoneNumer) {
		this.emergencyContactOnePhoneNumer = emergencyContactOnePhoneNumer;
	}

	/**
	 * @return the emergencyContactOnePhoneType
	 */
	public String getEmergencyContactOnePhoneType() {
		return emergencyContactOnePhoneType;
	}

	/**
	 * @param emergencyContactOnePhoneType
	 *            the emergencyContactOnePhoneType to set
	 */
	public void setEmergencyContactOnePhoneType(
			String emergencyContactOnePhoneType) {
		this.emergencyContactOnePhoneType = emergencyContactOnePhoneType;
	}

	/**
	 * @return the emergencyContactTwoName
	 */
	public String getEmergencyContactTwoName() {
		return emergencyContactTwoName;
	}

	/**
	 * @param emergencyContactTwoName
	 *            the emergencyContactTwoName to set
	 */
	public void setEmergencyContactTwoName(String emergencyContactTwoName) {
		this.emergencyContactTwoName = emergencyContactTwoName;
	}

	/**
	 * @return the emergencyContactTwoRelationship
	 */
	public String getEmergencyContactTwoRelationship() {
		return emergencyContactTwoRelationship;
	}

	/**
	 * @param emergencyContactTwoRelationship
	 *            the emergencyContactTwoRelationship to set
	 */
	public void setEmergencyContactTwoRelationship(
			String emergencyContactTwoRelationship) {
		this.emergencyContactTwoRelationship = emergencyContactTwoRelationship;
	}

	/**
	 * @return the emergencyContactTwoPhoneNumer
	 */
	public String getEmergencyContactTwoPhoneNumer() {
		return emergencyContactTwoPhoneNumer;
	}

	/**
	 * @param emergencyContactTwoPhoneNumer
	 *            the emergencyContactTwoPhoneNumer to set
	 */
	public void setEmergencyContactTwoPhoneNumer(
			String emergencyContactTwoPhoneNumer) {
		this.emergencyContactTwoPhoneNumer = emergencyContactTwoPhoneNumer;
	}

	/**
	 * @return the emergencyContactTwoPhoneType
	 */
	public String getEmergencyContactTwoPhoneType() {
		return emergencyContactTwoPhoneType;
	}

	/**
	 * @param emergencyContactTwoPhoneType
	 *            the emergencyContactTwoPhoneType to set
	 */
	public void setEmergencyContactTwoPhoneType(
			String emergencyContactTwoPhoneType) {
		this.emergencyContactTwoPhoneType = emergencyContactTwoPhoneType;
	}

	/**
	 * @return the healthInsuranceProvider
	 */
	public String getHealthInsuranceProvider() {
		return healthInsuranceProvider;
	}

	/**
	 * @param healthInsuranceProvider
	 *            the healthInsuranceProvider to set
	 */
	public void setHealthInsuranceProvider(String healthInsuranceProvider) {
		this.healthInsuranceProvider = healthInsuranceProvider;
	}

	/**
	 * @return the healthInsurancePolicyNumber
	 */
	public String getHealthInsurancePolicyNumber() {
		return healthInsurancePolicyNumber;
	}

	/**
	 * @param healthInsurancePolicyNumber
	 *            the healthInsurancePolicyNumber to set
	 */
	public void setHealthInsurancePolicyNumber(
			String healthInsurancePolicyNumber) {
		this.healthInsurancePolicyNumber = healthInsurancePolicyNumber;
	}

	/**
	 * @return the listOfRelationship
	 */
	public ArrayList<String> getListOfRelationship() {
		this.listOfRelationship = new ArrayList<String>();
		listOfRelationship.add("Father");
		listOfRelationship.add("Mother");
		return listOfRelationship;
	}

	/**
	 * @param listOfRelationship
	 *            the listOfRelationship to set
	 */
	public void setListOfRelationship(ArrayList<String> listOfRelationship) {
		this.listOfRelationship = listOfRelationship;
	}

	/**
	 * @return the listOfPhoneType
	 */
	public ArrayList<String> getListOfPhoneType() {
		this.listOfPhoneType = new ArrayList<String>();
		listOfPhoneType.add("Personal mobile");
		listOfPhoneType.add("Home land line");
		listOfPhoneType.add("Business phone");
		return listOfPhoneType;
	}

	/**
	 * @param listOfPhoneType
	 *            the listOfPhoneType to set
	 */
	public void setListOfPhoneType(ArrayList<String> listOfPhoneType) {
		this.listOfPhoneType = listOfPhoneType;
	}
}

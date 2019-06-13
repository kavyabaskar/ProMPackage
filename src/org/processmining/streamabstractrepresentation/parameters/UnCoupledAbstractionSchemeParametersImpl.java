package org.processmining.streamabstractrepresentation.parameters;
//can store case administration and internal representation data structure.
import java.util.HashMap;
import java.util.Map;

import org.processmining.eventstream.readers.abstr.XSEventReaderParameters;
import org.processmining.stream.model.datastructure.DSParameter;
import org.processmining.stream.model.datastructure.DSParameterDefinition;
import org.processmining.stream.model.datastructure.DSParameterFactory;
import org.processmining.stream.model.datastructure.DataStructure.Type;
import org.processmining.streamabstractrepresentation.algorithms.UncoupledCaseAdministrationXSEventReader;

public class UnCoupledAbstractionSchemeParametersImpl extends XSEventReaderParameters {

	private Type internalRepresentationDataStructure1 = null;
	private Map<DSParameterDefinition, DSParameter<?>> internalRepresentationDataStructureParameters1 = null;
	private Type caseAdministration1 = null;
	private Map<DSParameterDefinition, DSParameter<?>> caseAdministrationParameters1 = null;
	
	//for 2nd miner
	private Type internalRepresentationDataStructure2 = null;
	private Map<DSParameterDefinition, DSParameter<?>> internalRepresentationDataStructureParameters2 = null;
	private Type caseAdministration2 = null;
	private Map<DSParameterDefinition, DSParameter<?>> caseAdministrationParameters2 = null;
	////

	public UnCoupledAbstractionSchemeParametersImpl() {
		super();
		internalRepresentationDataStructure1 = UncoupledCaseAdministrationXSEventReader.DEFAULT_INTERNAL_REPRESENTATION_DATA_STRUCTURE;
		internalRepresentationDataStructureParameters1 = DSParameterFactory.createDefaultParameters(
				UncoupledCaseAdministrationXSEventReader.DEFAULT_INTERNAL_REPRESENTATION_DATA_STRUCTURE);
		caseAdministration1 = UncoupledCaseAdministrationXSEventReader.DEFAULT_CASE_ADMINISTRATION_DATA_STRUCTURE;
		caseAdministrationParameters1 = DSParameterFactory.createDefaultParameters(
				UncoupledCaseAdministrationXSEventReader.DEFAULT_CASE_ADMINISTRATION_DATA_STRUCTURE);
		
		//for 2nd miner
		internalRepresentationDataStructure2 = UncoupledCaseAdministrationXSEventReader.DEFAULT_INTERNAL_REPRESENTATION_DATA_STRUCTURE;
		internalRepresentationDataStructureParameters2 = DSParameterFactory.createDefaultParameters(
				UncoupledCaseAdministrationXSEventReader.DEFAULT_INTERNAL_REPRESENTATION_DATA_STRUCTURE);
		caseAdministration2 = UncoupledCaseAdministrationXSEventReader.DEFAULT_CASE_ADMINISTRATION_DATA_STRUCTURE;
		caseAdministrationParameters2 = DSParameterFactory.createDefaultParameters(
				UncoupledCaseAdministrationXSEventReader.DEFAULT_CASE_ADMINISTRATION_DATA_STRUCTURE);
		//
	}

	public UnCoupledAbstractionSchemeParametersImpl(final UnCoupledAbstractionSchemeParametersImpl params) {
		super(params);
		this.internalRepresentationDataStructure1 = params.getInternalRepresentationDataStructureType1();
		this.internalRepresentationDataStructureParameters1 = new HashMap<>(
				params.getInternalRepresentationDataStructureParameters1());
		this.caseAdministration1 = params.getCaseAdministrationDataStructureType1();
		this.caseAdministrationParameters1 = new HashMap<>(params.getCaseAdministrationDataStructureParameters1());
		
		//for the 2nd miner
		this.internalRepresentationDataStructure2 = params.getInternalRepresentationDataStructureType2();
		this.internalRepresentationDataStructureParameters2 = new HashMap<>(
				params.getInternalRepresentationDataStructureParameters2());
		this.caseAdministration2 = params.getCaseAdministrationDataStructureType2();
		this.caseAdministrationParameters2 = new HashMap<>(params.getCaseAdministrationDataStructureParameters2());
		//
	}

	public Type getInternalRepresentationDataStructureType1() {
		return internalRepresentationDataStructure1;
	}

	public Map<DSParameterDefinition, DSParameter<?>> getInternalRepresentationDataStructureParameters1() {
		return internalRepresentationDataStructureParameters1;
	}

	public Type getCaseAdministrationDataStructureType1() {
		return caseAdministration1;
	}

	public Map<DSParameterDefinition, DSParameter<?>> getCaseAdministrationDataStructureParameters1() {
		return caseAdministrationParameters1;
	}

	public void setInternalRepresentationDataStructureType1(Type activityActivityDataStructure) {
		this.internalRepresentationDataStructure1 = activityActivityDataStructure;
	}

	public void setInternalRepresentationDataStructureParameters1(
			Map<DSParameterDefinition, DSParameter<?>> activityActivityParams) {
		this.internalRepresentationDataStructureParameters1 = activityActivityParams;
	}

	public void setCaseAdministrationDataStructureType1(Type caseActivityDataStructure) {
		this.caseAdministration1 = caseActivityDataStructure;
	}

	public void setCaseAdministrationDataStructureParameters1(
			Map<DSParameterDefinition, DSParameter<?>> caseActivityParams) {
		this.caseAdministrationParameters1 = caseActivityParams;
	}

	//for 2nd miner
	public Type getInternalRepresentationDataStructureType2() {
		return internalRepresentationDataStructure2;
	}

	public Map<DSParameterDefinition, DSParameter<?>> getInternalRepresentationDataStructureParameters2() {
		return internalRepresentationDataStructureParameters2;
	}

	public Type getCaseAdministrationDataStructureType2() {
		return caseAdministration2;
	}

	public Map<DSParameterDefinition, DSParameter<?>> getCaseAdministrationDataStructureParameters2() {
		return caseAdministrationParameters2;
	}

	public void setInternalRepresentationDataStructureType2(Type activityActivityDataStructure) {
		this.internalRepresentationDataStructure2 = activityActivityDataStructure;
	}

	public void setInternalRepresentationDataStructureParameters2(
			Map<DSParameterDefinition, DSParameter<?>> activityActivityParams) {
		this.internalRepresentationDataStructureParameters2 = activityActivityParams;
	}

	public void setCaseAdministrationDataStructureType2(Type caseActivityDataStructure) {
		this.caseAdministration2 = caseActivityDataStructure;
	}

	public void setCaseAdministrationDataStructureParameters2(
			Map<DSParameterDefinition, DSParameter<?>> caseActivityParams) {
		this.caseAdministrationParameters2 = caseActivityParams;
	}
	//
	
	@Override
	public boolean equals(Object o) {
		if (!super.equals(o))
			return false;
		if (o instanceof UnCoupledAbstractionSchemeParametersImpl) {
			UnCoupledAbstractionSchemeParametersImpl c = (UnCoupledAbstractionSchemeParametersImpl) o;
			if (!c.getInternalRepresentationDataStructureParameters1()
					.equals(getInternalRepresentationDataStructureParameters1()))
				return false;
			if (!c.getInternalRepresentationDataStructureType1().equals(getInternalRepresentationDataStructureType1()))
				return false;
			if (!c.getCaseAdministrationDataStructureParameters1()
					.equals(getCaseAdministrationDataStructureParameters1()))
				return false;
			if (!c.getCaseAdministrationDataStructureType1().equals(c.getCaseAdministrationDataStructureType1()))
				return false;
			
			//for 2nd miner
			if (!c.getInternalRepresentationDataStructureParameters2()
					.equals(getInternalRepresentationDataStructureParameters2()))
				return false;
			if (!c.getInternalRepresentationDataStructureType2().equals(getInternalRepresentationDataStructureType2()))
				return false;
			if (!c.getCaseAdministrationDataStructureParameters2()
					.equals(getCaseAdministrationDataStructureParameters2()))
				return false;
			if (!c.getCaseAdministrationDataStructureType2().equals(c.getCaseAdministrationDataStructureType2()))
				return false;
			/////
			return true;
		}
		return false;
	}

	public void setModelUpdateFrequency(Object value) {
		// TODO Auto-generated method stub
		
	}

	public void setFitnessCalculationFrequency(Object value) {
		// TODO Auto-generated method stub
		
	}

	public void setPrecisionCalculationFrequency(Object value) {
		// TODO Auto-generated method stub
		
	}

}

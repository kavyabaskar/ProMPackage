
package org.processmining.streamabstractrepresentation.dialogs;

import java.util.Map;

//import javax.swing.ButtonGroup;
//import javax.swing.JCheckBox;
import javax.swing.JComponent;

import org.deckfour.xes.classification.XEventClass;
import org.processmining.contexts.uitopia.UIPluginContext;
import org.processmining.framework.util.Pair;
import org.processmining.stream.dialog.StreamBasedDataStructureSelector;
import org.processmining.stream.model.datastructure.DSParameter;
import org.processmining.stream.model.datastructure.DSParameterDefinition;
import org.processmining.stream.model.datastructure.DataStructure.Type;
import org.processmining.streamabstractrepresentation.algorithms.UncoupledXEventClassDFAReaderImpl;
import org.processmining.streamabstractrepresentation.parameters.UnCoupledAbstractionSchemeParametersImpl;
import org.processmining.widgets.wizard.AbstractRoutableDialog;
import org.processmining.widgets.wizard.Dialog;
import org.processmining.widgets.wizard.Route;

public class UncoupledDataStructuresDialogImpl<P extends UnCoupledAbstractionSchemeParametersImpl>
		extends AbstractRoutableDialog<P> {

	public static String DEFAULT_TITLE = "Configure Event Stream to Directly Follows Abstraction Reader";

	private static final long serialVersionUID = 4755769879740283267L;

	private StreamBasedDataStructureSelector<Pair<XEventClass, XEventClass>> activityActivityDataStructureSelector1 = new StreamBasedDataStructureSelector<>(
			//"Select Activity-Activity (AxA) Data Structure ",
			"Select Activity-Activity (AxA) Data Structure for Inductive Miner",
			UncoupledXEventClassDFAReaderImpl.DEFAULT_ALLOWED_INTERNAL_REPRESENTATION_DATA_STRUCTURES);

	private StreamBasedDataStructureSelector<Pair<String, XEventClass>> caseActivityDataStructureSelector1 = new StreamBasedDataStructureSelector<>(
			//"Select Case-Activity (CxA) Data Structure",
			"Select Case-Activity (CxA) Data Structure for Inductive Miner",
			UncoupledXEventClassDFAReaderImpl.DEFAULT_ALLOWED_CASE_ADMINISTRATION_DATA_STRUCTURES);

	public UncoupledDataStructuresDialogImpl(UIPluginContext context, String title, P parameters, Dialog<P> previous) {
		this(context, title, parameters, previous, null);
	}

	public UncoupledDataStructuresDialogImpl(UIPluginContext context, String title, P parameters, Dialog<P> previous,
			Route<P> route) {
		super(context, title, parameters, previous, route);
	}
	
	
	protected boolean canProceedToNext() {
		return true;
	}

	public Map<DSParameterDefinition, DSParameter<?>> getActivityActivityDataStructureParameters1() {
		return activityActivityDataStructureSelector1.getDataStructureParameterMapping1();
	}

	public Type getActivityActivityDataStructureType1() {
		return activityActivityDataStructureSelector1.getDataStructureType1();
	}

	public Map<DSParameterDefinition, DSParameter<?>> getCaseActivityDataStructureParameters1() {
		return caseActivityDataStructureSelector1.getDataStructureParameterMapping1();
	}

	public Type getCaseActivityDataStructureType1() {
		return caseActivityDataStructureSelector1.getDataStructureType1();
	}

	public JComponent visualize() {
		removeAll();
		add(activityActivityDataStructureSelector1);
		add(caseActivityDataStructureSelector1);;
		revalidate();
		repaint();
		return this;
	}

	protected String getUserInputProblems() {
		return "";
	}

	public void updateParametersOnGetNext() {
		getParameters().setInternalRepresentationDataStructureParameters1(getActivityActivityDataStructureParameters1());
		getParameters().setInternalRepresentationDataStructureType1(getActivityActivityDataStructureType1());
		getParameters().setCaseAdministrationDataStructureParameters1(getCaseActivityDataStructureParameters1());
		getParameters().setCaseAdministrationDataStructureType1(getCaseActivityDataStructureType1());
	}

	public void updateParametersOnGetPrevious() {
		updateParametersOnGetNext();
	}

}


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

public class UncoupledDataStructuresSecondDialogImpl<P extends UnCoupledAbstractionSchemeParametersImpl>
		extends AbstractRoutableDialog<P> {

	public static String DEFAULT_TITLE = "Configure Event Stream to Directly Follows Abstraction Reader";

	private static final long serialVersionUID = 4755769879740283267L;

	private StreamBasedDataStructureSelector<Pair<XEventClass, XEventClass>> activityActivityDataStructureSelector2 = new StreamBasedDataStructureSelector<>(
			"Select Activity-Activity (AxA) Data Structure for Alpha Miner",
			UncoupledXEventClassDFAReaderImpl.DEFAULT_ALLOWED_INTERNAL_REPRESENTATION_DATA_STRUCTURES);

	private StreamBasedDataStructureSelector<Pair<String, XEventClass>> caseActivityDataStructureSelector2 = new StreamBasedDataStructureSelector<>(
			"Select Case-Activity (CxA) Data Structure for Alpha Miner",
			UncoupledXEventClassDFAReaderImpl.DEFAULT_ALLOWED_CASE_ADMINISTRATION_DATA_STRUCTURES);
	
	public UncoupledDataStructuresSecondDialogImpl(UIPluginContext context, String title, P parameters, Dialog<P> previous) {
		this(context, title, parameters, previous, null);
	}

	public UncoupledDataStructuresSecondDialogImpl(UIPluginContext context, String title, P parameters, Dialog<P> previous,
			Route<P> route) {
		super(context, title, parameters, previous, route);
	}

	protected boolean canProceedToNext() {
		return true;
	}

	public Map<DSParameterDefinition, DSParameter<?>> getActivityActivityDataStructureParameters2() {
		return activityActivityDataStructureSelector2.getDataStructureParameterMapping2();
	}

	public Type getActivityActivityDataStructureType2() {
		return activityActivityDataStructureSelector2.getDataStructureType2();
	}

	public Map<DSParameterDefinition, DSParameter<?>> getCaseActivityDataStructureParameters2() {
		return caseActivityDataStructureSelector2.getDataStructureParameterMapping2();
	}

	public Type getCaseActivityDataStructureType2() {
		return caseActivityDataStructureSelector2.getDataStructureType2();
	}

	public JComponent visualize() {
		removeAll();
		add(activityActivityDataStructureSelector2);
		add(caseActivityDataStructureSelector2);
		revalidate();
		repaint();
		return this;
	}

	protected String getUserInputProblems() {
		return "";
	}

	public void updateParametersOnGetNext() {
		getParameters().setInternalRepresentationDataStructureParameters2(getActivityActivityDataStructureParameters2());
		getParameters().setInternalRepresentationDataStructureType2(getActivityActivityDataStructureType2());
		getParameters().setCaseAdministrationDataStructureParameters2(getCaseActivityDataStructureParameters2());
		getParameters().setCaseAdministrationDataStructureType2(getCaseActivityDataStructureType2());
	}

	public void updateParametersOnGetPrevious() {
		updateParametersOnGetNext();
	}

}

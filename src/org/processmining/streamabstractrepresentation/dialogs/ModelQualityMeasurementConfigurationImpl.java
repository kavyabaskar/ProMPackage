
package org.processmining.streamabstractrepresentation.dialogs;

//import java.awt.GridBagConstraints;
//import java.awt.GridBagLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.text.NumberFormat;

//import javax.swing.ButtonGroup;
//import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JRadioButton;

import org.processmining.contexts.uitopia.UIPluginContext;
import org.processmining.streamabstractrepresentation.parameters.UnCoupledAbstractionSchemeParametersImpl;
import org.processmining.widgets.wizard.AbstractRoutableDialog;
import org.processmining.widgets.wizard.Dialog;
import org.processmining.widgets.wizard.Route;

import com.fluxicon.slickerbox.factory.SlickerFactory;

public class ModelQualityMeasurementConfigurationImpl<P extends UnCoupledAbstractionSchemeParametersImpl>
		extends AbstractRoutableDialog<P> {

	public static String DEFAULT_TITLE = "Configure Model Metrics";
	private final JFormattedTextField modelupdateFrequencyfield = new JFormattedTextField(NumberFormat.getIntegerInstance());
	private final JFormattedTextField fitnesscalculationFrequencyfield = new JFormattedTextField(NumberFormat.getIntegerInstance());
	private final JFormattedTextField precisioncalculationFrequencyfield = new JFormattedTextField(NumberFormat.getIntegerInstance());

	private static final long serialVersionUID = 4755769879740283267L;

	public ModelQualityMeasurementConfigurationImpl(UIPluginContext context, String title, P parameters, Dialog<P> previous) {
		this(context, title, parameters, previous, null);
	}

	public ModelQualityMeasurementConfigurationImpl(UIPluginContext context, String title, P parameters, Dialog<P> previous,
			Route<P> route) {
		super(context, title, parameters, previous, route);
		this.modelupdateFrequencyfield.setValue(50);
		this.fitnesscalculationFrequencyfield.setValue(50);
		this.precisioncalculationFrequencyfield.setValue(50);
		
	}
	
	private void addModelUpdateFrequencyParameter() {
		JLabel label = SlickerFactory.instance().createLabel("Model Events Frequency:");
		add(label);
		add(modelupdateFrequencyfield);
	}

	private void addFitnessCalculationFrequencyParameter() {
		JLabel label = SlickerFactory.instance().createLabel("Fitness Events Frequency:");
		add(label);
		add(fitnesscalculationFrequencyfield);
	}
	
	private void addPrecisionCalculationFrequencyParameter() {
		JLabel label = SlickerFactory.instance().createLabel("Precision Events Frequency:");
		add(label);
		add(precisioncalculationFrequencyfield);
	}
	
	protected boolean canProceedToNext() {
		return true;
	}

	public JComponent visualize() {
		removeAll();
		addModelUpdateFrequencyParameter();
		addFitnessCalculationFrequencyParameter();
		addPrecisionCalculationFrequencyParameter();
		revalidate();
		repaint();
		return this;
	}

	protected String getUserInputProblems() {
		return "";
	}

	public void updateParametersOnGetNext() {
		getParameters().setModelUpdateFrequency(modelupdateFrequencyfield.getValue()); //created a new method setModelupdateFrequency in UnCoupledAbstractionSchemeParametersImpl
		getParameters().setFitnessCalculationFrequency(fitnesscalculationFrequencyfield.getValue());
		getParameters().setPrecisionCalculationFrequency(precisioncalculationFrequencyfield.getValue());
	}

	public void updateParametersOnGetPrevious() {
		updateParametersOnGetNext();
	}

}

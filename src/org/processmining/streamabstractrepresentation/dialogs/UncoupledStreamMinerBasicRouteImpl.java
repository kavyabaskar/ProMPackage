package org.processmining.streamabstractrepresentation.dialogs;

import org.processmining.eventstream.readers.dialogs.XSEventStreamCaseAndActivityIdentifierDialog;
import org.processmining.streamabstractrepresentation.parameters.UnCoupledAbstractionSchemeParametersImpl;
import org.processmining.widgets.wizard.Dialog;
import org.processmining.widgets.wizard.Route;

public class UncoupledStreamMinerBasicRouteImpl<P extends UnCoupledAbstractionSchemeParametersImpl>
		implements Route<P> {

	UncoupledDataStructuresDialogImpl<P> uncoupledDataStructuresDialog = null; //this class displays the Configure Event Stream to Directly Follows Abstraction Reader panel
	UncoupledDataStructuresSecondDialogImpl<P> uncoupledDataStructuresSecondDialog = null;
	ModelQualityMeasurementConfigurationImpl<P> modelQualityMeasurementConfigurationImpl = null;
	
	public Dialog<P> getNext(Dialog<P> current) {
		if (current instanceof XSEventStreamCaseAndActivityIdentifierDialog) {
			if (uncoupledDataStructuresDialog == null) {
				uncoupledDataStructuresDialog = new UncoupledDataStructuresDialogImpl<P>(current.getUIPluginContext(),
						UncoupledDataStructuresDialogImpl.DEFAULT_TITLE, current.getParameters(), current, this);
			}
			return uncoupledDataStructuresDialog;
		}

		//for 2nd miner
		if (current instanceof UncoupledDataStructuresDialogImpl) {
			if (uncoupledDataStructuresSecondDialog == null) {
				uncoupledDataStructuresSecondDialog = new UncoupledDataStructuresSecondDialogImpl<P>(current.getUIPluginContext(),
						UncoupledDataStructuresSecondDialogImpl.DEFAULT_TITLE, current.getParameters(), current, this);
			}
			return uncoupledDataStructuresSecondDialog;
		}
		//
		
		//for model metrics
		
		//for 2nd miner
		if (current instanceof UncoupledDataStructuresSecondDialogImpl) {
			if (modelQualityMeasurementConfigurationImpl == null) {
				modelQualityMeasurementConfigurationImpl = new ModelQualityMeasurementConfigurationImpl<P>(current.getUIPluginContext(),
						ModelQualityMeasurementConfigurationImpl.DEFAULT_TITLE, current.getParameters(), current, this);
			}
			return modelQualityMeasurementConfigurationImpl;
		}
		//
		
		return null;
	}

}

package org.processmining.streamabstractrepresentation.plugins;

import org.processmining.contexts.uitopia.UIPluginContext;
import org.processmining.eventstream.core.interfaces.XSEvent;
import org.processmining.eventstream.readers.dialogs.XSEventStreamCaseAndActivityIdentifierDialog;
import org.processmining.stream.core.interfaces.XSReader;
import org.processmining.streamabstractrepresentation.dialogs.UncoupledStreamMinerBasicRouteImpl;
import org.processmining.streamabstractrepresentation.parameters.UnCoupledAbstractionSchemeParametersImpl;
import org.processmining.widgets.wizard.Dialog;
import org.processmining.widgets.wizard.Route;

public abstract class AbstractUncoupledStreamMinerDefaultDialogsPlugin<R extends XSReader<XSEvent, ?>, P extends UnCoupledAbstractionSchemeParametersImpl>
		extends AbstractUncoupledStreamMinerPlugin<R, P> {

	protected Route<P> getDialogRoute(UIPluginContext context, P parameters) {
		return new UncoupledStreamMinerBasicRouteImpl<>(); //this calls the UncoupledDataStructuresDialogImpl
	}

	protected Dialog<P> getFirstDialogForRoute(UIPluginContext context, P parameters, Route<P> route) {
		return new XSEventStreamCaseAndActivityIdentifierDialog<P>(context, //after XSEventStreamCaseAndActivityIdentifierDialog
				XSEventStreamCaseAndActivityIdentifierDialog.DEFAULT_TITLE, parameters, null, route);
	} //goes to AbstractUncoupledStreamMinerPlugin

}

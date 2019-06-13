package org.processmining.streamabstractrepresentation.plugins;

import org.deckfour.uitopia.api.event.TaskListener.InteractionResult;
import org.processmining.contexts.uitopia.UIPluginContext;
import org.processmining.eventstream.core.interfaces.XSEvent;
import org.processmining.eventstream.core.interfaces.XSEventStream;
import org.processmining.framework.plugin.PluginContext;
import org.processmining.stream.core.enums.CommunicationType;
import org.processmining.stream.core.interfaces.XSReader;
import org.processmining.streamabstractrepresentation.parameters.UnCoupledAbstractionSchemeParametersImpl;
import org.processmining.widgets.wizard.Dialog;
import org.processmining.widgets.wizard.Route;
import org.processmining.widgets.wizard.Wizard;
import org.processmining.widgets.wizard.WizardResult;

public abstract class AbstractUncoupledStreamMinerPlugin<R extends XSReader<XSEvent, ?>, P extends UnCoupledAbstractionSchemeParametersImpl> {

	public R apply(final UIPluginContext context, final XSEventStream stream) {
		P parameters = getFreshParameters(context);
		Route<P> route = getDialogRoute(context, parameters);
		Dialog<P> dialog = getFirstDialogForRoute(context, parameters, route); //after AbstractUncoupledStreamMinerDefaultDialogsPlugin
		WizardResult<P> res = Wizard.show(context, dialog); //after this, displays the Case and Activity identification panel
		if (res.getInteractionResult().equals(InteractionResult.FINISHED)) { //after I click Finish, on the Configure Event stream to Directly Follows Abstraction panel
			return apply(context, stream, res.getParameters());
		} else {
			context.getFutureResult(0).cancel(true); //if Cancel is clicked, then goes back to plug-in screen
			return null;
		}
	}

	protected abstract P getFreshParameters(final PluginContext context);

	protected abstract Route<P> getDialogRoute(final UIPluginContext context, final P parameters);

	protected abstract Dialog<P> getFirstDialogForRoute(final UIPluginContext context, final P parameters,
			final Route<P> route);

	public R apply(final PluginContext context, final XSEventStream stream, P parameters) {
		R reader = constructReader(context, stream.getCommunicationType(), parameters); //constructReader is again implemented in the StreamInductiveMinerAcceptingPetriNetPlugin
		reader.start();
		stream.connect(reader);
		return reader;
	}

	protected abstract R constructReader(PluginContext context, CommunicationType comm, P parameters);

}

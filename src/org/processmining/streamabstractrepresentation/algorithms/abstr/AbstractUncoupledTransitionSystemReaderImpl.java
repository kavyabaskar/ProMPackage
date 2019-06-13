package org.processmining.streamabstractrepresentation.algorithms.abstr;

import org.processmining.models.graphbased.directed.transitionsystem.TransitionSystem;
import org.processmining.models.graphbased.directed.transitionsystem.TransitionSystemFactory;
import org.processmining.models.graphbased.directed.transitionsystem.TransitionSystemImpl;
import org.processmining.stream.core.interfaces.XSVisualization;
import org.processmining.stream.model.datastructure.PointerBasedDataStructure;
import org.processmining.streamabstractrepresentation.parameters.UnCoupledAbstractionSchemeParametersImpl;

public abstract class AbstractUncoupledTransitionSystemReaderImpl<V, I, C, P extends UnCoupledAbstractionSchemeParametersImpl>
		extends AbstractUncoupledCaseAdministrationReaderImpl<V, I, TransitionSystem, C, P> {

	private final TransitionSystem ts = new TransitionSystemImpl("stream_based_transition_system");

	public AbstractUncoupledTransitionSystemReaderImpl(String name, P params) {
		super(name, null, params);

	}

	public AbstractUncoupledTransitionSystemReaderImpl(final String name, final XSVisualization<V> visualization,
			final P parameters) {
		super(name, null, parameters, null, true);
	}

	public AbstractUncoupledTransitionSystemReaderImpl(final String name, final XSVisualization<V> visualization,
			final P parameters, final PointerBasedDataStructure<String, C> caseAdministration, final boolean updateCA) {
		super(name, visualization, parameters, caseAdministration, updateCA);
	}

	protected TransitionSystem getTransitionSystem() {
		return ts;
	}

	protected TransitionSystem computeCurrentResult1() {
		return TransitionSystemFactory.cloneTransitionSystem(ts);
	}

	//for 2nd miner
	protected TransitionSystem computeCurrentResult2() {
		return TransitionSystemFactory.cloneTransitionSystem(ts);
	}
	//
}

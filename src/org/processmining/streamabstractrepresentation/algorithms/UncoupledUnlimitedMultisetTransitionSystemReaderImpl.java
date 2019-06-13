package org.processmining.streamabstractrepresentation.algorithms;

import java.util.Collection;

import org.deckfour.xes.classification.XEventClass;
import org.processmining.framework.util.collection.HashMultiSet;
import org.processmining.framework.util.collection.MultiSet;
import org.processmining.stream.core.interfaces.XSVisualization;
import org.processmining.stream.model.datastructure.PointerBasedDataStructure;
import org.processmining.streamabstractrepresentation.algorithms.abstr.AbstractUncoupledTransitionSystemReaderImpl;
import org.processmining.streamabstractrepresentation.parameters.UnCoupledAbstractionSchemeParametersImpl;

// TODO: incorporate arbitrary multiset length
public class UncoupledUnlimitedMultisetTransitionSystemReaderImpl<V, P extends UnCoupledAbstractionSchemeParametersImpl>
		extends AbstractUncoupledTransitionSystemReaderImpl<V, MultiSet<XEventClass>, MultiSet<XEventClass>, P> {

	public UncoupledUnlimitedMultisetTransitionSystemReaderImpl(String name, P params) {
		super(name, null, params);
	}

	public UncoupledUnlimitedMultisetTransitionSystemReaderImpl(final String name,
			final XSVisualization<V> visualization, final P parameters) {
		super(name, null, parameters, null, true);
	}

	public UncoupledUnlimitedMultisetTransitionSystemReaderImpl(final String name,
			final XSVisualization<V> visualization, final P parameters,
			final PointerBasedDataStructure<String, MultiSet<XEventClass>> caseActivityDataStructure,
			final boolean updateCA) {
		super(name, visualization, parameters, caseActivityDataStructure, updateCA);
	}

	public void processNewXSEvent(String caseId, XEventClass activity) {
		MultiSet<XEventClass> prev;
		if (!getCaseAdministration1().contains(caseId)) {
			prev = new HashMultiSet<XEventClass>();
			getInternalRepresentation1().add(prev);
		} else {
			prev = getCaseAdministration1().getPointedElement(caseId);
		}
		MultiSet<XEventClass> newMs = new HashMultiSet<>(prev);
		newMs.add(activity);
		getTransitionSystem().addState(prev);
		getTransitionSystem().addState(newMs);
		getTransitionSystem().addTransition(prev, newMs, activity);
		Collection<MultiSet<XEventClass>> removed = getInternalRepresentation1().add(newMs);
		for (MultiSet<XEventClass> m : removed) {
			getTransitionSystem().removeState(m);
			//TODO: handle removal of state (i.e. fix the transition system)
		}
		if (isUpdateCaseAdministration()) {
			getCaseAdministration1().add(caseId, newMs);
		}
	}
	//for 2nd miner
	public void processNewXSEvent2(String caseId, XEventClass activity) {
		MultiSet<XEventClass> prev2;
		if (!getCaseAdministration2().contains(caseId)) {
			prev2 = new HashMultiSet<XEventClass>();
			getInternalRepresentation2().add(prev2);
		} else {
			prev2 = getCaseAdministration2().getPointedElement(caseId);
		}
		MultiSet<XEventClass> newMs2 = new HashMultiSet<>(prev2);
		newMs2.add(activity);
		getTransitionSystem().addState(prev2);
		getTransitionSystem().addState(newMs2);
		getTransitionSystem().addTransition(prev2, newMs2, activity);
		Collection<MultiSet<XEventClass>> removed2 = getInternalRepresentation2().add(newMs2);
		for (MultiSet<XEventClass> m : removed2) {
			getTransitionSystem().removeState(m);
			//TODO: handle removal of state (i.e. fix the transition system)
		}
		if (isUpdateCaseAdministration()) {
			getCaseAdministration2().add(caseId, newMs2);
		}
		//
	}

	public MultiSet<XEventClass> getNewMultiSetView(String caseId, XEventClass activity) {
		MultiSet<XEventClass> prev;
		if (!getCaseAdministration1().contains(caseId)) {
			prev = new HashMultiSet<XEventClass>();
		} else {
			prev = getCaseAdministration1().getPointedElement(caseId);
		}
		MultiSet<XEventClass> newMs = new HashMultiSet<>(prev);
		newMs.add(activity);
		return newMs;
	}
	
	//for 2nd miner
	public MultiSet<XEventClass> getNewMultiSetView2(String caseId, XEventClass activity) {
		MultiSet<XEventClass> prev2;
		if (!getCaseAdministration2().contains(caseId)) {
			prev2 = new HashMultiSet<XEventClass>();
		} else {
			prev2 = getCaseAdministration2().getPointedElement(caseId);
		}
		MultiSet<XEventClass> newMs2 = new HashMultiSet<>(prev2);
		newMs2.add(activity);
		return newMs2;
	}
	//
}

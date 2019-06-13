package org.processmining.streamabstractrepresentation.algorithms;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.deckfour.xes.classification.XEventClass;
import org.processmining.models.graphbased.directed.transitionsystem.Transition;
import org.processmining.stream.core.interfaces.XSVisualization;
import org.processmining.stream.model.datastructure.PointerBasedDataStructure;
import org.processmining.streamabstractrepresentation.algorithms.abstr.AbstractUncoupledTransitionSystemReaderImpl;
import org.processmining.streamabstractrepresentation.parameters.UnCoupledAbstractionSchemeParametersImpl;

// TODO: make size of set arbitrary
public class UncoupledSizeOneSetTransitionSystemReaderImpl<V, P extends UnCoupledAbstractionSchemeParametersImpl>
		extends AbstractUncoupledTransitionSystemReaderImpl<V, Set<XEventClass>, Set<XEventClass>, P> {

	public UncoupledSizeOneSetTransitionSystemReaderImpl(String name, P params) {
		super(name, null, params);

	}

	public UncoupledSizeOneSetTransitionSystemReaderImpl(final String name, final XSVisualization<V> visualization,
			final P parameters) {
		super(name, null, parameters, null, true);
	}

	public UncoupledSizeOneSetTransitionSystemReaderImpl(final String name, final XSVisualization<V> visualization,
			final P parameters, final PointerBasedDataStructure<String, Set<XEventClass>> caseActivityDataStructure,
			final boolean updateCA) {
		super(name, visualization, parameters, caseActivityDataStructure, updateCA);
	}

	public void processNewXSEvent(String caseId, XEventClass activity) {
		Set<XEventClass> prev;
		Set<XEventClass> prev2;
		Set<XEventClass> newSet = new HashSet<>();
		Set<XEventClass> newSet2 = new HashSet<>();
		Collection<Set<XEventClass>> removed = new HashSet<>();
		Collection<Set<XEventClass>> removed2 = new HashSet<>();
		if (!getCaseAdministration1().contains(caseId)) {
			prev = new HashSet<XEventClass>();
			removed.addAll(getInternalRepresentation1().add(prev));
			getTransitionSystem().addState(prev);
		} else {
			prev = getCaseAdministration1().getPointedElement(caseId);
		}
		newSet.add(activity);
		getTransitionSystem().addState(newSet);
		getTransitionSystem().addTransition(prev, newSet, activity);
		removed.addAll(getInternalRepresentation1().add(newSet));
		for (Set<XEventClass> m : removed) {

			org.processmining.models.graphbased.directed.transitionsystem.State s = getTransitionSystem().getNode(m);
			Collection<Transition> inEdges = getTransitionSystem().getInEdges(s);
			Collection<Transition> outEdges = getTransitionSystem().getOutEdges(s);
			for (Transition t : inEdges) {
				getTransitionSystem().removeEdge(t);
			}
			for (Transition t : outEdges) {
				getTransitionSystem().removeEdge(t);
			}
			getTransitionSystem().removeState(m);
		}
		if (isUpdateCaseAdministration()) {
			getCaseAdministration1().add(caseId, newSet);
		}
		
		//for 2nd miner
		if (!getCaseAdministration2().contains(caseId)) {
			prev2 = new HashSet<XEventClass>();
			removed2.addAll(getInternalRepresentation2().add(prev2));
			getTransitionSystem().addState(prev2);
		} else {
			prev2 = getCaseAdministration2().getPointedElement(caseId);
		}
		newSet2.add(activity);
		getTransitionSystem().addState(newSet2);
		getTransitionSystem().addTransition(prev2, newSet2, activity);
		removed2.addAll(getInternalRepresentation2().add(newSet2));
		for (Set<XEventClass> m : removed2) {

			org.processmining.models.graphbased.directed.transitionsystem.State s = getTransitionSystem().getNode(m);
			Collection<Transition> inEdges = getTransitionSystem().getInEdges(s);
			Collection<Transition> outEdges = getTransitionSystem().getOutEdges(s);
			for (Transition t : inEdges) {
				getTransitionSystem().removeEdge(t);
			}
			for (Transition t : outEdges) {
				getTransitionSystem().removeEdge(t);
			}
			getTransitionSystem().removeState(m);
		}
		if (isUpdateCaseAdministration()) {
			getCaseAdministration2().add(caseId, newSet2);
		}
	}
}

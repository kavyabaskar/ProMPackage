package org.processmining.streamabstractrepresentation.algorithms;

import org.deckfour.xes.classification.XEventClass;
import org.processmining.stream.core.interfaces.XSVisualization;
import org.processmining.stream.model.datastructure.PointerBasedDataStructure;
import org.processmining.streamabstractrepresentation.algorithms.abstr.AbstractUncoupledCaseAdministrationReaderImpl;
import org.processmining.streamabstractrepresentation.parameters.UnCoupledAbstractionSchemeParametersImpl;

import gnu.trove.map.TObjectLongMap;
import gnu.trove.map.hash.TObjectLongHashMap;

public class UncoupledActivityFrequencyApproximationReaderImpl<V, C, P extends UnCoupledAbstractionSchemeParametersImpl>
		extends AbstractUncoupledCaseAdministrationReaderImpl<V, XEventClass, TObjectLongMap<XEventClass>, C, P> {

	public UncoupledActivityFrequencyApproximationReaderImpl(String name, XSVisualization<V> visualization,
			P parameters, PointerBasedDataStructure<String, C> caseActivityDataStructure, boolean updateCA) {
		super(name, visualization, parameters, caseActivityDataStructure, updateCA);
	}

	public void processNewXSEvent(String caseId, XEventClass activity) {
		getInternalRepresentation1().add(activity);
		getInternalRepresentation2().add(activity);
	}

	protected TObjectLongMap<XEventClass> computeCurrentResult1() {
		TObjectLongMap<XEventClass> map = new TObjectLongHashMap<>();
		for (XEventClass ec : getInternalRepresentation1()) {
			map.put(ec, getInternalRepresentation1().getFrequencyOf(ec));
		}
		return map;
	}
	
	//for 2nd miner
	protected TObjectLongMap<XEventClass> computeCurrentResult2() {
		TObjectLongMap<XEventClass> map2 = new TObjectLongHashMap<>();
		for (XEventClass ec : getInternalRepresentation2()) {
			map2.put(ec, getInternalRepresentation2().getFrequencyOf(ec));
		}
		return map2;
	}
	//

}

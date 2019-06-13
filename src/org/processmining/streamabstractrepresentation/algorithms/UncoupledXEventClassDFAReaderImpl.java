package org.processmining.streamabstractrepresentation.algorithms;

import org.deckfour.xes.classification.XEventClass;
import org.processmining.framework.util.Pair;
import org.processmining.stream.core.interfaces.XSVisualization;
import org.processmining.stream.model.datastructure.PointerBasedDataStructure;
import org.processmining.streamabstractrepresentation.algorithms.abstr.AbstractUncoupledDFReaderImpl;
import org.processmining.streamabstractrepresentation.parameters.UnCoupledAbstractionSchemeParametersImpl;

public class UncoupledXEventClassDFAReaderImpl<V, P extends UnCoupledAbstractionSchemeParametersImpl>
		extends AbstractUncoupledDFReaderImpl<V, XEventClass, P> {

	public UncoupledXEventClassDFAReaderImpl(String name, P params) {
		super(name, null, params);

	}

	public UncoupledXEventClassDFAReaderImpl(final String name, final XSVisualization<V> visualization,
			final P parameters) {
		super(name, null, parameters, null, true);

	}

	public UncoupledXEventClassDFAReaderImpl(final String name, final XSVisualization<V> visualization,
			final P parameters, final PointerBasedDataStructure<String, XEventClass> caseActivityDataStructure,
			final boolean updateCA) {
		super(name, visualization, parameters, caseActivityDataStructure, updateCA);

	}

	protected void updateInternalDataStructure(String caseId, XEventClass activity) {
		if (getCaseAdministration1().contains(caseId)) {
			XEventClass previousActivityForCase1 = getCaseAdministration1().getPointedElement(caseId);
			getInternalRepresentation1().add(new Pair<XEventClass, XEventClass>(previousActivityForCase1, activity));
		}
		
		//for 2nd miner
		if (getCaseAdministration2().contains(caseId)) {
			XEventClass previousActivityForCase2 = getCaseAdministration2().getPointedElement(caseId);
			getInternalRepresentation2().add(new Pair<XEventClass, XEventClass>(previousActivityForCase2, activity));
		}
		//
	}

	protected void updateCaseAdministration(String caseId, XEventClass eventClass) {
		getCaseAdministration1().add(caseId, eventClass);
		getCaseAdministration2().add(caseId, eventClass);
	}

}

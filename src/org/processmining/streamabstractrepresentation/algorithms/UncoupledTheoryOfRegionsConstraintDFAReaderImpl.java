package org.processmining.streamabstractrepresentation.algorithms;

import org.deckfour.xes.classification.XEventClass;
import org.processmining.framework.util.Pair;
import org.processmining.framework.util.collection.HashMultiSet;
import org.processmining.framework.util.collection.MultiSet;
import org.processmining.hybridilpminer.models.thr.TheoryOfRegionsConstraintImpl;
import org.processmining.stream.core.interfaces.XSVisualization;
import org.processmining.stream.model.datastructure.PointerBasedDataStructure;
import org.processmining.streamabstractrepresentation.algorithms.abstr.AbstractUncoupledDFReaderImpl;
import org.processmining.streamabstractrepresentation.parameters.UnCoupledAbstractionSchemeParametersImpl;

public class UncoupledTheoryOfRegionsConstraintDFAReaderImpl<V, P extends UnCoupledAbstractionSchemeParametersImpl>
		extends AbstractUncoupledDFReaderImpl<V, TheoryOfRegionsConstraintImpl<XEventClass>, P> {

	public UncoupledTheoryOfRegionsConstraintDFAReaderImpl(String name, P params) {
		super(name, null, params);
	}

	public UncoupledTheoryOfRegionsConstraintDFAReaderImpl(final String name, final XSVisualization<V> visualization,
			final P parameters) {
		super(name, null, parameters, null, true);
	}

	public UncoupledTheoryOfRegionsConstraintDFAReaderImpl(final String name, final XSVisualization<V> visualization,
			final P parameters,
			final PointerBasedDataStructure<String, TheoryOfRegionsConstraintImpl<XEventClass>> caseActivityDataStructure,
			final boolean updateCA) {
		super(name, visualization, parameters, caseActivityDataStructure, updateCA);

	}

	protected void updateInternalDataStructure(String caseId, XEventClass activity) {
		if (getCaseAdministration1().contains(caseId)) {
			TheoryOfRegionsConstraintImpl<XEventClass> previousObjectForCase = getCaseAdministration1()
					.getPointedElement(caseId);
			getInternalRepresentation1()
					.add(new Pair<XEventClass, XEventClass>(previousObjectForCase.getLast(), activity));
		}
		//for 2nd miner
		if (getCaseAdministration2().contains(caseId)) {
			TheoryOfRegionsConstraintImpl<XEventClass> previousObjectForCase = getCaseAdministration2()
					.getPointedElement(caseId);
			getInternalRepresentation2()
					.add(new Pair<XEventClass, XEventClass>(previousObjectForCase.getLast(), activity));
		}
		//
	}

	protected void updateCaseAdministration(String caseId, XEventClass eventClass) {
		TheoryOfRegionsConstraintImpl<XEventClass> oldConstraint;
		TheoryOfRegionsConstraintImpl<XEventClass> oldConstraint2;
		MultiSet<XEventClass> prefix;
		MultiSet<XEventClass> prefix2;
		TheoryOfRegionsConstraintImpl<XEventClass> constraint;
		TheoryOfRegionsConstraintImpl<XEventClass> constraint2;
		if ((oldConstraint = getCaseAdministration1().getPointedElement(caseId)) != null) {
			prefix = new HashMultiSet<>(oldConstraint.getPrefix());
			prefix.add(oldConstraint.getLast());
			constraint = new TheoryOfRegionsConstraintImpl<XEventClass>(prefix, eventClass);
		} else {
			prefix = new HashMultiSet<>();
			constraint = new TheoryOfRegionsConstraintImpl<XEventClass>(prefix, eventClass);
		}
		getCaseAdministration1().add(caseId, constraint);
		
		//for 2nd miner
		if ((oldConstraint2 = getCaseAdministration2().getPointedElement(caseId)) != null) {
			prefix2 = new HashMultiSet<>(oldConstraint2.getPrefix());
			prefix2.add(oldConstraint2.getLast());
			constraint2 = new TheoryOfRegionsConstraintImpl<XEventClass>(prefix2, eventClass);
		} else {
			prefix2 = new HashMultiSet<>();
			constraint2 = new TheoryOfRegionsConstraintImpl<XEventClass>(prefix2, eventClass);
		}
		getCaseAdministration2().add(caseId, constraint2);
		//
	}

}

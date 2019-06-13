package org.processmining.streamabstractrepresentation.algorithms;

import java.util.Collection;
import java.util.EnumSet;

import javax.naming.OperationNotSupportedException;

import org.deckfour.xes.classification.XEventClass;
import org.processmining.eventstream.readers.abstr.AbstractXSEventReader;
import org.processmining.framework.util.collection.HashMultiSet;
import org.processmining.framework.util.collection.MultiSet;
import org.processmining.hybridilpminer.models.thr.TheoryOfRegionsConstraintImpl;
import org.processmining.stream.core.interfaces.XSVisualization;
import org.processmining.stream.model.datastructure.DSParameterMissingException;
import org.processmining.stream.model.datastructure.DSWrongParameterException;
import org.processmining.stream.model.datastructure.DataStructure.Type;
import org.processmining.stream.model.datastructure.DataStructureFactory;
import org.processmining.stream.model.datastructure.IterableDataStructure;
import org.processmining.stream.model.datastructure.PointerBasedDataStructure;
import org.processmining.streamabstractrepresentation.parameters.UnCoupledAbstractionSchemeParametersImpl;

/**
 * quick n dirty implementation of uncoupled ILP Base Engine reader. No fancy
 * optimization tricks are used as the algorithm is just used for some basic
 * experiments.
 * 
 * @author svzelst
 *
 * @param <V>
 * @param <P>
 */
public class UncoupledConstraintReaderImpl<V, P extends UnCoupledAbstractionSchemeParametersImpl>
		extends AbstractXSEventReader<MultiSet<TheoryOfRegionsConstraintImpl<XEventClass>>, V, P> {

	public static Collection<Type> DEFAULT_ALLOWED_ACTIVITY_ACTIVITY_DATA_STRUCTURES = EnumSet.of(Type.LOSSY_COUNTING,
			Type.FREQUENT_ALGORITHM, Type.SPACE_SAVING, Type.LOSSY_COUNTING_BUDGET, Type.SLIDING_WINDOW, Type.FORWARD_EXPONENTIAL_DECAY);

	public static Collection<Type> DEFAULT_ALLOWED_CASE_ACTIVITY_DATA_STRUCTURES = EnumSet
			.of(Type.LOSSY_COUNTING_POINTER, Type.FREQUENT_POINTER, Type.SPACE_SAVING_POINTER, Type.LOSSY_COUNTING_BUDGET_POINTER, Type.SLIDING_WINDOW_POINTER, Type.FORWARD_EXPONENTIAL_DECAY_POINTER);

	private PointerBasedDataStructure<String, TheoryOfRegionsConstraintImpl<XEventClass>> caseDataStructure1;
	private IterableDataStructure<TheoryOfRegionsConstraintImpl<XEventClass>> constraints1;
	
	//for 2nd miner
	private PointerBasedDataStructure<String, TheoryOfRegionsConstraintImpl<XEventClass>> caseDataStructure2;
	private IterableDataStructure<TheoryOfRegionsConstraintImpl<XEventClass>> constraints2;
	//
	
	private final TheoryOfRegionsConstraintImpl<XEventClass> emptyConstraint = new TheoryOfRegionsConstraintImpl<XEventClass>(
			new HashMultiSet<XEventClass>(), null);

	private final boolean updateCA1;
	
	//for 2nd miner
	private final boolean updateCA2;
	//

	public UncoupledConstraintReaderImpl(String name, P params) {
		this(name, null, params);

	}

	public UncoupledConstraintReaderImpl(final String name, final XSVisualization<V> visualization,
			final P parameters) {
		this(name, null, parameters, null, true, true);
	}

	@SuppressWarnings("unchecked")
	public UncoupledConstraintReaderImpl(final String name, final XSVisualization<V> visualization, final P parameters,
			final PointerBasedDataStructure<String, TheoryOfRegionsConstraintImpl<XEventClass>> caseActivityDataStructure,
			final boolean updateCA1, final boolean updateCA2) {
		super(name, visualization, parameters);
		try {
			this.caseDataStructure1 = (PointerBasedDataStructure<String, TheoryOfRegionsConstraintImpl<XEventClass>>) (caseActivityDataStructure == null
					? DataStructureFactory.createPointerDataStructure(parameters.getCaseAdministrationDataStructureType1(),
							parameters.getCaseAdministrationDataStructureParameters1())
					: caseActivityDataStructure);
		} catch (OperationNotSupportedException | DSParameterMissingException | DSWrongParameterException e) {
			e.printStackTrace();
			this.caseDataStructure1 = null;
			this.interrupt();
		}
		try {
			constraints1 = DataStructureFactory.createIterableDataStructure(
					parameters.getInternalRepresentationDataStructureType1(),
					parameters.getInternalRepresentationDataStructureParameters1());
		} catch (DSParameterMissingException | DSWrongParameterException | OperationNotSupportedException e) {
			e.printStackTrace();
			constraints1 = null;
			this.interrupt();
		}
		this.updateCA1 = updateCA1;
		
		//for 2nd miner
		try {
			this.caseDataStructure2 = (PointerBasedDataStructure<String, TheoryOfRegionsConstraintImpl<XEventClass>>) (caseActivityDataStructure == null
					? DataStructureFactory.createPointerDataStructure(parameters.getCaseAdministrationDataStructureType2(),
							parameters.getCaseAdministrationDataStructureParameters2())
					: caseActivityDataStructure);
		} catch (OperationNotSupportedException | DSParameterMissingException | DSWrongParameterException e) {
			e.printStackTrace();
			this.caseDataStructure2 = null;
			this.interrupt();
		}
		try {
			constraints2 = DataStructureFactory.createIterableDataStructure(
					parameters.getInternalRepresentationDataStructureType2(),
					parameters.getInternalRepresentationDataStructureParameters2());
		} catch (DSParameterMissingException | DSWrongParameterException | OperationNotSupportedException e) {
			e.printStackTrace();
			constraints2 = null;
			this.interrupt();
		}
		this.updateCA2 = updateCA2;
		//
	}

	protected MultiSet<TheoryOfRegionsConstraintImpl<XEventClass>> computeCurrentResult1() {
		MultiSet<TheoryOfRegionsConstraintImpl<XEventClass>> res1 = new HashMultiSet<>();
		for (TheoryOfRegionsConstraintImpl<XEventClass> c : constraints1) {
			res1.add(c, (int) constraints1.getFrequencyOf(c));
		}
		return res1;
	}

	//for 2nd miner
	protected MultiSet<TheoryOfRegionsConstraintImpl<XEventClass>> computeCurrentResult2() {
		MultiSet<TheoryOfRegionsConstraintImpl<XEventClass>> res2 = new HashMultiSet<>();
		for (TheoryOfRegionsConstraintImpl<XEventClass> c : constraints1) {
			res2.add(c, (int) constraints1.getFrequencyOf(c));
		}
		return res2;
	}
	//
	
	public void processNewXSEvent(String caseId, XEventClass activity) {
		if (!caseDataStructure1.contains(caseId)) {
			constraints1.add(emptyConstraint);
		}
		TheoryOfRegionsConstraintImpl<XEventClass> constraint1 = getNewConstraint1(caseId, activity);
		constraints1.add(constraint1);
		if (updateCA1) {
			caseDataStructure1.add(caseId, constraint1);
		}
		
		//for 2nd miner
		if (!caseDataStructure2.contains(caseId)) {
			constraints2.add(emptyConstraint);
		}
		TheoryOfRegionsConstraintImpl<XEventClass> constraint2 = getNewConstraint2(caseId, activity);
		constraints2.add(constraint2);
		if (updateCA2) {
			caseDataStructure2.add(caseId, constraint2);
		}
		//
	}

	public TheoryOfRegionsConstraintImpl<XEventClass> getNewConstraint1(String caseId, XEventClass activity) {
		TheoryOfRegionsConstraintImpl<XEventClass> oldConstraint;
		MultiSet<XEventClass> prefix;
		TheoryOfRegionsConstraintImpl<XEventClass> constraint1;
		if ((oldConstraint = caseDataStructure1.getPointedElement(caseId)) != null) {
			prefix = new HashMultiSet<>(oldConstraint.getPrefix());
			prefix.add(oldConstraint.getLast());
		} else {
			prefix = new HashMultiSet<>();
		}
		constraint1 = new TheoryOfRegionsConstraintImpl<XEventClass>(prefix, activity);
		return constraint1;
	}
	
	//for 2nd miner
	public TheoryOfRegionsConstraintImpl<XEventClass> getNewConstraint2(String caseId, XEventClass activity) {
		TheoryOfRegionsConstraintImpl<XEventClass> oldConstraint;
		MultiSet<XEventClass> prefix;
		TheoryOfRegionsConstraintImpl<XEventClass> constraint2;
		if ((oldConstraint = caseDataStructure2.getPointedElement(caseId)) != null) {
			prefix = new HashMultiSet<>(oldConstraint.getPrefix());
			prefix.add(oldConstraint.getLast());
		} else {
			prefix = new HashMultiSet<>();
		}
		constraint2 = new TheoryOfRegionsConstraintImpl<XEventClass>(prefix, activity);
		return constraint2;
	}
	//
}

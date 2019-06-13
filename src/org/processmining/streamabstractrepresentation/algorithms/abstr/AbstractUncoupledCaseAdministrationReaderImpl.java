package org.processmining.streamabstractrepresentation.algorithms.abstr;

import javax.naming.OperationNotSupportedException;

import org.processmining.eventstream.readers.abstr.AbstractXSEventReader;
import org.processmining.stream.core.interfaces.XSVisualization;
import org.processmining.stream.model.datastructure.DSParameterMissingException;
import org.processmining.stream.model.datastructure.DSWrongParameterException;
import org.processmining.stream.model.datastructure.DataStructureFactory;
import org.processmining.stream.model.datastructure.IterableDataStructure;
import org.processmining.stream.model.datastructure.PointerBasedDataStructure;
import org.processmining.streamabstractrepresentation.algorithms.UncoupledCaseAdministrationXSEventReader;
import org.processmining.streamabstractrepresentation.parameters.UnCoupledAbstractionSchemeParametersImpl;

/**
 * Entity that learns some (part of an) abstract representation on a stream. It
 * uses an uncoupled aging scheme, i.e., aging of the abstraction is independent
 * of the case administration. If it is used within a different entity, one can
 * provide it with a case administration and a boolean specifying whether this
 * class should update that structure.
 * 
 * @author svzelst
 *
 * @param <V>
 *            visualization type
 * 
 * @param <I>
 *            internal view of (part of) abstract representation
 * 
 * @param <R>
 *            (part of) abstract representation
 * 
 * @param <P>
 *            parameter object
 * @param <C>
 *            type of objects pointed to by cases
 */
public abstract class AbstractUncoupledCaseAdministrationReaderImpl<V, I, R, C, P extends UnCoupledAbstractionSchemeParametersImpl>
		extends AbstractXSEventReader<R, V, P> implements UncoupledCaseAdministrationXSEventReader<R, C> {

	private PointerBasedDataStructure<String, C> caseAdministration1;
	private PointerBasedDataStructure<String, C> caseAdministration2;
	private IterableDataStructure<I> internalRepresentation1;
	private IterableDataStructure<I> internalRepresentation2;
	private final boolean updateCA;

	public AbstractUncoupledCaseAdministrationReaderImpl(String name, P params) {
		this(name, null, params);
	}

	public AbstractUncoupledCaseAdministrationReaderImpl(final String name, final XSVisualization<V> visualization,
			final P parameters) {
		this(name, visualization, parameters, null, true);
	}

	@SuppressWarnings("unchecked")
	public AbstractUncoupledCaseAdministrationReaderImpl(final String name, final XSVisualization<V> visualization,
			final P parameters, final PointerBasedDataStructure<String, C> caseActivityDataStructure,
			final boolean updateCA) {
		super(name, visualization, parameters);
		try {
			this.caseAdministration1 = (PointerBasedDataStructure<String, C>) (caseActivityDataStructure == null
					? DataStructureFactory.createPointerDataStructure(
							parameters.getCaseAdministrationDataStructureType1(),
							parameters.getCaseAdministrationDataStructureParameters1())
					: caseActivityDataStructure);
		} catch (OperationNotSupportedException | DSParameterMissingException | DSWrongParameterException e) {
			e.printStackTrace();
			this.caseAdministration1 = null;
			this.interrupt();
		}
		try {
			internalRepresentation1 = DataStructureFactory.createIterableDataStructure(
					parameters.getInternalRepresentationDataStructureType1(),
					parameters.getInternalRepresentationDataStructureParameters1());
		} catch (DSParameterMissingException | DSWrongParameterException | OperationNotSupportedException e) {
			e.printStackTrace();
			internalRepresentation1 = null;
			this.interrupt();
		}
		
		//for 2nd miner
		try {
			this.caseAdministration2 = (PointerBasedDataStructure<String, C>) (caseActivityDataStructure == null
					? DataStructureFactory.createPointerDataStructure(
							parameters.getCaseAdministrationDataStructureType2(),
							parameters.getCaseAdministrationDataStructureParameters2())
					: caseActivityDataStructure);
		} catch (OperationNotSupportedException | DSParameterMissingException | DSWrongParameterException e) {
			e.printStackTrace();
			this.caseAdministration2 = null;
			this.interrupt();
		}
		try {
			internalRepresentation2 = DataStructureFactory.createIterableDataStructure(
					parameters.getInternalRepresentationDataStructureType2(),
					parameters.getInternalRepresentationDataStructureParameters2());
		} catch (DSParameterMissingException | DSWrongParameterException | OperationNotSupportedException e) {
			e.printStackTrace();
			internalRepresentation2 = null;
			this.interrupt();
		}
		//
		this.updateCA = updateCA;
	}

	protected IterableDataStructure<I> getInternalRepresentation1() {
		return internalRepresentation1;
	}
	
	//for 2nd miner
	protected IterableDataStructure<I> getInternalRepresentation2() {
		return internalRepresentation2;
	}
	//

	public PointerBasedDataStructure<String, C> getCaseAdministration1() {
		return caseAdministration1;
	}
	
	//for 2nd miner
	public PointerBasedDataStructure<String, C> getCaseAdministration2() {
		return caseAdministration2;
	}
	//

	public boolean isUpdateCaseAdministration() {
		return updateCA;
	}

}

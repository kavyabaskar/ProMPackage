package org.processmining.streamabstractrepresentation.algorithms;

import java.util.Collection;
import java.util.EnumSet;

import org.processmining.eventstream.core.interfaces.XSEvent;
import org.processmining.stream.core.interfaces.XSReader;
import org.processmining.stream.model.datastructure.DataStructure.Type;
import org.processmining.stream.model.datastructure.PointerBasedDataStructure;

public interface UncoupledCaseAdministrationXSEventReader<R, C> extends XSReader<XSEvent, R> {

	public static Type DEFAULT_INTERNAL_REPRESENTATION_DATA_STRUCTURE = Type.LOSSY_COUNTING;

	public static Type DEFAULT_CASE_ADMINISTRATION_DATA_STRUCTURE = Type.LOSSY_COUNTING;

	public static Collection<Type> DEFAULT_ALLOWED_INTERNAL_REPRESENTATION_DATA_STRUCTURES = EnumSet
			.of(Type.LOSSY_COUNTING, Type.FREQUENT_ALGORITHM, Type.SPACE_SAVING, Type.LOSSY_COUNTING_BUDGET, Type.SLIDING_WINDOW, Type.FORWARD_EXPONENTIAL_DECAY, Type.BACKWARD_EXPONENTIAL_DECAY);

	public static Collection<Type> DEFAULT_ALLOWED_CASE_ADMINISTRATION_DATA_STRUCTURES = EnumSet
			.of(Type.LOSSY_COUNTING_POINTER, Type.FREQUENT_POINTER, Type.SPACE_SAVING_POINTER, Type.LOSSY_COUNTING_BUDGET_POINTER, Type.SLIDING_WINDOW_POINTER, Type.FORWARD_EXPONENTIAL_DECAY_POINTER, Type.BACKWARD_EXPONENTIAL_DECAY_POINTER);

	PointerBasedDataStructure<String, C> getCaseAdministration1();
	PointerBasedDataStructure<String, C> getCaseAdministration2();

	boolean isUpdateCaseAdministration();

}

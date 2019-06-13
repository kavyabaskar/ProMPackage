package org.processmining.streamabstractrepresentation.algorithms.abstr;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.deckfour.xes.classification.XEventClass;
import org.processmining.framework.util.Pair;
import org.processmining.logabstractions.factories.DirectlyFollowsAbstractionFactory;
import org.processmining.logabstractions.models.DirectlyFollowsAbstraction;
import org.processmining.logabstractions.models.implementations.DirectlyFollowsAbstractionImpl;
import org.processmining.stream.core.interfaces.XSVisualization;
import org.processmining.stream.model.datastructure.PointerBasedDataStructure;
import org.processmining.streamabstractrepresentation.parameters.UnCoupledAbstractionSchemeParametersImpl;

public abstract class AbstractUncoupledDFReaderImpl<V, C, P extends UnCoupledAbstractionSchemeParametersImpl> extends
		AbstractUncoupledCaseAdministrationReaderImpl<V, Pair<XEventClass, XEventClass>, DirectlyFollowsAbstraction<XEventClass>, C, P> {

	public AbstractUncoupledDFReaderImpl(String name, P params) {
		this(name, null, params);
	}

	public AbstractUncoupledDFReaderImpl(final String name, final XSVisualization<V> visualization,
			final P parameters) {
		this(name, null, parameters, null, true);
	}

	public AbstractUncoupledDFReaderImpl(final String name, final XSVisualization<V> visualization, final P parameters,
			final PointerBasedDataStructure<String, C> caseActivityDataStructure, final boolean updateCA) {
		super(name, visualization, parameters, caseActivityDataStructure, updateCA);
	}

	protected DirectlyFollowsAbstraction<XEventClass> computeCurrentResult1() {
		Map<XEventClass, Integer> usedClasses = new HashMap<>();
		XEventClass[] classArr = new XEventClass[0];
		for (Pair<XEventClass, XEventClass> dfr : getInternalRepresentation1()) {
			XEventClass first = dfr.getFirst();
			XEventClass sec = dfr.getSecond();
			XEventClass newC;
			if (!usedClasses.containsKey(first)) {
				classArr = Arrays.copyOf(classArr, classArr.length + 1);
				int index = classArr.length - 1;
				newC = new XEventClass(first.getId(), index);
				classArr[index] = newC;
				usedClasses.put(newC, index);
			}
			if (!usedClasses.containsKey(sec)) {
				classArr = Arrays.copyOf(classArr, classArr.length + 1);
				int index = classArr.length - 1;
				newC = new XEventClass(sec.getId(), index);
				classArr[index] = newC;
				usedClasses.put(newC, index);
			}
		}
		int numClasses = classArr.length;
		double[][] matrix = new double[numClasses][numClasses];
		for (Pair<XEventClass, XEventClass> dfr : getInternalRepresentation1()) {
			matrix[usedClasses.get(dfr.getFirst())][usedClasses.get(dfr.getSecond())] = getInternalRepresentation1()
					.getFrequencyOf(dfr);
		}
		return new DirectlyFollowsAbstractionImpl<>(classArr, matrix,
				DirectlyFollowsAbstractionFactory.DEFAULT_THRESHOLD_BOOLEAN);
	}
	
	//for 2nd miner
	protected DirectlyFollowsAbstraction<XEventClass> computeCurrentResult2() {
		Map<XEventClass, Integer> usedClasses2 = new HashMap<>();
		XEventClass[] classArr2 = new XEventClass[0];
		for (Pair<XEventClass, XEventClass> dfr : getInternalRepresentation2()) {
			XEventClass first2 = dfr.getFirst();
			XEventClass sec2 = dfr.getSecond();
			XEventClass newC2;
			if (!usedClasses2.containsKey(first2)) {
				classArr2 = Arrays.copyOf(classArr2, classArr2.length + 1);
				int index = classArr2.length - 1;
				newC2 = new XEventClass(first2.getId(), index);
				classArr2[index] = newC2;
				usedClasses2.put(newC2, index);
			}
			if (!usedClasses2.containsKey(sec2)) {
				classArr2 = Arrays.copyOf(classArr2, classArr2.length + 1);
				int index2 = classArr2.length - 1;
				newC2 = new XEventClass(sec2.getId(), index2);
				classArr2[index2] = newC2;
				usedClasses2.put(newC2, index2);
			}
		}
		int numClasses2 = classArr2.length;
		double[][] matrix2 = new double[numClasses2][numClasses2];
		for (Pair<XEventClass, XEventClass> dfr2 : getInternalRepresentation2()) {
			matrix2[usedClasses2.get(dfr2.getFirst())][usedClasses2.get(dfr2.getSecond())] = getInternalRepresentation2()
					.getFrequencyOf(dfr2);
		}
		return new DirectlyFollowsAbstractionImpl<>(classArr2, matrix2,
				DirectlyFollowsAbstractionFactory.DEFAULT_THRESHOLD_BOOLEAN);
	}

	public void processNewXSEvent(String caseId, XEventClass activity) {
		updateInternalDataStructure(caseId, activity);
		if (isUpdateCaseAdministration()) {
			updateCaseAdministration(caseId, activity);
		}
	}

	protected abstract void updateInternalDataStructure(String caseId, XEventClass activity);

	protected abstract void updateCaseAdministration(String caseId, XEventClass eventClass);

}

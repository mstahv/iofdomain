package org.peimari.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ClassResult implements Serializable {

	private String className;
	private List<Result> results = new ArrayList<Result>();
	private boolean splitsCalculated = false;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<Result> getResults() {
		return results;
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}

	public void analyzeSplitTimes() {
		if (!splitsCalculated) {
			calculatePositions();
			calculateDeltas();
			ArrayList<Result> r = new ArrayList<Result>(results);
			calculateSplitPositions(r, false);
			calculateSplitPositions(r, true);
			// TODO other analyzes

			splitsCalculated = true;
		}

	}

	private void calculatePositions() {
		Result last = null;
		int i = 1;
		for (Result r : getResults()) {
			if (last == null) {
				r.setPosition(1);
			} else if (last.getTime() == r.getTime()) {
				r.setPosition(last.getPosition());
			} else {
				r.setPosition(i);
			}
			last = r;
			i++;
		}

	}

	private void calculateSplitPositions(ArrayList<Result> r,
			final boolean deltas) {
		int controlCount = getControlCount();
		for (int i = 0; i < controlCount; i++) {
			final int idx = i;
			Collections.sort(r, new Comparator<Result>() {
				public int compare(Result o1, Result o2) {
					if (o2.getCompetitorStatus() == CompetitorStatus.OK
							&& o2.getCompetitorStatus() == CompetitorStatus.OK) {
						Long splitTime1;
						Long splitTime2;
						if (o1.getSplitTimes().size() > idx) {
							SplitTime splitTime = o1.getSplitTimes().get(idx);
							splitTime1 = deltas ? splitTime.getDeltaTime()
									: splitTime.getTime();
							if (splitTime1 == 0) {
								return 1;
							}
						} else {
							return 1;
						}
						if (o2.getSplitTimes().size() > idx) {
							SplitTime splitTime = o2.getSplitTimes().get(idx);
							splitTime2 = deltas ? splitTime.getDeltaTime()
									: splitTime.getTime();
							if (splitTime2 == 0) {
								return -1;
							}
						} else {
							return -1;
						}
						return (int) (splitTime1 - splitTime2);
					}
					return 0;
				}
			});
			// r now ordered on time on this control, set positions
			SplitTime last = null;
			for (int j = 0; j < r.size(); j++) {
				Result result = r.get(j);
				if (result.getSplitTimes().size() > idx) {
					SplitTime splitTime = result.getSplitTimes().get(idx);
					if (last != null
							&& (deltas ? (last.getDeltaTime() == splitTime
									.getDeltaTime())
									: last.getTime() == splitTime.getTime())) {
						if (deltas) {
							splitTime.setDeltaPosition(last.getDeltaPosition());
						} else {
							splitTime.setPosition(last.getPosition());
						}
					} else {
						if (deltas) {
							splitTime.setDeltaPosition(j + 1);
						} else {
							splitTime.setPosition(j + 1);
						}
					}
					last = splitTime;
				}
			}
		}
	}

	private int getControlCount() {
		// TODO what if broken emit for first one?
		return getResults().get(0).getSplitTimes().size();
	}

	private void calculateDeltas() {
		for (Result r : results) {
			List<SplitTime> splitTimes = r.getSplitTimes();
			SplitTime lastSplit = null;
			for (SplitTime splitTime : splitTimes) {
				if(splitTime.getTime() == 0) {
					// handle missing punches
					splitTime.setDeltaTime(-1l);
				} else {
					if (lastSplit == null) {
						splitTime.setDeltaTime(splitTime.getTime());
					} else {
						splitTime.setDeltaTime(splitTime.getTime()
								- lastSplit.getTime());
					}
					lastSplit = splitTime;
				}
			}
		}
	}

	@Override
	public String toString() {
		return className;
	}

}

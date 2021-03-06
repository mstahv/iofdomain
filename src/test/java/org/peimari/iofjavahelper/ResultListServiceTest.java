package org.peimari.iofjavahelper;

import static org.junit.Assert.*;

import java.io.File;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.peimari.domain.ClassResult;
import org.peimari.domain.CompetitorStatus;
import org.peimari.domain.Result;
import org.peimari.domain.ResultList;
import org.peimari.domain.SplitTime;
import org.peimari.helper.ResultListService;
import org.peimari.util.Util;

public class ResultListServiceTest {

	public static final String SRC_TEST_RESOURCES_TASTI2_XML = "src/test/resources/tasti2.xml";

	@Test
	public void test() {
		ResultList rl = ResultListService.unmarshal(new File(
				SRC_TEST_RESOURCES_TASTI2_XML));
		PrintStream out = System.out;
		writeResults(rl, out);
		rl.analyzeSplitTimes();
		writeSplit(rl, out, false);
		writeSplit(rl, out, true);
	}

	private void writeResults(ResultList rl, PrintStream out) {
		for (ClassResult cl : rl.getClassResults()) {
			out.println(cl);
			List<Result> results = cl.getResults();
			Result lastResult = null;
			int pos = 1;
			for (int i = 0; i < results.size(); i++) {
				out.print("\t");
				Result r = results.get(i);
				if (r.getCompetitorStatus() == CompetitorStatus.OK) {

					if (lastResult == null
							|| lastResult.getTime() == r.getTime()) {
						out.print(pos);
					} else {
						out.print(i + 1);
					}
				} else {
					out.print(r.getCompetitorStatus());
				}

				out.print(". ");
				
				out.print(r);
				
				out.print(Util.formatTime(r.getTime()));
				
				out.println();
				lastResult = r;
			}
			out.println();
		}
	}
	
	private void writeSplit(ResultList rl, PrintStream out, boolean deltas) {
		for (ClassResult cl : rl.getClassResults()) {
			out.println(cl);
			List<Result> results = cl.getResults();
			Result lastResult = null;
			int pos = 1;
			for (int i = 0; i < results.size(); i++) {
				out.print("\t");
				Result r = results.get(i);
				if (r.getCompetitorStatus() == CompetitorStatus.OK) {

					if (lastResult == null
							|| lastResult.getTime() == r.getTime()) {
						out.print(pos);
					} else {
						out.print(i + 1);
					}
				} else {
					out.print(r.getCompetitorStatus());
				}

				out.print(". ");
				
				out.print(r);
				
				out.print(Util.formatTime(r.getTime()));
				
				List<SplitTime> splitTimes = r.getSplitTimes();
				
				for (SplitTime splitTime : splitTimes) {
					out.print("\t");
					if(deltas) {
						out.print(splitTime.getDeltaPosition());
						out.print("-");
						out.print(Util.formatTime(splitTime.getDeltaTime()));
					} else {
						out.print(splitTime.getPosition());
						out.print("-");
						out.print(Util.formatTime(splitTime.getTime()));
						
					}
					
				}
				
				out.println();
				lastResult = r;
			}
			out.println();
		}
	}

}

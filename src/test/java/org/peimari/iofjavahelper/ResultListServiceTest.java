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

	public static final String SRC_TEST_RESOURCES_TASTI3_XML = "src/test/resources/iof_from_navisport.xml";
	public static final String SRC_TEST_RESOURCES_TASTI4_XML = "src/test/resources/iof_from_navisport2.xml";
	public static final String SRC_TEST_RESOURCES_TASTI5_XML = "src/test/resources/iof_from_navisport3.xml";

	public static final String SRC_TEST_RESOURCES_IOF_XML_3_NAVISPORT = "src/test/resources/iof3_from_navisport.xml";
	public static final String SRC_TEST_RESOURCES_IOF_XML_3_ERESULTS = "src/test/resources/eresults4_iofxml3.xml";

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

	@Test
	public void testNavisport() {
		ResultList rl = ResultListService.unmarshal(new File(
				SRC_TEST_RESOURCES_TASTI3_XML));
		PrintStream out = System.out;
		writeResults(rl, out);
		rl.analyzeSplitTimes();
		writeSplit(rl, out, false);
		writeSplit(rl, out, true);
	}

	@Test
	public void testNavisport2() {
		ResultList rl = ResultListService.unmarshal(new File(
				SRC_TEST_RESOURCES_TASTI4_XML));
		PrintStream out = System.out;
		writeResults(rl, out);
		rl.analyzeSplitTimes();
		writeSplit(rl, out, false);
		writeSplit(rl, out, true);
	}

	@Test
	public void testNavisport3() {
		ResultList rl = ResultListService.unmarshal(new File(
				SRC_TEST_RESOURCES_TASTI5_XML));
		PrintStream out = System.out;
		writeResults(rl, out);
		rl.analyzeSplitTimes();
		writeSplit(rl, out, false);
		writeSplit(rl, out, true);
	}

	@Test
	public void test_iof3_from_navisport() {
		ResultList rl = ResultListService.unmarshal(new File(
				SRC_TEST_RESOURCES_IOF_XML_3_NAVISPORT));
		PrintStream out = System.out;
		writeResults(rl, out);
		rl.analyzeSplitTimes();
		writeSplit(rl, out, false);
		writeSplit(rl, out, true);
	}

	@Test
	public void test_iof3_from_eresults4() {
		ResultList rl = ResultListService.unmarshal(new File(
				SRC_TEST_RESOURCES_IOF_XML_3_ERESULTS));
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

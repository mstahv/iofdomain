package org.peimari.util;

public class Util {

	private static final long HOUR = 60*60;
	private static final long MINUTE = 60;

	public static final String formatTime(long l) {
		// not using String.format for GWT compatibility
		final int s = (int) (l/1000);
		StringBuilder sb = new StringBuilder();
		if(s >= HOUR) {
			sb.append(s/3600);
			sb.append(":");
		}
		if(s >= MINUTE) {
			final int m = (s/60)%60;
			if(s >= HOUR && m < 10) {
				sb.append("0");
			}
			sb.append(m);
			sb.append(":");
		}
		final int ls = s%60;
		if(s >= MINUTE && ls < 10) {
			sb.append("0");
		}
		sb.append(ls);
		return sb.toString();
	}

}

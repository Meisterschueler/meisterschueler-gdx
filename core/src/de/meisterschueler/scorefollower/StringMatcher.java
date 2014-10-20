package de.meisterschueler.scorefollower;

import de.meisterschueler.basic.Status;

public class StringMatcher {

	static int[] similarity = calcSimilarityMatrix();

	static char cost_del = 10;
	static char cost_ins = 10;
	static char cost_wro = 10;
	static char cost_mat = 0;

	public static String getAlignments(String seq1, String seq2) {

		int width = 30;

		int big = 10000;

		int n1 = seq1.length();
		int n2 = seq2.length();
		int dist[] = new int[((n1 + 1) * (n2 + 1))];

		// Initialize Matrix
		for (int i = 0; i < (n1 + 1) * (n2 + 1); i++)
			dist[i] = big;

		// Initialize Matrix bounds
		dist[0] = 0;

		for (int i = 1; i <= n1; ++i)
			dist[i * (n2 + 1)] = dist[(i - 1) * (n2 + 1)] + cost_del;

		for (int j = 1; j <= n2; ++j)
			dist[j] = dist[j - 1] + cost_ins;

		// Calculate Matrix
		for (int i = 1; i <= n1; ++i) {
			for (int j = Math.max(1, i - width); j <= Math.min(n2, i + width); ++j) {
				// for( int j = 1; j <= n2; ++j ) {
				int dist_del = dist[(i - 1) * (n2 + 1) + (j)] + cost_del;
				int dist_ins = dist[(i) * (n2 + 1) + (j - 1)] + cost_ins;
				// int dist_sub = dist[ (i-1)*(n2+1) + (j-1) ] + (
				// seq1.charAt(i-1) == seq2.charAt(j-1) ? cost_mat : cost_sub );
				int dist_sub = dist[(i - 1) * (n2 + 1) + (j - 1)] + getSimilarity(seq1.charAt(i - 1), seq2.charAt(j - 1));
				dist[i * (n2 + 1) + j] = Math.min(dist_del, Math.min(dist_ins, dist_sub));
			}
		}

		// Back trace
		String align = "";
		int i = n1;
		int j = n2;

		// For DTW only
		if (i > j) {
			while ((i > 0) && (dist[i * (n2 + 1) + j] == big)) {
				align = Status.DELETED + align;
				i--;
			}
		} else {
			while ((j > 0) && (dist[i * (n2 + 1) + j] == big)) {
				align = Status.INSERT + align;
				j--;
			}
		}

		int up;
		int diag;
		int left;
		while (i > 0 || j > 0) {
			if (i > 0)
				up = dist[i * (n2 + 1) + j] - dist[(i - 1) * (n2 + 1) + (j)];
			else
				up = -1;

			if (j > 0)
				left = dist[i * (n2 + 1) + j] - dist[(i) * (n2 + 1) + (j - 1)];
			else
				left = -1;

			if (i > 0 && j > 0)
				diag = dist[i * (n2 + 1) + j] - dist[(i - 1) * (n2 + 1) + (j - 1)];
			else
				diag = -1;

			if (diag >= up && diag >= left) {
				if (diag == cost_mat) {
					align = Status.MATCH + align;
					i--;
					j--;
				} else {
					align = Status.WRONG + align;
					i--;
					j--;
				}
			} else if (up >= left) {
				align = Status.DELETED + align;
				i--;
			} else {
				align = Status.INSERT + align;
				j--;
			}
		}

		return align;
	}

	private static int getSimilarity(int i, int j) {
		return similarity[i * 256 + j];
	}

	private static int[] calcSimilarityMatrix() {
		int[] result = new int[256 * 256];
		for (int i = 0; i < 256; i++) {
			for (int j = 0; j < 256; j++) {
				if (i == j) {
					result[i * 256 + j] = cost_mat;
				} else if (i - j == 1 || j - i == 1) {
					result[i * 256 + j] = 10;
				} else {
					result[i * 256 + j] = 200;
				}
			}
		}
		return result;
	}
}

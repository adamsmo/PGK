package model;

public class Edge2D {
	private Point2D start;
	private Point2D end;
	private double avrageDepth;

	public Edge2D(Point2D start, Point2D end, double avrageDepth) {
		super();
		this.start = start;
		this.end = end;
		this.avrageDepth = avrageDepth;
	}

	public Point2D getStart() {
		return start;
	}

	public void setStart(Point2D start) {
		this.start = start;
	}

	public Point2D getEnd() {
		return end;
	}

	public void setEnd(Point2D end) {
		this.end = end;
	}

	public double getAvrageDepth() {
		return avrageDepth;
	}

	public void setAvrageDepth(double avrageDepth) {
		this.avrageDepth = avrageDepth;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(avrageDepth);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge2D other = (Edge2D) obj;
		if (Double.doubleToLongBits(avrageDepth) != Double
				.doubleToLongBits(other.avrageDepth))
			return false;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Edge2D [start=" + start + ", end=" + end + ", avrageDepth="
				+ avrageDepth + "]";
	}

	public static Edge2D getEmptyEdge(double avrageDepth) {
		return new Edge2D(new Point2D(0, 0), new Point2D(0, 0), avrageDepth);
	}

}

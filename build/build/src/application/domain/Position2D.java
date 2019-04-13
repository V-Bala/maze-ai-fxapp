package application.domain;

public class Position2D {
	
	private int row;
	private int col;
	
	public Position2D(int row, int col)
	{
		this.row = row;
		this.col = col;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}
	
	public int setRow() {
		return row;
	}

	public int getRow() {
		return row;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Position2D [row=").append(row).append(", col=").append(col).append("]");
		return builder.toString();
	}
	
	
}

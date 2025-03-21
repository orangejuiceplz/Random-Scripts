public Theater(int seatsPerRow, int tier1Rows, int tier2Rows) {

	int comb = tier1Rows + tier2Rows;

	this.theaterSeats = new Seat[comb][seatsPerRow];

	for (int r = 0; r < comb; r++) {
		for (int c = 0; c <seatsPerRow; c++) {
			if (r < tier1Rows) {
				this.theaterSeats[r][c] = new Seat(true, 1); 
        	}
			else {
				this.theaterSeats[r][c]  = new Seat(true, 2); 
        	}
    	}
  }
}

public boolean reassignSeat(int fromRow, int fromCol, int toRow, int toCol) {
	Seat toSeat = theaterSeats[toRow][toCol];
	if (!toSeat.isAvailable()) {
		return false; 
  }
	Seat fromSeat = theaterSeats[fromRow][fromCol];
	if (toSeat.getTier() < fromSeat.getTier()) {
		return false; 
  }
	toSeat.setAvailability(false);
	fromSeat.setAvailability(true);
	return true; 
}

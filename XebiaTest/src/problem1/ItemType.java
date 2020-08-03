package problem1;

public enum ItemType {
	BOOK(true, false), FOOD(true, false), MEDICAL(true, false), OTHERS(false, false), IMPORTED_BOOK(true,
			true), IMPORTED_MEDICAL(true, true), IMPORTED_FOOD(true, true), IMPORTED_OTHERS(false, true);

	private boolean isExempted;
	private boolean isImported;

	private ItemType(boolean exempted, boolean imported) {
		isExempted = exempted;
		isImported = imported;
	}

	public boolean isImported() {
		return isImported;
	}

	public boolean isExempted() {
		return isExempted;
	}

}

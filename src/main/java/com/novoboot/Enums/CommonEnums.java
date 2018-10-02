package com.novoboot.Enums;

import java.io.Serializable;

public class CommonEnums {

	public enum STATUS {
		INACTIVE(0), ACTIVE(1), DELETE(2), BLOCK(4);
		public int ID;

		STATUS(int id) {
			this.ID = id;
		}
	}

	public enum ServiceCategoryEnum {
		HOME(1), OFFICE(0);

		private final int category;

		ServiceCategoryEnum(int category) {
			this.category = category;
		}

		public final int category() {
			return category;
		}
	}

	public enum ServiceType {
		BOOK(1), ENQUIRY(0);

		private final int type;

		ServiceType(int type) {
			this.type = type;
		}

		public final int type() {
			return type;
		}
	}

	public enum USER_TYPE {
		ADMIN(1), USER(2);

		public int ID;

		USER_TYPE(int id) {
			this.ID = id;
		}
	}

	public enum UserRoleType implements Serializable {
		SUPERADMIN("SUPERADMIN"), ADMIN("ADMIN"), USER("USER"), FRANCHISEE("RESTORENT"),
		ADMIN_ID("1") , USER_ID("2");

		String userProfileType;

		private UserRoleType(String userProfileType) {
			this.userProfileType = userProfileType;
		}

		public String getUserProfileType() {
			return userProfileType;
		}

	}

	public enum SERVICE_WINDOW {
		INCREMENT(1), CHECKBOX(2);

		public int ID;

		SERVICE_WINDOW(int id) {
			this.ID = id;
		}
	}
	
	public enum BOOKINGSTATUS implements Serializable {
		SUCCESSFULL("SUCCESSFULL"), INACTIVE("INACTIVE"),FAIL("FAIL"); 

		String bookingStatus;

		private BOOKINGSTATUS(String bookingStatus) {
			this.bookingStatus = bookingStatus;
		}

		public String getBookingStatus() {
			return bookingStatus;
		}

	}
}

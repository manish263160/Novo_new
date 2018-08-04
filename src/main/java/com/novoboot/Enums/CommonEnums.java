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

	public enum ServiceCategory {
		HOME(1), OFFICE(0);

		private final int category;

		ServiceCategory(int category) {
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
		SUPER_ADMIN(1), FRANCHISEE(2), USER(3);

		public int ID;

		USER_TYPE(int id) {
			this.ID = id;
		}
	}

	public enum UserRoleType implements Serializable {
		SUPERADMIN("SUPERADMIN"), ADMIN("ADMIN"), USER("USER"), FRANCHISEE("RESTORENT");

		String userProfileType;

		private UserRoleType(String userProfileType) {
			this.userProfileType = userProfileType;
		}

		public String getUserProfileType() {
			return userProfileType;
		}

	}

}

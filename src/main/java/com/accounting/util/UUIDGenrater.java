package com.accounting.util;

import java.util.UUID;

public class UUIDGenrater {
	public static String Uuidgenrater() {
		UUID uuid=UUID.randomUUID();  
		return uuid.toString();
	}
}

package com.accounting.util;

import java.util.UUID;

public class UuidGenerator {
	public static String Uuidgenrater() {
		UUID uuid=UUID.randomUUID();  
		return uuid.toString();
	}
}

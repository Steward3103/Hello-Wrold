package demo;

import org.apache.commons.codec.digest.DigestUtils;

public class md5 {
	public static void main(String[] args) {
		System.out.println(DigestUtils.md5Hex("123456"));
	}
}

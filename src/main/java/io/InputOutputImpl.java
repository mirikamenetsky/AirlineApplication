package io;

import java.util.Scanner;

public class InputOutputImpl implements InputOutput {

	private final Scanner scanner = new Scanner(System.in);

	@Override
	public void print(String message) {
		System.out.print(message);
	}

	@Override
	public String readLine() {
		return scanner.nextLine();
	}
}

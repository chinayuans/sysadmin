package org.appfuse.common.util.stacktrace;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

public class StackTrace {
	
	public static String getStackTrace(Throwable e) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		e.printStackTrace(new PrintWriter(byteArrayOutputStream, true));
		String stackTrace = byteArrayOutputStream.toString();
		try {
			byteArrayOutputStream.close();
		} catch (java.io.IOException ignore) {
		}
		return stackTrace;
	}

	public static String getStackTrace(Exception e, Object obj) {
		StringBuffer sb = new StringBuffer();
		String msg = e.getLocalizedMessage();
		String name = obj.getClass().getName();
		if (msg == null) {
			sb.append(name);
		} else {
			sb.append(name).append(": ").append(msg).toString();
		}

		sb.append(obj.toString() + "\n");
		// Don't use getStackTrace() as it calls clone()
		// Get stackTrace, in case stackTrace is reassigned
		StackTraceElement[] stack = e.getStackTrace();
		for (int i = 0; i < stack.length; i++)
			sb.append("\tat " + stack[i] + "\n");

		StackTraceElement[] parentStack = stack;
		Throwable throwable = e.getCause();
		while (throwable != null) {
			sb.append("Caused by: ");
			sb.append(throwable + "\n");
			StackTraceElement[] currentStack = throwable.getStackTrace();
			int duplicates = countDuplicates(currentStack, parentStack);
			for (int i = 0; i < currentStack.length - duplicates; i++)
				sb.append("\tat " + currentStack[i] + "\n");
			if (duplicates > 0) {
				sb.append("\t... " + duplicates + " more\n");
			}
			parentStack = currentStack;
			throwable = throwable.getCause();
		}
		return sb.toString();
	}

	private static int countDuplicates(StackTraceElement[] currentStack,
			StackTraceElement[] parentStack) {
		int duplicates = 0;
		int parentIndex = parentStack.length;
		for (int i = currentStack.length; --i >= 0 && --parentIndex >= 0;) {
			StackTraceElement parentFrame = parentStack[parentIndex];
			if (parentFrame.equals(currentStack[i])) {
				duplicates++;
			} else {
				break;
			}
		}
		return duplicates;
	}
}

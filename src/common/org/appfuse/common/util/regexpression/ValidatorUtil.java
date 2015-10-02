/* IBM Confidential
 * OCO Source Materials
 * 5724-L01, 5655-N53, 5724-I82, 5655-R15
 * (C) Copyright IBM Corporation 2006
 * The source code for this program is not published or otherwise
 * divested of its trade secrets, irrespective of what has been
 * deposited with the U.S. Copyright Office.
 */

package org.appfuse.common.util.regexpression;

import java.io.File;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * contains shared validators in common.
 */
public class ValidatorUtil {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ValidatorUtil.class);

	/**
	 * Automatically generated constructor for utility class
	 */
	private ValidatorUtil() {
	}

	/** only contains digital chars. */
	public static final String sNUMERIC_PATTERNS[] = { "^(\\d)*$" }; //$NON-NLS-1$

	/**
	 * do not support the following chars: \ * ? " < > | / , ^ & : ; = + % ' # $ { } [ ]
	 */
	public static final String sINVALID_CHARATCTERS_PATTERN[] = { "^.*([\\\\*?\"<>|/,\\^&:;=+%'#${}\\[\\]])+.*$" }; //$NON-NLS-1$  

	public static final String S_IPv4_REGEX = "^(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])$"; //$NON-NLS-1$

	public static final String S_IPv6_REGEX = "(^(:{2}((([0-9A-Fa-f]{1,4})?)|(([0-9A-Fa-f]{1,4}:){1,5}[0-9A-Fa-f]{1,4})))$)|(^(([0-9A-Fa-f]{1,4}:){7}[0-9A-Fa-f]{1,4})$|^(([0-9A-Fa-f]{1,4}:){5}:((([0-9A-Fa-f]{1,4}:)[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4})?)))$|^(([0-9A-Fa-f]{1,4}:){4}:((([0-9A-Fa-f]{1,4}:){1,2}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4})?)))$|^(([0-9A-Fa-f]{1,4}:){3}:((([0-9A-Fa-f]{1,4}:){1,3}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4})?)))$|^(([0-9A-Fa-f]{1,4}:){2}:((([0-9A-Fa-f]{1,4}:){1,4}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4})?)))$|^(([0-9A-Fa-f]{1,4}:):((([0-9A-Fa-f]{1,4}:){1,5}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4})?)))$)"; //$NON-NLS-1$

	public static final String S_IPv4_COMPATIBLE_IPv6_REGEX = "^([0]*[0]*[0]*[0]*:){1,7}((\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5]))$"; //$NON-NLS-1$

	public static final String S_IPv4_MAPPED_IPv6_REGEX = "^([0]*[0]*[0]*[0]*:){1,6}([fF][fF][fF][fF]:){1}((\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5]))$"; //$NON-NLS-1$

	public static final String S_HOST_NAME_REGEX = "^([a-zA-Z0-9]([a-zA-Z0-9\\-]*))$"; //$NON-NLS-1$

	public static final String S_DNS_HOST_NAME_REGEX = "^([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}$"; //$NON-NLS-1$

	public static final String S_LOCAL_DOMAIN_REGEX = "^([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+localdomain"; //$NON-NLS-1$

	public static final String S_REGEX[] = { S_IPv4_REGEX, S_IPv6_REGEX,
			S_IPv4_COMPATIBLE_IPv6_REGEX, S_IPv4_MAPPED_IPv6_REGEX,
			S_HOST_NAME_REGEX, S_DNS_HOST_NAME_REGEX, S_LOCAL_DOMAIN_REGEX };

	/** Invalid path characters on windows */
	public static final String S_INVALID_PATH_CHARS_WINDOWS_REGEX = "[{}:;\\&\\*?\"<>|%,=+'#$^`!\\[\\]]";

	/** Invalid path characters on unxi */
	public static final String S_INVALID_PATH_CHARS_UNIX_REGEX = "[{}`!\\&\\*\\(\\)|;:<>?\"\\\\,=+%'$#^\\[\\]]";

	public static final String sINVALID_CHAR_COMB_REGEX = "]]>";

	/** A colon */
	public static final String sCOLON = ":";

	/** A backslash */
	public static final String S_BACK_SLASH = "\\";

	/** A Space character */
	public static final String S_SPACE = " ";

	/**
	 * check if given argument is not null.
	 * 
	 * @param as_argument
	 *            given argument
	 * @return if given argument is not null ,return true, otherwise false
	 */
	public static final boolean isNotNull(String as_argument) {
		boolean return_value = false;
		if (as_argument == null) {
			return_value = false;
		} else {
			return_value = true;
		}

		return return_value;
	}

	/**
	 * check if given argument is null.
	 * 
	 * @param as_argument
	 *            given argument
	 * @return if given argument is null ,return true, otherwise false
	 */
	public static final boolean isNull(String as_argument) {

		boolean return_value = false;
		if (as_argument == null) {
			return_value = true;
		} else {
			return_value = false;
		}

		return return_value;
	}

	/**
	 * check if given argument is numeric.
	 * 
	 * @param as_argument
	 *            given argument
	 * @return if given argument is numeric,return true, otherwise false
	 */
	public static final boolean isNumeric(String as_argument) {

		boolean return_value = isOneMatched(sNUMERIC_PATTERNS, as_argument);

		return return_value;
	}

	/**
	 * check if the given argument does not contain spaces.
	 * 
	 * @param as_argument
	 *            given augment
	 * @return if given argument does not contain spaces,return true, otherwise
	 *         false
	 */
	public static final boolean hasNoSpaces(String as_argument) {

		boolean return_value = false;
		if (as_argument.indexOf(" ") != -1) { //$NON-NLS-1$
			return_value = false;
		} else {
			return_value = true;
		}

		return return_value;
	}

	/**
	 * check if the given argument does contain spaces.
	 * 
	 * @param as_argument
	 *            given augment
	 * @return if given argument does contain spaces,return true, otherwise
	 *         false
	 */
	public static final boolean hasSpaces(String as_argument) {

		boolean return_value = false;
		if (as_argument.indexOf(" ") != -1) { //$NON-NLS-1$
			return_value = true;
		} else {
			return_value = false;
		}

		return return_value;
	}

	/**
	 * check if the given argument does not contain invalid chars.
	 * 
	 * @param as_argument
	 *            given augment
	 * @return if given argument does not contain invalid chars,return true,
	 *         otherwise false
	 */
	public static final boolean hasValidCharacters(String as_argument) {

		boolean return_value = !isOneMatched(sINVALID_CHARATCTERS_PATTERN,
				as_argument);

		return return_value;
	}

	/**
	 * check if the given argument does contain invalid chars.
	 * 
	 * @param as_argument
	 *            given augment
	 * @return if given argument does contain invalid chars,return true,
	 *         otherwise false
	 */
	public static final boolean hasInvalidCharacters(String as_argument) {

		boolean return_value = isOneMatched(sINVALID_CHARATCTERS_PATTERN,
				as_argument);

		return return_value;
	}

	/**
	 * check if the given argument is boolean data type.
	 * 
	 * @param as_argument
	 *            given augment
	 * @return if given argument is boolean data type,return true, otherwise
	 *         false
	 */
	public static final boolean isBoolean(String as_argument) {

		boolean return_value = false;
		if ("true".equalsIgnoreCase(as_argument) //$NON-NLS-1$ 
				|| "false".equalsIgnoreCase(as_argument)) { //$NON-NLS-2$  //$NON-NLS-1$
			return_value = true;
		} else {
			return_value = false;
		}

		return return_value;
	}

	/**
	 * check if the given argument meets the requirement as a hostName.
	 * 
	 * @param as_argument
	 *            given augment
	 * @return if given argument meets the requirement as a hostName,return
	 *         true, otherwise false
	 */
	public static final boolean isHostName(String as_argument) {

		boolean return_value = isOneMatched(S_REGEX, as_argument);

		return return_value;
	}

	/**
	 * check if given path and files are available.
	 * 
	 * @param as_path
	 *            given path
	 * @param files
	 *            given files
	 * @return if available ,return true, otherwise false
	 */
	public static final boolean isValidFiles(String as_path, String[] files) {

		File dir = new File(as_path);
		for (int i = 0; i < files.length; i++) {
			File file = new File(dir.getAbsolutePath() + File.separatorChar
					+ files[i]);
			if (!file.isFile()) {
				return false;
			}
			if (!file.exists()) {
				return false;
			}
		}

		return true;
	}

	/**
	 * check if given path is available.
	 * 
	 * @param as_path
	 *            given path
	 * @return if available ,return true, otherwise false
	 */
	public static final boolean isValidDir(String as_path) {

		File file = new File(as_path);
		if (!file.isDirectory()) {
			return false;
		}
		if (!file.exists()) {
			return false;
		}

		return true;
	}

	/**
	 * check if the "as_searchedChars" argument matches one of all regular
	 * expression "as_regExp_patterns".
	 * 
	 * @param as_regExp_patterns
	 *            all regular expressions
	 * @param as_searchedChars
	 *            chars to be searched.
	 * @return if the "as_searchedChars" argument matches one of all regular
	 *         expression "as_regExp_patterns", return true, otherwise false
	 */
	public static final boolean isOneMatched(String[] as_regExp_patterns,
			String as_searchedChars) {
		boolean return_value = false;

		Pattern apattern[] = new Pattern[as_regExp_patterns.length];
		Matcher amatcher[] = new Matcher[as_regExp_patterns.length];
		for (int i = 0; i < as_regExp_patterns.length; i++) {
			apattern[i] = Pattern.compile(as_regExp_patterns[i]);
			amatcher[i] = apattern[i].matcher(as_searchedChars);
		}

		for (int i = 0; i < amatcher.length; i++) {
			if (amatcher[i].find()) {
				return_value = true;
				break;
			}
		}

		return return_value;
	}

	/**
	 * check if the "as_searchedChars" argument matches all regular expression
	 * "as_regExp_patterns".
	 * 
	 * @param as_regExp_patterns
	 *            all regular expressions
	 * @param as_searchedChars
	 *            chars to be searched.
	 * @return if the "as_searchedChars" argument matches all regular expression
	 *         "as_regExp_patterns", return true, otherwise false
	 */
	public static final boolean isAllMatched(String[] as_regExp_patterns,
			String as_searchedChars) {
		boolean return_value = true;

		Pattern apattern[] = new Pattern[as_regExp_patterns.length];
		Matcher amatcher[] = new Matcher[as_regExp_patterns.length];
		for (int i = 0; i < as_regExp_patterns.length; i++) {
			apattern[i] = Pattern.compile(as_regExp_patterns[i]);
			amatcher[i] = apattern[i].matcher(as_searchedChars);
		}

		for (int i = 0; i < amatcher.length; i++) {
			if (!amatcher[i].find()) {
				return_value = false;
				break;
			}
		}

		return return_value;
	}

	/**
	 * This validator ensures that a correct directory path was specified.
	 * 
	 * Ensures that the following illegal characters are not used: windows:
	 * {}:;*?"<>|%,=+&'#$^`!
	 * 
	 * Note: if the specified path is a windows path, the ":" after the drive
	 * letter is considered legal.
	 * 
	 * Note: The combination "]]>" is also invalid
	 * 
	 * @return true if sValidatorArgValue passed validation without exception,
	 *         otherwise false
	 */

	public static final boolean isValidDirCharsOnWindows(String directoryPath) {

		boolean result = true;
		int driveLocation = directoryPath.indexOf(sCOLON);
		logger.debug("driveLocation:" + driveLocation);

		// if os is windows. then check if it is valid path on windows
		/*
		 * Checks to see if there is a colon in the path, and if it refers to a
		 * valid directory path
		 */
		if (driveLocation < 0) {
			logger.debug("driver location:" + driveLocation);
			result = false;
		} else {
			/*
			 * get a disk symbol, for example , c:\ , d:\, then check if this
			 * disk exists.
			 */
			File path = new File(directoryPath.substring(0, driveLocation + 2));
			result = path.exists();
			logger.debug(path.getAbsolutePath() + " exists? " + result);

			/*
			 * if disk symbol is valid, then check if the rest of path contains
			 * invalid chars.
			 */
			if (result == true) {
				if (evaluateRegex(S_INVALID_PATH_CHARS_WINDOWS_REGEX,
						directoryPath.substring(driveLocation + 1))
						|| evaluateRegex(sINVALID_CHAR_COMB_REGEX,
								directoryPath.substring(driveLocation + 1))) {
					logger.debug(directoryPath + "is a invalid directory.");
					result = false;
				}
			}
		}
		logger.debug("isValidDirChars():" + result);
		return result;
	}

	/**
	 * This validator ensures that a correct directory path was specified.
	 * 
	 * Ensures that the following illegal characters are not used: unix :
	 * {}`!&*()|;:<>?"\,=+%'#$^ note: a space is also not supported
	 * 
	 * Note: if the specified path is a windows path, the ":" after the drive
	 * letter is considered legal.
	 * 
	 * Note: The combination "]]>" is also invalid
	 * 
	 * @return true if sValidatorArgValue passed validation without exception,
	 *         otherwise false
	 */

	public static final boolean isValidDirCharsOnUnix(String directoryPath) {
		logger.debug("isValidDirCharsOnUnix");//$NON-NLS-1$

		boolean result = true;

		// if os is unix. then check if it is valid path on unix.
		/*
		 * if directoryPath contains "\\" or " ", then path is wrong.
		 */
		if (directoryPath.indexOf(S_BACK_SLASH) != -1
				|| directoryPath.indexOf(S_SPACE) != -1) {
			logger.debug(directoryPath + "is a invalid directory.");//$NON-NLS-1$ //$NON-NLS-2$
			result = false;
		} else {
			// check to see if there are any invalid characters
			if (evaluateRegex(S_INVALID_PATH_CHARS_UNIX_REGEX, directoryPath)
					|| evaluateRegex(sINVALID_CHAR_COMB_REGEX, directoryPath)) {
				logger.debug(directoryPath + "is a invalid directory.");//$NON-NLS-1$ //$NON-NLS-2$
				result = false;
			}
		}

		logger.debug("isValidDirChars():" + result); //$NON-NLS-1$ //$NON-NLS-2$
		return result;
	}

	/**
	 * Evaluates the pattern to see if there is a match to sRegex
	 * 
	 * @param sRegex -
	 *            the regular expression
	 * @param searchedCharacters -
	 *            the string pattern,ie searched characters
	 * @return true if there is a match otherwise false
	 */
	public static boolean evaluateRegex(String sRegex, String searchedCharacters) {

		Pattern pattern = Pattern.compile(sRegex);
		Matcher matcher = pattern.matcher(searchedCharacters);

		return matcher.find();
	}

	/**
	 * check if local OS is windows.
	 * 
	 * @return true if local Os is windows otherwise false
	 */
	public static final boolean isWindows() {

		String OS_NAME = System.getProperty("os.name").toLowerCase(Locale.US); //$NON-NLS-1$

		return OS_NAME.indexOf("windows") > -1; //$NON-NLS-1$
	}

	/**
	 * check if local OS is unix.
	 * 
	 * @return true if local Os is unix otherwise false
	 */
	public static final boolean isUnix() {

		String OS_NAME = System.getProperty("os.name").toLowerCase(Locale.US); //$NON-NLS-1$
		String PATH_SEP = System.getProperty("path.separator"); //$NON-NLS-1$
		boolean isMac = OS_NAME.indexOf("mac") > -1; //$NON-NLS-1$
		boolean isOpenvms = OS_NAME.indexOf("openvms") > -1; //$NON-NLS-1$
		boolean isUnix = PATH_SEP.equals(":") && !isOpenvms && (!isMac || OS_NAME.endsWith("x")); //$NON-NLS-1$ //$NON-NLS-2$

		return isUnix;
	}
}

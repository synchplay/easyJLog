package org.easyj.easyjlog.util;

import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.event.Level;
import org.slf4j.spi.LocationAwareLogger;

/**
 * 日志打印工具 <br>
 * 可用于打印日志、动态修改Root级别、更新配置文件等操作
 * 
 * @author dengjianjun
 *
 */
public final class LoggerUtil {

	private static final String FQCN = LoggerUtil.class.getName();
	private static final LocationAwareLogger logger = (LocationAwareLogger) LoggerFactory.getLogger(LoggerUtil.class);

	/**
	 * 记录日志信息
	 */
	public static void warn(Marker marker, String fqcn, Throwable throwable, String message, Object[] argArray) {
		if (!logger.isWarnEnabled(marker)) {
			return;
		}
		logger.log(marker, fqcn, LocationAwareLogger.WARN_INT, message, argArray, throwable);
	}

	/**
	 * 记录日志信息
	 */
	public static void warn(String fqcn, Throwable throwable, String message, Object... argArray) {
		warn(null, fqcn, throwable, message, argArray);
	}

	/**
	 * 记录日志信息
	 */
	public static void warn(Throwable throwable, String message, Object... argArray) {
		warn(null, FQCN, throwable, message, argArray);
	}

	/**
	 * 记录日志信息
	 */
	public static void warn(String message, Object... argArray) {
		warn(null, FQCN, null, message, argArray);
	}

	/**
	 * 记录日志信息
	 */
	public static void warn(Throwable throwable, String message) {
		warn(null, FQCN, throwable, message, null);
	}

	/**
	 * 记录日志信息
	 */
	public static void warn(String message) {
		warn(null, FQCN, null, message, null);
	}

	/**
	 * 记录日志信息
	 */
	public static void info(Marker marker, String fqcn, String message, Object[] argArray) {
		if (!logger.isInfoEnabled(marker)) {
			return;
		}
		logger.log(marker, fqcn, LocationAwareLogger.INFO_INT, message, argArray, null);
	}

	/**
	 * 记录日志信息
	 */
	public static void info(Marker marker, String message, Object... argArray) {
		info(marker, FQCN, message, argArray);
	}

	/**
	 * 记录日志信息
	 */
	public static void info(Marker marker, String message) {
		info(marker, FQCN, message, null);
	}

	/**
	 * 记录日志信息
	 */
	public static void info(String fqcn, String message, Object[] argArray) {
		info(null, fqcn, message, argArray);
	}

	/**
	 * 记录日志信息
	 */
	public static void info(String message, Object... argArray) {
		info(null, FQCN, message, argArray);
	}

	/**
	 * 记录日志信息
	 */
	public static void info(String message) {
		info(null, FQCN, message, null);
	}

	/**
	 * 记录日志信息
	 */
	public static void error(Marker marker, String fqcn, Throwable throwable, String message, Object[] argArray) {
		if (!logger.isErrorEnabled(marker)) {
			return;
		}
		logger.log(marker, fqcn, LocationAwareLogger.ERROR_INT, message, argArray, throwable);
	}

	/**
	 * 记录日志信息
	 */
	public static void error(String fqcn, Throwable throwable, String message, Object... argArray) {
		error(null, fqcn, throwable, message, argArray);
	}

	/**
	 * 记录日志信息
	 */
	public static void error(Throwable throwable, String message, Object... argArray) {
		error(null, FQCN, throwable, message, argArray);
	}

	/**
	 * 记录日志信息
	 */
	public static void error(String message, Object... argArray) {
		error(null, FQCN, null, message, argArray);
	}

	/**
	 * 记录日志信息
	 */
	public static void error(Throwable throwable, String message) {
		error(null, FQCN, throwable, message, null);
	}

	/**
	 * 记录日志信息
	 */
	public static void error(String message) {
		error(null, FQCN, null, message, null);
	}

	/**
	 * 记录日志信息
	 */
	public static void debug(Marker marker, String fqcn, String message, Object[] argArray) {
		if (!logger.isDebugEnabled(marker)) {
			return;
		}
		logger.log(marker, fqcn, LocationAwareLogger.DEBUG_INT, message, argArray, null);
	}

	/**
	 * 记录日志信息
	 */
	public static void debug(Marker marker, String message, Object... argArray) {
		debug(marker, FQCN, message, argArray);
	}

	/**
	 * 记录日志信息
	 */
	public static void debug(Marker marker, String message) {
		debug(marker, FQCN, message, null);
	}

	/**
	 * 记录日志信息
	 */
	public static void debug(String fqcn, String message, Object[] argArray) {
		debug(null, fqcn, message, argArray);
	}

	/**
	 * 记录日志信息
	 */
	public static void debug(String message, Object... argArray) {
		debug(null, FQCN, message, argArray);
	}

	/**
	 * 记录日志信息
	 */
	public static void debug(String message) {
		debug(null, FQCN, message, null);
	}

	/**
	 * 公共类获取调用者跟踪信息 <br/>
	 * 下标说明：0（Thread.getStackTrace）、1（LogUtil.getTraceInfo）、2（公共类）、3（业务调用类）
	 * 
	 * @return
	 */
	public static String getTraceInfo() {
		StackTraceElement[] lvStacks = Thread.currentThread().getStackTrace();
		StringBuffer sb = new StringBuffer();

		String classSimpleName = lvStacks[3].getClassName();
		int index = classSimpleName.lastIndexOf('.');
		if (index != -1) {
			classSimpleName = classSimpleName.substring(index + 1);
		}
		sb.append(classSimpleName).append("(").append(lvStacks[3].getMethodName()).append(":")
				.append(lvStacks[3].getLineNumber()).append(")");
		return sb.toString();
	}

	/**
	 * 动态修改Root日志级别，重启后失效
	 * 
	 * @param newLevel
	 *            设置日志级别
	 */
	public static void changeRootLevel(Level newLevel) {
		try {
			ILoggerFactory factory = LoggerFactory.getILoggerFactory();

			if (isLog4j2(factory)) {
				Log4j2Config.changeRootLevel(newLevel);
			} else if (isLogback(factory)) {
				LogbackConfig.changeRootLevel(newLevel);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 重新初始化日志配置文件
	 * 
	 * @param configFileLocation
	 *            日志文件路径
	 */
	public static void reconfigure(String configFileLocation) {
		try {
			ILoggerFactory factory = LoggerFactory.getILoggerFactory();

			if (isLog4j2(factory)) {
				Log4j2Config.reconfigure(configFileLocation);
			} else if (isLogback(factory)) {
				LogbackConfig.reconfigure(configFileLocation);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * 判断是否Log4j2.x
	 */
	private static boolean isLog4j2(ILoggerFactory factory) {
		if ("Log4jLoggerFactory".equals(factory.getClass().getSimpleName())) {
			return true;
		}
		return false;
	}

	/*
	 * 判断是否Logback
	 */
	private static boolean isLogback(ILoggerFactory factory) {
		if ("LoggerContext".equals(factory.getClass().getSimpleName())) {
			return true;
		}
		return false;
	}

}

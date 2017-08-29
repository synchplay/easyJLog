package org.easyj.easyjlog.util;

import java.net.URI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.slf4j.event.Level;

/**
 * Log4j2 配置更新类
 * 
 * @author dengjianjun
 *
 */
public class Log4j2Config {

	/**
	 * 重新初始化日志配置文件
	 * 
	 * @param configFileLocation
	 *            日志文件路径
	 */
	public static void reconfigure(String configFileLocation) throws Exception {
		LoggerContext context = (LoggerContext) LogManager.getContext(false);
		if (configFileLocation == null || configFileLocation.trim().length() == 0) {
			context.reconfigure();
		} else {
			context.setConfigLocation(new URI(configFileLocation));
		}
	}

	/**
	 * 动态修改Root日志级别，重启后失效
	 * 
	 * @param newLevel
	 *            设置日志级别
	 */
	public static void changeRootLevel(Level newLevel) {
		LoggerContext context = (LoggerContext) LogManager.getContext(false);
		LoggerConfig loggerConfig = context.getConfiguration().getRootLogger();

		if (Level.INFO.equals(newLevel)) {
			loggerConfig.setLevel(org.apache.logging.log4j.Level.INFO);
		} else if (Level.WARN.equals(newLevel)) {
			loggerConfig.setLevel(org.apache.logging.log4j.Level.WARN);
		} else if (Level.DEBUG.equals(newLevel)) {
			loggerConfig.setLevel(org.apache.logging.log4j.Level.DEBUG);
		} else if (Level.ERROR.equals(newLevel)) {
			loggerConfig.setLevel(org.apache.logging.log4j.Level.ERROR);
		} else if (Level.TRACE.equals(newLevel)) {
			loggerConfig.setLevel(org.apache.logging.log4j.Level.TRACE);
		}
		context.updateLoggers();
	}

}

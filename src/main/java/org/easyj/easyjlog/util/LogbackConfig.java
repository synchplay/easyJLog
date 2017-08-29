package org.easyj.easyjlog.util;

import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.util.StatusPrinter;

/**
 * Logback 配置更新类
 * 
 * @author dengjianjun
 *
 */
public class LogbackConfig {

	/**
	 * 重新初始化日志配置文件
	 * 
	 * @param configFileLocation
	 *            日志文件路径
	 */
	public static void reconfigure(String configFileLocation) throws Exception {
		ILoggerFactory factory = LoggerFactory.getILoggerFactory();
		LoggerContext context = (LoggerContext) factory;
		JoranConfigurator configurator = new JoranConfigurator();
		configurator.setContext(context);
		context.reset();
		configurator.doConfigure(configFileLocation);
		StatusPrinter.printInCaseOfErrorsOrWarnings(context);
	}

	/**
	 * 动态修改Root日志级别，重启后失效
	 * 
	 * @param newLevel
	 *            设置日志级别
	 */
	public static void changeRootLevel(Level newLevel) {
		ILoggerFactory factory = LoggerFactory.getILoggerFactory();
		LoggerContext context = (LoggerContext) factory;
		Logger loggerConfig = context.getLogger(Logger.ROOT_LOGGER_NAME);

		if (Level.INFO.equals(newLevel)) {
			loggerConfig.setLevel(ch.qos.logback.classic.Level.INFO);
		} else if (Level.WARN.equals(newLevel)) {
			loggerConfig.setLevel(ch.qos.logback.classic.Level.WARN);
		} else if (Level.DEBUG.equals(newLevel)) {
			loggerConfig.setLevel(ch.qos.logback.classic.Level.DEBUG);
		} else if (Level.ERROR.equals(newLevel)) {
			loggerConfig.setLevel(ch.qos.logback.classic.Level.ERROR);
		} else if (Level.TRACE.equals(newLevel)) {
			loggerConfig.setLevel(ch.qos.logback.classic.Level.TRACE);
		}
	}

}

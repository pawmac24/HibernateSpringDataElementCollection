package com.pm.hibernate;

import com.pm.hibernate.controller.MyController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static final Logger LOG = LoggerFactory.getLogger(Main.class);

	private MyController controller;

	public static void main(String[] args) {
		new Main().run();
	}

	public Main() {
	}

	private void run() {
		try (ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfiguration.class)) {
			this.controller = ctx.getBean(MyController.class);
			this.controller.prepare();
		}
	}
}

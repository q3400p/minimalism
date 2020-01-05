package com.windf.core.frame;

import java.util.List;

public class InitializationController {

	private static InitializationController initializationControler = new InitializationController();

	public static InitializationController getInstance() {
		return initializationControler;
	}

	private List<InitializationHandler> initializationHandlerList = null;

	private InitializationController() {

	}

	public void initInitializationHandler(List<InitializationHandler> initializationHandlerList) {
		this.initializationHandlerList = initializationHandlerList;
	}
	
	public void doInit() {
		for (InitializationHandler initializationable : initializationHandlerList) {
			initializationable.init();
		}
	}

}

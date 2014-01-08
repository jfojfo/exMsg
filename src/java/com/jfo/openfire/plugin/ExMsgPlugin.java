package com.jfo.openfire.plugin;

import java.io.File;

import org.jivesoftware.openfire.IQRouter;
import org.jivesoftware.openfire.XMPPServer;
import org.jivesoftware.openfire.container.Plugin;
import org.jivesoftware.openfire.container.PluginManager;


public class ExMsgPlugin implements Plugin {
	private XMPPServer server = XMPPServer.getInstance();;

	public void initializePlugin(PluginManager manager, File pluginDirectory) {
		IQRouter router = server.getIQRouter();
		router.addHandler(new ExMsgIQHandler());
	}

	public void destroyPlugin() {
		
	}
	
}